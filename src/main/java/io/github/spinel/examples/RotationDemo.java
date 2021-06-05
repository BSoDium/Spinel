package io.github.spinel.examples;

import io.github.spinel.Engine;
import io.github.spinel.gfx.Mesh;

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
