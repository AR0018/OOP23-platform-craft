package it.unibo.model.level.impl;

import java.util.Set;

import it.unibo.common.EntityType;
import it.unibo.model.collisions.api.EntityCollision;
import it.unibo.model.engine.impl.EngineImpl;
import it.unibo.model.entities.api.FinishLocation;
import it.unibo.model.entities.api.GameEntity;
import it.unibo.model.entities.api.Character;
import it.unibo.model.physics.api.Direction;
import it.unibo.model.physics.api.Position;
import it.unibo.model.level.api.Level;
import it.unibo.model.level.api.GameState;
import it.unibo.model.level.impl.MapBoundaries;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;

/**
 * An implementation of Level.
 */
public final class GameLevel implements Level {

    private Set<GameEntity> levelConfiguration; // set of GameEntity contains all game entity
    private Character character; // save the Charachter
    private GameState gameState; // save the game state
    private MapBoundaries boundaries;

    /**
     * The constructor of this Level.
     * @param entities the set of all the entities in the Level
     * @param character the character in the Level
     * @param width the width of the Level
     * @param height the height of the Level
     */
    public GameLevel(final Set<GameEntity> entities, final Character character, final double width, final double height) {
        this.levelConfiguration = Objects.requireNonNull(entities);
        this.character = Objects.requireNonNull(character);
        this.boundaries = new MapBoundariesimpl(height, width);
        this.gameState = GameState.RUNNING;
    }

    @Override
    public Set<GameEntity> getGameEntities() {
        return Collections.unmodifiableSet(this.levelConfiguration);
    }

    @Override
    public void computeChanges() {
        levelConfiguration.stream().forEach(GameEntity::updateState);
        checkWin();
        //If the character is dead, the player loses.
        if (!character.isAlive()) {
            this.gameState = GameState.GAMEOVER;
        }
        removeDeadEntities();
    }

    /*
     * Checks if the win conditions are met.
     * If the Character touches the finish location, the player wins.
     */
    private void checkWin() {
        Set<EntityCollision> entityCollisions = new HashSet<>();
        character.getCollisions()
            .stream()
            .filter(e -> e instanceof EntityCollision)
            .forEach(e -> entityCollisions.add((EntityCollision) e));
        if (entityCollisions.stream()
            .map(coll -> coll.getGameEntity()).anyMatch(e -> e instanceof FinishLocation)) {
            this.gameState = GameState.WIN;
        }
    }

    @Override
    public void moveCharacter(final Direction dir) {
        this.character.move(Objects.requireNonNull(dir));
    }

    @Override
    public GameState getGameState() {
        //return the game state.
        return this.gameState;
    }

    @Override
    public GameEntity getCharacter() {
        return this.character;
    }

    /*
     * Removes every dead entity in the level (isAlive == false).
     */
    private void removeDeadEntities() {
        Set<GameEntity> entitiesToCheck = new HashSet<>(levelConfiguration);
        entitiesToCheck.stream()
            .filter(e -> !e.isAlive())
            .forEach(e -> levelConfiguration.remove(e));
    }

    @Override
    public MapBoundaries getBoundaries() {
        return this.boundaries;
    } 
}
