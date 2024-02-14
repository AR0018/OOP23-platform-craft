package it.unibo.common.api; 

/**
 * Models the concept of the type of an entity present in the game.
 * 
 */
public enum EntityType {
    /**
     * The Character controlled by the player.
     */
    CHARACTER,
    // /**
    //  * Power up for the character  //perhaps
    //  */
    // POWERUP,
    /**
     * A trap in the game.
     */
    TRAP,
    /**
     * An enemy in the game.
     */
    ENEMY,
    /**
     * A walkable element of the map.
     */
    MAP_ELEMENT,
    /**
     * The ending location of the level.
     */
    FINISH_LOCATION,
    /**
     * The start location.
     */

    START_LOCATION;
}