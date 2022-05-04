package cgg.a03;

import cgtools.Color;
import cgtools.Direction;
import cgtools.Point;

public record Hit(double t, Point position, Direction normal, Color color) {

}
