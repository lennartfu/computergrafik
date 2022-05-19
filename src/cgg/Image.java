/** @author henrik.tramberend@beuth-hochschule.de */
package cgg;

import cgtools.*;

import java.util.ArrayList;
import java.util.List;

public class Image {

  private final int samplePoints = 100;

  private final int components = 3;
  private int width;
  private int height;
  private double[] data;

  public Image(int width, int height) {
    this.width = width;
    this.height = height;
    this.data = new double[width * height * components];
  }

  public void setPixel(int x, int y, Color color) {
    int indexRed = components * (y * width + x);
    int indexGreen = indexRed + 1;
    int indexBlue = indexRed + 2;

    color = correctGamma(color);

    data[indexRed] = color.r();
    data[indexGreen] = color.g();
    data[indexBlue] = color.b();
  }

  private Color correctGamma(Color color) {
    final double gamma = 2.2;
    double r = Math.pow(color.r(), 1 / gamma);
    double g = Math.pow(color.g(), 1 / gamma);
    double b = Math.pow(color.b(), 1 / gamma);
    return new Color(r, g, b);
  }

  public void write(String filename) {
    ImageWriter.write(filename, data, width, height);
  }

  public void sample(Sampler s, boolean useStratifiedSampling) {
    // Iterates over all pixel positions inside this image.
    for (int x = 0; x != width; x++) {
      for (int y = 0; y != height; y++) {
        Color color;
        if (useStratifiedSampling) {
          // Supersampling
          color = superSample(s, x, y);
        } else {
          color = s.getColor(x, y);
        }
        // Sets the color for one particular pixel.
        this.setPixel(x, y, color);
      }
    }
  }

  private Color superSample(Sampler s, int x, int y) {
    List<Color> pixelColors = new ArrayList<>();
    // Get the color from a random point in the pixel 100 times and add it to a list.
    for (int i = 0; i < samplePoints; i++) {
      double scanX = x + Random.random();
      double scanY = y + Random.random();
      pixelColors.add(s.getColor(scanX, scanY));
    }
    // Calculate the average from all colors in the list.
    Color color = pixelColors.remove(0);
    for (Color c: pixelColors) {
      double r = color.r() + c.r();
      double g = color.g() + c.g();
      double b = color.b() + c.b();
      color = new Color(r, g, b);
    }
    double r = color.r() / samplePoints;
    double g = color.g() / samplePoints;
    double b = color.b() / samplePoints;
    return new Color(r, g, b);
  }

  private void notYetImplemented() {
    System.err.println("Please complete the implementation of class cgg.Image as part of assignment 1.");
    System.exit(1);
  }

  public static String getFilepath(String filename) {
    String projectDir = System.getProperty("user.dir");
    return projectDir + "/doc/" + filename;
  }
}
