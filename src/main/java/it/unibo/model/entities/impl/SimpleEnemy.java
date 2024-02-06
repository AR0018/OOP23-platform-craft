package it.unibo.model.entities.impl;

import it.unibo.common.EntityType;
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
    private Level level;
    private CollisionBox box;
    private Position position;
    private PhysicsBuilder physicsBuilder;          //TODO: physics impl
    private boolean isAlive;
    private Direction direction;

    /**
     * Constructor of the class SimpleEnemy used to initialize the class.
     * @param position where the enemy starts
     */
    public SimpleEnemy(final Position position) {  //TODO: level fornisce il character
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

    @Override
    public CollisionBox getCollisionBox() {
        return this.box;
    }

    private void moveEnemy() {
        this.physics.setMovement(this.direction);
    }

    private void checkEnemyCollisions() {
        this.box.checkCollisions(this.level.getGameEntities());     //TODO: understand what checkCollisions does
        if (!this.box.getCollisions().isEmpty()) {
            if (this.box.isCollidingWith(this.level.getCharacter())) {
                checkEnemyIsDead();
            } else {   
            }
        }
    }

    private void checkEnemyIsDead() {
        if (this.box.getCollisions().stream().filter(x -> x instanceof Character)
                .anyMatch(x -> x.getDirection().equals(Direction.UP))) {
            this.isAlive = false;
        }
    }
}
