package it.unibo.model.collisions.api;

import org.locationtech.jts.geom.Polygon;

/**
 * Models the geometric boundaries of a CollisionBox
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
}
