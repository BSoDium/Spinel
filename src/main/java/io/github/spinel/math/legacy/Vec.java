package io.github.spinel.math.legacy;

/**
 * An interface for vector (unidimensional) objects of multiple sizes.
 * 
 * @deprecated
 */
@Deprecated
public interface Vec<V extends Vec<V>> {

  /**
   * Add the current Vec to another {@link Vec} object.
   * 
   * @param vec other Vec
   * @return this + vec
   */
  public V add(V vec);

  /**
   * Substract another Vec from the current {@link Vec} object
   * 
   * @param vec other Vec
   * @return this - vec
   */
  public V sub(V vec);

  /**
   * Calculate the dot product by another Vec.
   * 
   * @param vec other Vec
   * @return this . vec
   */
  public float dot(V vec);

  /**
   * Calculate the norm of the Vec.
   * 
   * @return norm
   */
  public float norm();

  /**
   * Multiply the current Vec by another {@link Vec}.
   * 
   * @param vec other Vec
   * @return result
   */
  public V multiply(V vec);

  /**
   * Multiply the current Vec's components by a value.
   * 
   * @param value given value
   * @return result
   */
  public V multiply(float value);

  /**
   * Divide the current Vec by another Vec.
   * 
   * @param vec other Vec
   * @return result
   */
  public V divide(V vec);

  /**
   * Divide the current Vec's components by a value.
   * 
   * @param value given value
   * @return result
   */
  public V divide(float value);

  /**
   * Normalize and return the Vec.
   * 
   * @return normalized Vec
   */
  public V normalize();
}
