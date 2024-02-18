package it.unibo.model.entities.impl;

import it.unibo.common.EntityType;
import it.unibo.model.collisions.api.BorderCollision;
import it.unibo.model.collisions.api.EntityCollision;
import it.unibo.model.entities.api.Character;
import it.unibo.model.entities.api.Enemy;
import it.unibo.model.entities.api.MapElement;
import it.unibo.model.level.api.Level;
import it.unibo.model.physics.api.Direction;
import it.unibo.model.physics.api.Position;

import java.util.Set;
import java.util.HashSet;
import java.util.stream.Collectors;

/**
 * Models the concept of an enemy with all its characteristics.
 */
public abstract class EnemyImpl extends GameEntityImpl implements Enemy {

    private Direction direction;

    /**
     * The constructor of the implementation of enemy that initialize 
     * the position, the size and the direction where the enemy starts
     * to move.
     * @param position the first position of the enemy
     * @param level is the level of the game
     * @param width the width of the enemy
     * @param height the heigth of the enemy
     */
    public EnemyImpl(final Position position, final Level level, final double width, final double height) {
        super(position, level, width, height);
        this.setDirection(Direction.RIGHT);
    }

    /**
     * Obtain the direction where the enemy moves.
     * @return the direction
     */
    public final Direction getDirection() {
        return this.direction;
    }

    /**
     * Sets the right direction of the enemy.
     * @param direction the direction
     */
    protected final void setDirection(final Direction direction) {
        this.direction = direction;
    }

    /**
     * Return the type of the enemy.
     * @return the type of the enemy
     */
    @Override
    public abstract EntityType getType();

    @Override
    public final void updateState() {
        moveEnemy();
        checkEnemyCollisions();
    }

    /**
     * This method lets the enemy moves in the right direction.
     */
    protected abstract void moveEnemy();           //Template method

    /**
     * Check if the enemy has some collisions. 
     */
    private void checkEnemyCollisions() {
        if (!getCollisions().isEmpty()) {
            checkEnemyCharacter();
            checkEnemyBorder();
            checkEnemyBlock();
        }
    }

    /**
     * Check if the enemy needs to change direction
     * due to a collision with another enemy or a map element
     * on right or left edge.
     */
    private void checkEnemyBlock() {
        final var blockEnemyCollision = getEntity()
            .stream()
            .filter(x -> x.getGameEntity() instanceof MapElement 
                || x.getGameEntity() instanceof Enemy)
                .collect(Collectors.toSet());
        if (blockEnemyCollision.stream()
            .anyMatch(x -> x.getDirection().equals(Direction.RIGHT) 
                || x.getDirection().equals(Direction.LEFT))) {
            if (blockEnemyCollision.stream().anyMatch(x -> x.getDirection().equals(Direction.LEFT))) {
                this.setDirection(Direction.RIGHT);
            }
            if (blockEnemyCollision.stream().anyMatch(x -> x.getDirection().equals(Direction.RIGHT))) {
                this.setDirection(Direction.LEFT);
            }
        }
    }

    /**
     * Check if the enemy needs to die due to a collision
     * with the down border.
     */
    private void checkEnemyBorder() {
        final var enemyBorder = getBorder()
        .stream()
        .collect(Collectors.toSet());
        if (!enemyBorder.isEmpty()) {
            if (enemyBorder.stream().anyMatch(x -> x.getDirection().equals(Direction.DOWN))) {
                setAlive(false);
            }
            if (enemyBorder.stream().anyMatch(x -> x.getDirection().equals(Direction.LEFT))) {
                this.setDirection(Direction.RIGHT);
            }
            if (enemyBorder.stream().anyMatch(x -> x.getDirection().equals(Direction.RIGHT))) {
                this.setDirection(Direction.LEFT);
            }
        }
    }

    /**
     * Check if the enemy dies due to the player who collided with
     * the head of the enemy.
     */
    private void checkEnemyCharacter() {
        final var enemyCharacter = getEntity()
                .stream()
                .filter(x -> x.getGameEntity() instanceof Character)
                .collect(Collectors.toSet());
        if (!enemyCharacter.isEmpty() && enemyCharacter
            .stream().anyMatch(x -> x.getDirection().equals(Direction.UP))) {
                setAlive(false);
        }
    }

    //CPD-OFF utilities methods or the alternative would be to use a generic class
    private Set<BorderCollision> getBorder() {
        final Set<BorderCollision> borderCollision = new HashSet<>();
        getCollisions()
                .stream()
                .filter(x -> x instanceof BorderCollision)
                .forEach(x -> borderCollision.add((BorderCollision) x));
        return borderCollision;
    }

    private Set<EntityCollision> getEntity() {
        final Set<EntityCollision> entityCollision = new HashSet<>();
        getCollisions()
                .stream()
                .filter(x -> x instanceof EntityCollision)
                .forEach(x -> entityCollision.add((EntityCollision) x));
        return entityCollision;
    }
    //CPD-ON
}
