package it.unibo.model.entities.api;

import org.locationtech.jts.geom.CoordinateXY;

import it.unibo.common.EntityType;

/**
 * Models an entity in the game.
 * Every entity has a position, a CollisionBox and a type of Physics.
 */
public interface GameEntity {

    /**
     * Sets the position of the character to the given input.
     * @param position the input position
     */
    void setPosition(CoordinateXY position);

    /**
     * @return the current position of the character
     */
    CoordinateXY getPosition();

    /**
     * Check if the entity is alive or not
     * @return true if the entity is dead
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
}