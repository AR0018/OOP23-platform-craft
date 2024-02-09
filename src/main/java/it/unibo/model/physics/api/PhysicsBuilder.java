package it.unibo.model.physics.api;

import it.unibo.model.entities.api.GameEntity;

/**
 * This builder is used to configure an instance of Physics with the desired behaviour.
 * The default Physics has a linear motion on both X and Y axis.
 */
public interface PhysicsBuilder {

    /**
     * Sets the GameEntity that the Physics is associated to.
     * This method must be called once in the creation of the Physics, 
     * otherwise the configuration is not complete.
     * @return this builder, for method chaining
     * @param entity the GameEntity
     * @throws NullPointerException if entity is null
     */
    PhysicsBuilder setGameEntity(GameEntity entity);

    /**
     * Sets the speed of movement on the X axis to the desired configuration.
     * The default configuration is MEDIUM
     * @param speed
     * @return this builder, for method chaining
     * @throws NullPointerException if speed is null
     */
    PhysicsBuilder setSpeedOnX(SpeedLevels speed);

    /**
     * Sets the speed of movement on the Y axis to the desired configuration.
     * The default configuration is MEDIUM
     * @param speed
     * @return this builder, for method chaining
     * @throws NullPointerException if speed is null
     */
    PhysicsBuilder setSpeedOnY(SpeedLevels speed);

    /**
     * Sets the Physics so that the collisions on the x axis are handled by
     * flipping the velocity on the axis, instead of setting it to 0.
     * @return this builder, for method chaining
     */
    PhysicsBuilder addBouncingOnX();

    /**
     * Sets the Physics so that the collisions on the y axis are handled by
     * flipping the velocity on the axis, instead of setting it to 0.
     * @return this builder, for method chaining
     */
    PhysicsBuilder addBouncingOnY();

    /**
     * Sets the Physics so that the movement on the X axis is accelerated.
     * @return this builder, for method chaining
     * @throws IllegalStateException if this method is called more than once
     */
    PhysicsBuilder addAccelerationOnX();

    /**
     * Sets the Physics so that the GameObject falls with a constant acceleration.
     * @return this builder, for method chaining
     * @throws IllegalStateException if this method is called more than once
     */
    PhysicsBuilder addFallingPhysics();

    /**
     * Creates the Physics with the desired configuration.
     * @return the instance of Physics
     * @throws IllegalStateException if the configuration is invalid (the method setGameEntity() has never been called)
     */
    Physics create();

}
