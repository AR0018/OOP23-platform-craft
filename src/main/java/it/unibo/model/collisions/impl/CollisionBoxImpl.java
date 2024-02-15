package it.unibo.model.collisions.impl;

import java.util.Set;

import it.unibo.model.collisions.api.Boundaries;
import it.unibo.model.collisions.api.Collision;
import it.unibo.model.collisions.api.CollisionBox;
import it.unibo.model.entities.api.GameEntity;

<<<<<<< HEAD
public class CollisionBoxImpl implements CollisionBox{

    @Override
    public Set<Collision> getCollisions(Set<GameEntity> entities) {
=======
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
>>>>>>> master
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCollisions'");
    }

    @Override
<<<<<<< HEAD
    public boolean isCollidingWith(GameEntity object) {
=======
    public boolean isCollidingWith(final GameEntity object) {
>>>>>>> master
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isCollidingWith'");
    }

    @Override
    public Boundaries getBoundaries() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBoundaries'");
    }
<<<<<<< HEAD
    
=======
>>>>>>> master
}
