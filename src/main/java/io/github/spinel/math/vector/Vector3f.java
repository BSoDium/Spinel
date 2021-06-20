package io.github.spinel.math.vector;

/**
 * A three-dimensional mathematical vector.
 */
public final class Vector3f extends VectorNf<Vector3f> {

  /**
   * Create a new Vector3f with a predefined dimension count of 1, and a size of 1
   * by 3 without any initial data.
   */
  protected Vector3f() {
    super(3);
  }

  /**
   * Create a new Vector3f with a predefined dimension count of 1, and a size of 1
   * by 3. Provide the constructor with some initial data to start with.
   */
  protected Vector3f(float[] content) {
    super(3, content);
  }

  /**
   * Create a three-dimensional vector object.
   * 
   * @param x coordinate on the X axis
   * @param y coordinate on the Y axis
   * @param z coordinate on the Z axis
   */
  public Vector3f(float x, float y, float z) {
    this(new float[] { x, y, z });
  }

  /**
   * Create a three-dimensional vector object.
   * 
   * @param vect vector input
   */
  public Vector3f(Vector3f vect) {
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

  public void setX(float x) {
    set(x, 0);
  }

  public void setY(float y) {
    set(y, 1);
  }

  public void setZ(float z) {
    set(z, 2);
  }

  @Override
  public float norm() {
    float x = get(0);
    float y = get(1);
    float z = get(2);
    return (float) Math.sqrt(x * x + y * y + z * z);
  }

  @Override
  public Vector3f normalize() {
    float length = norm();
    return divide(new Vector3f(length, length, length));
  }

  /**
   * Dot product.
   * 
   * @param vec other vector
   * @return (this | vec)
   */
  public float dot(Vector3f vect) {
    return content[0] * vect.getX() + content[1] * vect.getY() + content[2] * vect.getZ();
  }

  /**
   * Term by term product.
   * 
   * @param container other vector
   */
  @Override
  public Vector3f product(Vector3f container) {
    Vector3f output = copy();
    output.set(output.get(0) * container.get(0), 0);
    output.set(output.get(1) * container.get(1), 1);
    output.set(output.get(2) * container.get(2), 2);
    return output;
  }

  @Override
  public Vector3f copy() {
    return new Vector3f(this.content);
  }

}
