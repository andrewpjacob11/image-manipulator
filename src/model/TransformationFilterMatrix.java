package src.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum TransformationFilterMatrix {

  blur(new ArrayList<List<Double>>(Arrays.asList(
      new ArrayList<Double>(Arrays.asList(0.0625, 0.125, 0.0625)),
      new ArrayList<Double>(Arrays.asList(0.125, 0.25, 0.125)),
      new ArrayList<Double>(Arrays.asList(0.0625, 0.125, 0.0625))))),
  sharpen(new ArrayList<List<Double>>(Arrays.asList(
      new ArrayList<Double>(Arrays.asList(-0.125, -0.125, -0.125, -0.125, -0.125)),
      new ArrayList<Double>(Arrays.asList(-0.125, 0.25, 0.25, 0.25, -0.125)),
      new ArrayList<Double>(Arrays.asList(-0.125, 0.25, 1.0, 0.25, -0.125)),
      new ArrayList<Double>(Arrays.asList(-0.125, 0.25, 0.25, 0.25, -0.125)),
      new ArrayList<Double>(Arrays.asList(-0.125, -0.125, -0.125, -0.125, -0.125)))));

  private final List<List<Double>> matrix;

  TransformationFilterMatrix(List<List<Double>> matrix) {
    if (matrix.size() == 0) {
      throw new IllegalArgumentException("Invalid argument");
    }
    //enforcing the invariant that the kernel must be odd
    if (matrix.get(0).size() % 2 != 1 || matrix.size() % 2 != 1) {
      throw new IllegalArgumentException("Kernel needs to be an odd, square matrix");
    }
    this.matrix = new ArrayList<List<Double>>();
    Double d;
    for (int i = 0; i < matrix.size(); i++) {
      matrix.add(new ArrayList<Double>());
      for (int j = 0; j < matrix.get(0).size(); j++) {
        d = matrix.get(i).get(j);
        matrix.get(i).add(d);
      }
    }
  }

  /**
   * Returns the selected matrix as a two-dimensional arraylist for color manipulation
   *
   * @return color transformation matrix as two-dimensional arraylist of doubles
   */
  public List<List<Double>> getMatrix() {
    List<List<Double>> filterMatrix = new ArrayList<List<Double>>();
    Double d;
    for (int i = 0; i < this.matrix.size(); i++) {
      filterMatrix.add(new ArrayList<Double>());
      for (int j = 0; j < this.matrix.get(0).size(); j++) {
        d = matrix.get(i).get(j);
        filterMatrix.get(i).add(d);
      }
    }
    return filterMatrix;

  }

  /**
   * returns the length (number of rows) of the filter matrix
   *
   * @return
   */
  public int getLength() {
    return matrix.size();
  }

  /**
   * returns the width (number of columns) of the filter matrix
   *
   * @return
   */
  public int getWidth() {
    return matrix.get(0).size();
  }
}
