package it.unibo.model.engine.api;

import java.util.Set;

import it.unibo.common.api.SimpleEntity;
import it.unibo.model.entities.api.GameEntity;
import it.unibo.model.level.api.GameState;
import it.unibo.model.physics.api.Direction;

/**
 * Models the Engine of the game, whose role is to configure a Level
 * and update it.
 * Before creating the actual Level, the Engine stores a configuration
 * which contains a simple representation of all the objects that will be in the Level.
 */
public interface Editor {

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
     * Adds an entity to the configuration of the Level.
     * @param entity the entity to be added
     * @return true if the entity can be added, false otherwise
     */
    boolean addGameEntity(GameEntity entity);

    /**
     * Removes the GameEntity whose CollisionBox contains the specified position.
     * @param x the position on X-asis of the Entity to be removed 
     * @param y the position on Y-asis of the Entity to be removed
     * @return true if the object has been removed, false otherwise
     */
    boolean removeGameEntity(double x, double y);

   
    /**
     * reset the level configuration;
     * @param configuration
     */
    void clearAll();

    

    /**
     * @return the state of the game in the current Level
     */
    GameState getGameState();

    /**
     * All the entities that appear in the game are memorized inside a Set.
     * @return the set that contains all the entities should display by the view
     */
    Set<SimpleEntity> getLevelEntities();
}
