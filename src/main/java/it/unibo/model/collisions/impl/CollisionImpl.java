package it.unibo.model.collisions.impl;

import it.unibo.model.collisions.api.Collision;
import it.unibo.model.entities.api.GameEntity;
import it.unibo.model.physics.api.Direction;

public class CollisionImpl implements Collision {

    private GameEntity gameEntity;
    private Direction direction;

    public CollisionImpl(final GameEntity gameEntity, final Direction direction ){
        this.gameEntity=gameEntity;
        this.direction=direction;
    }

    @Override
    public GameEntity getGameEntity() {
       return this.gameEntity;
    }
    
    @Override
    public Direction getDirection() {
       return this.direction;
    }

}
