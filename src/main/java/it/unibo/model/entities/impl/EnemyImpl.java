package it.unibo.model.entities.impl;

import it.unibo.common.EntityType;
import it.unibo.model.collisions.api.CollisionBox;
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
        setDirection(direction);
    }

    @Override
    public Position getPosition() {
        return this.position;
    }

    public Direction getDirection() {
        return this.direction;
    }

    public void setDirection(final Direction direction) {
        this.direction = direction;
    }

    public EntitySize getSize() {
        return size;
    }

    @Override
    public boolean isAlive() {
        return this.isAlive;
    }

    public void setAlive(final boolean isAlive) {
        this.isAlive = isAlive;
    }

    @Override
    public EntityType getType() {
        return EntityType.ENEMY;
    }

    @Override
    public Set<Collision> getCollisions() {
        return null;
    }

    public abstract void updateState(); /*{
        this.physics.calculateMovement();
        checkEnemyCollisions();
    }*/

    public abstract void moveEnemy();

    /**
     * Check if the enemy has some collisions. 
     */
    protected void checkEnemyCollisions() {
        //TODO: understand what checkCollisions does
        if (!this.box.getCollisions(this.level.getGameEntities()).isEmpty()) {
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
    protected void checkEnemyIsDead() {
        var str = this.box.getCollisions(this.level.getGameEntities()).stream()
                .filter(x -> x.getGameEntity() instanceof Character);
        if (str.anyMatch(x -> x.getDirection().equals(Direction.UP))) {
            this.isAlive = false;
        } else {
            this.level.getCharacter(); //TODO: settare isAlive per character false
        }
    }
}
