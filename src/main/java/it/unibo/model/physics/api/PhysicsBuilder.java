package it.unibo.model.physics.api;

import it.unibo.model.entities.api.GameEntity;

/**
 * This builder is used to configure an instance of Physics with the desired behaviour.
 * The default Physics has a linear motion on both X and Y axis.
 */
public interface PhysicsBuilder {

    /**
     * Sets the GameObject that the Physics is associated to.
     * This method must be called once in the creation of the Physics, 
     * otherwise the configuration is not complete.
     * @return this builder, for method chaining
     * @param obj the GameObject
     */
    PhysicsBuilder setGameObject(GameEntity obj);

    /**
     * Sets the speed of movement on the X axis to the desired configuration.
     * The default configuration is MEDIUM
     * @param speed
     * @return this builder, for method chaining
     */
    PhysicsBuilder setSpeedOnX(SpeedLevels speed);

    /**
     * Sets the speed of movement on the Y axis to the desired configuration.
     * The default configuration is MEDIUM
     * @param speed
     * @return this builder, for method chaining
     */
    PhysicsBuilder setSpeedOnY(SpeedLevels speed);

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
     * @throws IllegalStateException if the configuration is invalid (the method setGameObject() has never been called)
     */
    Physics create();

}
