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
        List<Shape> shapes = new ArrayList<>();
        Matrix identity = Matrix.identity();
        Matrix rotation = Matrix.rotation(1, 0, 0, 8);
        Matrix transformation = Matrix.multiply(Matrix.translation(0, 500, 1500), Matrix.rotation(1, 0, 0, -18));

        Shape sky = new Background(background(1, 1, 1));

        Shape sun1 = new Plane(point(0, 0, -1), direction(0, 0, 1), 400, background(1, 1, 1));
        Shape sun2 = new Plane(point(0, 0, 0), direction(0, 0, 1), 400, transparent(0.95, 0.1, 0.97));
        Shape sun = new Group(new Transformation(Matrix.translation(0, 0, 0)), sun1, sun2);

        Shape ground1 = new Plane(point(0, -0.01, 0), direction(0, 1, 0), Double.POSITIVE_INFINITY, diffuse(0, 0, 0));
        Shape ground2 = new Plane(point(0, 0, 0), direction(0, 1, 0), Double.POSITIVE_INFINITY, transparent(0.02, 0.04, 0.14));
        Shape ground = new Group(new Transformation(Matrix.translation(0, -0.5, 0)), ground1, ground2);

        Group scene = new Group(sky, sun, ground, grid());
//        Group scene = new Group(sky, sun, ground);
//        Group scene = new Group(sky, sun(), ground);
        createImage(transformation, scene, "a08-1.png");
    }

    private static Shape grid() {
        List<Shape> shapes = new ArrayList<>();
        for (int i = 0; i <= 1000; i += 100) {
            Shape s = new Cylinder(point(0, -0.5, i), 2, 6000, background(1, 1, 1));
            shapes.add(new Group(new Transformation(Matrix.translation(3000, 0, 0), Matrix.rotation(0, 0, 1, 90)), s));
        }
        for (int i = -1000; i <= 1000; i += 100) {
            Shape s = new Cylinder(point(i, -0.5, 0), 2, 3000, background(1, 1, 1));
            shapes.add(new Group(new Transformation(Matrix.translation(0, 0, 3000), Matrix.rotation(1, 0, 0, -90)), s));
        }
        return new Group(shapes);
    }

    private static Shape sun() {
        List<Shape> shapes = new ArrayList<>();
        shapes.add(new Plane(point(0, 0, -1), direction(0, 0, 1), 400, background(1, 1, 1)));
        shapes.add(new Plane(point(0, 0, 0), direction(0, 0, 1), 400, transparent(0.95, 0.1, 0.97)));
        return new Group(shapes);
    }
}
