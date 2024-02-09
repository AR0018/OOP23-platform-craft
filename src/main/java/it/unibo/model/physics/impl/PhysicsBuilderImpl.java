package it.unibo.model.physics.impl;

import java.util.Objects;
import java.util.Optional;

import it.unibo.model.entities.api.GameEntity;
import it.unibo.model.physics.api.Physics;
import it.unibo.model.physics.api.PhysicsBuilder;
import it.unibo.model.physics.api.SpeedLevels;

/**
 * Implementation of a PhysicsBuilder.
 */
public final class PhysicsBuilderImpl implements PhysicsBuilder {

    private Optional<GameEntity> entity;
    private SpeedLevels speedX;
    private SpeedLevels speedY;
    private boolean bouncingX;
    private boolean bouncingY;
    private boolean acceleratedX;
    private boolean falling;

    /**
     * Constructor of a PhysicsBuilderImpl.
     */
    public PhysicsBuilderImpl() {
        this.entity = Optional.empty();
        this.speedX = SpeedLevels.MEDIUM;
        this.speedY = SpeedLevels.MEDIUM;
        this.bouncingX = false;
        this.bouncingY = false;
        this.acceleratedX = false;
        this.falling = false;
    }

    @Override
    public PhysicsBuilder setGameEntity(final GameEntity entity) {
        this.entity = Optional.of(Objects.requireNonNull(entity));
        return this;
    }

    @Override
    public PhysicsBuilder setSpeedOnX(final SpeedLevels speed) {
        this.speedX = Objects.requireNonNull(speed);
        return this;
    }

    @Override
    public PhysicsBuilder setSpeedOnY(final SpeedLevels speed) {
        this.speedY = Objects.requireNonNull(speed);
        return this;
    }

    @Override
    public PhysicsBuilder addBouncingOnX() {
        this.bouncingX = true;
        return this;
    }

    @Override
    public PhysicsBuilder addBouncingOnY() {
        this.bouncingY = true;
        return this;
    }

    @Override
    public PhysicsBuilder addAccelerationOnX() {
        this.acceleratedX = true;
        return this;
    }

    @Override
    public PhysicsBuilder addFallingPhysics() {
        this.falling = true;
        return this;
    }

    @Override
    public Physics create() {
        if (this.entity.equals(Optional.empty())) {
            throw new IllegalStateException("No GameEntity has been defined."
                + "Call the method setGameEntity() on this builder with a valid GameEntity");
        }
        if (acceleratedX || falling) {
            return new AcceleratedPhysics(entity.get(), speedX, speedY, bouncingX, bouncingY, acceleratedX, falling);
        }
        return new LinearPhysics(entity.get(), speedX, speedY, bouncingX, bouncingY);
    }

}
