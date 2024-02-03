package it.unibo.model.collisions.api;

import it.unibo.model.entities.api.GameEntity;
import it.unibo.model.physics.api.Direction;

/**
 * Contains informations about the collision
 */

public interface Collision {
    
    /**
     * Returns the direction where the object is touched
     */
    Direction getDirection();

    /**
     * Returns the GameObject that caused the collision
     */
    GameEntity getGameObject();

}
