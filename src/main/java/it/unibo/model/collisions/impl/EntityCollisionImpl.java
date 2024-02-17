package it.unibo.model.collisions.impl;

import java.util.Objects;

import it.unibo.model.collisions.api.EntityCollision;
import it.unibo.model.entities.api.GameEntity;
import it.unibo.model.physics.api.Direction;

/**
 * Implementation of the entity collision.
 */
public final class EntityCollisionImpl implements EntityCollision {

    private final GameEntity gameEntity;
    private final Direction direction;

    /**
     * @param gameEntity the game entity that caused the collision
     * @param direction the direction of the collision
     */
    public EntityCollisionImpl(final GameEntity gameEntity, final Direction direction) {
       this.gameEntity = gameEntity;
       this.direction = direction;
    }

    @Override
    public GameEntity getGameEntity() {
        return this.gameEntity;
    }

    @Override
    public Direction getDirection() {
        return this.direction;
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        final EntityCollision other = (EntityCollision) obj;
        return this.getDirection().equals(other.getDirection())
        && this.getGameEntity().equals(other.getGameEntity());
    }

    @Override
    public String toString() {
        return "EntityCollisionImpl[" + this.gameEntity + ", " + this.direction + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameEntity, direction);
    }
}
