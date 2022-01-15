package io.github.spinel.examples;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.github.spinel.Engine;
import io.github.spinel.elements.colliders.PlaneCollider;
import io.github.spinel.elements.geom.Item;
import io.github.spinel.elements.gui.GuiLayer;
import io.github.spinel.gfx.Color;
import io.github.spinel.gfx.Mesh;
import io.github.spinel.gfx.Vertex;
import io.github.spinel.math.vector.Vector3f;

import org.lwjgl.glfw.GLFW;

import imgui.ImGui;

/**
 * This demo aims to show how to create procedural meshes without using shaders,
 * such as a plane with randomized vertex height and color.
 * 
 * <p>
 * Controls :
 * <ul>
 * <li>arrow keys controls camera pitch and yaw</li>
 * <li>a and e / q and e controls camera roll</li>
 * <li>zqsd / wasd to move</li>
 * </ul>
 * </p>
 * 
 */
public class PlaneDemo {
  private Engine engine = new Engine();
  private Mesh surfaceMesh;
  private Item surfaceItem;
  private PlaneCollider surfaceCollider;

  /** A hashmap containing all the keybinds in this demo */
  private HashMap<Integer, Runnable> keybinds = new HashMap<>();

  private static final float TILESIZE = 0.5f; // Size of a tile in 3D units
  private static final int SURFACESIZE = 15; // Number of tiles per side of the surface

  private void start() {
    // initializing lists
    List<Vertex> vertices = new ArrayList<>();
    List<Integer> indices = new ArrayList<>();

    // iterate through the vertices and create the geoms
    for (int i = 0; i < SURFACESIZE; i++) {
      for (int j = 0; j < SURFACESIZE; j++) {
        // the height is chosen randomly
        float height = (float) Math.random();
        // assign color corresponding to the height using a smoothstep function
        Color color = new Color((double) smoothstep(height, 0, 1), (double) 0f, (double) 0f);
        vertices.add(new Vertex(new Vector3f((float) i * TILESIZE, height, (float) j * TILESIZE), color));
        int currentIndex = i * SURFACESIZE + j;
        // create quad
        if (i < SURFACESIZE - 1 && j < SURFACESIZE - 1) {
          indices.add(currentIndex);
          indices.add(currentIndex + 1);
          indices.add(currentIndex + SURFACESIZE);

          indices.add(currentIndex + 1);
          indices.add(currentIndex + SURFACESIZE);
          indices.add(currentIndex + SURFACESIZE + 1);
        }
      }
    }

    // instanciate mesh and item and reparent the item to the RootNode
    surfaceMesh = new Mesh(vertices, indices);
    surfaceItem = new Item(new Vector3f(-TILESIZE * SURFACESIZE / 2, -0.5f, -TILESIZE * SURFACESIZE / 2),
        new Vector3f(0, 0, 0), new Vector3f(1, 1, 1), surfaceMesh);
    surfaceCollider = new PlaneCollider(new Vector3f(0, 0, 0), new Vector3f(0, 0, 0));
    surfaceCollider.setParent(surfaceItem);
    surfaceItem.setParent(engine.getSceneRoot());
    // surfaceItem.hide();
    createGui();
    // debug
    // Collider.toggleDebug();

    engine.getSceneRoot().tree();
    engine.getGuiRoot().tree();

    // set up key bindings
    setup();

    // start the engine
    engine.start();
  }

