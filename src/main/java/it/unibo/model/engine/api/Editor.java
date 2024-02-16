package it.unibo.model.engine.api;

import java.util.Optional;
import java.util.Set;

import it.unibo.common.SimpleEntity;

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
     * A valid configuration must include a starting point for the player and a finish location.
     * @return an Optional containing the Engine if the Level has been created,
     * an empty Optional if the Level cannot be created
     */
    Optional<Engine> createLevel();

    /**
     * Adds an entity to the configuration of the Level.
     * @param entity the entity to be added
     * @return true if the entity can be added, false otherwise
     */
    boolean addGameEntity(SimpleEntity entity);

    /**
     * Removes the GameEntity whose CollisionBox contains the specified position.
     * @param x the position on X-asis of the Entity to be removed 
     * @param y the position on Y-asis of the Entity to be removed
     * @return true if the object has been removed, false otherwise
     */
    boolean removeGameEntity(double x, double y);

    /**
     * Resets the level configuration, creating a new one from scratch.
     */
    void clearAll();

    /**
     * Returns all the entities in the current Level configuration.
     * @return the set that contains all the entities in the current configuration
     */
    Set<SimpleEntity> getLevelEntities();

    /**
     * @return the width of the Level created by this Editor
     */
    double getLevelWidth();

    /**
     * @return the height of the Level created by this Editor
     */
    double getLevelHeight();
}
