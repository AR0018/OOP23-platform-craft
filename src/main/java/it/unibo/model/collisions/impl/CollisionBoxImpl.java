package it.unibo.model.collisions.impl;

import java.util.Set;

import it.unibo.model.collisions.api.Boundaries;
import it.unibo.model.collisions.api.Collision;
import it.unibo.model.collisions.api.CollisionBox;
import it.unibo.model.entities.api.GameEntity;

public class CollisionBoxImpl implements CollisionBox{

    @Override
    public Set<Collision> getCollisions(Set<GameEntity> entities) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCollisions'");
    }

    @Override
    public boolean isCollidingWith(GameEntity object) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isCollidingWith'");
    }

    @Override
    public Boundaries getBoundaries() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBoundaries'");
    }
    
}