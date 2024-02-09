package it.unibo.model.physics.api;

/**
 * Defines the possible configurations for the movement speed of an object.
 */
public enum SpeedLevels {
    /**
     * Fast movement speed.
     */
    FAST(1),
    /**
     * Medium movement speed.
     */
    MEDIUM(0.6),
    /**
     * Slow movement speed.
     */
    SLOW(0.3);

    private double value;

    SpeedLevels(final double value) {
        this.value = value;
    }

    /**
     * @return the speed value associated to this speed level
     */
    public double getValue() {
        return this.value;
    }
}
