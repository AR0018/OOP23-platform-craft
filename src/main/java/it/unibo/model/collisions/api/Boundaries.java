package it.unibo.model.collisions.api;

import java.util.List;

import it.unibo.model.physics.api.Position;

/**
 * Models the geometric boundaries of a CollisionBox.
 */
public interface Boundaries {

    /**
     * Checks if the current boudaries intersects the given line.
     * The input parameters are the two extreme points of the segment.
     * @param a the first point
     * @param b the second point
     * @return true if this boundaries intersects the line, false otherwise
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

    /**
     * @return the width of the boundaries
     */
    double getWidth();

    /**
     * @return the height of the boundaries
     */
<<<<<<< HEAD
    int getHeight();

    Position getLevelBoundaries();
=======
    double getHeight();
>>>>>>> master
}
