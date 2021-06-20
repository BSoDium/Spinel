package io.github.spinel.math.matrix;

import io.github.spinel.math.ContainerNf;

/**
 * A Matrixf is a matrix of floats. It inherits from ContainerNf and has 2
 * dimensions, its size can vary, depending on whether it is a Matrix4f or a
 * Matrix3f for example.
 */
public abstract class MatrixNf<M extends MatrixNf<M>> extends ContainerNf<M> {

  /**
   * Create a new `Matrixf` with a predefined dimension count of 2 and a specified
   * size.
   * 
   * @param size the size of each dimension of the `Matrixf` : {columns, lines}
   */
  protected MatrixNf(int[] size) {
    super(2, size);
  }

  /**
   * Create a new `Matrixf` with a predefined dimension count of 2, a specified
   * size and some initial data (content array).
   * 
   * @param size  the size of each dimension of the `Matrixf` : {columns, lines}
   * @param content the initial data
   */
  protected MatrixNf(int[] size, float[] content) {
    super(2, size, content);
  }

  /**
   * Retrieve data at coordinates (a, b).
   * 
   * @param i column index
   * @param j line index
   * @return data(i,j)
   */
  public float get(int i, int j) {
    return super.get(i, j);
  }

  /**
   * Set data at coordinates (i, j)
   * 
   * @param value data(i,j)
   * @param i   column index
   * @param j   line index
   */
  public void set(float value, int i, int j) {
    super.set(value, i, j);
  }

}
