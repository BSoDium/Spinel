package io.github.spinel;

import org.junit.Test;

import io.github.spinel.scheduling.events.EmptyEvent;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unfinished.
 */
public class EngineTest extends TestCase {
  private Engine engine;

  public EngineTest(String TestName) {
    super(TestName);
    setup();
  }

  public static junit.framework.Test suite() {
    return new TestSuite(EngineTest.class);
  }

  public void setup() {
    engine = new Engine();
  }

  @Test
  public void testEngine() {
    // run for one frame then stop
    engine.getWindow().onEvent(new EmptyEvent(), () -> {
      engine.stop();
      assertTrue(true);
    });
    engine.start();
  }
}
