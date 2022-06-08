package tests.a06;

import cgtools.Direction;
import org.junit.jupiter.api.Test;

import static cgg.a06.TransparentMaterial.reflect;
import static cgtools.Vector.direction;

public class ReflectTest {

    @Test
    public void reflectTest1() {
        Direction d = direction(0, 0, 0);
        Direction n = direction(0, 1, 0);

        Direction result = direction(0, 0, 0);
        Direction dir = reflect(n, d);
        assert dir.equals(result);
    }

    @Test
    public void reflectTest2() {
        Direction d = direction(0.707, -0.707, 0);
        Direction n = direction(0, 1, 0);

        Direction result = direction(0.707, 0.707, 0);
        Direction dir = reflect(n, d);
        assert dir.equals(result);
    }

    @Test
    public void reflectTest3() {
        Direction d = direction(0.707, 0.707, 0);
        Direction n = direction(0, 1, 0);

        Direction result = direction(0.707, -0.707, 0);
        Direction dir = reflect(n, d);
        assert dir.equals(result);
    }
}
