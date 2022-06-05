package cgg.a03;

import cgg.a05.Material;
import cgtools.Direction;
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
}
