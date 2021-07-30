package io.github.spinel.math.vector;

/**
 * A four-dimensional mathematical vector.
 */
public final class Vector4f extends VectorNf<Vector4f> {

  /**
   * Create a new Vector4f with a predefined dimension count of 1, and a size of 1
   * by 4 without any initial data.
   */
  protected Vector4f() {
    super(4);
  }

  /**
   * Create a new Vector4f with a predefined dimension count of 1, and a size of 1
   * by 4. Provide the constructor with some initial data to start with.
   */
  public Vector4f(float[] content) {
    super(4, content);
  }

  /**
   * Create a four-dimensional vector object.
   * 
   * @param x coordinate on the X axis
   * @param y coordinate on the Y axis
   * @param z coordinate on the Z axis
   * @param w coordinate on the W axis
   */
  public Vector4f(float x, float y, float z, float w) {
    this(new float[] { x, y, z, w });
  }

  /**
   * Create a four-dimensional vector object.
   * 
   * @param vect vector input
   */
  public Vector4f(Vector4f vect) {
    this(vect.getContent());
  }

  public float getX() {
    return get(0);
  }

  public float getY() {
    return get(1);
  }

  public float getZ() {
    return get(2);
  }

  public float getW() {
    return get(3);
  }

  public void setX(float x) {
    set(x, 0);
  }

  public void setY(float y) {
    set(y, 1);
  }

  public void setZ(float z) {
    set(z, 2);
  }

  public void setW(float w) {
    set(w, 3);
  }

  @Override
  public float norm() {
    return (float) Math
        .sqrt(content[0] * content[0] + content[1] * content[1] + content[2] * content[2] + content[3] * content[3]);
  }

  @Override
  public Vector4f normalize() {
    float length = norm();
    return tbtdivide(new Vector4f(length, length, length, length));
  }

  /**
   * Dot product.
   * 
   * @param vec other vector
   * @return (this | vec)
   */
  public float dot(Vector4f vect) {
    return content[0] * vect.getX() + content[1] * vect.getY() + content[2] * vect.getZ() + content[3] * vect.getW();
  }

  /**
   * Term by term product.
   * 
   * @param container other vector
   */
  @Override
  public Vector4f product(Vector4f container) {
    Vector4f output = copy();
    output.set(output.get(0) * container.get(0), 0);
    output.set(output.get(1) * container.get(1), 1);
    output.set(output.get(2) * container.get(2), 2);
    output.set(output.get(3) * container.get(3), 3);
    return output;
  }

  @Override
  public Vector4f copy() {
    return new Vector4f(this.content);
  }

}
