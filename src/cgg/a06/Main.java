package cgg.a06;

import cgg.Image;
import cgg.a03.Camera;
import cgg.a03.Hit;
import cgg.a03.Ray;
import cgg.a03.Sphere;
import cgg.a04.*;
import cgg.a05.BackgroundMaterial;
import cgg.a05.DiffuseMaterial;
import cgg.a05.Material;
import cgtools.Color;
import cgtools.Point;

import java.util.ArrayList;
import java.util.List;

import static cgtools.Vector.direction;
import static cgtools.Vector.point;

public class Main {

    private static final Camera camera = new Camera(Math.PI / 3, 1280, 720);

    private static Shape background = new Background(new BackgroundMaterial(new Color(1, 1, 1)));
    private static Shape ground = new Plane(point(0.0, -0.5, 0.0), direction(0, 1, 0),
            Double.POSITIVE_INFINITY, new DiffuseMaterial(new Color(0.7, 0.7, 0.7)));

    public static void main(String[] args) {
        createImage1();
        createImage2();
    }


    private static void createImage1() {
        List<Shape> shapes = new ArrayList<>();

        shapes.add(ground);
        shapes.add(background);
        shapes.add(sphere(640, 540, 0.4, reflective(0)));
        shapes.add(sphere(940, 540, 0.4, reflective(0)));
        shapes.add(sphere(1200, 650, 0.2, diffuse(0.3, 0.65, 1)));
        shapes.add(sphere(150, 620, 0.2, diffuse(0.4, 1, 0.4)));
        shapes.add(sphere(550, 700, 0.15, diffuse(1, 0.15, 0.15)));
        shapes.add(sphere(300, 400, 0.5, diffuse(1, 0.9, 0.6)));

        Group scene = new Group(shapes);
        createImage(scene, "a06-mirrors-glass-1.png");
    }

    private static void createImage2() {
        List<Shape> shapes = new ArrayList<>();

        shapes.add(ground);
        shapes.add(background);
        shapes.add(sphere(640, 650, 0.3, transparent(0.9, 0.9, 0.9)));
        shapes.add(sphere(470, 500, 0.5, diffuse(0.4, 1, 0.4)));
        shapes.add(sphere(810, 500, 0.5, reflective(0.25)));
        shapes.add(sphere(300, 550, 0.3, reflective(0)));
        shapes.add(sphere(980, 550, 0.3, diffuse(0.3, 0.65, 1)));
        shapes.add(sphere(130, 600, 0.2, diffuse(1, 0.15, 0.15)));
        shapes.add(sphere(1150, 600, 0.2, transparent(1, 0.9, 0.6)));

        Group scene = new Group(shapes);
        createImage(scene, "a06-mirrors-glass-2.png");
    }

    private static void createImage(Group scene, String filename) {
        Image image = new Image();
        Raytracer raytracer = new Raytracer(scene, camera, image);
        raytracer.raytrace();
        image.write(Image.getFilepath(filename));
    }

    private static Sphere sphere(double x, double y, double radius, Material material) {
        Ray ray = camera.generateRay(x, y);
        Hit hit = ground.intersect(ray);
        Point center = point(hit.position().x(), -0.5 + radius, hit.position().z());
        return new Sphere(center, radius, material);
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
