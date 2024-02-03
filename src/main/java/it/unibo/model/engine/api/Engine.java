package it.unibo.model.engine.api;

import java.util.Set;

import it.unibo.common.SimpleEntiete;
import it.unibo.model.level.api.GameState;

/**
 * Models the Engine of the game, whose role is to configure a Level
 * and update it.
 * Before creating the actual Level, the Engine stores a configuration
 * which contains a simple representation of all the objects that will be in the Level.
 */
public interface Engine {
    
    /**
     * Creates a runnable Level based on the current configuration.
     * The Level is created only if the configuration is valid.
     * 
     * A valid configuration must include a starting point for the player
     * and a finish location.
     * @return true if the configuration is valid and the Level can be created, 
     *      false otherwise
     */
    boolean createLevel();

    /**
     * Adds an object to the configuration of the Level.
     * @param type the type of the object
     * @param pos the position of the object inside the Level
     * @return true if the object can be added, false otherwise
     */
    boolean addGameObject(SimpleEntiete entiete);

    //boolean removeGameObject(Position pos) TODO:decide how to find the specified object in the configuration (either by position or something else)

    /**
     * Updates the current Level.
     */
    void updateLevel();

    /**
     * @return the state of the game in the current Level
     */
    GameState getGameState();

    Set<SimpleEntiete> getLevelEntities();
}
