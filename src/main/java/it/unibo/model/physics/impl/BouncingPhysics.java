package it.unibo.model.physics.impl;

import it.unibo.model.entities.api.GameEntity;
import it.unibo.model.physics.api.SpeedLevels;

/**
 * A type of Physics where the speed in a certain direction
 * is flipped on the axis in case of a collision in said direction.
 */
public class BouncingPhysics extends LinearPhysics {

    /**
     * @param entity the GameEntity affected by this Physics
     * @param speedLevelX the desired speed on the X axis
     * @param speedLevelY the desired speed on the Y axis
     */
    public BouncingPhysics(final GameEntity entity, final SpeedLevels speedLevelX, final SpeedLevels speedLevelY) {
        super(entity, speedLevelX, speedLevelY);
    }

    @Override
    protected final void handleCollisionX() {
        super.setVelocityX(-super.getVelocity().getX());
    }

    @Override
    protected final void handleCollisionY() {
        super.setVelocityY(-super.getVelocity().getY());
    }

}
