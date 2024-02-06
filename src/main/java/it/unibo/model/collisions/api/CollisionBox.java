package it.unibo.model.collisions.api;

import java.util.Set;

import it.unibo.model.entities.api.GameEntity;

/**
 * Models the collision box of a GameObject, which is a representation
 * of the boundaries of the object in a 2D space.
 */
public interface CollisionBox {

    /**
     * Checks if the current entity is colliding with any of the entities in the set,
     * and returns the informations about all the current collisions of the object.
     * @param entities the set of entities that need to be checked
     * @return a set containing all the current collisions of the object.
     */
    Set<Collision> getCollisions(Set<GameEntity> entities);

    /**
     * @param object
     * @return true if there is a collision between the current object and the input object
     */
    boolean isCollidingWith(GameEntity object);

    /**
     * @return the boundaries of this CollisionBox
     */
    Boundaries getBoundaries();
}
