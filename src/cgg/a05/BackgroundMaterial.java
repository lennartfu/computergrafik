package cgg.a05;

import cgg.a03.Hit;
import cgg.a03.Ray;
import cgtools.Color;

public class BackgroundMaterial implements Material {

    public final Color albedo = new Color(0, 0, 0);
    public final Color emission;
    private final Ray scatteredRay = null;

    public BackgroundMaterial(Color emission) {
        this.emission = emission;
    }

    @Override
    public Properties properties(Ray incomingRay, Hit hit) {
        return new Properties(scatteredRay, emission, albedo);
    }
}
