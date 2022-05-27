package cgg.a06;

import cgg.a03.Hit;
import cgg.a03.Ray;
import cgg.a05.Material;
import cgg.a05.Properties;
import cgtools.Color;
import cgtools.Direction;

import static cgtools.Random.random;
import static cgtools.Vector.*;

public class ReflectiveMaterial implements Material {

    public final Color emission = new Color(0, 0, 0);
    public final Color albedo;
    public final double diffusionFactor;

    private double EPSILON = 0.0001;

    public ReflectiveMaterial(Color albedo, double diffusionFactor) {
        this.albedo = albedo;
        this.diffusionFactor = diffusionFactor;
    }

    @Override
    public Properties properties(Ray incomingRay, Hit hit) {
        Direction d = incomingRay.direction;
        Direction n = hit.normal();
        // Calculate direction of reflected ray.
        Direction b = multiply(n, dotProduct(negate(d), n));
        Direction reflected = add(d, multiply(2, b));
        // Diffuse the vector by adding a random direction
        Direction random = multiply(randomDirection(), diffusionFactor);
        // Generate reflected ray and return the properties of the material
        Ray scatteredRay = new Ray(hit.position(), add(reflected, random), EPSILON, Double.POSITIVE_INFINITY);
        return new Properties(scatteredRay, emission, albedo);
    }

    private Direction randomDirection() {
        double x = random() * 2 - 1;
        double y = random() * 2 - 1;
        double z = random() * 2 - 1;
        while (x * x + y * y + z * z > 1) {
            x = random() * 2 - 1;
            y = random() * 2 - 1;
            z = random() * 2 - 1;
        }
        return direction(x, y, z);
    }
}
