package it.unibo.model.collisions.api;

import it.unibo.model.entities.api.GameEntity;
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

    /**
     * Returns the GameEntity that caused the collision.
     * @return the GameEntity
     */
    GameEntity getGameObject();

}
