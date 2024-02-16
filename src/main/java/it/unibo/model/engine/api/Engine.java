package it.unibo.model.engine.api;

import java.util.Set;

import it.unibo.common.SimpleEntity;
import it.unibo.model.physics.api.Direction;
import it.unibo.model.level.api.GameState;

/**
 * Models an Engine used to update a Level during a game.
 */
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
     * @return the set that contains all the entities that are currently in the level
     */
    Set<SimpleEntity> getLevelEntities();

    /**
     * Returns the GameState of the current Level.
     * @return the GameState of the current level
     */
    GameState getGameState();
}
