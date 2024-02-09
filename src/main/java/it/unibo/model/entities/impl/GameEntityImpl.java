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

/**
 * Class used to accumulate the same code of the GameEntities.
 */
public abstract class GameEntityImpl implements GameEntity {

    private final Level level;
    private CollisionBox box;
    private Position position;
    private boolean isAlive;

    /**
     * Constructor for the GameEntity who needs a initial position.
     * @param position the initial position
     * @param level the level of the game
     */
    public GameEntityImpl(final Position position, final Level level) {
        Objects.requireNonNull(position);
        this.position = position;
        this.level = level;
        this.isAlive = true;
    }

    @Override
    public final Position getPosition() {
        return this.position;
    }

    @Override
    public final boolean isAlive() {
        return this.isAlive;
    }

    /**
     * Update the state (movement) of the GameEntity.
     */
    public abstract void updateState();

    /**
     * Return the type of the GameEntity.
     * @return an EntityType
     */
    public abstract EntityType getType();

    @Override
    public final Set<Collision> getCollisions() {
        return this.box.getCollisions(this.level.getGameEntities());
    }

    @Override
    public final Boundaries getBoundaries() {
        return this.box.getBoundaries();
    }

    /**
     * Sets the condition to the character.
     * @param isAlive true if it's alive false otherwise
     */
    public void setAlive(final boolean isAlive) {
        this.isAlive = isAlive;
    }

    /**
     * @return the CollisionBox of the GameEntity
     */
    protected CollisionBox getCollisionBox() {
        return this.box;
    }

    /**
     * Helps to reduce code in the subclasses.
     * @return the level of the GameEntity
     */
    protected Level getLevel() {
        return this.level;
    }
}
