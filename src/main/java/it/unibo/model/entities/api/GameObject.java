package it.unibo.model.entities.api;

import java.awt.Point;
/**
 * Models an entity in the game.
 * Every object has a position, a CollisionBox and a type of Physics.
 */
public interface GameObject {

    /**
     * Updates the state of the object, according to its desired behaviour.
     */
    void updateState();

    void setPosition(Point position); //TODO: add a 2D Position as the input parameter. Throws IllegalArgumentException

    Point getPosition(); //TODO: add a 2D Position as the output
    
}