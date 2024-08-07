/** @author henrik.tramberend@beuth-hochschule.de */
package cgtools;

// A Sampler is something that can return a color value for a two dimensional
// position.
public interface Sampler {

  public int width();

  public int height();

  public Color getColor(double x, double y);
}
