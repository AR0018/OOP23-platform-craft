package it.unibo.model.entities.api;

/**
 * Interface to model the concept of the character.
 */
public interface Character extends GameEntity {

    /**
     * This method lets the character moves in the right direction choosen by the player.
     */
    void move();

}
