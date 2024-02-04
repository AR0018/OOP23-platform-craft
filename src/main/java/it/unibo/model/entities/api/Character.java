package it.unibo.model.entities.api;

import it.unibo.model.physics.api.Direction;

/**
 * Interface to model the concept of the character.
 */
public interface Character extends GameEntity {

    /**
     * This method lets the character moves in the right direction choosen by the player.
     * @param dir says which direction chose the player
     */
    void move(Direction dir);

}
