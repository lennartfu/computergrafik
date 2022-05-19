package cgg.a03;

import cgg.a05.Material;
import cgtools.Direction;
import cgtools.Point;

public record Hit(double t, Point position, Direction normal, Material material) {
}
