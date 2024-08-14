package src.model;

import java.util.List;

/**
 * Represents public facing methods for the PhotoModel. The two public facing methods are
 * filterTransformation which applies a filtertransformation across a subimage of the original image
 * and a colorTransformation which multiplies the pixel values of each pixel within the image by a
 * matrix to create a color filter effect.
 */
public interface IPhotoModel {

  /**
   * Applies the given transformationFilter on every pixel within the image.
   * @param filterType
   * @return
   */
  public List<List<Pixel>> filterTransformation(TransformationFilterMatrix filterType);

  /**
   * Applies the given colorFilter on every pixel within the image.
   * @param color
   * @return
   */
  public List<List<Pixel>> colorTransform(ColorFilterMatrix color);
}
