package it.unibo.model.level.impl;

import java.util.Set;

import it.unibo.model.collisions.api.MapBoundaries;
import it.unibo.model.entities.api.Character;
import it.unibo.model.entities.api.GameEntity;
import it.unibo.model.level.api.GameState;
import it.unibo.model.level.api.Level;
import it.unibo.model.physics.api.Direction;

/**
 * This class is a Proxy to a Level, which allows reading operations
 * on the inner GameLevel, but disables modifications such as addGameEntity and removeGameEntity.
 */
public final class UnmodifiableLevel implements Level {

    private final Level level;

    /**
     * @param level the stored Level
     */
    public UnmodifiableLevel(final Level level) {
        this.level = level;
    }

    @Override
    public Set<GameEntity> getGameEntities() {
        return this.level.getGameEntities();
    }

    @Override
    public void computeChanges() {
        this.level.computeChanges();
    }

    @Override
    public void moveCharacter(final Direction dir) {
        this.level.moveCharacter(dir);
    }

    @Override
    public void addGameEntity(final GameEntity entity) {
        throw new UnsupportedOperationException("Modifying operations are disabled for this Level");
    }

    @Override
    public void removeGameEntity(final GameEntity entity) {
        throw new UnsupportedOperationException("Modifying operations are disabled for this Level");
    }

    @Override
    public void setCharacter(final Character character) {
        throw new UnsupportedOperationException("Modifying operations are disabled for this Level");
    }

    @Override
    public GameState getGameState() {
        return this.level.getGameState();
    }

    @Override
    public GameEntity getCharacter() {
        return this.level.getCharacter();
    }

    @Override
    public MapBoundaries getBoundaries() {
        return this.level.getBoundaries();
    }
}
