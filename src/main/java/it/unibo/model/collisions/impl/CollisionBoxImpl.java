package it.unibo.model.collisions.impl;

import java.util.HashSet;
import java.util.Set;


import it.unibo.model.collisions.api.Boundaries;
import it.unibo.model.collisions.api.Collision;
import it.unibo.model.collisions.api.CollisionBox;
import it.unibo.model.entities.api.GameEntity;

public class CollisionBoxImpl implements CollisionBox{

    private GameEntity gameEntity;
    private Boundaries boundaries;

    public CollisionBoxImpl(GameEntity gameEntity){
        this.gameEntity=gameEntity;
        this.boundaries=gameEntity.getBoundaries();
    }

    @Override
    public Set<Collision> getCollisions(Set<GameEntity> entities) {
        Set<Collision> collisions=new HashSet<>();
        for(GameEntity entity:entities){
            if(this.isCollidingWith(entity)){
                collisions.add(new EntityCollisionImpl(gameEntity,entity));
            }
        } 
        return collisions;
    }

    @Override
    public boolean isCollidingWith(GameEntity object) {
        return boundaries.intersects(object.getBoundaries());
    }

    @Override
    public Boundaries getBoundaries() {
       return this.boundaries;
    }

}
