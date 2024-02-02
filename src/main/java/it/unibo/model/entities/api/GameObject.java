package it.unibo.model.entities.api;

import org.locationtech.jts.geom.CoordinateXY;

/**
 * Models an entity in the game.
 * Every object has a position, a CollisionBox and a type of Physics.
 */
public interface GameObject {

    /**
     * Updates the state of the object, according to its desired behaviour.
     */
    void updateState();

    /**
     * Sets the position of the character to the given input.
     * @param position the input position
     */
    void setPosition(CoordinateXY position);

    /**
     * @return the current position of the character
     */
    CoordinateXY getPosition();
    
}