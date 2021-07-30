package io.github.spinel.math.matrix;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import io.github.spinel.math.vector.Vector4f;

public class Matrix4fTest extends MatrixNfTest<Matrix4f> {

	@Before
	public void setup() {
		data = new float[7][];
		containers = new Matrix4f[3];
		vectors = new Vector4f[2];

		data[0] = new float[] { 1, 1, 1, 1, 0, 0, 0, 0, 3, 1, 2, 1, 0, 1, 1, 0 };
		data[1] = new float[] { 1, 0, 0, -4, 3, 0, 0, 1, 1, 7, 1, 1, 0, 1, 1, 0 };
		data[2] = new float[] { 1, 9, 0, 2 };

		// data(containers[0] * containers[1]) = data[3]
		data[3] = new float[] { 5, 8, 2, -2, 0, 0, 0, 0, 8, 15, 3, -9, 4, 7, 1, 2 };
		// data(containers[0] + containers[1]) = data[4]
		data[4] = new float[] { 2, 1, 1, -3, 3, 0, 0, 1, 4, 8, 3, 2, 0, 2, 2, 0 };
		// data(containers[0] - containers[1]) = data[5]
		data[5] = new float[] { 0, 1, 1, 5, -3, 0, 0, -1, 2, -6, 1, 0, 0, 0, 0, 0 };
		// data(containers[0] * vectors[0]) = data[6]
		data[6] = new float[] { 12, 0, 14, 9 };

		containers[0] = new Matrix4f();
		containers[1] = new Matrix4f(data[1]);
		containers[0].setContent(data[0]);
		vectors[0] = new Vector4f(data[2]);

	}

	@Test
	public void dimSizeTest() {
		assertTrue(containers[0].getDim() == 2);
		assertTrue(containers[0].getSize().length == 2);
		assertTrue(containers[0].getSize()[0] == 4 && containers[0].getSize()[1] == 4);
	}

	@Test
	public void getSetTest() {
		Matrix4f id = Matrix4f.id();
		id.set(45, 1, 3);
		id.set(-8.78f, 2, 0);
		id.set(6.2f, 1, 1);

		assertEquals(id.get(1, 3), 45, delta);
		assertEquals(id.get(2, 0), -8.78f, delta);
		assertEquals(id.get(1, 1), 6.2f, delta);
	}

	@Test
	public void idTest() {
		Matrix4f id = Matrix4f.id();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (i == j) {
					assertEquals(id.get(i, j), 1, delta);
				} else {
					assertEquals(id.get(i, j), 0, delta);
				}
			}
		}
	}

	@Test
	public void zeroTest() {
		Matrix4f zeros = Matrix4f.zeros();
		for (float z : zeros.getContent()) {
			assertEquals(z, 0, delta);
		}
	}

	@Test
	public void contentTest() {
		for (int i = 0; i < 4; i++) { // lines
			for (int j = 0; j < 4; j++) { // columns
				assertEquals(containers[0].get(j, i), data[0][i * 4 + j], delta);
				assertEquals(containers[1].get(j, i), data[1][i * 4 + j], delta);
			}
		}
	}

	@Test
	public void addTest() {
		Matrix4f zeros = Matrix4f.zeros();
		zeros = zeros.add(4);
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				assertEquals(zeros.get(i, j), 4, delta);
			}
		}

		zeros = containers[0].add(containers[1]);
		assertTrue("Add check with arbitrary data failed", zeros.equals(new Matrix4f(data[4])));

	}

	@Test
	public void subTest() {
		Matrix4f zeros = Matrix4f.zeros();
		zeros = zeros.sub(4);
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				assertEquals(zeros.get(i, j), -4, delta);
			}
		}

		zeros = containers[0].sub(containers[1]);
		assertTrue("Sub check with arbitrary data failed", zeros.equals(new Matrix4f(data[5])));

	}

	@Test
	public void productTest() {
		Matrix4f id = Matrix4f.id();
		containers[2] = id.product(containers[0]);
		assertTrue("Id * M = M : statement returned false", containers[2].equals(containers[0]));

		containers[2] = containers[0].product(containers[1]);
		assertTrue("Product check with arbitrary data failed", containers[2].equals(new Matrix4f(data[3])));

		containers[2] = containers[0].product(2f);
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				assertEquals(containers[2].get(i, j), containers[0].get(i, j) * 2, delta);
			}
		}

		vectors[1] = containers[0].product((Vector4f) vectors[0]);
		assertTrue("Product check with Vector4f failed", vectors[1].equals(new Vector4f(data[6])));
	}

	@Test
	public void divideTest() {
		Matrix4f id;
		// test float division
		id = Matrix4f.id();
		id = id.divide(3.5f);
		for (int i = 0; i < 4; i++) {
			assertEquals(id.get(i, i), 1 / 3.5f, delta);
		}

		// test matrix division (term by term)
		id = Matrix4f.id();
		id = id.tbtdivide(containers[0]);
		for (int i = 0; i < 4; i++) {
			assertEquals(id.get(i, i), 1 / containers[0].get(i, i), delta);
		}
	}

}
