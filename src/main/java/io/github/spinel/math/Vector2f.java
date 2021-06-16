package io.github.spinel.math;

/**
 * A two-dimensional mathematical vector.
 */
public class Vector2f extends VectorNf<Vector2f> {

    /**
     * Create a new Vector2f with a predefined dimension count of 1, and a size of 1
     * by 3 without any initial data.
     */
    protected Vector2f() {
        super(2);
    }

    /**
     * Create a new Vector2f with a predefined dimension count of 1, and a size of 1
     * by 2. Provide the constructor with some initial data to start with.
     */
    protected Vector2f(float[] content) {
        super(2, content);
    }

    /**
     * Create a two-dimensional vector object.
     * 
     * @param x coordinate on the X axis
     * @param y coordinate on the Y axis
     */
    public Vector2f(float x, float y) {
        this(new float[] { x, y });
    }

    /**
     * Create a two-dimensional vector object.
     * 
     * @param vect vector input
     */
    public Vector2f(Vector2f vect) {
        this(vect.getContent());
    }

    public float getX() {
        return get(0);
    }

    public float getY() {
        return get(1);
    }

    public void setX(float x) {
        set(x, 0);
    }

    public void setY(float y) {
        set(y, 1);
    }

    @Override
    public float norm() {
        return (float) Math.sqrt(content[0] * content[0] + content[1] * content[1]);
    }

    @Override
    public Vector2f normalize() {
        float length = norm();
        return divide(new Vector2f(length, length));
    }

    /**
     * Dot product.
     * 
     * @param vec other vector
     * @return (this | vec)
     */
    public float dot(Vector2f vect) {
        return content[0] * vect.getX() + content[1] * vect.getY();
    }

    /**
     * Term by term product.
     * 
     * @param container other vector
     */
    @Override
    public Vector2f product(Vector2f container) {
        Vector2f output = copy();
        output.set(output.get(0) * container.get(0), 0);
        output.set(output.get(1) * container.get(1), 1);
        return output;
    }

    @Override
    public Vector2f copy() {
        return new Vector2f(this.content);
    }

}
