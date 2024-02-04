package it.unibo.model.physics.api;

import org.locationtech.jts.geom.CoordinateXY;

/**
 * A position in a 2-Dimensional space.
 */
public interface Position {

    /**
     * @return the coordinates of this position
     */
    CoordinateXY getCoordinates();
}
