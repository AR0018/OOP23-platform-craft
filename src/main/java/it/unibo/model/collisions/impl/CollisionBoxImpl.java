package it.unibo.model.collisions.impl;

import java.util.Set;

import it.unibo.model.collisions.api.Boundaries;
import it.unibo.model.collisions.api.Collision;
import it.unibo.model.collisions.api.CollisionBox;
import it.unibo.model.entities.api.GameEntity;

/**
 * 
 */
public final class CollisionBoxImpl implements CollisionBox {

    /**
     * 
     * @param width
     * @param heigth
     */
    public CollisionBoxImpl(final double width, final double heigth) {
    }

    @Override
    public Set<Collision> getCollisions(final Set<GameEntity> entities) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCollisions'");
    }

    @Override
    public boolean isCollidingWith(final GameEntity object) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isCollidingWith'");
    }

    @Override
    public Boundaries getBoundaries() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBoundaries'");
    }
}
