package io.github.spinel.elements.geom;

import io.github.spinel.elements.IncorrectChildTypeException;
import io.github.spinel.elements.Node;
import io.github.spinel.gfx.Mesh;
import io.github.spinel.math.vector.Vector3f;

/**
 * A 3D object which can be rendered
 */
public class Item extends GeomNode {
  protected Vector3f scale;
  protected Mesh mesh;

  public Item(Vector3f position, Vector3f rotation) {
    super(position, rotation);
    name = this.getClass().getSimpleName();
  }

  public Item(Vector3f position, Vector3f rotation, Vector3f scale, Mesh mesh) {
    this(position, rotation);
    this.scale = scale;
    this.mesh = mesh;
  }

  public Item(Vector3f position, Vector3f rotation, String name) {
    super(position, rotation, name);
  }

  public Item(Vector3f position, Vector3f rotation, Vector3f scale, Mesh mesh, String name) {
    this(position, rotation, name);
    this.scale = scale;
    this.mesh = mesh;
  }

  // getters and setters

  /**
   * Get the Item's scale.
   * 
   * @return scale vector
   */
  public Vector3f getScale() {
    return scale;
  }

  /**
   * Get the mesh associated to the Item.
   */
  public Mesh getMesh() {
    return mesh;
  }

  /**
   * Set the Item's scale.
   * 
   * @param sx scale on the X axis
   * @param sy scale on the Y axis
   * @param sz scale on the Z axis
   */
  public void setScale(float sx, float sy, float sz) {
    scale.set(new Vector3f(sx, sy, sz));
  }

  // other methods

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean hasMesh() {
    return true;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void destroy() {
    mesh.destroy();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void compatibilityCheck(Node child) {
    if (!(child instanceof GeomNode)) {
      throw new IncorrectChildTypeException("Item element can only receive children of types GeomNode or lower.");
    }
  }
}
