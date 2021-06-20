package io.github.spinel.math.vector;

import io.github.spinel.math.ContainerNfTest;

public abstract class VectorNfTest<V extends VectorNf<V>> extends ContainerNfTest<V> {

  /**
   * Test vector dimension and size after initialization.
   */
  public abstract void dimSizeTest();
}
