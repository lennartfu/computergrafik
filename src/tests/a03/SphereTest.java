package tests.a03;

import cgg.a03.Hit;
import cgg.a03.Ray;
import cgg.a03.Sphere;
import cgtools.Color;
import cgtools.Direction;
import cgtools.Point;
import org.junit.jupiter.api.Test;

import static cgtools.Vector.direction;
import static cgtools.Vector.point;

public class SphereTest {

    @Test
    public void SphereTest1() {
        Point center = point(0, 0, -2);
        int radius = 1;

        Point source = point(0, 0, 0);
        Direction direction = direction(0, 0, -1);
        double tmin = 0;
        double tmax = Double.POSITIVE_INFINITY;

        Sphere sphere = new Sphere(center, radius, new Color(0, 0, 0));
        Hit hit = sphere.intersect(new Ray(source, direction, tmin, tmax));

        assert hit.position().equals(point(0, 0, -1));
        assert hit.normal().equals(direction(0, 0, 1));
    }

    @Test
    public void SphereTest2() {
        Point center = point(0, 0, -2);
        int radius = 1;

        Point source = point(0, 0, 0);
        Direction direction = direction(0, 1, -1);
        double tmin = 0;
        double tmax = Double.POSITIVE_INFINITY;

        Sphere sphere = new Sphere(center, radius, new Color(0, 0, 0));
        Hit hit = sphere.intersect(new Ray(source, direction, tmin, tmax));

        assert hit == null;
    }

    @Test
    public void SphereTest3() {
        Point center = point(0, -1, -2);
        int radius = 1;

        Point source = point(0, 0, 0);
        Direction direction = direction(0, 0, -1);
        double tmin = 0;
        double tmax = Double.POSITIVE_INFINITY;

        Sphere sphere = new Sphere(center, radius, new Color(0, 0, 0));
        Hit hit = sphere.intersect(new Ray(source, direction, tmin, tmax));

        assert hit.position().equals(point(0, 0, -2));
        assert hit.normal().equals(direction(0, 1, 0));
    }

    @Test
    public void SphereTest4() {
        Point center = point(0, 0, -2);
        int radius = 1;

        Point source = point(0, 0, -4);
        Direction direction = direction(0, 0, -1);
        double tmin = 0;
        double tmax = Double.POSITIVE_INFINITY;

        Sphere sphere = new Sphere(center, radius, new Color(0, 0, 0));
        Hit hit = sphere.intersect(new Ray(source, direction, tmin, tmax));

        assert hit == null;
    }

    @Test
    public void SphereTest5() {
        Point center = point(0, 0, -4);
        int radius = 1;

        Point source = point(0, 0, 0);
        Direction direction = direction(0, 0, -1);
        double tmin = 0;
        double tmax = 2;

        Sphere sphere = new Sphere(center, radius, new Color(0, 0, 0));
        Hit hit = sphere.intersect(new Ray(source, direction, tmin, tmax));

        assert hit == null;
    }


}
