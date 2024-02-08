package it.unibo.model.physics.impl;

import it.unibo.model.entities.api.GameEntity;
import it.unibo.model.physics.api.Position;
import it.unibo.model.physics.api.SpeedLevels;

/**
 * A Physics which implements a constantly accelerated movement on the x axis.
 * This Physics can also be configured to implement gravity.
 */
public class AcceleratedPhysics extends LinearPhysics {

    private static final double ACCELERATION = 0.1;
    private final boolean falling;
    private boolean hasAccelerated;

    /**
     * @param entity the GameEntity affected by this Physics
     * @param speedLevelX the desired speed on the X axis
     * @param speedLevelY the desired speed on the Y axis
     * @param bouncingX true if this Physics needs to bounce in case of a collision on the x axis
     * @param bouncingY true if this Physics needs to bounce in case of a collision on the y axis
     * @param falling true if this Physics needs to apply gravity
     */
    public AcceleratedPhysics(
        final GameEntity entity,
        final SpeedLevels speedLevelX,
        final SpeedLevels speedLevelY,
        final boolean bouncingX,
        final boolean bouncingY,
        final boolean falling) {
        super(entity, speedLevelX, speedLevelY, bouncingX, bouncingY);
        this.falling = falling;
        this.hasAccelerated = false;
    }

    /**
     * Calls the LinearPhysics' version of calculateMovement and then decelerates on the x axis
     * and accelerates downwards on the y axis.
     * The acceleration on y is uncapped (it accelerates indefinitely)
     */
    @Override
    public Position calculateMovement() {
        Position pos = super.calculateMovement();
        if (!this.hasAccelerated) {
            this.setVelocityX(0);
            this.hasAccelerated = false;
        }
        if (this.falling) {
            super.setVelocityY(super.getVelocity().getY() + ACCELERATION);
        }
        return pos;
    }

    /**
     * Applies an acceleration whose direction is the same as the specified velocity.
     * This implies that if the object is moving in the opposite direction, it decelerates the object
     * until the speed is reset and then it accelerates it in the specified direction.
     * The object is accelerated up to a maximum amount of xVelocity.
     */
    @Override
    protected void setVelocityX(final double xVelocity) {
        double newVel = super.getVelocity().getX();
        if (xVelocity > 0) {
            newVel += ACCELERATION;
            if (newVel > xVelocity) {
                newVel = xVelocity;
            }
        } else if (xVelocity < 0) {
            newVel -= ACCELERATION;
            if (newVel < xVelocity) {
                newVel = xVelocity;
            }
        } else {    //If the velocity passed is 0, it decelerates the object. Used in calculateMovement.
            if (newVel > 0) {
                newVel -= ACCELERATION;
                newVel = newVel < 0 ? 0 : newVel; //If newVel gets below 0, it is set to 0
            } else if (newVel < 0) {
                newVel += ACCELERATION;
                newVel = newVel > 0 ? 0 : newVel; //If newVel gets above 0, it is set to 0
            }
        }
        super.setVelocityX(newVel);
        this.hasAccelerated = true;
    }

}
