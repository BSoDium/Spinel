package io.github.spinel.math;

public class TransferMatrix4f {

    /**
     * Generate translation matrix.
     * 
     * @param translate translation vector
     * @return translation matrix
     */
    public static Matrix4f translation(Vector3f translate) {
        Matrix4f output = Matrix4f.id();
        output.set(translate.getX(), 3, 0);
        output.set(translate.getY(), 3, 1);
        output.set(translate.getZ(), 3, 2);
        return output;
    }

    /**
     * Generate rotation matrix.
     * 
     * @param angle rotation angle
     * @param axis  rotation axis
     * @return rotation matrix
     */
    public static Matrix4f rotation(float angle, Vector3f axis) {
        Matrix4f output = Matrix4f.id();

        float costheta = (float) Math.cos(Math.toRadians(angle));
        float sintheta = (float) Math.sin(Math.toRadians(angle));
        float umcostheta = 1 - costheta;
        output.set(costheta + axis.getX() * axis.getX() * umcostheta, 0, 0);
        output.set(axis.getX() * axis.getY() * umcostheta - axis.getZ() * sintheta, 0, 1);
        output.set(axis.getX() * axis.getZ() * umcostheta + axis.getY() * sintheta, 0, 2);
        output.set(axis.getY() * axis.getX() * umcostheta + axis.getZ() * sintheta, 1, 0);
        output.set(costheta + axis.getY() * axis.getY() * umcostheta, 1, 1);
        output.set(axis.getY() * axis.getZ() * umcostheta - axis.getX() * sintheta, 1, 2);
        output.set(axis.getZ() * axis.getX() * umcostheta - axis.getY() * sintheta, 2, 0);
        output.set(axis.getZ() * axis.getY() * umcostheta + axis.getX() * sintheta, 2, 1);
        output.set(costheta + axis.getZ() * axis.getZ() * umcostheta, 2, 2);
        return output;
    }

    /**
     * Generate a scaling matrix.
     * 
     * @param factor scaling factor
     * @return scaling matrix
     */
    public static Matrix4f scale(Vector3f factor) {
        Matrix4f output = Matrix4f.id();

        output.set(factor.getX(), 0, 0);
        output.set(factor.getY(), 1, 1);
        output.set(factor.getZ(), 2, 2);

        return output;
    }

    /**
     * Generate a scaling matrix.
     * 
     * @param position position vector
     * @param rotation rotation vector
     * @param scale    scaling factor
     * @return scaling matrix
     */
    public static Matrix4f transform(Vector3f position, Vector3f rotation, Vector3f scale) {
        Matrix4f output;

        Matrix4f tMatrix = translation(position);
        Matrix4f rXMatrix = rotation(rotation.getX(), new Vector3f(1, 0, 0));
        Matrix4f rYMatrix = rotation(rotation.getY(), new Vector3f(0, 1, 0));
        Matrix4f rZMatrix = rotation(rotation.getZ(), new Vector3f(0, 0, 1));
        Matrix4f sMatrix = scale(scale);

        Matrix4f rMatrix = rXMatrix.product(rYMatrix.product(rZMatrix));

        output = tMatrix.product(rMatrix.product(sMatrix));

        return output;
    }

    /**
     * Generate a projection matrix for the specified parameters.
     * 
     * @param aspect   aspect ratio
     * @param fov      field of view
     * @param nearDist near distance
     * @param farDist  far distance
     * @return projection matrix
     */
    public static Matrix4f project(float aspect, float fov, float nearDist, float farDist) {
        Matrix4f output = Matrix4f.zero();

        float tanFOV = (float) Math.tan(Math.toRadians(fov / 2));
        float range = farDist - nearDist;

        output.set(1.0f / (aspect * tanFOV), 0, 0);
        output.set(1.0f / tanFOV, 1, 1);
        output.set(-(farDist + nearDist) / range, 2, 2);
        output.set(-2 * farDist * nearDist / (farDist - nearDist), 3, 2);
        output.set(-1.0f, 2, 3);

        return output;
    }

    /**
     * Generate a view matrix from the provided position and rotation.
     * 
     * @param position position vector (x, y, z)
     * @param rotation rotation vector (h, p, r)
     * @return view matrix
     */
    public static Matrix4f view(Vector3f position, Vector3f rotation) {
        Matrix4f output;

        Vector3f invertedPos = new Vector3f(-position.getX(), -position.getY(), -position.getZ());
        // translation matrix
        Matrix4f tMatrix = translation(invertedPos);
        Matrix4f rXMatrix = rotation(rotation.getX(), new Vector3f(1, 0, 0));
        Matrix4f rYMatrix = rotation(rotation.getY(), new Vector3f(0, 1, 0));
        Matrix4f rZMatrix = rotation(rotation.getZ(), new Vector3f(0, 0, 1));
        // rotation matrix
        Matrix4f rMatrix = rZMatrix.product(rYMatrix.product(rXMatrix));

        output = tMatrix.product(rMatrix);

        return output;
    }

}
