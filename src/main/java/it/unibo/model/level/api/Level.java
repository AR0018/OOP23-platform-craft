package it.unibo.model.level.api;

import java.util.Set;

import it.unibo.model.entities.api.GameEntity;
import it.unibo.model.physics.api.Direction;
import it.unibo.model.physics.api.Position;

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
     * Adds a GameEntity to the level.
     * @param entity the GameEntity to add
     */
    void addGameEntity(GameEntity entity);  //TODO: remove, put in constructor

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
     * Adds a finish location to the Level.
     * If the Character touches this location, the game must end.
     * @param position the position in which to put the finish location
     */
    void addFinishLocation(Position position);  //TODO: remove, put in constructor

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
    //MapBoundaries getBoundaries();
}
