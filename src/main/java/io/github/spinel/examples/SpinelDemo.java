package io.github.spinel.examples;

import io.github.spinel.Engine;

/**
 * A basic Spinel demo application template.
 */
public abstract class SpinelDemo {
    /**The one and only engine instance */
    protected Engine engine = new Engine();

    /**
     * Start up the application. Calls the build and setup routines.
     * <p>
     * Synopsis :
     * <pre>
     * protected void start() {
     *      build();
     *      setup();
     *      engine.start();
     * }
     * </pre>
     * </p>
     */
    protected void start() {
        build();
        setup();
        engine.start();
    }

    /**
     * Build all the graphical objects used in the scene.
     * 
     */
    protected abstract void build();

    /**
     * Setup events and tasks, initialize the application.
     */
    protected abstract void setup();

}
