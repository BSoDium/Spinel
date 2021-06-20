package io.github.spinel.math.legacy;

import java.util.Arrays;

/**
 * A 2d matrix of 4 by 4 elements stored as a 1d object.
 * 
 * @deprecated
 */
@Deprecated
public class Mat4f {
  private float[] elements = new float[SIZE * SIZE]; // 2d matrix stored in a 1d system
  public static final int SIZE = 4;

  // getters and setters

  /**
   * Retrieve data at coordinates (a, b).
   * 
   * @param a index
   * @param b index
   * @return data
   */
  public float get(int a, int b) {
    return elements[b * SIZE + a];
  }

  /**
   * Set data at coordinates (a, b).
   * 
   * @param a   column's index
   * @param b   line's index
   * @param val value
   */
  public void set(int a, int b, float val) {
    elements[b * SIZE + a] = val;
  }

  /**
   * Retrieve the stored raw data.
   * 
   * @return data array
   */
  public float[] getContent() {
    return elements;
  }

  /**
   * Set all elements inside the matrix at once.
   * 
   * @param content element float array
   */
  public void setContent(float[] content) {
    elements = content;
  }

  // other methods

  /**
   * Generate identity matrix.
   * 
   * @return 4 by 4 Identity matrix
   */
  public static Mat4f id() {
    Mat4f output = new Mat4f();

    output.setContent(new float[] { 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1 });
    return output;
  }

  /**
   * Generate an empty matrix of zeros.
   * 
   * @return zero matrix
   */
  public static Mat4f zero() {
    Mat4f output = new Mat4f();
    output.setContent(new float[] { 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f });
    return output;
  }

  /**
   * Generate translation matrix.
   * 
   * @param translate translation Vec
   * @return translation matrix
   */
  public static Mat4f translate(Vec3f translate) {
    Mat4f output = Mat4f.id();
    output.set(3, 0, translate.getX());
    output.set(3, 1, translate.getY());
    output.set(3, 2, translate.getZ());
    return output;
  }

  /**
   * Generate rotation matrix.
   * 
   * @param angle rotation angle
   * @param axis  rotation axis
   * @return rotation matrix
   */
  public static Mat4f rotate(float angle, Vec3f axis) {
    Mat4f output = Mat4f.id();

    float costheta = (float) Math.cos(Math.toRadians(angle));
    float sintheta = (float) Math.sin(Math.toRadians(angle));
    float umcostheta = 1 - costheta;
    output.set(0, 0, costheta + axis.getX() * axis.getX() * umcostheta);
    output.set(0, 1, axis.getX() * axis.getY() * umcostheta - axis.getZ() * sintheta);
    output.set(0, 2, axis.getX() * axis.getZ() * umcostheta + axis.getY() * sintheta);
    output.set(1, 0, axis.getY() * axis.getX() * umcostheta + axis.getZ() * sintheta);
    output.set(1, 1, costheta + axis.getY() * axis.getY() * umcostheta);
    output.set(1, 2, axis.getY() * axis.getZ() * umcostheta - axis.getX() * sintheta);
    output.set(2, 0, axis.getZ() * axis.getX() * umcostheta - axis.getY() * sintheta);
    output.set(2, 1, axis.getZ() * axis.getY() * umcostheta + axis.getX() * sintheta);
    output.set(2, 2, costheta + axis.getZ() * axis.getZ() * umcostheta);
    return output;
  }

  /**
   * Generate a scaling matrix.
   * 
   * @param factor scaling factor
   * @return scaling matrix
   */
  public static Mat4f scale(Vec3f factor) {
    Mat4f output = Mat4f.id();

    output.set(0, 0, factor.getX());
    output.set(1, 1, factor.getY());
    output.set(2, 2, factor.getZ());

    return output;
  }

  /**
   * Generate a scaling matrix.
   * 
   * @param position position Vec
   * @param rotation rotation Vec
   * @param scale    scaling factor
   * @return scaling matrix
   */
  public static Mat4f transform(Vec3f position, Vec3f rotation, Vec3f scale) {
    Mat4f output;

    Mat4f tMatrix = Mat4f.translate(position);
    Mat4f rXMatrix = Mat4f.rotate(rotation.getX(), new Vec3f(1, 0, 0));
    Mat4f rYMatrix = Mat4f.rotate(rotation.getY(), new Vec3f(0, 1, 0));
    Mat4f rZMatrix = Mat4f.rotate(rotation.getZ(), new Vec3f(0, 0, 1));
    Mat4f sMatrix = Mat4f.scale(scale);

    Mat4f rMatrix = Mat4f.product(rXMatrix, Mat4f.product(rYMatrix, rZMatrix));

    output = Mat4f.product(tMatrix, Mat4f.product(rMatrix, sMatrix));

    return output;
  }

  /**
   * Generate a projection matrix for the specified parameters.
   * 
   * @param aspect   aspect ratio
   * @param fov      field of view
   * @param nearDist near distance
   * @param farDist  far distance
   * @return projection matrix
   */
  public static Mat4f project(float aspect, float fov, float nearDist, float farDist) {
    Mat4f output = Mat4f.zero();

    float tanFOV = (float) Math.tan(Math.toRadians(fov / 2));
    float range = farDist - nearDist;

    output.set(0, 0, 1.0f / (aspect * tanFOV));
    output.set(1, 1, 1.0f / tanFOV);
    output.set(2, 2, -(farDist + nearDist) / range);
    output.set(3, 2, -2 * farDist * nearDist / (farDist - nearDist));
    output.set(2, 3, -1.0f);

    return output;
  }

  /**
   * Generate a view matrix from the provided position and rotation.
   * 
   * @param position position Vec (x, y, z)
   * @param rotation rotation Vec (h, p, r)
   * @return view matrix
   */
  public static Mat4f view(Vec3f position, Vec3f rotation) {
    Mat4f output;

    Vec3f invertedPos = new Vec3f(-position.getX(), -position.getY(), -position.getZ());
    // translation matrix
    Mat4f tMatrix = Mat4f.translate(invertedPos);
    Mat4f rXMatrix = Mat4f.rotate(rotation.getX(), new Vec3f(1, 0, 0));
    Mat4f rYMatrix = Mat4f.rotate(rotation.getY(), new Vec3f(0, 1, 0));
    Mat4f rZMatrix = Mat4f.rotate(rotation.getZ(), new Vec3f(0, 0, 1));
    // rotation matrix
    Mat4f rMatrix = Mat4f.product(rZMatrix, Mat4f.product(rYMatrix, rXMatrix));

    output = Mat4f.product(tMatrix, rMatrix);

    return output;
  }

  /**
   * Calculate basic matrix product.
   * 
   * @param mat1 first matrix
   * @param mat2 second matrix
   * @return mat1*mat2
   */
  public static Mat4f product(Mat4f mat1, Mat4f mat2) {
    Mat4f output = Mat4f.id();

    for (int i = 0; i < SIZE; i++) {
      for (int j = 0; j < SIZE; j++) {
        float sum = 0;
        for (int k = 0; k < SIZE; k++) {
          sum += mat1.get(i, k) * mat2.get(k, j);
        }
        output.set(i, j, sum);
      }
    }
    return output;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + Arrays.hashCode(elements);
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Mat4f other = (Mat4f) obj;
    if (!Arrays.equals(elements, other.elements))
      return false;
    return true;
  }

}
