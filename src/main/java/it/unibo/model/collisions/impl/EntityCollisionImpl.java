package it.unibo.model.collisions.impl;

import it.unibo.model.collisions.api.EntityCollision;
import it.unibo.model.entities.api.GameEntity;
import it.unibo.model.physics.api.Direction;

public class  EntityCollisionImpl implements EntityCollision{

    private final GameEntity gameEntity;
    private final Direction direction;

    public EntityCollisionImpl(final GameEntity gameEntity, final Direction direction) {
       this.gameEntity=gameEntity;
       this.direction=direction;
    }

    @Override
    public GameEntity getGameEntity(){
        return this.gameEntity;
    }

    @Override
    public Direction getDirection() {
        return this.direction;
    }

    @Override
    public boolean equals(Object obj) {
        if (this.getClass().equals(obj.getClass())) {
            EntityCollision other = (EntityCollision) obj;
            if(this.getDirection().equals(other.getDirection())
                && this.getGameEntity().equals(other.getGameEntity())) {
                return true;
            }
        }
        return false;
    }
}
