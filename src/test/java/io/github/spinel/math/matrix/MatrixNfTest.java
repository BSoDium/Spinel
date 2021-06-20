package io.github.spinel.math.matrix;

import io.github.spinel.math.ContainerNfTest;

public abstract class MatrixNfTest<M extends MatrixNf<M>> extends ContainerNfTest<M> {

  /**
   * Test matrix dimensions and size after initialization.
   */
  public abstract void dimSizeTest();

}