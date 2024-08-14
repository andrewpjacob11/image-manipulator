package src.model;

import java.util.List;
import java.util.ArrayList;

/**
 * Represents a single pixel on an image. Contains three channels of colors: red, green, blue— each
 * within bounds of 0 to 255. If not max value is given, 255 is default max value. None of the
 * channels are mutable.
 */
public class Pixel {


  private final double red;
  private final double green;
  private final double blue;

  /**
   * Creates a pixel of an image with pre-set red, green, blue values that cannot be altered later.
   * The values for each color in this pixel are bounded by 0<=value<=255.
   *
   * @param red   red value of this pixel
   * @param green green value of this pixel
   * @param blue  blue value of this pixel
   */
  public Pixel(double red, double green, double blue) {
    this.red = clamp(red, 0, 255);
    this.green = clamp(green, 0, 255);
    this.blue = clamp(blue, 0, 255);
  }


  /**
   * Constructs a pixel from a 3-element vector. Throws an exception if the vector is not
   * 3-elements. The values for each color in this pixel are bounded by 0<=value<=255.
   *
   * @param newPixelData
   * @throws IllegalArgumentException if the input list is not 3 elements
   */
  public Pixel(List<Double> newPixelData) {
    if (newPixelData.size() != 3) {
      throw new IllegalArgumentException("Invalid input list");
    }
    this.red = clamp(newPixelData.get(0), 0, 255);
    this.green = clamp(newPixelData.get(1), 0, 255);
    this.blue = clamp(newPixelData.get(2), 0, 255);


  }


  /**
   * Retrieves the red channel value of a pixel.
   *
   * @return this red value
   */
  public double getRed() {
    return this.red;
  }

  /**
   * Retrieves the green channel value of a pixel.
   *
   * @return this green value
   */
  public double getGreen() {
    return this.green;
  }

  /**
   * Retrieves the blue channel value of a pixel.
   *
   * @return this blue value
   */
  public double getBlue() {
    return this.blue;
  }

  /**
   * Returns the pixel as a 1 dimensional List such in the format [Red, Green, Blue].
   *
   * @return pixel as List in the format specified above
   */
  public List<Double> pixelAsList() {
    return new ArrayList<Double>(List.of(this.red, this.green, this.blue));
  }

  /**
   * Returns the pixel as a string in the format of [red][newline][green][newline][blue][newline].
   *
   * @return pixel as a string as formatted above.
   */
  @Override
  public String toString() {
    String returnString = (int) getRed() + "\n" + (int) getGreen() + "\n" + (int) getBlue() + "\n";
    return returnString;
  }

  /**
   * Restricts the given input to be bounded by the given min and max value. If the input is out of
   * bounds, this method returns the closest value within bounds.
   *
   * @param input input being bounded
   * @param min   min allowed value
   * @param max   max allowed value
   * @return bounded value
   */
  protected double clamp(double input, double min, double max) {
    if (input < min) {
      return min;
    } else if (input > max) {
      return max;
    } else {
      return input;
    }
  }

  /**
   * Overriding hashcode becuase equals was overwritten to avoid collisions.
   *
   * @return hashcode for this pixel
   */
  @Override
  public int hashCode() {
    return this.hashCode();
  }

  /**
   * Returns whether this pixel has the same values as the given object if the object is a pixel. If
   * the object is not a pixel, throws an error.
   *
   * @param other object being compared
   * @return whether the object is an equal pixel to this one
   */
  @Override
  public boolean equals(Object other) {
    if (!(other instanceof Pixel)) {
      return false;
    }

    return (this.getRed() == ((Pixel) other).getRed() && this.getGreen() == ((Pixel) other)
        .getGreen() && this.getBlue() == ((Pixel) other).getBlue());
  }

  /**
   * Builder constructor for pixel.
   */
  public static class Builder {

    private double red = 255;
    private double green = 255;
    private double blue = 255;

    /**
     * Constructs a pixel object.
     *
     * @return Pixel
     */
    public Pixel build() {
      return new Pixel(red, green, blue);
    }

    /**
     * Sets the red value of the pixel to the given value.
     *
     * @param red
     * @return builder with red
     */
    public Builder withRed(double red) {
      this.red = red;
      return this;
    }

    /**
     * Sets the green value of the pixel to the given value.
     *
     * @param green
     * @return builder with green
     */
    public Builder withGreen(double green) {
      this.green = green;
      return this;
    }

    /**
     * Sets the blue value of the pixel to the given value.
     *
     * @param blue
     * @return builder with blue
     */
    public Builder withBlue(double blue) {
      this.blue = blue;
      return this;
    }
  }

}

