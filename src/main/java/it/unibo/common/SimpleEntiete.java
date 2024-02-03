package it.unibo.common;

/**
 * A simple representation of a GameEntity, containing only the type and position of the entity.
 */
public interface SimpleEntiete {

    /**
     * @return the type of entity
     */
    EntityType getType();

    /**
     * @return the X coordinate of the entity
     */
    double getX();

    /**
     * @return the Y coordinate of the entity
     */
    double getY();

}
