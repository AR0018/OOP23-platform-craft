package it.unibo.model.physics.impl;

import it.unibo.model.collisions.api.Collision;
import it.unibo.model.entities.api.GameEntity;
import it.unibo.model.physics.api.Direction;
import it.unibo.model.physics.api.Physics;
import it.unibo.model.physics.api.Position;
import it.unibo.model.physics.api.SpeedLevels;

/**
 * A Physics which implements a linear movement.
 * In case of a collision in a certain direction, the velocity
 * in that direction is set to 0.
 */
public class LinearPhysics implements Physics {

    private final GameEntity entity;
    private Position velocity;
    private SpeedLevels maxSpeedOnX;
    private SpeedLevels maxSpeedOnY;
    private boolean bouncingX;
    private boolean bouncingY;

    /**
     * @param entity the GameEntity affected by this Physics
     * @param speedLevelX the desired speed on the X axis
     * @param speedLevelY the desired speed on the Y axis
     * @param bouncingX true if this Physics needs to bounce in case of a collision on the x axis
     * @param bouncingY true if this Physics needs to bounce in case of a collision on the y axis
     */
    public LinearPhysics(
            final GameEntity entity,
            final SpeedLevels speedLevelX,
            final SpeedLevels speedLevelY,
            final boolean bouncingX,
            final boolean bouncingY) {
        this.entity = entity;
        velocity = new Position2D(0, 0);
        // TODO: decide whether to keep this implementation or store the values of speed
        // directly in SpeedLevels
        this.maxSpeedOnX = speedLevelX;
        this.maxSpeedOnY = speedLevelY;
        this.bouncingX = bouncingX;
        this.bouncingY = bouncingY;
    }

    @Override
    public Position calculateMovement() {
        this.entity.getCollisions().stream()
                .map(Collision::getDirection)
                .forEach(this::handleCollision);
        return new Position2D(
                entity.getPosition().getX() + this.velocity.getX(),
                entity.getPosition().getY() + this.velocity.getY());
    }

    /**
     * Defines the behaviour of this Physics in case of a collision.
     * 
     * @param dir the direction of the collision
     */
    private void handleCollision(final Direction dir) {
        switch (dir) {
            case UP:
                if (velocity.getY() < 0) {
                    handleCollisionY();
                }
                break;
            case DOWN:
                if (velocity.getY() > 0) {
                    handleCollisionY();
                }
                break;
            case LEFT:
                if (velocity.getX() < 0) {
                    handleCollisionX();
                }
                break;
            case RIGHT:
                if (velocity.getX() > 0) {
                    handleCollisionX();
                }
                break;
            default:
                throw new IllegalStateException("Invalid value for Direction");
        }
    }

    /**
     * Defines the behaviour in case there is a collision in the same
     * direction on the x axis as the movement.
     * This implementation sets the speed on the x axis to 0 in case of a collision.
     */
    private void handleCollisionX() {
        if (this.bouncingX) {
            this.setVelocityX(-this.velocity.getX());
        } else {
            this.stopOnX();
        }
    }

    /**
     * Defines the behaviour in case there is a collision in the same
     * direction on the y axis as the movement.
     * This implementation sets the speed on the y axis to 0 in case of a collision.
     */
    private void handleCollisionY() {
        if (this.bouncingY) {
            this.setVelocityY(-this.velocity.getY());
        } else {
            this.stopOnY();
        }
    }

    @Override
    public final void setMovement(final Direction direction) {
        switch (direction) {
            case UP:
                setVelocityY(-this.maxSpeedOnY.getValue());
                break;
            case DOWN:
                setVelocityY(this.maxSpeedOnY.getValue());
                break;
            case LEFT:
                setVelocityX(-this.maxSpeedOnX.getValue());
                break;
            case RIGHT:
                setVelocityX(this.maxSpeedOnX.getValue());
                break;
            default:
                throw new IllegalStateException("Invalid value for Direction");
        }
    }

    /**
     * Sets the velocity of this Physics on the x axis.
     * 
     * @param xVelocity the velocity to set
     */
    protected void setVelocityX(final double xVelocity) {
        this.velocity = new Position2D(xVelocity, this.velocity.getY());
    }

    /**
     * Sets the velocity of this Physics on the y axis.
     * 
     * @param yVelocity the velocity to set
     */
    protected final void setVelocityY(final double yVelocity) {
        this.velocity = new Position2D(this.velocity.getX(), yVelocity);
    }

    @Override
    public final void stopOnX() {
        this.velocity = new Position2D(0, this.velocity.getY());
    }

    @Override
    public final void stopOnY() {
        this.velocity = new Position2D(this.velocity.getX(), 0);
    }

    /**
     * @return the velocity of this Physics
     */
    protected final Position getVelocity() {
        return new Position2D(this.velocity.getX(), this.velocity.getY());
    }

}
