package tests.a06;

import cgtools.Direction;
import org.junit.jupiter.api.Test;

import static cgg.a06.TransparentMaterial.refract;
import static cgtools.Vector.direction;

public class RefractTest {

    @Test
    public void refractTest1(){
        Direction d = direction(0.707, 0.707, 0);
        Direction n = direction(0, 1, 0);
        double n1 = 1;
        double n2 = 1.5;

        Direction result = direction(0.471, -0.882, 0);
        Direction dir = refract(n, d, n1, n2);
        assert dir.equals(result);
    }

    @Test
    public void refractTest2(){
        Direction d = direction(0.707, 0.707, 0);
        Direction n = direction(0, 1, 0);
        double n1 = 1.5;
        double n2 = 1;

        Direction dir = refract(n, d, n1, n2);
        assert dir == null;
    }

    @Test
    public void refractTest3(){
        Direction d = direction(0.995, 0.1, 0);
        Direction n = direction(0, 1, 0);
        double n1 = 1;
        double n2 = 1.5;

        Direction result = direction(0.663, -0.748, 0);
        Direction dir = refract(n, d, n1, n2);
        assert dir.equals(result);
    }

    @Test
    public void refractTest4(){
        Direction d = direction(0.995, 0.1, 0);
        Direction n = direction(0, 1, 0);
        double n1 = 1.5;
        double n2 = 1;

        Direction dir = refract(n, d, n1, n2);
        assert dir == null;
    }

    @Test
    public void refractTest5(){
        Direction d = direction(0.1, -0.995, 0);
        Direction n = direction(0, 1, 0);
        double n1 = 1;
        double n2 = 1.5;

        Direction result = direction(0.066, -0.998, 0);
        Direction dir = refract(n, d, n1, n2);
        assert dir.equals(result);
    }

    @Test
    public void refractTest6(){
        Direction d = direction(0.1, -0.995, 0);
        Direction n = direction(0, 1, 0);
        double n1 = 1.5;
        double n2 = 1;

        Direction result = direction(0.150, -0.989, 0);
        Direction dir = refract(n, d, n1, n2);
        assert dir.equals(result);
    }
}
