package it.unibo.model.entities.api;

/**
 * Interface that model the concept of an enemy
 */
public interface Enemy extends GameObject{
    
    /**
     * Updates the state of the object, according to its desired behaviour.
     */
    void updateState();
    
    /**
     * Method used to manage how the enemy has to move during the game
     */
    void move();  

    /**
     * Check if the enemy is alive or not
     * @return true if the enemy is dead
     */
    boolean isAlive();

}
