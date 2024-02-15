package it.unibo.model.collisions.api;

import it.unibo.model.physics.api.Direction;

/**
 * Contains informations about the collision.
 */
public interface Collision {
    /**
     * Returns the direction where the entity is touched.
     * @return the direction of the collision
     */
    Direction getDirection();
}
