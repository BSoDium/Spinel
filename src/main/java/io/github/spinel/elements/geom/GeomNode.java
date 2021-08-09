package io.github.spinel.elements.geom;

import io.github.spinel.exceptions.IncorrectChildTypeException;
import io.github.spinel.math.matrix.Matrix4f;
import io.github.spinel.math.matrix.TransferMatrix4f;
import io.github.spinel.math.vector.Vector3f;
import io.github.spinel.math.vector.Vector4f;
import io.github.spinel.Engine;
import io.github.spinel.elements.Node;
import io.github.spinel.elements.RootNode;

public class GeomNode extends Node {
  protected Vector3f position;
  protected Vector3f rotation;
  /** @see #getFlag() */
  protected boolean flag;

  public GeomNode(Vector3f position, Vector3f rotation, String name) {
    this.position = position;
    this.rotation = rotation;
    /**
     * the flag is true if the Node is instanciated before the Engine has started
     */
    this.flag = !(Engine.getInstance() != null && Engine.getInstance().isRunning());
    this.name = name;
  }

  public GeomNode(Vector3f position, Vector3f rotation) {
    this(position, rotation, GeomNode.class.getClass().getSimpleName());
  }

  @Override
  public void setParent(RootNode parent) {
    super.setParent(parent);
    if (parent != null && !flag) {
      Engine.getInstance().getSceneRoot().buildChildren(this);
    }
  }

  /**
   * The renderer uses this method to distinguish a GeomNode from an Item.
   * <p>
   * This isn't the most elegant solution ever, so suggestions are welcome.
   * </p>
   * 
   * @return
   */
  public boolean hasMesh() {
    return false;
  }

  /**
   * Get the GeomNode's position.
   * 
   * @return position vector
   */
  public Vector3f getPos() {
    return position;
  }

  /**
   * Get the GeomNode's rotation.
   * 
   * @return rotation angle vector
   */
  public Vector3f getRot() {
    return rotation;
  }

  /**
   * Set the GeomNode's position.
   * 
   * @param x coordinate on the X axis
   * @param y coordinate on the Y axis
   * @param z coordinate on the Z axis
   */
  public void setPos(float x, float y, float z) {
    Vector3f delta = new Vector3f(x, y, z).sub(new Vector3f(position));
    position.setContent(new float[] { x, y, z });
    hiddenChildren.forEach((Node cNode) -> {
      Vector3f current = ((GeomNode) cNode).getPos();
      Vector3f next = current.add(delta);
      ((GeomNode) cNode).setPos(next);
    });
    shownChildren.forEach((Node cNode) -> {
      Vector3f current = ((GeomNode) cNode).getPos();
      Vector3f next = current.add(delta);
      ((GeomNode) cNode).setPos(next);
    });
  }

  /**
   * Set the GeomNode's position.
   * 
   * @param vect the position vector to be read
   */
  public void setPos(Vector3f vect) {
    setPos(vect.getX(), vect.getY(), vect.getZ());
  }

  /**
   * Set the GeomNode's rotation.
   * 
   * @param l coordinate on the X axis
   * @param m coordinate on the Y axis
   * @param n coordinate on the Z axis
   */
  public void setRot(float l, float m, float n) {
    Vector3f delta = new Vector3f(l, m, n).sub(new Vector3f(rotation));
    rotation.setContent(new float[] { l, m, n });
    hiddenChildren.forEach((Node cNode) -> {
      Vector3f current = ((GeomNode) cNode).getRot();
      Vector3f next = current.add(delta);
      ((GeomNode) cNode).setRot(next);
    });
    shownChildren.forEach((Node cNode) -> {
      Vector3f current = ((GeomNode) cNode).getRot();
      Vector3f next = current.add(delta);
      ((GeomNode) cNode).setRot(next);
    });
  }

  /**
   * Set the GeomNode's rotation.
   * 
   * @param vect the rotation vector to be read
   */
  public void setRot(Vector3f vect) {
    setRot(vect.getX(), vect.getY(), vect.getZ());
  }

  /**
   * Project the normal vector e_r into the cartesian scene-specific frame of
   * reference.
   * 
   * @return normal vector e_r
   */
  public Vector3f getReferenceX() {
    double phi = rotation.getX();
    double theta = rotation.getY();
    double beta = rotation.getZ();
    // return new Vector3f((float) -(Math.sin(theta) * Math.cos(phi)), (float)
    // Math.sin(phi),
    // (float) -(Math.cos(theta) * Math.cos(phi)));
    Vector4f output = TransferMatrix4f.rotation((float) phi, new Vector3f(1, 0, 0))
        .product(TransferMatrix4f.rotation((float) theta, new Vector3f(0, 1, 0))
            .product(TransferMatrix4f.rotation((float) beta, new Vector3f(0, 0, 1)).product(new Vector4f(1, 0, 0, 0))));
    return new Vector3f(output.getX(), output.getY(), output.getZ());
  }

  /**
   * Project the tangent vector e_phi into the cartesian scene-specific frame of
   * reference. Note: Phi is the pitch angle
   * 
   * @return tangent vector
   */
  public Vector3f getReferenceY() {
    double phi = rotation.getX();
    double theta = rotation.getY();
    double beta = rotation.getZ();
    // return new Vector3f((float) -Math.sin(beta), (float) (Math.cos(phi) *
    // Math.cos(beta)), (float) Math.sin(phi));
    Vector4f output = TransferMatrix4f.rotation((float) phi, new Vector3f(1, 0, 0))
        .product(TransferMatrix4f.rotation((float) theta, new Vector3f(0, 1, 0))
            .product(TransferMatrix4f.rotation((float) beta, new Vector3f(0, 0, 1)).product(new Vector4f(0, 1, 0, 0))));
    return new Vector3f(output.getX(), output.getY(), output.getZ());
  }

  /**
   * Project the tangent vector e_theta into the cartesian scene-specific frame of
   * reference. Note: Theta is the yaw angle
   * 
   * @return tangent vector
   */
  public Vector3f getReferenceZ() {
    double phi = rotation.getX();
    double theta = rotation.getY();
    double beta = rotation.getZ();
    // return new Vector3f((float) -(Math.cos(theta) * Math.cos(beta)), (float)
    // -Math.sin(beta),
    // (float) (Math.sin(theta) * Math.cos(beta)));
    Vector4f output = TransferMatrix4f.rotation((float) phi, new Vector3f(1, 0, 0))
        .product(TransferMatrix4f.rotation((float) theta, new Vector3f(0, 1, 0))
            .product(TransferMatrix4f.rotation((float) beta, new Vector3f(0, 0, 1)).product(new Vector4f(0, 0, 1, 0))));
    return new Vector3f(output.getX(), output.getY(), output.getZ());
  }

  /**
   * Returns true if the Item has been already built (False by default).
   * 
   * @return flag
   */
  public boolean getFlag() {
    return flag;
  }

  /**
   * Flag the Item as already built by the engine / not built yet.
   * 
   * @param status value to be affected to flag
   * @return
   */
  public void setFlag(boolean status) {
    this.flag = status;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void compatibilityCheck(Node child) {
    if (!(child instanceof GeomNode)) {
      throw new IncorrectChildTypeException("GeomNode element can only receive children of types GeomNode or lower.");
    }
  }
}
