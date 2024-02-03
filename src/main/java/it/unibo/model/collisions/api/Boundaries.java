package it.unibo.model.collisions.api;

import org.locationtech.jts.geom.CoordinateXY;
import org.locationtech.jts.geom.Polygon;

/**
 * Models the geometric boundaries of a CollisionBox.
 */
public interface Boundaries {

    /**
     * @return the polygon which represents the geometry of the boundaries
    */
    Polygon getPolygon();

    /**
     * @param other
     * @return true if the current boundaries intersect the input, false otherwise
     */
    boolean intersects(Boundaries other);

    /**
     * @param position the position to check
     * @return true if the input position is inside the polygon of the boundaries
    */
    boolean contains(CoordinateXY position);
}
