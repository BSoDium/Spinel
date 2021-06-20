package io.github.spinel.elements.geom;

import io.github.spinel.math.matrix.Matrix4f;
import io.github.spinel.math.matrix.TransferMatrix4f;
import io.github.spinel.math.vector.Vector3f;

/**
 * Documentation required @l3alr0g
 */
public class Camera extends GeomNode {
  private float fov = 70.0f;
  private float[] nearfar = { 0.15f, 10_000.0f };
  private Matrix4f projector;

  public Camera(Vector3f position, Vector3f rotation, float aspect) {
    super(position, rotation, "Camera");
    projector = TransferMatrix4f.project(aspect, fov, nearfar[0], nearfar[1]); // (float) width / (float) height
  }

  public Matrix4f getProjector() {
    return projector;
  }

  public void setProjector(Matrix4f projector) {
    this.projector = projector;
  }
}
