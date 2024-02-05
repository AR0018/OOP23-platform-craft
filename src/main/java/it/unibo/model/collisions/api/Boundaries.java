package it.unibo.model.collisions.api;

import it.unibo.model.physics.api.Position;

/**
 * Models the geometric boundaries of a CollisionBox.
 */
public interface Boundaries {

    //TODO: there may be a necessity to add a method: boolean intersectsLine(Position a, Position b)

    /**
     * @param other
     * @return true if the current boundaries intersect the input, false otherwise
     */
    boolean intersects(Boundaries other);

    /**
     * @param position the position to check
     * @return true if the input position is inside the polygon of the boundaries
    */
    boolean contains(Position position);

    //TODO: add method List<Position> getVertices()
}
