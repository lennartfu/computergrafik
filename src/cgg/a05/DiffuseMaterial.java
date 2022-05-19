package cgg.a05;

import cgg.a03.Hit;
import cgg.a03.Ray;
import cgtools.Color;

import static cgtools.Random.random;
import static cgtools.Vector.*;

public class DiffuseMaterial implements Material {

    public final Color albedo;
    public final Color emission = new Color(0, 0, 0);

    private final double EPSILON = 0.0001;

    public DiffuseMaterial(Color albedo) {
        this.albedo = albedo;
    }

    @Override
    public Properties properties(Ray incomingRay, Hit hit) {
        double x = random() * 2 - 1;
        double y = random() * 2 - 1;
        double z = random() * 2 - 1;
        while (x * x + y * y + z * z > 1) {
            x = random() * 2 - 1;
            y = random() * 2 - 1;
            z = random() * 2 - 1;
        }
        Ray scatteredRay = new Ray(hit.position(), normalize(add(hit.normal(), direction(x, y, z))), EPSILON, Double.POSITIVE_INFINITY);
        return new Properties(scatteredRay, emission, albedo);
    }

}
