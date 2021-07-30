package io.github.spinel.math.matrix;

import io.github.spinel.math.ContainerNfTest;
import io.github.spinel.math.vector.VectorNf;

public abstract class MatrixNfTest<M extends MatrixNf<M>> extends ContainerNfTest<M> {
  protected VectorNf<?>[] vectors;

  /**
   * Test matrix dimensions and size after initialization.
   */
  public abstract void dimSizeTest();

}