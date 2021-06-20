package io.github.spinel.gfx;

import io.github.spinel.math.vector.Vector2f;
import io.github.spinel.math.vector.Vector3f;

public class Vertex {
  private Vector3f pos;
  private Vector3f color;
  private Vector2f textureCoord;

  /**
   * Define a vertex with no texture coordinates.
   * <p>
   * Warning : trying to add a texture to a mesh with no texture coordinates may
   * cause unexpected results.
   * </p>
   * <p>
   * By default, the texture coordinates will be initialized as
   * {@code new Vector2f(0.0f, 0.0f)}.
   * </p>
   * 
   * @param position position vector (3D)
   * @param color  color vector (3D)
   */
  public Vertex(Vector3f position, Vector3f color) {
    this.pos = position;
    this.color = color;
    this.textureCoord = new Vector2f(0.0f, 0.0f);
  }

  /**
   * Define a vertex with no texture coordinates.
   * <p>
   * Warning : trying to add a texture to a mesh with no texture coordinates may
   * cause unexpected results.
   * </p>
   * <p>
   * By default, the texture coordinates will be initialized as
   * {@code new Vector2f(0.0f, 0.0f)}.
   * </p>
   * 
   * @param position position vector (3D)
   * @param color  color vector (7map.ui.utils.Color)
   */
  public Vertex(Vector3f position, Color color) {
    this.pos = position;
    this.color = color.toVector3f();
    this.textureCoord = new Vector2f(0.0f, 0.0f);
  }

  /**
   * Define a vertex with texture coordinates.
   * 
   * @param position   position vector (3D)
   * @param color    color vector (3D)
   * @param textureCoord texture coordinates (2D)
   */
  public Vertex(Vector3f position, Vector3f color, Vector2f textureCoord) {
    this(position, color);
    this.textureCoord = textureCoord;
  }

  // @l3alr0g something has to be done about this
  public Vertex(Vector3f position, Color color, Vector2f textureCoord) {
    this.pos = position;
    this.color = color.toVector3f();
    this.textureCoord = textureCoord;
  }

  /**
   * Get the position of the vertex.
   * 
   * @return position (3D)
   */
  public Vector3f getPos() {
    return pos;
  }

  /**
   * Get the color of the vertex.
   * 
   * @return color (3D)
   */
  public Vector3f getColor() {
    return color;
  }

  /**
   * Get the texture coordinates of the vertex.
   * 
   * @return texture coordinates (2D)
   */
  public Vector2f getTexCoord() {
    return textureCoord;
  }
}
