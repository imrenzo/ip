package bossexceptions;

/**
 * Thrown to indicate an error originating from methods in the Boss class.
 */
public class BossException extends Exception {
    public BossException(String message) {
        super(message);
    }
}
