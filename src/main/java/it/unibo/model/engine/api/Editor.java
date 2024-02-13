package it.unibo.model.engine.api;

import it.unibo.model.physics.api.Direction;

public interface Editor {

    
 /**
     * Updates the current Level.
     */
    void updateLevel();

    /**
     * Moves the character in the current Level.
     * @param dir the direction of movement
     */
    void moveCharacter(Direction dir);
}
