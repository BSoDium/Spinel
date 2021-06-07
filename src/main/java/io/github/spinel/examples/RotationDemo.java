package io.github.spinel.examples;

import java.util.HashMap;
import java.util.Map;

import org.lwjgl.glfw.GLFW;

import io.github.spinel.Engine;
import io.github.spinel.elements.geom.Item;
import io.github.spinel.gfx.Color;
import io.github.spinel.gfx.Mesh;
import io.github.spinel.gfx.Vertex;
import io.github.spinel.math.Vector3f;
import io.github.spinel.scheduling.events.EmptyEvent;

/**
 * This demo aims to show how to control an Item's movement at runtime, such as
 * a basic cube in this particular scenario.
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
public class RotationDemo {
        private Engine engine = new Engine();
        private Item cube;
        private int time = 0;

        private void start() {
                // build Items
                build();
                // set up key bindings
                setup();
                engine.start();
        }

        private void build() {
                Vertex[] vertices = new Vertex[] { new Vertex(new Vector3f(-1, -1, 1), new Color(255, 255, 255)),
                                new Vertex(new Vector3f(1, -1, 1), new Color(0, 255, 255)),
                                new Vertex(new Vector3f(1, -1, -1), new Color(255, 0, 255)),
                                new Vertex(new Vector3f(-1, -1, -1), new Color(255, 255, 0)),

                                new Vertex(new Vector3f(-1, 1, 1), new Color(0, 255, 255)),
                                new Vertex(new Vector3f(1, 1, 1), new Color(255, 0, 255)),
                                new Vertex(new Vector3f(1, 1, -1), new Color(255, 255, 0)),
                                new Vertex(new Vector3f(-1, 1, -1), new Color(255, 255, 255)), };
                int[] indices = new int[] { 0, 1, 2, 2, 3, 0,

                                0, 1, 4, 4, 1, 5,

                                1, 5, 6, 1, 2, 6,

                                2, 3, 7, 2, 7, 6,

                                0, 4, 7, 0, 7, 3,

                                4, 7, 5, 5, 6, 7

                };
                cube = new Item(new Vector3f(0, 0, 0), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1),
                                new Mesh(vertices, indices));
                cube.setParent(engine.getSceneRoot());

                engine.getCamera().setPos(1, 2, 4);
        }

        private void setup() {
                HashMap<Integer, Runnable> keybinds = new HashMap<>();
                keybinds.put(GLFW.GLFW_KEY_A, () -> engine.getCamera()
                                .setPos(engine.getCamera().getPos().add(engine.getCamera().getReferenceZ().divide(8))));
                keybinds.put(GLFW.GLFW_KEY_D, () -> engine.getCamera()
                                .setPos(engine.getCamera().getPos().sub(engine.getCamera().getReferenceZ().divide(8))));
                keybinds.put(GLFW.GLFW_KEY_W, () -> engine.getCamera()
                                .setPos(engine.getCamera().getPos().add(engine.getCamera().getReferenceX().divide(8))));
                keybinds.put(GLFW.GLFW_KEY_S, () -> engine.getCamera()
                                .setPos(engine.getCamera().getPos().sub(engine.getCamera().getReferenceX().divide(8))));

                keybinds.put(GLFW.GLFW_KEY_Z, () -> engine.getCamera()
                                .setPos(engine.getCamera().getPos().add(engine.getCamera().getReferenceY().divide(8))));
                keybinds.put(GLFW.GLFW_KEY_X, () -> engine.getCamera()
                                .setPos(engine.getCamera().getPos().sub(engine.getCamera().getReferenceY().divide(8))));
                keybinds.put(GLFW.GLFW_KEY_E, () -> engine.getCamera().setRot(engine.getCamera().getRot().getX(),
                                engine.getCamera().getRot().getY(), engine.getCamera().getRot().getZ() + 1f));
                keybinds.put(GLFW.GLFW_KEY_Q, () -> engine.getCamera().setRot(engine.getCamera().getRot().getX(),
                                engine.getCamera().getRot().getY(), engine.getCamera().getRot().getZ() - 1f));

                // arrow keys
                keybinds.put(GLFW.GLFW_KEY_UP, () -> engine.getCamera().setRot(engine.getCamera().getRot().getX() + 1f,
                                engine.getCamera().getRot().getY(), engine.getCamera().getRot().getZ()));
                keybinds.put(GLFW.GLFW_KEY_DOWN,
                                () -> engine.getCamera().setRot(engine.getCamera().getRot().getX() - 1f,
                                                engine.getCamera().getRot().getY(),
                                                engine.getCamera().getRot().getZ()));
                keybinds.put(GLFW.GLFW_KEY_LEFT, () -> engine.getCamera().setRot(engine.getCamera().getRot().getX(),
                                engine.getCamera().getRot().getY() + 1f, engine.getCamera().getRot().getZ()));
                keybinds.put(GLFW.GLFW_KEY_RIGHT, () -> engine.getCamera().setRot(engine.getCamera().getRot().getX(),
                                engine.getCamera().getRot().getY() - 1f, engine.getCamera().getRot().getZ()));
                keybinds.put(GLFW.GLFW_KEY_F11,
                                () -> engine.getWindow().setFullscreen(!engine.getWindow().isFullscreen()));
                // stopping the engine
                keybinds.put(GLFW.GLFW_KEY_ESCAPE, () -> engine.stop());

                // apply key bindings
                for (Map.Entry<Integer, Runnable> entry : keybinds.entrySet()) {
                        engine.getWindow().onKeyDown(entry.getKey(), entry.getValue());
                }

                engine.getWindow().onEvent(new EmptyEvent(), () -> {
                        cube.setRot(new Vector3f(time, time, time));
                        time++;
                });
        }

        public static void main(String[] args) {
                new RotationDemo().start();
        }

}
