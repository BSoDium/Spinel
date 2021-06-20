package io.github.spinel.examples;

import io.github.spinel.elements.geom.Item;

/**
 * An empty demo (for now).
 * <p>
 * This demo cannot be implemented yet due to a lack of 
 * support for external shaders in the Spinel API.
 * </p>
 */
public class ShaderDemo extends SpinelDemo {
  private Item ocean;

  @Override
  protected void build() {
    // TODO Auto-generated method stub
    
  }

  @Override
  protected void setup() {
    engine.getSceneRoot().tree();
    
  }

  public static void main(String[] args) {
    new ShaderDemo().start();
  }  
}
