package it.unibo.common.api;

/**
 * A simple representation of a GameEntity, containing only the type and position of the entity.
 */

public interface SimpleEntity {

    /**
     * @return the type of entity
     */
    EntityType getType(EntityType e);

    /**
     * @return the X coordinate of the entity
     */
    double getX(double x);

    /**
     * @return the Y coordinate of the entity
     */
<<<<<<< HEAD:src/main/java/it/unibo/common/api/SimpleEntity.java
    double getY(double y);

    //TODO: add a method to return the dimension of this entity

}
=======
    double getY();
}
>>>>>>> master:src/main/java/it/unibo/common/SimpleEntity.java
