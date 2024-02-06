package it.unibo.model.entities.impl;

import it.unibo.common.EntityType;
import it.unibo.model.collisions.api.CollisionBox;
import it.unibo.model.entities.api.Character;
import it.unibo.model.entities.api.Enemy;
import it.unibo.model.level.api.Level;
import it.unibo.model.physics.api.Direction;
import it.unibo.model.physics.api.Physics;
import it.unibo.model.physics.api.PhysicsBuilder;
import it.unibo.model.physics.api.Position;

/**
 * Implementation of the Interface Character, 
 * where it cointains all the necessary to create the character.
 * Final because the class doesn't need to be extended
 */
public final class CharacterImpl implements Character {     //TODO: remove the final comment

    private final Physics physic;
    private Level level;
    private CollisionBox box;
    private PhysicsBuilder physicsBuilder;
    private Position position;
    private boolean isAlive;

    /**
     * It is the constructor of the class to initialize the character itself.
     * @param position the initial coordinate of the character
     */
    public CharacterImpl(final Position position) {
        this.position = position;
        this.isAlive = true;
        this.physic = this.physicsBuilder
                .setGameObject(this)
                .addAccelerationOnX()
                .addFallingPhysics()
                .create();
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

    public void setAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

    @Override
    public void updateState() {
        physic.calculateMovement();
        this.box.checkCollisions(this.level.getGameEntities());
        checkEnemyCollision();
    }

    @Override
    public EntityType getType() {
        return EntityType.CHARACTER;
    }

    @Override
    public CollisionBox getCollisionBox() {
        return this.box;
    }

    @Override
    public void move(final Direction dir) {     //TODO: needs to be improved
        if (checkMove(dir)) {
            physic.setMovement(dir);            //CheckEnemyCollision here?
        }
    }

    private void checkEnemyCollision() {
        if (this.box.getCollisions().stream().anyMatch(x -> x instanceof Enemy)) {
            this.isAlive = false;
        }
    }

    private boolean checkMove(final Direction dir) {
        for (var movement : Direction.values()) {
            if (movement.equals(dir)) {
                return true;
            }
        }
        return false;
    }
}
