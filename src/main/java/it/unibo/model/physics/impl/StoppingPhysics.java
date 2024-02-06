package it.unibo.model.physics.impl;

import it.unibo.model.entities.api.GameEntity;
import it.unibo.model.physics.api.Direction;
import it.unibo.model.physics.api.SpeedLevels;

/**
 * A type of Physics where the speed in a certain direction
 * is set to 0 in case of a collision in said direction.
 */
public class StoppingPhysics extends LinearPhysics {

    /**
     * @param entity the GameEntity affected by this Physics
     * @param speedLevelX the desired speed on the X axis
     * @param speedLevelY the desired speed on the Y axis
     */
    public StoppingPhysics(final GameEntity entity, final SpeedLevels speedLevelX, final SpeedLevels speedLevelY) {
        super(entity, speedLevelX, speedLevelY);
    }

    /**
     * Handles the collision by setting the velocity to 0 if the
     * movement matches the direction of the collision.
     */
    @Override
    protected void handleCollision(final Direction dir) {
        switch (dir) {
            case UP:
                if (super.getVelocity().getY() < 0) {
                    stopOnY();
                }
                break;
            case DOWN:
                if (super.getVelocity().getY() > 0) {
                    stopOnY();
                }
                break;
            case LEFT:
                if (super.getVelocity().getX() < 0) {
                    stopOnX();
                }
                break;
            case RIGHT:
                if (super.getVelocity().getX() > 0) {
                    stopOnX();
                }
                break;
            default:
                throw new IllegalStateException("Invalid value for Direction");
        }
    }

}
