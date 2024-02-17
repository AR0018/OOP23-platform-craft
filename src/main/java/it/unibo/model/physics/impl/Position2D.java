package it.unibo.model.physics.impl;

import org.locationtech.jts.geom.Coordinate;

import it.unibo.model.physics.api.Position;

/**
 * A position in a 2D space.
 */
public final class Position2D extends Coordinate implements Position {

    //Needed, since Coordinate is Serializable
    private static final long serialVersionUID = 0;

    /**
     * @param x the x coordinate of this position
     * @param y the y coordinate of this position
    */
    public Position2D(final double x, final double y) {
        super(x, y);
    }

    /**
     * Confronts two positions by controlling whether the difference between
     * the coordinates is lower than the tolerance.
     */
    @Override
    public boolean equals(final Object other) {
        if (other == null || this.getClass() != other.getClass()) {
            return false;
        }
        final Position2D pos = (Position2D) other;
        final double tolerance = 0.1;
        return Math.max(this.x, pos.x) - Math.min(this.x, pos.x) < tolerance
        && Math.max(this.y, pos.y) - Math.min(this.y, pos.y) < tolerance;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

}
