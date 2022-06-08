package tests.a06;

import cgtools.Direction;
import org.junit.jupiter.api.Test;
import static cgg.a06.TransparentMaterial.schlick;
import static cgtools.Util.areEqual;
import static cgtools.Util.round;
import static cgtools.Vector.direction;

public class SchlickTest {

    @Test
    public void schlickTest1() {
        Direction d = direction(0.707, 0.707, 0);
        Direction n = direction(0, 1, 0);
        double n1 = 1;
        double n2 = 1.5;

        double result = 13.95;
        double schlick = round(schlick(n, d, n1, n2), 2);
        assert areEqual(schlick, result);
    }

    @Test
    public void schlickTest2() {
        Direction d = direction(0.707, 0.707, 0);
        Direction n = direction(0, 1, 0);
        double n1 = 1.5;
        double n2 = 1;

        double result = 13.95;
        double schlick = round(schlick(n, d, n1, n2), 2);
        assert areEqual(schlick, result);
    }

    @Test
    public void schlickTest3() {
        Direction d = direction(0.995, -0.1, 0);
        Direction n = direction(0, 1, 0);
        double n1 = 1;
        double n2 = 1.5;

        double result = 0.61;
        double schlick = round(schlick(n, d, n1, n2), 2);
        assert areEqual(schlick, result);
    }

    @Test
    public void schlickTest4() {
        Direction d = direction(0.995, -0.1, 0);
        Direction n = direction(0, 1, 0);
        double n1 = 1.5;
        double n2 = 1;

        double result = 0.61;
        double schlick = round(schlick(n, d, n1, n2), 2);
        assert areEqual(schlick, result);
    }

}
