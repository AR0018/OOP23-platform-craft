package it.unibo.model.level.api;

import java.util.Set;

import it.unibo.model.collisions.api.MapBoundaries;
import it.unibo.model.entities.api.Character;
import it.unibo.model.entities.api.FinishLocation;
import it.unibo.model.entities.api.GameEntity;
import it.unibo.model.level.impl.MapBoundaries;
import it.unibo.model.physics.api.Direction;

/**
 * Models the concept of a Level in the game.
 * A Level consists of a series of GameEntities which interact together in the game.
 */
public interface Level {

    /**
     * @return every GameEntity in the level
     */
    Set<GameEntity> getGameEntities();
    /**
     * Updates the level, modifying the state of every GameEntity
     * and updating the state of the game in case of a win/loss.
     */
    void computeChanges();
    /**
     * Moves the playable character in the specified direction.
     * @param dir the direction of movement
     */
    void moveCharacter(Direction dir);

    /**
     * Sets the Character of the level as the specified Character.
     * @param character the character to be added
     */
    void setCharacter(Character character);

    /**
     * Adds a GameEntity to this Level.
     * @param entity the entity to be added
     */
    void addGameEntity(GameEntity entity);

    /**
     * Removes the specified GameEntity from this Level.
     * @param entity the entity to be removed
     */
    void removeGameEntity(GameEntity entity);

    /**
     * @return the current state of the game
     */
    GameState getGameState();
    /**
     * @return the playable character in this level
     */
    GameEntity getCharacter();
    /**
     * Returns the boundaries of this Level.
     * @return the boundaries of this Level
     */
    MapBoundaries getBoundaries();
}
