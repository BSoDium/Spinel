package io.github.spinel.math.vector;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class Vector3fTest extends VectorNfTest<Vector3f> {

	@Before
	public void setup() {
		containers = new Vector3f[3];
		data = new float[5][];

		data[0] = new float[] { 1, 3, 0 };
		data[1] = new float[] { 6, 2, 1 };

		// data(containers[0] * containers[1]) = data[2]
		data[2] = new float[] { 6, 6, 0 };
		// data(containers[0] + containers[1]) = data[3]
		data[3] = new float[] { 7, 5, 1 };
		// data(containers[0] - containers[1]) = data[4]
		data[4] = new float[] { -5, 1, -1 };

		containers[0] = new Vector3f();
		containers[1] = new Vector3f(data[1]);

		containers[0].setContent(data[0]);
	}

	@Test
	public void dimSizeTest() {
		assertTrue(containers[0].getDim() == 1);
		assertTrue(containers[0].getSize().length == 1);
		assertTrue(containers[0].getSize()[0] == 3);
	}

	@Test
	public void contentTest() {
		for (int i = 0; i < 3; i++) {
			assertEquals(containers[0].get(i), data[0][i], delta);
			assertEquals(containers[1].get(i), data[1][i], delta);
		}
	}

	@Test
	public void getSetTest() {
		Vector3f temp = new Vector3f(new float[] { 0, 0, 0 });
		temp.set(23f, 1);
		temp.set(98.2f, 0);
		temp.set(-4.3f, 2);

		assertEquals(temp.get(0), 98.2f, delta);
		assertEquals(temp.get(1), 23f, delta);
		assertEquals(temp.get(2), -4.3f, delta);
	}

	@Test
	public void addTest() {
		Vector3f temp = new Vector3f(new float[] { 0, 0, 0 });
		temp = temp.add(4);
		for (int i = 0; i < 3; i++) {
			assertEquals(temp.get(i), 4, delta);
		}

		temp = containers[0].add(containers[1]);
		assertTrue("Add check with arbitrary data failed", temp.equals(new Vector3f(data[3])));
	}

	@Test
	public void subTest() {
		Vector3f temp = new Vector3f(new float[] { 0, 0, 0 });
		temp = temp.sub(8);
		for (int i = 0; i < 3; i++) {
			assertEquals(temp.get(i), -8, delta);
		}

		temp = containers[0].sub(containers[1]);
		assertTrue("Add check with arbitrary data failed", temp.equals(new Vector3f(data[4])));
	}

	@Test
	public void productTest() {
		containers[2] = containers[0].product(containers[1]);
		assertTrue("Product check with arbitrary data failed", containers[2].equals(new Vector3f(data[2])));

		containers[2] = containers[0].product(2f);
		for (int i = 0; i < 3; i++) {
			assertEquals(containers[2].get(i), containers[0].get(i) * 2, delta);
		}
	}

	@Test
	public void divideTest() {
		Vector3f temp;
		// test float division
		temp = new Vector3f(new float[] { 1, 1, 1 });
		temp = temp.divide(3.2f);
		for (int i = 0; i < 3; i++) {
			assertEquals(temp.get(i), 1 / 3.2f, delta);
		}

		// test vector division (term by term)
		temp = new Vector3f(new float[] { 1, 1, 1 });
		temp = temp.tbtdivide(containers[0]);
		for (int i = 0; i < 3; i++) {
			assertEquals(temp.get(i), 1 / containers[0].get(i), delta);
		}
	}
}
