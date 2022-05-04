package tests.a03;

import cgg.a03.Camera;
import cgg.a03.Ray;
import cgtools.Direction;
import cgtools.Point;
import org.junit.jupiter.api.Test;

import static cgtools.Vector.direction;
import static cgtools.Vector.point;
import static java.lang.Math.sqrt;

public class CameraTest {

    @Test
    public void cameraTest1() {
        Camera cam = new Camera(Math.PI / 2, 10, 10);
        Ray ray = cam.generateRay(0, 0);

        Point expectedPoint = point(0, 0, 0);
        Direction expectedDirection = direction(-1 / sqrt(3), 1 / sqrt(3), -1 / sqrt(3));

        assert ray.source.equals(expectedPoint);
        assert ray.direction.equals(expectedDirection);
    }

    @Test
    public void cameraTest2() {
        Camera cam = new Camera(Math.PI / 2, 10, 10);
        Ray ray = cam.generateRay(5, 5);

        Point expectedPoint = point(0, 0, 0);
        Direction expectedDirection = direction(0, 0, -1);

        assert ray.source.equals(expectedPoint);
        assert ray.direction.equals(expectedDirection);
    }

    @Test
    public void cameraTest3() {
        Camera cam = new Camera(Math.PI / 2, 10, 10);
        Ray ray = cam.generateRay(10, 10);

        Point expectedPoint = point(0, 0, 0);
        Direction expectedDirection = direction(1 / sqrt(3), -1 / sqrt(3), -1 / sqrt(3));

        assert ray.source.equals(expectedPoint);
        assert ray.direction.equals(expectedDirection);
    }
}
