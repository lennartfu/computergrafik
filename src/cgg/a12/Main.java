package cgg.a12;

import cgg.a03.Sphere;
import cgg.a04.Background;
import cgg.a04.Group;
import cgg.a04.Plane;
import cgg.a04.Shape;
import cgg.a07.Cylinder;
import cgg.a08.Transformation;
import cgtools.Matrix;

import static cgg.a07.Main.*;
import static cgtools.Vector.direction;
import static cgtools.Vector.point;

public class Main {

    public static void main(String[] args) {
        Matrix transformation = Matrix.multiply(Matrix.translation(0, 500, 1000), Matrix.rotation(1, 0, 0, -20));

        Shape sky = new Background(background(1, 1, 1));
        Group scene = new Group(sky, ground(), rainbow());

        createImage(transformation, scene, "cgg-competition-ss-22-914135.png");
    }

    private static Shape ground() {
        Shape ground1 = new Plane(point(0, 0, 0), direction(0, 1, 0), Double.POSITIVE_INFINITY, diffuse(0, 0, 0));
        Shape ground2 = new Plane(point(0, 20, 0), direction(0, 1, 0), Double.POSITIVE_INFINITY, transparent(0.02, 0.04, 0.14));
        return new Group(new Transformation(Matrix.translation(0, -0.5, 0)), ground1, ground2);
    }

    private static Shape rainbow() {
        Shape violet = new Plane(point(0, 20, 0), direction(0, 0, 1), 400, diffuse(0.58, 0, 0.82));
        Shape indigo = new Plane(point(0, 20, 0.01), direction(0, 0, 1), 390, diffuse(0.29, 0, 0.5));
        Shape blue = new Plane(point(0, 20, 0.02), direction(0, 0, 1), 380, diffuse(0, 0, 1));
        Shape green = new Plane(point(0, 20, 0.03), direction(0, 0, 1), 370, diffuse(0, 1, 0));
        Shape yellow = new Plane(point(0, 20, 0.04), direction(0, 0, 1), 360, diffuse(1, 1, 0));
        Shape orange = new Plane(point(0, 20, 0.05), direction(0, 0, 1), 350, diffuse(1, 0.5, 0));
        Shape red = new Plane(point(0, 20, 0.06), direction(0, 0, 1), 340, diffuse(1, 0, 0));
        Shape cover1 = new Plane(point(0, 20, 0.07), direction(0, 0, 1), 330, diffuse(0.58, 0, 0.82));
        Shape cover2 = new Plane(point(0, 20, 0.08), direction(0, 0, 1), 330, transparent(0.02, 0.04, 0.14));
        Shape line = new Group(new Transformation(Matrix.translation(450, 0, 0), Matrix.rotation(0, 0, 1, 90)), new Cylinder(point(20, 0, 0.1), 2, 900, background(1, 1, 1)));
        return new Group(new Transformation(Matrix.rotation(0, 1, 0, 7)), violet, indigo, blue, green, yellow, orange, red, cover1, cover2, line);
    }
}
