package it.unibo.model.entities.api;

import java.util.Set;

import it.unibo.common.api.EntityType;
import it.unibo.model.collisions.api.Boundaries;
import it.unibo.model.collisions.api.Collision;
import it.unibo.model.physics.api.Position;

/**
 * Models an entity in the game.
 * Every entity has a position, a CollisionBox and a type of Physics.
 */
public interface GameEntity {

    /**
     * @return the current position of the entity
     */
    Position getPosition();

    /**
     * Check if the entity is alive or not.
     * @return true if the entity is alive, false otherwise
     */
    boolean isAlive();

    /**
     * Updates the state of the entity, according to its desired behaviour.
     */
    void updateState();

    /**
     * @return the type of this entity
     */
    EntityType getType();

    /**
     * @return all the current collisions of the entity
     */
    Set<Collision> getCollisions();

    /**
     * @return the boundaries of the game entity
     */
    Boundaries getBoundaries();
}
