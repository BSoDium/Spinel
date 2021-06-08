package io.github.spinel.math;

/**
 * A Matrixf is a matrix of floats. It inherits from ContainerNf and has 2
 * dimensions, its size can vary, depending on whether it is a Matrix4f or a
 * Matrix3f for example.
 */
public abstract class Matrixf extends ContainerNf<Matrixf> {

    /**
     * Create a new `Matrixf` with a predefined dimension count of 2 and a specified
     * size.
     * 
     * @param size the size of each dimension of the `Matrixf` : {columns, lines}
     */
    protected Matrixf(int[] size) {
        super(2, size);
        this.content = new float[size[0] * size[1]];
    }

    /**
     * Create a new `Matrixf` with a predefined dimension count of 2, a specified
     * size and some initial data (content array).
     * 
     * @param size    the size of each dimension of the `Matrixf` : {columns, lines}
     * @param content the initial data
     */
    protected Matrixf(int[] size, float[] content) {
        super(2, size, content);
    }

    /**
     * Retrieve data at coordinates (a, b).
     * 
     * @param i column index
     * @param j line index
     * @return data(i,j)
     */
    public float get(int i, int j) {
        return super.get(i, j);
    }

    /**
     * Set data at coordinates (i, j)
     * 
     * @param value
     * @param i     column index
     * @param j     line index
     */
    public void set(float value, int i, int j) {
        super.set(value, i, j);
    }

    @Override
    public Matrixf add(Matrixf container) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Matrixf add(float value) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Matrixf sub(Matrixf container) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Matrixf sub(float value) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public float norm() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Matrixf normalize() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Matrixf product(Matrixf container) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Matrixf product(float value) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Matrixf divide(float value) {
        // TODO Auto-generated method stub
        return null;
    }

}