  private void setup() {
    keybinds.put(GLFW.GLFW_KEY_A, () -> engine.getCamera()
        .setPos(engine.getCamera().getPos().add(engine.getCamera().getReferenceX().divide(-8))));
    keybinds.put(GLFW.GLFW_KEY_D, () -> engine.getCamera()
        .setPos(engine.getCamera().getPos().sub(engine.getCamera().getReferenceX().divide(-8))));
    keybinds.put(GLFW.GLFW_KEY_W, () -> engine.getCamera()
        .setPos(engine.getCamera().getPos().add(engine.getCamera().getReferenceZ().divide(-8))));
    keybinds.put(GLFW.GLFW_KEY_S, () -> engine.getCamera()
        .setPos(engine.getCamera().getPos().sub(engine.getCamera().getReferenceZ().divide(-8))));

    keybinds.put(GLFW.GLFW_KEY_Z,
        () -> engine.getCamera().setPos(engine.getCamera().getPos().add(engine.getCamera().getReferenceY().divide(8))));
    keybinds.put(GLFW.GLFW_KEY_X,
        () -> engine.getCamera().setPos(engine.getCamera().getPos().sub(engine.getCamera().getReferenceY().divide(8))));
    keybinds.put(GLFW.GLFW_KEY_E, () -> engine.getCamera().setRot(engine.getCamera().getRot().getX(),
        engine.getCamera().getRot().getY(), engine.getCamera().getRot().getZ() + 1f));
    keybinds.put(GLFW.GLFW_KEY_Q, () -> engine.getCamera().setRot(engine.getCamera().getRot().getX(),
        engine.getCamera().getRot().getY(), engine.getCamera().getRot().getZ() - 1f));

    // arrow keys
    keybinds.put(GLFW.GLFW_KEY_UP, () -> engine.getCamera().setRot(engine.getCamera().getRot().getX() + 1f,
        engine.getCamera().getRot().getY(), engine.getCamera().getRot().getZ()));
    keybinds.put(GLFW.GLFW_KEY_DOWN, () -> engine.getCamera().setRot(engine.getCamera().getRot().getX() - 1f,
        engine.getCamera().getRot().getY(), engine.getCamera().getRot().getZ()));
    keybinds.put(GLFW.GLFW_KEY_LEFT, () -> engine.getCamera().setRot(engine.getCamera().getRot().getX(),
        engine.getCamera().getRot().getY() + 1f, engine.getCamera().getRot().getZ()));
    keybinds.put(GLFW.GLFW_KEY_RIGHT, () -> engine.getCamera().setRot(engine.getCamera().getRot().getX(),
        engine.getCamera().getRot().getY() - 1f, engine.getCamera().getRot().getZ()));
    keybinds.put(GLFW.GLFW_KEY_F11, () -> engine.getWindow().setFullscreen(!engine.getWindow().isFullscreen()));
    // stopping the engine
    keybinds.put(GLFW.GLFW_KEY_ESCAPE, () -> engine.stop());

    // apply key bindings
    for (Map.Entry<Integer, Runnable> entry : keybinds.entrySet()) {
      engine.getWindow().onKeyDown(entry.getKey(), entry.getValue());
    }
  }

  private void createGui() {
    // ImGui.getIO().getFonts().addFontFromFileTTF(Demo.class.getClassLoader().getResource("fonts/Raleway/static/Raleway-Regular.ttf").toString(),
    // 20);
    // ImGui.getIO().getFonts().addFontFromFileTTF("src/main/resources/fonts/Raleway/static/Raleway-Regular.ttf",
    // 20);

    GuiLayer testGuiNode = new GuiLayer("Test GuiNode");
    testGuiNode.addLogic(() -> {
      Vector3f cameraPos = engine.getCamera().getPos();
      Vector3f cameraRot = engine.getCamera().getRot();
      Vector3f[] base = new Vector3f[] { engine.getCamera().getReferenceX(), engine.getCamera().getReferenceY(),
          engine.getCamera().getReferenceZ() };
      ImGui.text(String.format("Camera pos : %f, %f, %f", cameraPos.getX(), cameraPos.getY(), cameraPos.getZ()));
      ImGui.text(String.format("Camera rot : %f, %f, %f", cameraRot.getX(), cameraRot.getY(), cameraRot.getZ()));
      ImGui.text(String.format("ReferenceX : %f, %f, %f", base[0].getX(), base[0].getY(), base[0].getZ()));
      ImGui.text(String.format("ReferenceY : %f, %f, %f", base[1].getX(), base[1].getY(), base[1].getZ()));
      ImGui.text(String.format("ReferenceZ : %f, %f, %f", base[2].getX(), base[2].getY(), base[2].getZ()));
    });
    testGuiNode.setParent(engine.getGuiRoot());
  }

  private float smoothstep(float x, float min, float max) {
    if (x <= min) {
      return min;
    } else if (x <= max) {
      return max * (3 * x * x - 2 * x * x * x) + min;
    } else {
      return max;
    }
  }

  public static void main(String[] args) {
    new PlaneDemo().start();
  }
}
