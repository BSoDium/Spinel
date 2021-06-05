package io.github.spinel;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

import io.github.spinel.gfx.Color;

public class WindowTest {
    private Window window;
    private int[] size;
    private String title;
    private Color bgColor;

    @Before
    public void init() {
        size = new int[] { 1600, 900 };
        title = "sample title";
        window = new Window(size[0], size[1], title);
        bgColor = new Color(0, 255, 0);
        window.create();
    }

    @Test
    public void testCreate() {
        assertTrue("Window creation failed", window.getWindowElement() > 0);
    }

    @Test
    public void testOnButtonDown() {

    }

    @Test
    public void testOnEvent() {

    }

    @Test
    public void testOnKeyDown() {

    }

    @Test
    public void testScheduleTask() {

    }

    @Test
    public void testSetBgColor() {

    }

    @Test
    public void testStack() {

    }

    @Test
    @After
    public void testDestroy() {
        window.destroy();
        assertTrue("Capabilities were not unloaded properly", GL.getCapabilities() != null);
    }
}
