package it.unibo.model.entities.api;

/**
 * Identify the finish location where the game finishes and the
 * player wins.
 */
public interface FinishLocation extends MapElement {

    /**
     * Checks if the player has some collisions
     * with the FinishLocation. 
     * @return true if the player has ended the game
     * false otherwise.
     */
    boolean theEnd();
}
