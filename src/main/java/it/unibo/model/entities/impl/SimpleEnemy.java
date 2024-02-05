package it.unibo.model.entities.impl;

import it.unibo.common.EntityType;
import it.unibo.model.collisions.api.CollisionBox;
import it.unibo.model.entities.api.Enemy;
import it.unibo.model.physics.api.Physics;
import it.unibo.model.physics.api.PhysicsBuilder;
import it.unibo.model.physics.api.Position;

public class SimpleEnemy implements Enemy{

    private final Physics physics;
    private CollisionBox box;
    private Position position;
    private PhysicsBuilder physicsBuilder;
    //private EntityCondition condition;
    
    public SimpleEnemy(final Position position){        //TODO: set an enum Condition both for Char and Enemy
        this.position = position;
        this.physics = this.physicsBuilder.setGameObject(this).create();
    }

    @Override
    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public Position getPosition() {
        return this.position;
    }

    @Override
    public boolean isAlive() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isAlive'");
    }

    @Override
    public void updateState() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateState'");
    }

    @Override
    public EntityType getType() {
        return EntityType.ENEMY;
    }

    @Override
    public CollisionBox getCollisionBox() {
        return this.box;
    }
    
}
