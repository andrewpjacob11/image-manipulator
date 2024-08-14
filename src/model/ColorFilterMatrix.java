package src.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Represents a 3x3 matrix that when multiplied by each pixel within an image as a vector, performs
 * an image filter.
 */
public enum ColorFilterMatrix {

  sepia(new ArrayList<List<Double>>(Arrays.asList(
      new ArrayList<Double>(Arrays.asList(0.393, 0.769, 0.189)),
      new ArrayList<Double>(Arrays.asList(0.349, 0.686, 0.168)),
      new ArrayList<Double>(Arrays.asList(0.272, 0.534, 0.131))))),
  grayscale(new ArrayList<List<Double>>(Arrays.asList(
      new ArrayList<Double>(Arrays.asList(0.2126, 0.7152, 0.0722)),
      new ArrayList<Double>(Arrays.asList(0.2126, 0.7152, 0.0722)),
      new ArrayList<Double>(Arrays.asList(0.2126, 0.7152, 0.0722))))),
  identity(new ArrayList<List<Double>>(Arrays.asList(
      new ArrayList<Double>(Arrays.asList(1.0, 0.0, 0.0)),
      new ArrayList<Double>(Arrays.asList(0.0, 1.0, 0.0)),
      new ArrayList<Double>(Arrays.asList(0.0, 0.0, 1.0)))));


  private final List<List<Double>> matrix;

  /**
   * Creates a deep copy of the matrix within the constructor.
   *
   * @param matrix given matrix
   */
  ColorFilterMatrix(List<List<Double>> matrix) {
    this.matrix = new ArrayList<List<Double>>();
    Double d;

    for (int i = 0; i < matrix.size(); i++) {
      this.matrix.add(new ArrayList<Double>());
      for (int j = 0; j < matrix.get(0).size(); j++) {
        d = matrix.get(i).get(j);
        this.matrix.get(i).add(d);
      }
    }
  }

  /**
   * Returns a deep copy of the selected matrix as a two-dimensional arraylist for color
   * manipulation.
   *
   * @return color transformation matrix as two-dimensional arraylist of doubles
   */
  public List<List<Double>> getMatrix() {
    List<List<Double>> colorMatrix = new ArrayList<List<Double>>();
    Double d;
    for (int i = 0; i < this.matrix.size(); i++) {
      colorMatrix.add(new ArrayList<Double>());
      for (int j = 0; j < this.matrix.get(0).size(); j++) {
        d = matrix.get(i).get(j);
        colorMatrix.get(i).add(d);
      }
    }
    return colorMatrix;

  }

}
