package cgg.a03;

import cgtools.Direction;

import static cgtools.Vector.*;

public class Camera {

    private double angle;
    private double width;
    private double height;

    public Camera(double angle, double width, double height) {
        this.angle = angle;
        this.width = width;
        this.height = height;
    }

    public Ray generateRay(double x, double y) {
        double halfWidth = width / 2;
        double halfHeight = height / 2;

        // Calculate x, y, and z coordinates of the direction vector.
        double xdir = x - halfWidth;
        double ydir = halfHeight - y;
        double zdir = -(halfWidth / Math.tan(angle / 2));
        Direction normal = normalize(direction(xdir, ydir, zdir));

        return new Ray(point(0, 0, 0), normal, 0, Integer.MAX_VALUE);
    }
}
