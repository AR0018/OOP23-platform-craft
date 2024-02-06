package it.unibo.model.entities.impl;

import java.util.Set;

import it.unibo.common.EntityType;
import it.unibo.model.collisions.api.Collision;
import it.unibo.model.collisions.api.CollisionBox;
import it.unibo.model.entities.api.Enemy;
import it.unibo.model.entities.api.Character;
import it.unibo.model.level.api.Level;
import it.unibo.model.physics.api.Direction;
import it.unibo.model.physics.api.Physics;
import it.unibo.model.physics.api.PhysicsBuilder;
import it.unibo.model.physics.api.Position;

/**
 * Implementation of a simple enemy.
 */
public final class SimpleEnemy implements Enemy {

    private final Physics physics;
    private final Character player;
    private Level level;
    private CollisionBox box;
    private Position position;
    private PhysicsBuilder physicsBuilder;
    private boolean isAlive;
    private boolean collisionUp;
    private Direction direction;

    /**
     * Constructor of the class SimpleEnemy used to initialize the class.
     * @param position where the enemy starts
     * @param player indicates who is the character of the game
     */
    public SimpleEnemy(final Position position, final Character player) {
        this.player = player;
        this.position = position;
        this.direction = Direction.RIGHT;
        this.isAlive = true;
        this.physics = this.physicsBuilder.setGameObject(this).create();
    }

    @Override
    public void setPosition(final Position position) {
        this.position = position;
    }

    @Override
    public Position getPosition() {
        return this.position;
    }

    @Override
    public boolean isAlive() {
        return this.isAlive == true;
    }

    @Override
    public void updateState() {
        moveEnemy();
        this.physics.calculateMovement();
        checkEnemyCollisions();
    }

    @Override
    public EntityType getType() {
        return EntityType.ENEMY;
    }

    /* 
    @Override
    public CollisionBox getCollisionBox() {
        return this.box;    TODO: remove this method
    }
    */

    private void moveEnemy() {
        this.physics.setMovement(this.direction);
    }

    private void checkEnemyCollisions() {
        /*this.box.checkCollisions(this.level.getGameEntities());     //TODO: understand what checkCollisions does
        if (!this.box.getCollisions().isEmpty()) {  TODO: causes error with new design
            if (this.box.isCollidingWith(this.player)) {
                checkEnemyIsDead();
            } else {   
            }
        }*/
    }

    private void checkEnemyIsDead() {
        /*this.collisionUp = this.box.getCollisions().stream() TODO: causes error with new design
                    .filter(x -> x instanceof Character)
                    .anyMatch(x -> x.getDirection().equals(Direction.UP));*/
        if (this.collisionUp) {
            this.isAlive = false;
        }
    }

    @Override
    public Set<Collision> getCollisions() {
        // TODO implement this method
        throw new UnsupportedOperationException("Unimplemented method 'getCollisions'");
    }
}
