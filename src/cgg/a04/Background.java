package cgg.a04;

import cgg.a03.Hit;
import cgg.a03.Ray;
import cgtools.Color;
import cgtools.Point;

import static cgtools.Vector.*;

public record Background(Color color) implements Shape {

    @Override
    public Hit intersect(Ray ray) {
        if (ray.tmax != Double.POSITIVE_INFINITY) {
            return null;
        }
        Point hit = add(ray.source, multiply(ray.tmax, ray.direction));
        return new Hit(ray.tmax, hit, ray.direction, color);
    }
}
