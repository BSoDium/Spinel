package fr.l3alr0g.spinel.examples;

import fr.l3alr0g.spinel.Engine;
import fr.l3alr0g.spinel.gfx.Mesh;

public class RotationDemo {
    private Engine engine = new Engine();
    private Mesh cubeMesh;
    private Mesh reference;

    private void start() {

        engine.start();
    }

    public static void main(String[] args) {
        new RotationDemo().start();
    }

}
