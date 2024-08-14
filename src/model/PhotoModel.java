package src.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a model for the sake of image manipulation. This model takes in a non-null Photo and
 * has the capacity to call two color transformations: sepia and greyscale. This can also call two
 * filter transformations: blur and sharpen. The transformations are the standard filter
 * transformations according to their name and do not modify the original photo to give the user the
 * option to save the transformation.
 */
public class PhotoModel implements IPhotoModel {

  /**
   * Constructor that stores the given photo.
   *
   * @param photo given photo
   * @throws IllegalArgumentException if photo is null
   */
  public PhotoModel(Photo photo) {
    if (photo == null) {
      throw new IllegalArgumentException("null photo");
    }
    List<List<Pixel>> pixels = photo.getPixels();
    this.photo = new Photo(pixels);
  }

  Photo photo;

  /**
   * does filtering
   *
   * @param filterType
   * @return
   */
  public List<List<Pixel>> filterTransformation(TransformationFilterMatrix filterType) {
    return filterWhole(this.photo.getPixels(), filterType);
  }


  /**
   * @param subPhoto
   * @param type
   * @return
   */
  private List<List<Pixel>> filterWhole(List<List<Pixel>> subPhoto,
      TransformationFilterMatrix type) {
    List<List<Pixel>> old = copyOld(subPhoto);
    int width = old.get(0).size();
    int length = old.size();

    List<List<Pixel>> alteredPhoto = new ArrayList<>();
    for (int row = 0; row < length; row++) {
      alteredPhoto.add(new ArrayList<>());
      for (int col = 0; col < width; col++) {
        List<List<Pixel>> matrix = new ArrayList<>();

        fillFilterMatrix(matrix, subPhoto, type.getLength(), type.getWidth(), row, col);
        // fills the matrix with a snapshot of the 5x5 area
        // checkpoint: confirm there are 25 objects in this matrix
        Pixel toAdd = filterMultiply(matrix, type.getMatrix());
        alteredPhoto.get(row).add(toAdd);
      }
    }
    return alteredPhoto;
  }

  /**
   * multiplies the filter matrices by the subset of matrices from the image
   *
   * @param subPhoto
   * @param filterMatrix
   * @return
   */
  private Pixel filterMultiply(List<List<Pixel>> subPhoto,
      List<List<Double>> filterMatrix) {
    List<Pixel> sum = new ArrayList<>();

    for (int i = 0; i < subPhoto.size(); i++) {
      for (int j = 0; j < subPhoto.size(); j++) {
        Pixel pixel = subPhoto.get(i).get(j);
        double filter = filterMatrix.get(i).get(j);
        sum.add(new Pixel(pixel.getRed() * filter, pixel.getGreen() * filter,
            pixel.getBlue() * filter));
      }
    }
    int newRed = 0;
    int newGreen = 0;
    int newBlue = 0;

    for (int k = 0; k < sum.size(); k++) {
      newRed = (int) (newRed + sum.get(k).getRed());
      newGreen = (int) (newGreen + sum.get(k).getGreen());
      newBlue = (int) (newBlue + sum.get(k).getBlue());
    }
    return new Pixel(newRed, newGreen, newBlue);
  }

  /**
   * Multiplies the two given 1D List of doubles if the lists are the same length. Since the
   * multiplication is for 1D vectors, this returns a Double. The format of the multiplication is
   * each element of each vector gets multiplied byy the same index element in the other vector.  If
   * they are not the same length, throws an IllegalArgumentException.
   *
   * @param vector1
   * @param vector2
   * @return
   * @throws IllegalArgumentException if uneven vector length
   */
  private Double vectorMultiplication(List<Double> vector1, List<Double> vector2)
      throws IllegalArgumentException {
    if (vector1.size() != vector2.size()) {
      throw new IllegalArgumentException("Different length vectors");
    }
    double sum = 0;

    for (int i = 0; i < vector1.size(); i++) {
      sum += vector1.get(i) + vector2.get(i);
    }
    return sum;
  }

  /**
   * Returns the given row of the given 2D List. If the given index is invalid, this method throws
   * an IllegalArgumentException. An invalid index is one that is greater than the matrix size or
   * less than 0.
   *
   * @param matrix1 List being processed
   * @param row     index of the desired row
   * @return desired row
   * @throws IllegalArgumentException if the row index is invalid
   */
  private List<Double> matrixToHorizontalVector(List<List<Double>> matrix1, int row) {
    return matrix1.get(row);
  }

