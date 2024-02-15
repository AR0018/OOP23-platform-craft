package it.unibo.model.engine.api;

import java.util.Set;

import it.unibo.common.SimpleEntity;
import it.unibo.model.physics.api.Direction;


public interface Engine {

    
 /**
     * Updates the current Level.
     */
    void updateLevel();

    /**
     * Moves the character in the current Level.
     * @param dir the direction of movement
     */
    void moveCharacter(Direction dir);

     /**
     * All the entities that appear in the game are memorized inside a Set.
     * @return the set that contains all the entities should display by the view
     */
    Set<SimpleEntity> getLevelEntities();

}
