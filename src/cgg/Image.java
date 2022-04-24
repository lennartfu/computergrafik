/** @author henrik.tramberend@beuth-hochschule.de */
package cgg;

import cgtools.*;

public class Image {

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

    data[indexRed] = color.r();
    data[indexGreen] = color.g();
    data[indexBlue] = color.b();
  }

  public void write(String filename) {
    ImageWriter.write(filename, data, width, height);
  }

  public void sample(Sampler s) {
    notYetImplemented();
  }

  private void notYetImplemented() {
    System.err.println("Please complete the implementation of class cgg.Image as part of assignment 1.");
    System.exit(1);
  }
}
