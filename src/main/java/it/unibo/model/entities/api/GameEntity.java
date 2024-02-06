package it.unibo.model.entities.api;



import it.unibo.common.EntityType;
import it.unibo.model.collisions.api.CollisionBox;
import it.unibo.model.physics.api.Position;

/**
 * Models an entity in the game.
 * Every entity has a position, a CollisionBox and a type of Physics.
 */
public interface GameEntity {

    /**
     * Sets the position of the character to the given input.
     * @param position the input position
     */
    void setPosition(Position position);

    /**
     * Obtain the position of the game entity which calls the method
     * @return the current position of the character
     */
    Position getPosition();

    /**
     * Check if the entity is alive or not.
     * @return true if the entity is dead
     */
    boolean isAlive();

    /**
     * Updates the state of the entity, according to its desired behaviour.
     */
    void updateState();

    /**
     * @return the type of this entity
     */
    EntityType getType();

    /**
     * @return the CollisionBox of the character
     */
    CollisionBox getCollisionBox();
}
