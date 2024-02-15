package it.unibo.model.collisions.impl;

import it.unibo.model.collisions.api.EntityCollision;
import it.unibo.model.entities.api.GameEntity;

public class  EntityCollisionImpl extends CollisionImpl implements EntityCollision{

    private GameEntity gameEntity;

    public EntityCollisionImpl(GameEntity gameEntity, GameEntity gameEntity2) {
        super(gameEntity, gameEntity2);
    }

    @Override
    public GameEntity getGameEntity(){
        return this.gameEntity;
    }

}
