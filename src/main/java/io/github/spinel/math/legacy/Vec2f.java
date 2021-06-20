package io.github.spinel.math.legacy;

/**
 * A two-dimensional mathematical Vector.
 * 
 * @deprecated
 */
@Deprecated
public class Vec2f implements Vec<Vec2f> {
  private float x, y;

  /**
   * Generate a two-dimensional Vec object.
   * 
   * @param x coordinate on the X axis
   * @param y coordinate on the Y axis
   */
  public Vec2f(float x, float y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Generate a two-dimensional Vec object.
   * 
   * @param vect Vec to be read
   */
  public Vec2f(Vec2f vect) {
    this.x = vect.getX();
    this.y = vect.getY();
  }

  // getters and setters

  public float getX() {
    return x;
  }

  public float getY() {
    return y;
  }

  public void setX(float x) {
    this.x = x;
  }

  public void setY(float y) {
    this.y = y;
  }

  public void set(float x, float y) {
    this.x = x;
    this.y = y;
  }

  public void set(Vec2f Vec) {
    x = Vec.getX();
    y = Vec.getY();
  }

  // other methods

  /**
   * {@inheritDoc}
   * 
   * @param vec {@inheritDoc}
   */
  public Vec2f add(Vec2f vec) {
    return new Vec2f(x + vec.getX(), y + vec.getY());
  }

  /**
   * {@inheritDoc}
   * 
   * @param vec {@inheritDoc}
   */
  public Vec2f sub(Vec2f vec) {
    return new Vec2f(x - vec.getX(), y - vec.getY());
  }

  /**
   * {@inheritDoc}
   * 
   * @param vec {@inheritDoc}
   */
  public float dot(Vec2f vec) {
    return x * vec.getX() + y * vec.getY();
  }

  /**
   * {@inheritDoc}
   */
  public float norm() {
    return (float) Math.sqrt(x * x + y * y);
  }

  /**
   * {@inheritDoc}
   * 
   * @param vec {@inheritDoc}
   */
  public Vec2f multiply(Vec2f vec) {
    return new Vec2f(x * vec.getX(), y * vec.getY());
  }

  /**
   * {@inheritDoc}
   * 
   * @param value {@inheritDoc}
   * @return {@inheritDoc}
   */
  public Vec2f multiply(float value) {
    return new Vec2f(x * value, y * value);
  }

  /**
   * {@inheritDoc}
   * 
   * @param vec {@inheritDoc}
   */
  public Vec2f divide(Vec2f vec) {
    return new Vec2f(x / vec.getX(), y / vec.getY());
  }

  /**
   * {@inheritDoc}
   * 
   * @param value {@inheritDoc}
   */
  public Vec2f divide(float value) {
    return new Vec2f(x / value, y / value);
  }

  /**
   * {@inheritDoc}
   */
  public Vec2f normalize() {
    float length = this.norm();
    return this.divide(new Vec2f(length, length));
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + Float.floatToIntBits(x);
    result = prime * result + Float.floatToIntBits(y);
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
    Vec2f other = (Vec2f) obj;
    if (Float.floatToIntBits(x) != Float.floatToIntBits(other.x))
      return false;
    if (Float.floatToIntBits(y) != Float.floatToIntBits(other.y))
      return false;
    return true;
  }

}
