package it.unibo.model.collisions.api;

import it.unibo.model.entities.api.GameEntity;

/**
 * Models a collision with a GameEntity.
 */
public interface EntityCollision extends Collision {
    /**
     * Returns the GameEntity that caused the collision.
     * @return the GameEntity
     */
    GameEntity getGameEntity();
}
