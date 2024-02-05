package it.unibo.model.physics.impl;

import org.locationtech.jts.geom.Coordinate;

import it.unibo.model.physics.api.Position;

/**
 * A position in a 2D space.
 */
public class Position2D extends Coordinate implements Position {
    /**
     * @param x the x coordinate of this position
     * @param y the y coordinate of this position
    */
    public Position2D(final double x, final double y) {
        super(x, y);
    }
}
