package src.model;

import java.util.List;
import java.util.ArrayList;
import java.awt.Color;

public class Photo implements IPhoto<Pixel> {

  private final int length;
  private final int width;
  private final List<List<Pixel>> pixels;

  /**
   * Constructor that takes in a length and a width. Constructs a Photo that's a white image with
   * the specified width and length.
   *
   * @param length
   * @param width
   */
  public Photo(int length, int width) {
    if (length <= 0 || width <= 0) {
      throw new IllegalArgumentException("Invalid Length or Width.");
    }
    this.length = length;
    this.width = width;

    pixels = new ArrayList<List<Pixel>>();
    // initalizes a photo of light blue pixels
    for (int row = 0; row < length; row++) {
      pixels.add(new ArrayList<Pixel>());
      for (int col = 0; col < width; col++) {
        pixels.get(row).add(new Pixel(255, 255, 255));
      }
    }
  }

  /**
   * Constructor that creates a checkerboard pattern of specified size— assuming a square image—
   * with two colors of the user's choosing.
   *
   * @param length
   * @param width
   * @param first
   * @param second
   */
  public Photo(int length, int width, Color first, Color second) {

    if (length < 0 || width < 0) {
      throw new IllegalArgumentException("Invalid Length or Width.");
    }

    this.length = length;
    this.width = width;

    pixels = new ArrayList<>();
    // initalizes a photo of light blue pixels
    for (int row = 0; row < length; row++) {
      pixels.add(new ArrayList<>());
      for (int col = 0; col < width; col++) {
        if ((row % 2 == 0 && col % 2 != 0) || (row % 2 != 0 && col % 2 == 0)) {
          pixels.get(row).add(new Pixel(first.getRed(), first.getGreen(), first.getBlue()));
        } else {
          pixels.get(row).add(new Pixel(second.getRed(), second.getGreen(), second.getBlue()));
        }
      }
    }
  }

  /**
   * Constructor that takes in a 2D list of Pixels. Creates a deep copy of the 2D list of pixels to
   * set this.Pixels to the given 2D list of pixels.
   *
   * @param otherPixels
   */
  public Photo(List<List<Pixel>> otherPixels) {
    if(otherPixels == null){
      throw new IllegalArgumentException();
    }
    this.length = otherPixels.size();
    this.width = otherPixels.get(0).size();
    this.pixels = new ArrayList<List<Pixel>>();

    Pixel otherPixel;
    Pixel newPixel;
    for (int row = 0; row < length; row++) {
      this.pixels.add(new ArrayList<Pixel>());
      for (int col = 0; col < width; col++) {
        otherPixel = otherPixels.get(row).get(col);
        newPixel= new Pixel(otherPixel.getRed(), otherPixel.getGreen(), otherPixel.getBlue());
        this.pixels.get(row).add(newPixel);
      }
    }
  }

  /**
   * gets a copy of the 2-D matrix that contains pixel data.
   *
   * @return
   */
  public List<List<Pixel>> getPixels() {
    List<List<Pixel>> returnPixels = new ArrayList<List<Pixel>>();
    Pixel newPixel;
    Pixel thisPixel;
    for (int i = 0; i < this.pixels.size(); i++) {
      returnPixels.add(new ArrayList<Pixel>());
      for (int j = 0; j < this.pixels.get(0).size(); j++) {
        thisPixel = pixels.get(i).get(j);
        newPixel= new Pixel(thisPixel.getRed(), thisPixel.getGreen(), thisPixel.getBlue());

        returnPixels.get(i).add(newPixel);

      }
    }
    return returnPixels;
  }

  /**
   * Returns the number of vertical pixels in this photo.
   *
   * @return
   */
  public int getLength() {
    return this.length;
  }

  /**
   * Returns the number of horizontal pixels in this photo.
   *
   * @return
   */
  public int getWidth() {
    return this.width;
  }

  @Override
  public String toString() {
    StringBuilder returnString = new StringBuilder();
    for (int i = 0; i < this.getLength(); i++) {
      for (int j = 0; j < this.getWidth(); j++) {
        returnString.append( this.pixels.get(i).get(j).toString());
      }
    }
    return returnString.toString();
  }

  /**
   * Overriding hashcode for the sake of avoiding collisions. Returns this object's hashcode.
   * @return
   */
  @Override
  public int hashCode(){
    return this.hashCode();
  }

  /**
   * Returns whether the given object is a Photo and has the same values as this photo.
   * @param other object being compared
   * @return whether objects are equivalt as defined above
   */
  @Override
  public boolean equals(Object other) {
    if(!(other instanceof Photo)){
      return false;
    }

    //testing uneven lengths
    if(((Photo) other).getLength()!= this.getLength() || ((Photo) other).getWidth()!= this.getWidth()){
      return false;
    }

    //testing each pixel equality
    List<List<Pixel>> otherPixels = ((Photo) other).getPixels();
    for (int i = 0; i < this.getLength(); i++) {
      for (int j = 0; j < this.getWidth(); j++) {
        if(!otherPixels.get(i).get(j).equals(this.pixels.get(i).get(j))){
          return false;
        }
      }
    }
    return true;
  }
}
