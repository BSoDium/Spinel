package io.github.spinel.math;

/**
 * A Pointf is a position vector of floats. It inherits from ContainerNf and has
 * 1 dimension, its size can vary, depending on whether it is a Point2f, a
 * Point3f or a Point4f for example.
 */
public abstract class Pointf extends ContainerNf<Pointf> {

    /**
     * Create a new `Pointf` with a predefined dimension count of 1 and a specified
     * size.
     * 
     * @param size the size of the position vector
     */
    protected Pointf(int size) {
        super(1, new int[] { size });
        this.content = new float[size];
    }

    /**
     * Create a new `Pointf` with a predefined dimension count of 1 a specified
     * size, and some initial data (content array).
     * 
     * @param size    the size of the position vector
     * @param content the initial data
     */
    protected Pointf(int size, float[] content) {
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
    public Pointf add(Pointf container) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Pointf add(float value) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Pointf sub(Pointf container) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Pointf sub(float value) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public float norm() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Pointf normalize() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Pointf product(Pointf container) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Pointf product(float value) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Pointf divide(float value) {
        // TODO Auto-generated method stub
        return null;
    }

}
