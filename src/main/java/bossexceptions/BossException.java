package bossexceptions;

/**
 * Creates exceptions originating from Boss program.
 */
public class BossException extends Exception {

    /**
     * Passes error message to Exception class.
     *
     * @param message Message string.
     */
    public BossException(String message) {
        super(message);
    }
}
