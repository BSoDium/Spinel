package io.github.spinel.elements.colliders;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import io.github.spinel.elements.geom.GeomNode;
import io.github.spinel.elements.geom.Item;
import io.github.spinel.math.vector.Vector3f;

public abstract class Collider extends GeomNode {
  protected Consumer<Collider> logic;
  protected Item debugObject;
  protected static boolean debug = false;
  private static List<Collider> instances = new ArrayList<>();

  /**
   * Create a new Collider object with a default name.
   * 
   * @param position the collider's position
   * @param rotation the collider's rotation
   */
  protected Collider(Vector3f position, Vector3f rotation) {
    super(position, rotation);
    this.name = this.getClass().getSimpleName();
    instances.add(this);
  }

  /**
   * Create a new Collider object with a chosen name.
   * 
   * @param position the collider's position
   * @param rotation the collider's rotation
   * @param name   the given name
   */
  protected Collider(Vector3f position, Vector3f rotation, String name) {
    this(position, rotation);
    this.name = name;
  }

  public void onCollision(Consumer<Collider> logic) {
    this.logic = logic;
  }

  /**
   * Check for existing collisions and execute associated logic lambdas.
   * <p>
   * By default, this method is empty, any subtype of Collider must override it in
   * order to actually be able to do something.
   * </p>
   */
  public void update() {
  }

  /**
   * Enable/disable debug mode.
   * <p>
   * Shows/hides collision boundaries
   * <p/>
   */
  public static void toggleDebug() {
    debug = !debug;
    if (debug) {
      instances.forEach((Collider c) -> c.debugObject.show());
    } else {
      instances.forEach((Collider c) -> c.debugObject.hide());
    }
  }

  /**
   * Get collider debugObject Item.
   * 
   * @return debug object
   */
  public Item getDebugObject() {
    return debugObject;
  }

  @Override
  public void destroy() {
    super.destroy();
    debugObject.destroy();
  }
}
