package it.unibo.model.entities.impl;

import java.util.Objects;
import java.util.Set;

import it.unibo.common.EntityType;
import it.unibo.model.collisions.api.Boundaries;
import it.unibo.model.collisions.api.Collision;
import it.unibo.model.collisions.api.CollisionBox;
import it.unibo.model.entities.api.GameEntity;
import it.unibo.model.level.api.Level;
import it.unibo.model.physics.api.Position;

public abstract class GameEntityImpl implements GameEntity{

    private Level level;
    private CollisionBox box;
    private Position position;
    private boolean isAlive;
    
    public GameEntityImpl(final Position position) {
        Objects.requireNonNull(position);
        this.position = position;
        this.isAlive = true;
    }

    @Override
    public Position getPosition() {
        return this.position;
    }

    @Override
    public boolean isAlive() {
        return this.isAlive;
    }

    public abstract void updateState();

    public abstract EntityType getType();

    @Override
    public Set<Collision> getCollisions() {
        return this.box.getCollisions(this.level.getGameEntities());
    }

    @Override
    public Boundaries getBoundaries() {
        return this.box.getBoundaries();
    }

    /**
     * Sets the condition to the character.
     * @param isAlive true if it's alive false otherwise
     */
    public void setAlive(final boolean isAlive) {
        this.isAlive = isAlive;
    }

    protected CollisionBox getCollisionBox() {
        return this.box;
    }

    protected Level getLevel() {
        return this.level;
    }
}
