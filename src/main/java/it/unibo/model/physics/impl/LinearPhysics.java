package it.unibo.model.physics.impl;

import java.util.Objects;

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
    private final SpeedLevels maxSpeedOnX;
    private final SpeedLevels maxSpeedOnY;
    private final boolean bouncingX;
    private final boolean bouncingY;

    /**
     * @param entity the GameEntity affected by this Physics
     * @param speedLevelX the desired speed on the X axis
     * @param speedLevelY the desired speed on the Y axis
     * @param bouncingX true if this Physics needs to bounce in case of a collision on the x axis
     * @param bouncingY true if this Physics needs to bounce in case of a collision on the y axis
     * @throws NullPointerException if a null parameter is passed
     */
    public LinearPhysics(
            final GameEntity entity,
            final SpeedLevels speedLevelX,
            final SpeedLevels speedLevelY,
            final boolean bouncingX,
            final boolean bouncingY) {
        this.entity = Objects.requireNonNull(entity);
        velocity = new Position2D(0, 0);
        this.maxSpeedOnX = Objects.requireNonNull(speedLevelX);
        this.maxSpeedOnY = Objects.requireNonNull(speedLevelY);
        this.bouncingX = bouncingX;
        this.bouncingY = bouncingY;
    }

    /**
     * Modifies the position according to a linear movement.
     * This method also takes into account the state of the collision of the GameEntity
     * (if there is a collision in a certain direction, it modifies the object's speed in that direction).
     * 
     * This method can be overridden by modifiying the velocity of the physics after calling this method.
     * @return the new position of the entity after calculating the movement
     */
    @Override
    public Position calculateMovement() {
        this.entity.getCollisions().stream()
                .map(Collision::getDirection)
                .forEach(this::handleCollision);
        return new Position2D(
                entity.getPosition().getX() + this.velocity.getX(),
                entity.getPosition().getY() + this.velocity.getY());
    }

    /*
     * Defines the behaviour of this Physics in case of a collision.
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
                throw new IllegalArgumentException("Invalid value for Direction");
        }
    }

    /*
     * Defines the behaviour in case there is a collision in the same
     * direction on the x axis as the movement.
     * If bouncing is true, flips the speed of the object, otherwise it stops the object
     * in case of a collision.
     */
    private void handleCollisionX() {
        if (this.bouncingX) {
            this.setVelocityX(-this.velocity.getX());
        } else {
            this.stopOnX();
        }
    }

    /*
     * Defines the behaviour in case there is a collision in the same
     * direction on the y axis as the movement.
     * If bouncing is true, flips the speed of the object, otherwise it stops the object
     * in case of a collision.
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
                throw new IllegalArgumentException("Invalid value for Direction");
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
