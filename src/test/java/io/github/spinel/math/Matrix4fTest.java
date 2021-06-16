package io.github.spinel.math;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class Matrix4fTest {
    private Matrix4f mat1;
    private Matrix4f mat2;

    private float[] data1 = new float[] { 1, 1, 1, 1, 0, 0, 0, 0, 3, 1, 2, 1, 0, 1, 1, 0 };
    private float[] data2 = new float[] { 1, 0, 0, -4, 3, 0, 0, 1, 1, 7, 1, 1, 0, 1, 1, 0 };

    // data(mat1 * mat2) = data3
    private float[] data3 = new float[] { 5, 8, 2, -2, 0, 0, 0, 0, 8, 15, 3, -9, 4, 7, 1, 2 };
    // data(mat1 + mat2) = mat4
    private float[] data4 = new float[] { 2, 1, 1, -3, 3, 0, 0, 1, 4, 8, 3, 2, 0, 2, 2, 0 };

    private double delta = 1e-7;

    @Before
    public void setup() {
        mat1 = new Matrix4f();
        mat2 = new Matrix4f(data2);

        mat1.setContent(data1);
    }

    @Test
    public void testGetSet() {
        Matrix4f id = Matrix4f.id();
        id.set(45, 1, 3);
        id.set(-8.78f, 2, 0);
        id.set(6.2f, 1, 1);

        assertEquals(id.get(1, 3), 45, delta);
        assertEquals(id.get(2, 0), -8.78f, delta);
        assertEquals(id.get(1, 1), 6.2f, delta);
    }

    @Test
    public void testId() {
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
    public void testZero() {
        Matrix4f zeros = Matrix4f.zero();
        for (float z : zeros.getContent()) {
            assertEquals(z, 0, delta);
        }
    }

    @Test
    public void testContent() {
        for (int i = 0; i < 4; i++) { // lines
            for (int j = 0; j < 4; j++) { // columns
                assertEquals(mat1.get(j, i), data1[i * 4 + j], delta);
                assertEquals(mat2.get(j, i), data2[i * 4 + j], delta);
            }
        }
    }

    @Test
    public void testAdd() {
        Matrix4f zeros = Matrix4f.zero();
        zeros = zeros.add(4);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                assertEquals(zeros.get(i, j), 4, delta);
            }
        }

        zeros = mat1.add(mat2);
        assertTrue("Add check with arbitrary data failed", zeros.equals(new Matrix4f(data4)));

    }

    @Test
    public void testProduct() {
        Matrix4f id = Matrix4f.id();
        Matrix4f mat3 = id.product(mat1);
        assertTrue("Id * M = M : statement returned false", mat3.equals(mat1));

        mat3 = mat1.product(mat2);
        assertTrue("Product check with arbitrary data failed", mat3.equals(new Matrix4f(data3)));
    }

    @Test
    public void testDivide() {
        Matrix4f id;
        // test float division
        id = Matrix4f.id();
        id = id.divide(3.5f);
        for (int i = 0; i < 4; i++) {
            assertEquals(id.get(i, i), 1 / 3.5f, delta);
        }

        // test matrix division
        id = Matrix4f.id();
        id = id.divide(mat1);
        for (int i = 0; i < 4; i++) {
            assertEquals(id.get(i, i), 1 / mat1.get(i, i), delta);
        }
    }

}
