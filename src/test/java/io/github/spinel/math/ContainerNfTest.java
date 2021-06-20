package io.github.spinel.math;

public abstract class ContainerNfTest<C extends ContainerNf<C>> {
  protected C[] containers;

  protected float[][] data;

  /**
   * Precision used when calling assertEquals().
   */
  protected double delta = 1e-7;

  /**
   * Initialize attributes
   */
  public abstract void setup();

  /**
   * Test content integrity after initialization.
   */
  public abstract void contentTest();

  /**
   * Test the set and get methods.
   */
  public abstract void getSetTest();

  /**
   * Test the add method on float values and containers.
   */
  public abstract void addTest();

  /**
   * Test the sub method on float values and containers.
   */
  public abstract void subTest();

  /**
   * Test the product method on float values and containers.
   */
  public abstract void productTest();

  /**
   * Test the divide method on float values and containers.
   */
  public abstract void divideTest();
}
