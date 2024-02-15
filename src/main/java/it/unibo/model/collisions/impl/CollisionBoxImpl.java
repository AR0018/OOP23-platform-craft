package it.unibo.model.collisions.impl;

import java.util.HashSet;
import java.util.Set;


import it.unibo.model.collisions.api.Boundaries;
import it.unibo.model.collisions.api.Collision;
import it.unibo.model.collisions.api.CollisionBox;
import it.unibo.model.entities.api.GameEntity;

public class CollisionBoxImpl implements CollisionBox{

    private GameEntity gameEntity;
    private double width;
    private double height;

    public CollisionBoxImpl(final double width, final double height, final GameEntity gameEntity){
        
        this.gameEntity=gameEntity;
    }

    @Override
    public Set<Collision> getCollisions(final Set<GameEntity> entities) {
        Set<Collision> collisions=new HashSet<>();
        for(GameEntity entity:entities){
            if(this.isCollidingWith(entity)){
                collisions.add(new EntityCollisionImpl(gameEntity,entity));
            }
        } 
        return collisions;
    }

    @Override
    public boolean isCollidingWith(final GameEntity object) {
        return this.getBoundaries().intersects(object.getBoundaries());
    }

    @Override
    public Boundaries getBoundaries() {
       return new BoundariesImpl(height, width, gameEntity.getPosition());
    }

}
