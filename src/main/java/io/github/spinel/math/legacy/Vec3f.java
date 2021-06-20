package io.github.spinel.math.legacy;

/**
 * A three-dimensional mathematical Vector.
 * 
 * @deprecated
 */
@Deprecated
public class Vec3f implements Vec<Vec3f> {
  private float x, y, z;

  /**
   * Generate a three-dimensional Vec object.
   * 
   * @param x coordinate on the X axis
   * @param y coordinate on the Y axis
   * @param z coordinate on the Z axis
   */
  public Vec3f(float x, float y, float z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }

  /**
   * Generate a three-dimensional Vec object.
   * 
   * @param vect Vec to be read
   */
  public Vec3f(Vec3f vect) {
    this.x = vect.getX();
    this.y = vect.getY();
    this.z = vect.getZ();
  }

  // getters and setters

  public float getX() {
    return x;
  }

  public float getY() {
    return y;
  }

  public float getZ() {
    return z;
  }

  public void setX(float x) {
    this.x = x;
  }

  public void setY(float y) {
    this.y = y;
  }

  public void setZ(float z) {
    this.z = z;
  }

  public void set(float x, float y, float z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }

  public void set(Vec3f Vec) {
    x = Vec.getX();
    y = Vec.getY();
    z = Vec.getZ();
  }

  // other methods

  /**
   * {@inheritDoc}
   * 
   * @param vec {@inheritDoc}
   */
  public Vec3f add(Vec3f vec) {
    return new Vec3f(x + vec.getX(), y + vec.getY(), z + vec.getZ());
  }

  /**
   * {@inheritDoc}
   * 
   * @param vec {@inheritDoc}
   */
  public Vec3f sub(Vec3f vec) {
    return new Vec3f(x - vec.getX(), y - vec.getY(), z - vec.getZ());
  }

  /**
   * {@inheritDoc}
   * 
   * @param vec {@inheritDoc}
   */
  public float dot(Vec3f vec) {
    return x * vec.getX() + y * vec.getY() + z * vec.getZ();
  }

  /**
   * {@inheritDoc}
   */
  public float norm() {
    return (float) Math.sqrt(x * x + y * y + z * z);
  }

  /**
   * {@inheritDoc}
   * 
   * @param vec {@inheritDoc}
   * @return {@inheritDoc}
   */
  public Vec3f multiply(Vec3f vec) {
    return new Vec3f(x * vec.getX(), y * vec.getY(), z * vec.getZ());
  }

  /**
   * {@inheritDoc}
   * 
   * @param value {@inheritDoc}
   * @return {@inheritDoc}
   */
  public Vec3f multiply(float value) {
    return new Vec3f(x * value, y * value, z * value);
  }

  /**
   * {@inheritDoc}
   * 
   * @param vec {@inheritDoc}
   */
  public Vec3f divide(Vec3f vec) {
    return new Vec3f(x / vec.getX(), y / vec.getY(), z / vec.getZ());
  }

  /**
   * {@inheritDoc}
   * 
   * @param value {@inheritDoc}
   */
  public Vec3f divide(float value) {
    return new Vec3f(x / value, y / value, z / value);
  }

  /**
   * {@inheritDoc}
   */
  public Vec3f normalize() {
    float length = this.norm();
    return this.divide(new Vec3f(length, length, length));
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + Float.floatToIntBits(x);
    result = prime * result + Float.floatToIntBits(y);
    result = prime * result + Float.floatToIntBits(z);
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
    Vec3f other = (Vec3f) obj;
    if (Float.floatToIntBits(x) != Float.floatToIntBits(other.x))
      return false;
    if (Float.floatToIntBits(y) != Float.floatToIntBits(other.y))
      return false;
    if (Float.floatToIntBits(z) != Float.floatToIntBits(other.z))
      return false;
    return true;
  }
}
