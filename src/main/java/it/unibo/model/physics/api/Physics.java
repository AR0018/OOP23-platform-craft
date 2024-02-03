package it.unibo.model.physics.api;

/**
 * Models the physics of a GameObject, which calculates the way a GameObject moves in a 2D space.
 */
public interface Physics {

    /**
     * Calculates the movement of the GameObject in the direction specified by setMovement,
     * modifying its position accordingly.
     * This method also takes into account the state of the collision of the GameObject
     * (if there is a collision in a certain direction, it sets the object's speed in that direction to 0).
     */
    void calculateMovement();

    /**
     * Sets the movement of the object to the specified direction.
     * The movement on the X axis is treated separately from the movement on the Y axis.
     * For instance, calling this method with UP and LEFT will set the X movement to LEFT
     * and the Y movement to UP, so the object will move UP-LEFT.
     * @param direction
     */
    void setMovement(Direction direction);

    /**
     * Sets the speed on the X axis to 0.
     */
    void stopOnX();

    /**
     * Sets the speed on the Y axis to 0.
     */
    void stopOnY();
}
