package it.unibo.model.level.api;

import java.util.Set;

import it.unibo.model.entities.api.GameObject;

//TODO: add description of a level
public interface Level {

    /**
     * @return every GameObject in the level
     */
    Set<GameObject> getGameObjects();

    /**
     * Adds a GameObject to the level
     * @param object the GameObject to add
     */
    void addGameObject(GameObject object);

    /**
     * Updates the level, modifying the state of every GameObject
     * and updating the state of the game in case of a win/loss
     */
    void computeChanges();

    void addFinishLocation(); //TODO: add a 2D Position as an input

    /**
     * @return the current state of the game
     */
    GameState getGameState();
}