package cgg.a04;

import cgg.a03.Hit;
import cgg.a03.Ray;

import java.util.List;

public record Group(List<Shape> shapes) implements Shape {

    @Override
    public Hit intersect(Ray ray) {
        Hit hit = null;
        for (Shape s: shapes) {
            Hit h = s.intersect(ray);
            if (h != null) {
                if (hit == null) {
                    hit = h;
                } else {
                    if (h.t() < hit.t()) {
                        hit = h;
                    }
                }
            }
        }
        return hit;
    }
}
