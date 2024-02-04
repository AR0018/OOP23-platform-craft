package it.unibo.model.collisions.api;

import java.util.Set;

import it.unibo.model.entities.api.GameEntity;

/**
 * Models the collision box of a GameObject, which is a representation
 * of the boundaries of the object in a 2D space.
 */
public interface CollisionBox {

    /**
     * Checks if the current object is colliding with any of the objects in the set, 
     * and modifies the state of the collision accordingly.
     * 
     * @param objects the set of objects that need to be checked
     */
    void checkCollisions(Set<GameEntity> objects);

    /**
     * @param object
     * @return true if there is a collision between the current object and the input object
     */
    boolean isCollidingWith(GameEntity object);

    /**
     * Returns the informations about all the current collisions of the object.
     * @return a set containing all the current collisions of the object.
     */
    Set<Collision> getCollisions();

    /**
     * @return the boundaries of this CollisionBox
     */
    Boundaries getBoundaries();
}
