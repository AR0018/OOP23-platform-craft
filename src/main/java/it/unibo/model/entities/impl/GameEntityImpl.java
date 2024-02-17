package it.unibo.model.entities.impl;

import java.util.Set;
import java.util.stream.Collectors;
import it.unibo.common.EntityType;
import it.unibo.model.collisions.api.Boundaries;
import it.unibo.model.collisions.api.Collision;
import it.unibo.model.collisions.api.CollisionBox;
import it.unibo.model.collisions.impl.CollisionBoxImpl;
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
     * @param width the width of the GameEntity
     * @param height the heigth of the GameEntity
     */
    public GameEntityImpl(final Position position, final Level level, 
            final double width, final double height) {
        //Objects.requireNonNull(position);
        this.position = position;
        this.level = level;
        this.isAlive = true;
        this.box = new CollisionBoxImpl(width, height, this, level.getBoundaries());
    }

    @Override
    public final Position getPosition() {
        return this.position;
    }

    /**
     * Sets the new position of the Game Entity.
     * @param position the new position
     */
    protected final void setPosition(final Position position) {
        this.position = position;
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
        return this.box.getCollisions(this.level.getGameEntities()
                .stream()
                .filter(x -> !x.equals(this))
                .collect(Collectors.toSet()));
    }

    /**
     * Defines the standard boundaries for a game entity.
     * May be overriden to modify the size of the bound.
     * @return the boundaries of the game entity
     */
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
