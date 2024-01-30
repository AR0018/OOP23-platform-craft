package it.unibo.model.physics.api;

/**
 * Models the physics of a GameObject, which calculates the way a GameObject moves in a 2D space.
 */
public interface Physics {

    /**
     * Calculates the movement of the GameObject in the specified direction,
     * modifying its position accordingly.
     * This method also takes into account the state of the collision of the GameObject
     * (if there is a collision in a certain direction, it sets the object's speed in that direction to 0).
     * @param dir the direction of the movement
     */
    void calculateMovement(Direction dir);
}