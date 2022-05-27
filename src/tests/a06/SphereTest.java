package tests.a06;

import cgg.a03.Hit;
import cgg.a03.Ray;
import cgg.a03.Sphere;
import cgg.a05.DiffuseMaterial;
import cgtools.Color;
import cgtools.Direction;
import cgtools.Point;
import org.junit.jupiter.api.Test;
import static cgtools.Vector.direction;
import static cgtools.Vector.point;

public class SphereTest {

    @Test
    public void sphereTest1() {
        Point center = point(0, 0, -2);
        double radius = 1;
        Sphere sphere = new Sphere(center, radius, new DiffuseMaterial(new Color(0, 0, 0)));

        Point source = point(0, 0, 0);
        Direction dir = direction(0, 0, -1);
        double tmin = 0;
        double tmax = Double.POSITIVE_INFINITY;
        Ray ray = new Ray(source, dir, tmin, tmax);

        Point hit = point(0, 0, -1);
        Direction normal = direction(0, 0, 1);

        Hit result = sphere.intersect(ray);
        assert result.position().equals(hit);
        assert result.normal().equals(normal);
    }

    @Test
    public void sphereTest2() {
        Point center = point(0, 0, -2);
        double radius = 1;
        Sphere sphere = new Sphere(center, radius, new DiffuseMaterial(new Color(0, 0, 0)));

        Point source = point(0, 0, -2);
        Direction dir = direction(0, 0, -1);
        double tmin = 0;
        double tmax = Double.POSITIVE_INFINITY;
        Ray ray = new Ray(source, dir, tmin, tmax);

        Point hit = point(0, 0, -3);
        Direction normal = direction(0, 0, -1);

        Hit result = sphere.intersect(ray);
        assert result.position().equals(hit);
        assert result.normal().equals(normal);
    }

    @Test
    public void sphereTest3() {
        Point center = point(0, 0, -2);
        double radius = 1;
        Sphere sphere = new Sphere(center, radius, new DiffuseMaterial(new Color(0, 0, 0)));

        Point source = point(0, 0, -4);
        Direction dir = direction(0, 0, -1);
        double tmin = 0;
        double tmax = Double.POSITIVE_INFINITY;
        Ray ray = new Ray(source, dir, tmin, tmax);

        Hit result = sphere.intersect(ray);
        assert result == null;
    }
}
