package io.github.spinel.math.matrix;

import io.github.spinel.math.vector.Vector3f;

public class Matrix3f extends MatrixNf<Matrix3f> {

  /**
   * Create a new Matrix3f with a predefined dimension count of 2, and a size of 3
   * by 3 without any initial data.
   */
  public Matrix3f() {
    super(new int[] { 3, 3 });
  }

  /**
   * Create a new Matrix3f with a predefined dimension count of 2, and a size of 3
   * by 3. Provide the constructor with some initial data to start with.
   * 
   * @param content the initial data
   */
  public Matrix3f(float[] content) {
    super(new int[] { 3, 3 }, content);
  }

  /**
   * Generate an identity matrix.
   * 
   * @return 3 by 3 Identity matrix
   */
  public static Matrix3f id() {
    return new Matrix3f(new float[] { 1, 0, 0, 0, 1, 0, 0, 0, 1 });
  }

  /**
   * Generate an empty matrix of zeros.
   * 
   * @return zero matrix
   */
  public static Matrix3f zeros() {
    return new Matrix3f(new float[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 });
  }

  /**
   * Not implemented yet.
   */
  @Override
  public float norm() {
    // TODO Auto-generated method stub
    return 0;
  }

  /**
   * Not implemented yet.
   */
  @Override
  public Matrix3f normalize() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Basic matrix multiplication.
   * 
   * @param matrix the matrix by which is to be multiplied the current Matrix3f
   *               instance
   * @return the matrix product
   */
  @Override
  public Matrix3f product(Matrix3f matrix) {
    Matrix3f output = id();

    for (int i = 0; i < size[1]; i++) { // lines
      for (int j = 0; j < size[0]; j++) { // columns
        float sum = 0;
        for (int k = 0; k < size[0]; k++) {
          sum += this.get(k, i) * matrix.get(j, k);
        }
        output.set(sum, j, i);
      }
    }

    return output;
  }

  /**
   * Matrix-vector multiplication.
   * 
   * @param vector the vector by which is to be multiplied the current Matrix3f
   *               instance
   * @return the matrix-vector product
   */
  public Vector3f product(Vector3f vector) {
    Vector3f output = new Vector3f(0, 0, 0);

    for (int i = 0; i < size[1]; i++) { // lines
      float sum = 0;
      for (int k = 0; k < size[0]; k++) {
        sum += this.get(k, i) * vector.get(k);
      }
      output.set(sum, i);
    }

    return output;
  }

  @Override
  public Matrix3f copy() {
    return new Matrix3f(this.content);
  }

}
