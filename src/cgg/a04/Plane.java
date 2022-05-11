package cgg.a04;

import cgg.a03.Hit;
import cgg.a03.Ray;
import cgtools.Color;
import cgtools.Direction;
import cgtools.Point;

import static cgtools.Vector.*;

public record Plane(Point anchor, Direction normal, double radius, Color color) implements Shape {

    @Override
    public Hit intersect(Ray ray) {
        double denominator = dotProduct(ray.direction, normal);
        if (denominator == 0) {
            return null;
        }
        double t = dotProduct(subtract(anchor, ray.source), normal) / denominator;
        if (t < ray.tmin || t > ray.tmax) {
            return null;
        }
        Point hit = add(ray.source, multiply(t, ray.direction));
        if (length(subtract(hit, anchor)) > radius) {
            return null;
        }
        return new Hit(t, hit, normal, color);
    }
}
