package fr.l3alr0g.spinel.examples;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.l3alr0g.spinel.Engine;
import fr.l3alr0g.spinel.elements.colliders.PlaneCollider;
import fr.l3alr0g.spinel.elements.geom.Item;
import fr.l3alr0g.spinel.elements.gui.GuiNode;
import fr.l3alr0g.spinel.gfx.Color;
import fr.l3alr0g.spinel.gfx.Mesh;
import fr.l3alr0g.spinel.gfx.Vertex;
import fr.l3alr0g.spinel.math.Vector3f;

import org.lwjgl.glfw.GLFW;

import imgui.ImGui;

public class Demo {
    private Engine engine = new Engine();
    private Mesh surfaceMesh;
    private Item surfaceItem;
    private PlaneCollider surfaceCollider;
    private GuiNode debugStats;

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

        GuiNode testGuiNode = new GuiNode("Test GuiNode");
        testGuiNode.addLogic(() -> {
            ImGui.showDemoWindow();
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
        new Demo().start();
    }
}
