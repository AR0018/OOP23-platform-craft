package it.unibo.model.entities.api;

import org.locationtech.jts.geom.CoordinateXY;

/**
 * Models an entity in the game.
 * Every entity has a position, a CollisionBox and a type of Physics.
 */
public interface GameEntity {
    
    /**
     * Enum used to understand what is the condition of the different game entity
     */
    enum CONDITION{
        ALIVE,
        DEAD;
    }

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
     * Check if the enemy is alive or not
     * @return true if the enemy is dead
     */
    boolean isAlive();

    /**
     * Updates the state of the object, according to its desired behaviour.
     */
    void updateState();

    
}