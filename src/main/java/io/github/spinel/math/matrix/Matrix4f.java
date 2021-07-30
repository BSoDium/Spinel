package io.github.spinel.math.matrix;

import io.github.spinel.math.vector.Vector4f;

public final class Matrix4f extends MatrixNf<Matrix4f> {

	/**
	 * Create a new Matrix4f with a predefined dimension count of 2, and a size of 4
	 * by 4 without any initial data.
	 */
	public Matrix4f() {
		super(new int[] { 4, 4 });
	}

	/**
	 * Create a new Matrix4f with a predefined dimension count of 2, and a size of 4
	 * by 4. Provide the constructor with some initial data to start with.
	 * 
	 * @param content the initial data
	 */
	public Matrix4f(float[] content) {
		super(new int[] { 4, 4 }, content);
	}

	/**
	 * Generate an identity matrix.
	 * 
	 * @return 4 by 4 Identity matrix
	 */
	public static Matrix4f id() {
		return new Matrix4f(new float[] { 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1 });
	}

	/**
	 * Generate an empty matrix of zeros.
	 * 
	 * @return zero matrix
	 */
	public static Matrix4f zeros() {
		return new Matrix4f(new float[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 });
	}

	/**
	 * Not implemented yet.
	 */
	@Override
	public float norm() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * Not implemented yet.
	 */
	@Override
	public Matrix4f normalize() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Basic matrix multiplication.
	 * 
	 * @param matrix the matrix by which is to be multiplied the current Matrix4f
	 *               instance
	 * @return the matrix product
	 */
	@Override
	public Matrix4f product(Matrix4f matrix) {
		Matrix4f output = id();

		for (int i = 0; i < size[1]; i++) { // lines
			for (int j = 0; j < size[0]; j++) { // columns
				float sum = 0;
				for (int k = 0; k < size[0]; k++) {
					sum += this.get(k, i) * matrix.get(j, k);
				}
				output.set(sum, j, i);
			}
		}

		return output;
	}

	/**
	 * Matrix-vector multiplication.
	 * 
	 * @param vector the vector by which is to be multiplied the current Matrix4f
	 *               instance
	 * @return the matrix-vector product
	 */
	public Vector4f product(Vector4f vector) {
		Vector4f output = new Vector4f(0, 0, 0, 0);

		for (int i = 0; i < size[1]; i++) { // lines
			float sum = 0;
			for (int k = 0; k < size[0]; k++) {
				sum += this.get(k, i) * vector.get(k);
			}
			output.set(sum, i);
		}

		return output;
	}

	@Override
	public Matrix4f copy() {
		return new Matrix4f(this.content);
	}
}
