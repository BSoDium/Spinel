package io.github.spinel.math;

/**
 * A Vectorf is a vector of floats. It inherits from ContainerNf and has 1
 * dimension, its size can vary, depending on whether it is a Vector2f, a
 * Vector3f or a Vector4f for example.
 */
public abstract class VectorNf<V extends VectorNf<V>> extends ContainerNf<V> {

    /**
     * Create a new `Vectorf` with a predefined dimension count of 1 and a specified
     * size.
     * 
     * @param size the size of the vector
     */
    protected VectorNf(int size) {
        super(1, new int[] { size });
    }

    /**
     * Create a new `Vectorf` with a predefined dimension count of 1 a specified
     * size and some initial data (content array).
     * 
     * @param size    the size of the vector
     * @param content the initial data
     */
    protected VectorNf(int size, float[] content) {
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

    /**
     * Set data at position i.
     * 
     * @param value data(i)
     * @param i     column index
     */
    public void set(float value, int i) {
        super.set(value, i);
    }

    /**
     * Copy data from one vector to another.
     * 
     * @param value data
     */
    public void set(V value) {
        super.setContent(value.content);
    }
}
