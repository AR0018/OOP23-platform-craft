package it.unibo.model.physics.impl;

import it.unibo.model.collisions.api.Collision;
import it.unibo.model.entities.api.GameEntity;
import it.unibo.model.physics.api.Direction;
import it.unibo.model.physics.api.Physics;
import it.unibo.model.physics.api.Position;
import it.unibo.model.physics.api.SpeedLevels;

/**
 * A Physics which implements a linear movement.
 */
public abstract class LinearPhysics implements Physics {

    private final GameEntity entity;
    private Position velocity;
    private Speed speedOnX;
    private Speed speedOnY;

    private enum Speed {
        FAST(1), MEDIUM(0.6), SLOW(0.3); //TODO: decide actual values

        private final double value;

        Speed(final double value) {
            this.value = value;
        }

        private double getValue() {
            return this.value;
        }
    }

    /**
     * @param entity the GameEntity affected by this Physics
     * @param speedLevelX the desired speed on the X axis
     * @param speedLevelY the desired speed on the Y axis
     */
    public LinearPhysics(final GameEntity entity, final SpeedLevels speedLevelX, final SpeedLevels speedLevelY) {
        this.entity = entity;
        velocity = new Position2D(0, 0);
        //TODO: decide whether to keep this implementation or store the values of speed directly in SpeedLevels
        this.speedOnX = switch(speedLevelX) {
            case SLOW -> Speed.SLOW;
            case MEDIUM -> Speed.MEDIUM;
            case FAST -> Speed.FAST;
        };
        this.speedOnY = switch(speedLevelY) {
            case SLOW -> Speed.SLOW;
            case MEDIUM -> Speed.MEDIUM;
            case FAST -> Speed.FAST;
        };
    }

    @Override
    public final Position calculateMovement() {
        this.entity.getCollisions().stream()
            .map(Collision::getDirection)
            .forEach(this::handleCollision);
            return new Position2D(
                entity.getPosition().getX() + this.velocity.getX(),
                entity.getPosition().getY() + this.velocity.getY()
                );
    }

    /**
     * Defines the behaviour of this Physics in case of a collision.
     * @param dir the direction of the collision
     */
    protected abstract void handleCollision(Direction dir);

    @Override
    public final void setMovement(final Direction direction) {
        switch (direction) {
            case UP:
                setVelocityY(-this.speedOnY.getValue());
                break;
            case DOWN:
                setVelocityY(this.speedOnY.getValue());
                break;
            case LEFT:
                setVelocityX(-this.speedOnX.getValue());
                break;
            case RIGHT:
                setVelocityX(this.speedOnX.getValue());
                break;
            default:
                throw new IllegalStateException("Invalid value for Direction");
        }
    }

    private void setVelocityX(final double xVelocity) {
        this.velocity = new Position2D(xVelocity, this.velocity.getY());
    }

    private void setVelocityY(final double yVelocity) {
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
