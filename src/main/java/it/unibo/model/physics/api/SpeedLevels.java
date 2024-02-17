package it.unibo.model.physics.api;

/**
 * Defines the possible configurations for the movement speed of an object.
 */
public enum SpeedLevels {

    /**
     * Character movement speed.
     */
    CHARACTER_SPEED(5),
    /**
     * Fast movement speed.
     */
    FAST(5),
    /**
     * Medium movement speed.
     */
    MEDIUM(3),
    /**
     * Slow movement speed.
     */
    SLOW(1.5);

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
