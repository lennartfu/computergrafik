package cgg.a08;

import cgg.a04.Background;
import cgg.a04.Group;
import cgg.a04.Plane;
import cgg.a04.Shape;
import cgg.a07.Cylinder;
import cgtools.Matrix;

import java.util.ArrayList;
import java.util.List;

import static cgg.a07.Main.*;
import static cgtools.Vector.direction;
import static cgtools.Vector.point;

public class Main {

    public static void main(String[] args) {
        Matrix transformation = Matrix.multiply(Matrix.translation(0, 500, 1500), Matrix.rotation(1, 0, 0, -18));
        Matrix transformation2 = Matrix.multiply(Matrix.rotation(0, 1, 0, -15), Matrix.translation(-50, 500, 1500), Matrix.rotation(1, 0, 0, -18));

        Shape sky = new Background(background(1, 1, 1));
        Group scene = new Group(sky, sun(), ground(), grid());

        createImage(transformation, scene, "a08-1.png");
        createImage(transformation2, scene, "a08-2.png");
    }

    private static Shape grid() {
        List<Shape> shapes = new ArrayList<>();
        for (int i = 0; i <= 6000; i += 100) {
            Shape s = new Cylinder(point(0, -0.5, i), 2, 6000, background(1, 1, 1));
            shapes.add(new Group(new Transformation(Matrix.translation(3000, 0, 0), Matrix.rotation(0, 0, 1, 90)), s));
        }
        for (int i = -1000; i <= 1000; i += 100) {
            Shape s = new Cylinder(point(i, -0.5, 0), 2, 6000, background(1, 1, 1));
            shapes.add(new Group(new Transformation(Matrix.rotation(1, 0, 0, 90)), s));
        }
        for (int i = -1000; i <= 1000; i += 100) {
            int z = -1;
            if (i >= -400 && i <= 400) z = -50;
            shapes.add(new Cylinder(point(i, -100, z), 2, 3000, background(0.1, 0.1, 0.1)));
        }
        for (int i = 90; i <= 500; i += 100) {
            Shape s = new Cylinder(point(0, 0, -50), 2, 6000, background(0.1, 0.1, 0.1));
            shapes.add(new Group(new Transformation(Matrix.translation(3000, i, 0), Matrix.rotation(0, 0, 1, 90)), s));
        }
        return new Group(shapes);
    }

    private static Shape sun() {
        List<Shape> shapes = new ArrayList<>();
        double r = 0.98;
        double g = 0.068;
        double b = 0.964;
        int z = -40;
        for (int i = 0; i >= -400; i -= 10) {
            shapes.add(new Plane(point(0, i - 6, z), direction(0, 0, 1), 400, background(r, g, b)));
            if (i > -200) {
                r -= 0.0285;
                g += 0.0136;
                b += 0.0018;
            } else {
                r -= 0.0163;
                g += 0.032;
            }
            z++;
        }
        shapes.add(new Plane(point(0, 0, 0), direction(0, 0, 1), 400, transparent(0.9, 0.9, 0.9)));
        return new Group(shapes);
    }

    private static Shape ground() {
        Shape ground1 = new Plane(point(0, -20, 0), direction(0, 1, 0), Double.POSITIVE_INFINITY, diffuse(0, 0, 0));
        Shape ground2 = new Plane(point(0, -1, 0), direction(0, 1, 0), Double.POSITIVE_INFINITY, transparent(0.02, 0.04, 0.14));
        return new Group(new Transformation(Matrix.translation(0, -0.5, 0)), ground1, ground2);
    }
}
