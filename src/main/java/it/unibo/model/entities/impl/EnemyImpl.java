package it.unibo.model.entities.impl;

import it.unibo.common.EntityType;
import it.unibo.model.collisions.api.BorderCollision;
import it.unibo.model.collisions.api.Collision;
import it.unibo.model.collisions.api.EntityCollision;
import it.unibo.model.entities.api.Character;
import it.unibo.model.entities.api.Enemy;
import it.unibo.model.level.api.Level;
import it.unibo.model.physics.api.Direction;
import it.unibo.model.physics.api.Position;

import java.util.Set;
import java.util.HashSet;
import java.util.stream.Collectors;

//Si valuta l'ereditarietà piuttosto che il pattern Decorator
//perchè si dovrebbe riscrivere un gran numero di metodi ogni volta
//mentre così si mantengono dei metodi gia predisposti comuni e se
//ne mettono alcuni astratti come move che verrà poi usata da updateState
//così modifico i parametri passati al costruttore mentre con il decorator 
//dovrei passare l'intefaccia enemy e quindi per modificare i campi sarebbero necesari
//dei metodi aggiuntivi 
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
     */
    public EnemyImpl(final Position position, final Level level, final float width, final float heigth) {
        super(position, level, width, heigth);
        setDirection(Direction.RIGHT);
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
    protected void setDirection(final Direction direction) {
        this.direction = direction;
    }
    
    /**
     * Return the type of the enemy.
     */
    public abstract EntityType getType();

    @Override
    public final void updateState() {     //TODO: sistemare bene updateState
        moveEnemy();
        checkEnemyCollisions();
    }

    /**
     * Update the correct movement of the enemy checking 
     * collisions or the presence of the player.
     */
    /*public abstract void updateState() {
        this.physics.calculateMovement();
        checkEnemyCollisions();
    }*/

    /**
     * This method lets the enemy moves in the right direction.
     */
    protected abstract void moveEnemy();           //Template method

    /**
     * Check if the enemy has some collisions. 
     */
    private void checkEnemyCollisions() {
        if (!getCollisions().isEmpty()) {
            if (getCollisionBox().isCollidingWith(getLevel().getCharacter())) {
                checkEnemyIsDead();
            } else {
                setDirection(getDirection().equals(Direction.RIGHT) ? Direction.LEFT : Direction.RIGHT);
            }
        }
    }

    /**
     * Check if the enemy died because of the player who collided with
     * the head of the enemy.
     */
    private void checkEnemyIsDead() {
        /*var enemyCharacter = getCollisions().stream().filter(x -> x.getGameEntity() instanceof Character);
        if (enemyCharacter.anyMatch(x -> x.getDirection().equals(Direction.UP))) {
            setAlive(false);
        }*/
        var enemyCharacter = getEntity(getCollisions())
                .stream()
                .filter(x -> x.getGameEntity() instanceof Character)
                .collect(Collectors.toSet());
        if (!enemyCharacter.isEmpty()) {
            if (enemyCharacter.stream().anyMatch(x -> x.getDirection().equals(Direction.UP))) {
                setAlive(false);
            }
        }

        //Border
        var enemyBorder = getBorder(getCollisions())
                .stream()
                .collect(Collectors.toSet());
        if (!enemyBorder.isEmpty()) {
            if (enemyBorder.stream().anyMatch(x -> x.getDirection().equals(Direction.DOWN))) {
                setAlive(false);
            }
        }
    }

    private Set<BorderCollision> getBorder(Set<Collision> collisions) {
        Set<BorderCollision> borderCollision = new HashSet<>();
        getCollisions()
                .stream()
                .filter(x -> x instanceof BorderCollision)
                .forEach(x -> borderCollision.add((BorderCollision) x));
        return borderCollision;
    }

    private Set<EntityCollision> getEntity(final Set<Collision> collision) {
        Set<EntityCollision> entityCollision = new HashSet<>();
        getCollisions()
                .stream()
                .filter(x -> x instanceof EntityCollision)
                .forEach(x -> entityCollision.add((EntityCollision) x));
        return entityCollision;
    }
}
