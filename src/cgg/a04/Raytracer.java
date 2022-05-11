package cgg.a04;

import cgg.Image;
import cgg.a03.Camera;
import cgg.a03.Hit;
import cgg.a03.Ray;
import cgtools.Color;
import cgtools.Sampler;

import static cgtools.Util.shade;

public record Raytracer(Group scene, Camera cam, Image image) implements Sampler {

    public void raytrace() {
        image.sample(this, true);
    }

    @Override
    public Color getColor(double x, double y) {
        Ray ray = cam.generateRay(x, y);
        Hit hit = scene.intersect(ray);
        return shade(hit.normal(), hit.color());
    }
}