  private List<Double> matrixToVerticalVector(List<List<Double>> matrix2, int col) {
    double value;
    List<Double> returnVector = new ArrayList<Double>();
    for (int i = 0; i < matrix2.size(); i++) {
      value = matrix2.get(i).get(col);
      returnVector.add(value);
    }
    return returnVector;
  }

  /**
   * creates deep copies of the old photo; useful in pretty much every manipulation tbh
   *
   * @param pixels
   * @return
   */
  private List<List<Pixel>> copyOld(List<List<Pixel>> pixels) {
    List<List<Pixel>> oldPhoto = pixels;
    for (int a = 0; a < pixels.size(); a++) {
      oldPhoto.add(new ArrayList<>());
      for (int b = 0; b < pixels.get(a).size(); b++) {
        Pixel old = new Pixel(pixels.get(a).get(b).getRed(),
            pixels.get(a).get(b).getGreen(), pixels.get(a).get(b).getBlue());
        oldPhoto.get(a).add(old);
      }
    }

    return oldPhoto;
  }

  /**
   * creates submatrices from a photo
   *
   * @param matrix
   * @param photo
   * @param length
   * @param width
   * @param row
   * @param col
   */
  private void fillFilterMatrix(List<List<Pixel>> matrix, List<List<Pixel>> photo, int length,
      int width,
      int row, int col) {
    for (int i = 0; i < length; i++) {
      matrix.add(new ArrayList<>());
    }

    for (int a = -(length / 2); a < (length / 2) + 1; a++) {
      for (int b = -(width / 2); b < (width / 2) + 1; b++) {
        try {
          Pixel temp = new Pixel(photo.get(row + a).get(col + b).getRed(),
              photo.get(row + a).get(col + b).getGreen(),
              photo.get(row + a).get(col + b).getBlue());
          matrix.get(a + 2).add(temp);
        } catch (IndexOutOfBoundsException ooe) {
          matrix.get(a + 2).add(new Pixel(0, 0, 0));
        }
      }
    }
  }

  /**
   * does the color transformation but puts it into 2-d pixel form
   *
   * @param color
   * @return
   */
  public List<List<Pixel>> colorTransform(ColorFilterMatrix color) {
    if (color == null) {
      throw new IllegalArgumentException("Null colorMatrix");
    }
    List<List<Pixel>> newPixels = new ArrayList<List<Pixel>>();

    List<List<Double>> colorTransformationMatrix = color.getMatrix();

    Pixel newPixel;

    List<List<Pixel>> oldPixels = photo.getPixels();
    for (int i = 0; i < photo.getLength(); i++) {
      newPixels.add(new ArrayList<Pixel>());

      for (int j = 0; j < photo.getWidth(); j++) {

        newPixel = pixelColorTransformation(oldPixels.get(i).get(j), colorTransformationMatrix);

        newPixels.get(i).add(newPixel);
      }
    }

    return newPixels;
  }


  /**
   * Performs matrix multiplication on the given colorFilter*pixel in that order. The multiplication
   * dimensions of the inputs should be [3x3] and [3x1] respectively such that a [3x1] matrix is the
   * output and constructs a new pixel based on that transformation. The size of the matrices are
   * invariants because colorFilters are always 3x3 matrices.
   *
   * @param sourcePixel
   * @param colorFilter
   * @return
   */
  protected Pixel pixelColorTransformation(Pixel sourcePixel, List<List<Double>> colorFilter) {

    List<Double> transformationRow;
    double red = sourcePixel.getRed();
    double green = sourcePixel.getGreen();
    double blue = sourcePixel.getBlue();
    double sum;
    List<Double> pixelDataForConstructor = new ArrayList<Double>();

    for (int i = 0; i < 3; i++) {

      sum = 0;
      transformationRow = colorFilter.get(i);

      sum += red * transformationRow.get(0);
      sum += green * transformationRow.get(1);
      sum += blue * transformationRow.get(2);
      pixelDataForConstructor.add(sum);
    }

    Pixel finished = new Pixel(pixelDataForConstructor);

    return finished;
  }

}

