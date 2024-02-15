package it.unibo.common.api; 

/**
 * Models the concept of the type of an entity present in the game.
 * 
 */
public enum EntityType {
    /**
     * The Character controlled by the player.
     */
<<<<<<< HEAD:src/main/java/it/unibo/common/api/EntityType.java
    CHARACTER,
    // /**
    //  * Power up for the character  //perhaps
    //  */
    // POWERUP,
=======
    CHARACTER(1, 1),
>>>>>>> master:src/main/java/it/unibo/common/EntityType.java
    /**
     * A trap in the game.
     */
    TRAP(1, 1),
    /**
     * Trap Explosion.
     */
    EXPLOSION(1, 1),
    /**
     * A simple enemy.
     */
    SIMPLE_ENEMY(1, 1),
    /**
     * An enemy in the game that follows the player.
     */
    ENEMY(1, 1),
    /**
     * A walkable element of the map.
     */
    MAP_ELEMENT(1, 1),
    /**
     * The ending location of the level.
     */
<<<<<<< HEAD:src/main/java/it/unibo/common/api/EntityType.java
    FINISH_LOCATION,
    /**
     * The start location.
     */

    START_LOCATION;
=======
    FINISH_LOCATION(1, 1);

    private final double x;
    private final double y;

    EntityType(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Gets the width of the entity.
     * @return the width of the entity
     */
    public double getWidth() {
        return this.x;
    }

    /**
     * Gets the heigth of the entity.
     * @return the heigth of the entity
     */
    public double getHeigth() {
        return this.y;
    }
>>>>>>> master:src/main/java/it/unibo/common/EntityType.java
}
