package it.unibo.model.entities.impl;

import it.unibo.common.EntityType;
import it.unibo.model.collisions.api.CollisionBox;
import it.unibo.model.collisions.api.Boundaries;
import it.unibo.model.collisions.api.Collision;
import it.unibo.model.entities.api.Character;
import it.unibo.model.entities.api.Enemy;
import it.unibo.model.entities.api.EntitySize;
import it.unibo.model.level.api.Level;
import it.unibo.model.physics.api.Direction;
import it.unibo.model.physics.api.Position;
import java.util.Set;

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
public abstract class EnemyImpl implements Enemy {

    private final EntitySize size;
    private Level level;
    private CollisionBox box;
    private Position position;
    private boolean isAlive;
    private Direction direction;

    //TODO: costruttore che prende fisica position velocita e dimensione
    //le classi che estendono fanno super con la fisica desiderata
    /**
     * The constructor of the implementation of enemy that initialize 
     * the position, the size and the direction where the enemy starts
     * to move.
     * @param position the first position of the enemy
     * @param size is the size of the enemy
     */
    public EnemyImpl(final Position position, final EntitySize size) {
        this.position = position;
        this.size = size;
        this.isAlive = true;
        this.direction = Direction.RIGHT;
        setDirection(direction);
    }

    @Override
    public final Boundaries getBoundaries() {
        return this.box.getBoundaries();
    }

    @Override
    public final Position getPosition() {
        return this.position;
    }

    /**
     * Obtain the direction where the enemy moves.
     * @return the direction
     */
    public Direction getDirection() {   //TODO: forse superfluo il metodo
        return this.direction;
    }

    /**
     * Sets the right direction of the enemy.
     * @param direction the direction
     */
    public void setDirection(final Direction direction) {
        this.direction = direction;
    }

    /**
     * @return the size of the enemy
     */
    public EntitySize getSize() {
        return size;
    }

    @Override
    public final boolean isAlive() {
        return this.isAlive;
    }

    /**
     * Sets the condition to the enemy.
     * @param isAlive true if it's alive and false otherwise
     */
    public void setAlive(final boolean isAlive) {
        this.isAlive = isAlive;
    }

    @Override
    public final EntityType getType() {
        return EntityType.ENEMY;
    }

    @Override
    public final Set<Collision> getCollisions() {
        return this.box.getCollisions(this.level.getGameEntities());
    }

    /*@Override
    public void updateState() {     //TODO: sistemare bene updateState
        moveEnemy();
        checkEnemyCollisions();
    }*/

    /**
     * Update the correct movement of the enemy checking 
     * collisions or the presence of the player.
     */
    public abstract void updateState(); /*{
        this.physics.calculateMovement();
        checkEnemyCollisions();
    }*/

    /**
     * This method lets the enemy moves in the right direction.
     */
    //public abstract void moveEnemy();

    /**
     * Check if the enemy has some collisions. 
     */
    protected void checkEnemyCollisions() {
        if (!getCollisions().isEmpty()) {
            if (this.box.isCollidingWith(this.level.getCharacter())) {
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
    private void checkEnemyIsDead() {     //TODO: need to check collision with the bounds of the map
        var str = getCollisions().stream()
                .filter(x -> x.getGameEntity() instanceof Character);
        if (str.anyMatch(x -> x.getDirection().equals(Direction.UP))) {
            setAlive(false);
        }
    }
}
