package io.github.spinel.math;

/**
 * A Vectorf is a vector of floats. It inherits from ContainerNf and has 1
 * dimension, its size can vary, depending on whether it is a Vector2f, a
 * Vector3f or a Vector4f for example.
 */
public abstract class Vectorf extends ContainerNf<Vectorf> {

    /**
     * Create a new `Vectorf` with a predefined dimension count of 1 and a specified
     * size.
     * 
     * @param size the size of the vector
     */
    protected Vectorf(int size) {
        super(1, new int[] { size });
        this.content = new float[size];
    }

    /**
     * Create a new `Vectorf` with a predefined dimension count of 1 a specified
     * size and some initial data (content array).
     * 
     * @param size    the size of the vector
     * @param content the initial data
     */
    protected Vectorf(int size, float[] content) {
        super(1, new int[] { size }, content);
    }

    /**
     * Retrieve data at position i.
     * 
     * @param i index
     * @return data(i)
     */
    public float get(int i) {
        return content[i];
    }

    @Override
    public Vectorf add(Vectorf container) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Vectorf add(float value) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Vectorf sub(Vectorf container) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Vectorf sub(float value) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public float norm() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Vectorf normalize() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Vectorf product(Vectorf container) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Vectorf product(float value) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Vectorf divide(float value) {
        // TODO Auto-generated method stub
        return null;
    }

}
