package it.unibo.model.entities.impl;

import org.locationtech.jts.geom.CoordinateXY;
import it.unibo.common.EntityType;
import it.unibo.model.collisions.api.Boundaries;
import it.unibo.model.entities.api.Character;
import it.unibo.model.physics.api.Direction;
import it.unibo.model.physics.api.Physics;
import it.unibo.model.physics.api.PhysicsBuilder;

public class CharacterImpl implements Character{

    private final Physics physic;
    //private final CollisionBox box;
    private PhysicsBuilder physicsBuilder;
    private Direction dir;
    private CoordinateXY position;
    private PlayerCondition condition;
    private boolean collisionState;

    enum PlayerCondition{
        ALIVE,
        DEAD;
    }

    public CharacterImpl(final CoordinateXY position, final Boundaries bounds) {
        this.position = position;
        this.collisionState = false;
        this.condition = PlayerCondition.ALIVE;
        this.physic = this.physicsBuilder
                .setGameObject(this)
                .addAccelerationOnX()
                .addFallingPhysics()
                .create();
    }

    @Override
    public void setPosition(CoordinateXY position) {
        this.position = position;
    }

    @Override
    public CoordinateXY getPosition() {
        return this.position;
    }

    @Override
    public boolean isAlive() {
        return condition == PlayerCondition.ALIVE;    
    }

    @Override
    public void updateState() {
    
    }

    @Override
    public EntityType getType() {
        return EntityType.CHARACTER;
    }

    @Override
    public void move(final Direction dir) {
        if(dir == Direction.DOWN    || dir == Direction.LEFT
                || dir ==  Direction.RIGHT || dir == Direction.UP){
            this.dir = dir;
            physic.setMovement(this.dir);
        }
    }
}
