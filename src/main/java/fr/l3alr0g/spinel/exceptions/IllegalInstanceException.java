package fr.l3alr0g.spinel.exceptions;

/**
 * Thrown when attempting to instanciate a class which only allows one instance
 * of itself.
 */
public class IllegalInstanceException extends RuntimeException {

    public IllegalInstanceException(String instanceName) {
        super(String.format("Attempting to spawn multiple instances of %s", instanceName));
    }
}
