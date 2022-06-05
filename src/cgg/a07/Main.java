package cgg.a07;

import cgg.Image;
import cgg.a03.Camera;
import cgg.a03.Hit;
import cgg.a03.Ray;
import cgg.a03.Sphere;
import cgg.a04.*;
import cgg.a05.BackgroundMaterial;
import cgg.a05.DiffuseMaterial;
import cgg.a05.Material;
import cgg.a06.ReflectiveMaterial;
import cgg.a06.TransparentMaterial;
import cgtools.Color;
import cgtools.Matrix;
import cgtools.Point;

import java.util.ArrayList;
import java.util.List;

import static cgtools.Vector.direction;
import static cgtools.Vector.point;

public class Main {

    private static final Camera camera = new Camera(Math.PI / 3, 1280, 720);

    private static final Shape background = new Background(background(0, 0, 0));
    private static final Shape ground = new Plane(point(0.0, -0.5, 0.0), direction(0, 1, 0),
            Double.POSITIVE_INFINITY, new ReflectiveMaterial(new Color(0.3, 0.3, 0.3), 0));

    public static void main(String[] args) {
        createImage1();
        createImage2();
    }

    private static void createImage1() {
        Matrix identity = Matrix.identity();
        List<Shape> shapes = new ArrayList<>();

        shapes.add(ground);
        shapes.add(background);

        int z = 40;
        for (int i = 0; i < 50; i++) {
            shapes.add(new Cylinder(point(5, -0.5, z), 0.5, 2, background(1, 0.1, 1)));
            shapes.add(new Cylinder(point(-5, -0.5, z), 0.5, 2, background(1, 0.1, 1)));
            shapes.add(new Cylinder(point(5, -0.5, z), 0.55, 2.05, transparent(0.3, 0.3, 0.3)));
            shapes.add(new Cylinder(point(-5, -0.5, z), 0.55, 2.05, transparent(0.3, 0.3, 0.3)));
            z -= 10;
        }

        Group scene = new Group(shapes);
        createImage(identity, scene, "a07-1.png");
    }

    private static void createImage2() {
        Matrix translation = Matrix.translation(0, 10, 50);
        Matrix rotation = Matrix.rotation(1, 0, 0, -10);
        Matrix transformation = Matrix.multiply(translation, rotation);
        List<Shape> shapes = new ArrayList<>();

        shapes.add(ground);
        shapes.add(background);

        int z = 40;
        for (int i = 0; i < 50; i++) {
            shapes.add(new Cylinder(point(5, -0.5, z), 0.5, 2, background(1, 0.1, 1)));
            shapes.add(new Cylinder(point(-5, -0.5, z), 0.5, 2, background(1, 0.1, 1)));
            shapes.add(new Cylinder(point(5, -0.5, z), 0.55, 2.05, transparent(0.3, 0.3, 0.3)));
            shapes.add(new Cylinder(point(-5, -0.5, z), 0.55, 2.05, transparent(0.3, 0.3, 0.3)));
            z -= 10;
        }

        Group scene = new Group(shapes);
        createImage(transformation, scene, "a07-2.png");
    }

    private static void createImage(Matrix matrix, Group scene, String filename) {
        System.out.println("Creating image '" + filename + "'...");
        Image image = new Image(1280, 720);
        Camera camera = new Camera(Math.PI / 3, 1280, 720, matrix);
        Raytracer raytracer = new Raytracer(scene, camera, image);
        raytracer.raytrace();
        image.write(Image.getFilepath(filename));
        System.out.println("Done.");
    }

    private static Sphere sphere(Matrix matrix, double x, double y, double radius, Material material) {
        Camera cam = new Camera(Math.PI / 3, 1280, 720, matrix);
        Ray ray = cam.generateRay(x, y);
        Hit hit = ground.intersect(ray);
        Point center = point(hit.position().x(), -0.5 + radius, hit.position().z());
        return new Sphere(center, radius, material);
    }

    public static Cylinder cylinder(Matrix matrix, double x, double y, double radius, double height, Material material) {
        Camera cam = new Camera(Math.PI / 3, 1280, 720, matrix);
        Ray ray = cam.generateRay(x, y);
        Hit hit = ground.intersect(ray);
        return new Cylinder(hit.position(), radius, height, material);
    }

    private static Material background(double r, double g, double b) {
        return new BackgroundMaterial(new Color(r, g, b));
    }

    private static Material diffuse(double r, double g, double b) {
        return new DiffuseMaterial(new Color(r, g, b));
    }

    private static Material reflective(double diffusionFactor) {
        return new ReflectiveMaterial(new Color(0.9, 0.9, 0.9), diffusionFactor);
    }

    private static Material transparent(double r, double g, double b) {
        return new TransparentMaterial(new Color(r, g, b), 1, 1.5);
    }
}
