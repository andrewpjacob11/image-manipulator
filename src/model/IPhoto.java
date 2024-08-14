package src.model;

import java.util.List;
import src.model.Pixel;

/**
 * Represents public facing methods for the Photo class.
 *
 * @param <K>
 */
public interface IPhoto<K> {

  /**
   * Creates a deep copy of the list of list of pixels that represent this photo.
   *
   * @return
   */
  List<List<Pixel>> getPixels();

  /**
   * Returns the number of pixels horizontally wide this photo is.
   *
   * @return
   */
  int getWidth();

  /**
   * Returns the number of pixels vertically long this photo is.
   *
   * @return
   */
  int getLength();

  /**
   * Calls the toString method for each pixel within the image in the format of
   * [red][newline][green][newline][blue][newline].
   *
   * @return photo as string
   */
  @Override
  String toString();

  /**
   * Returns the hashcode of this photo.
   *
   * @return
   */
  @Override
  int hashCode();

  /**
   * If the other object is a photo, returns whether the two photos have equivalent values. If the
   * other object is not a photo, returns false.
   *
   * @param other
   * @return
   */
  @Override
  boolean equals(Object other);
}
