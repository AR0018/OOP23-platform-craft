package it.unibo.model.collisions.api;

import java.util.List;

import it.unibo.model.physics.api.Position;

/**
 * Models the geometric boundaries of a CollisionBox.
 */
public interface Boundaries {

    //TODO: there may be a necessity to add a method: boolean intersectsLine(Position a, Position b)

    /**
     * Checks if there's a line that intetsecate both Position a and b.
     * @param a first Position
     * @param b second Position
     * @return 
     */
    boolean intersectsLine(Position a, Position b);

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

    /**
     * @return the vertices of the rectangle represented by the boundaries
     */
    List<Position> getVertices();
}
