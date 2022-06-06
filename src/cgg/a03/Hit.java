package cgg.a03;

import cgg.a05.Material;
import cgtools.Direction;
import cgtools.Matrix;
import cgtools.Point;

public record Hit(double t, Point position, Direction normal, Material material) {

    public static Hit min(Hit... hits) {
        Hit min = null;
        for (Hit h: hits) {
            if (h != null) {
                if (min == null) {
                    min = h;
                } else if (h.t < min.t) {
                    min = h;
                }
            }
        }
        return min;
    }

    public Hit transform(Matrix m) {
        Point position = Matrix.multiply(m, this.position);
        Direction normal = Matrix.multiply(m, this.normal);
        return new Hit(this.t, position, normal, this.material);
    }
}
