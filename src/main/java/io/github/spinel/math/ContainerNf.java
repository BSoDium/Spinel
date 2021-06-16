package io.github.spinel.math;

import java.util.Arrays;

import io.github.spinel.exceptions.IncorrectDimensionError;

/**
 * A container is a mathematical object which can contain n-dimensional data
 * such as matrices or vectors.
 */
public abstract class ContainerNf<C extends ContainerNf<C>> {
    /** The data inside the container. */
    protected float[] content;
    /** The number of dimensions of the container. */
    protected final int dim;
    /** The size of each dimension inside the container. */
    protected final int[] size;
    /** An array containing precomputed values used in the get() method loop. */
    private int[] coefs;

    // TODO : javadoc
    protected ContainerNf(int dim, int[] size) {
        if (size.length != dim) {
            throw new IncorrectDimensionError(String
                    .format("Illegal size array length provided in ContainerNf constructor call with dim = %d", dim));
        } else if (dim <= 0) {
            throw new IncorrectDimensionError("Cannot create ContainerNf with negative or zero dimension count");
        }
        this.dim = dim;
        this.size = size;

        // initialize content
        int contentLength = 1;
        for (int s : size) {
            contentLength *= s;
        }
        content = new float[contentLength];
        computeCoefs();
    }

    // TODO : javadoc
    protected ContainerNf(int dim, int[] size, float[] content) {
        this(dim, size);
        setContent(content);
    }

    /**
     * Precompute the values used in the get() method.
     */
    private void computeCoefs() {
        coefs = new int[dim];
        for (int i = 0; i < dim; i++) {
            coefs[i] = 1;
            for (int j = 0; j < i; j++) {
                coefs[i] *= size[j];
            }
        }
    }

    /**
     * Retrieve data from the container.
     * 
     * @param args the indices at which the data should be retrieved
     * @return
     */
    protected float get(int... args) {
        if (args.length == dim) {
            int index = 0;
            for (int i = 0; i < dim; i++) {
                index += args[i] * coefs[i];
            }
            return content[index];
        } else {
            throw new IncorrectDimensionError(String.format(
                    "Illegal argument count when calling ContainerNf.get() : %d arguments provided ", args.length));
        }
    }

    /**
     * Retrieve raw unformatted data.
     * 
     * @return content array
     */
    public float[] getContent() {
        return content;
    }

    /**
     * Set data at coordinates (args) in the container.
     * 
     * @param value the value which should be set
     * @param args  the coordinates at which the data should be written
     */
    protected void set(float value, int... args) {
        if (args.length == dim) {
            int index = 0;
            for (int i = 0; i < dim; i++) {
                index += args[i] * coefs[i];
            }
            content[index] = value;
        } else {
            throw new IncorrectDimensionError(String.format(
                    "Illegal argument count when calling ContainerNf.get() : %d arguments provided ", args.length));
        }
    }

    /**
     * Overwrite the container's raw data with an array of floats.
     * 
     * @param content an array of floats - its length must be equal to the product
     *                of the container's size array elements
     */
    public void setContent(float[] content) {
        if (content.length == this.content.length) {
            this.content = content;
        } else {
            throw new IncorrectDimensionError(
                    String.format("Illegal content length in setContent call : expected %d, got %d",
                            this.content.length, content.length));
        }
    }

    /**
     * Add the current container instance to another one.
     * 
     * @param container the container which should be added
     * @return this + container
     */
    public C add(C container) {
        C output = copy();
        float[] addedContent = container.getContent();
        float[] baseContent = content.clone();
        if (container.getDim() == dim && Arrays.equals(container.getSize(), size)) {
            for (int i = 0; i < content.length; i++) {
                baseContent[i] += addedContent[i];
            }
            output.setContent(baseContent);
        } else {
            throw new IncorrectDimensionError("Cannot add two containers of incompatible sizes / dimensions");
        }
        return output;
    }

    /**
     * Add a float value to the current container instance.
     * 
     * @param value the value which should be added
     * @return this + value
     */
    public C add(float value) {
        C output = copy();
        float[] baseContent = content.clone();
        for (int i = 0; i < content.length; i++) {
            baseContent[i] += value;
        }
        output.setContent(baseContent);
        return output;
    }

    /**
     * Substract a container from the current container instance.
     * 
     * @param container the container which should be substracted
     * @return this - container
     */
    public C sub(C container) {
        C output = copy();
        float[] addedContent = container.getContent();
        float[] baseContent = content.clone();
        if (container.getDim() == dim && Arrays.equals(container.getSize(), size)) {
            for (int i = 0; i < content.length; i++) {
                baseContent[i] -= addedContent[i];
            }
            output.setContent(baseContent);
        } else {
            throw new IncorrectDimensionError("Cannot add two containers of incompatible sizes / dimensions");
        }
        return output;
    }

    /**
     * Substract a float value from the current container instance.
     * 
     * @param value the value which should be substracted
     * @return this - value
     */
    public C sub(float value) {
        C output = copy();
        float[] baseContent = content.clone();
        for (int i = 0; i < content.length; i++) {
            baseContent[i] -= value;
        }
        output.setContent(baseContent);
        return output;
    }

    /**
     * Calculate the norm of the container.
     * 
     * @return norm
     */
    public abstract float norm();

    /**
     * Normalize the container and return it.
     * 
     * @return normalized container
     */
    public abstract C normalize();

    /**
     * Calculate the product between the current container instance and another
     * container.
     * 
     * @param container the container by which the instance is to be multiplied
     * @return this * container
     */
    public abstract C product(C container);

    /**
     * Calculate the product between the current container instance and a float.
     * 
     * @param value the float by which the instance is to be multiplied
     * @return this * value
     */
    public C product(float value) {
        C output = copy();
        float[] baseContent = content.clone();
        for (int i = 0; i < content.length; i++) {
            baseContent[i] *= value;
        }
        output.setContent(baseContent);
        return output;
    }

    public C divide(C container) {
        C output = copy();
        float[] addedContent = container.getContent();
        float[] baseContent = content.clone();
        if (container.getDim() == dim && Arrays.equals(container.getSize(), size)) {
            for (int i = 0; i < content.length; i++) {
                baseContent[i] /= addedContent[i];
            }
            output.setContent(baseContent);
        } else {
            throw new IncorrectDimensionError("Cannot add two containers of incompatible sizes / dimensions");
        }
        return output;
    }

    /**
     * Calculate the quotient between the current container instance and a float.
     * 
     * @param value the float by which the instance is to be divided
     * @return this / value
     */
    public C divide(float value) {
        C output = copy();
        float[] baseContent = content.clone();
        for (int i = 0; i < content.length; i++) {
            baseContent[i] /= value;
        }
        output.setContent(baseContent);
        return output;
    }

    /**
     * Create a deep copy of the current container instance.
     * 
     * @return container copy
     */
    public abstract C copy();

    public int getDim() {
        return dim;
    }

    public int[] getSize() {
        return size;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(coefs);
        result = prime * result + Arrays.hashCode(content);
        result = prime * result + dim;
        result = prime * result + Arrays.hashCode(size);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ContainerNf<C> other = (ContainerNf<C>) obj;
        if (!Arrays.equals(coefs, other.coefs))
            return false;
        if (!Arrays.equals(content, other.content))
            return false;
        if (dim != other.dim)
            return false;
        if (!Arrays.equals(size, other.size))
            return false;
        return true;
    }

}
