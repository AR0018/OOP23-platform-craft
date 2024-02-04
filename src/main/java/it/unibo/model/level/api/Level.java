package it.unibo.model.level.api;

import java.util.Set;

import it.unibo.model.entities.api.GameEntity;
import it.unibo.model.physics.api.Position;

/**
 * Models the concept of a Level in the game.
 * A Level consists of a series of GameObjects which interact together in the game.
 */
public interface Level {

    /**
     * @return every GameObject in the level
     */
    Set<GameEntity> getGameObjects();

    /**
     * Adds a GameObject to the level.
     * @param object the GameObject to add
     */
    void addGameObject(GameEntity object);

    /**
     * Updates the level, modifying the state of every GameObject
     * and updating the state of the game in case of a win/loss.
     */
    void computeChanges();

    /**
     * @param position
     */
    void addFinishLocation(Position position);

    /**
     * @return the current state of the game
     */
    GameState getGameState();
}
