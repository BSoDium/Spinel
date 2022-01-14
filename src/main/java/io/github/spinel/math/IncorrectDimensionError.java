package io.github.spinel.math;

/**
 * Thrown when mathematical objects encounter dimensional inconsistencies.
 */
public class IncorrectDimensionError extends Error {

  public IncorrectDimensionError(String report) {
    super(String.format("[Math error] %s", report));
  }
}
