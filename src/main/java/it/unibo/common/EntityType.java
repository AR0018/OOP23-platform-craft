package it.unibo.common; 

/**
 * Models the concept of the type of an entity present in the game.
 * 
 */
public enum EntityType {
    /**
     * The Character controlled by the player.
     */
    CHARACTER,
    /**
     * A trap in the game.
     */
    TRAP,
    /**
     * A simple enemy.
     */
    SIMPLE_ENEMY,
    /**
     * An enemy in the game that follows the player.
     */
    ENEMY,
    /**
     * A walkable element of the map.
     */
    MAP_ELEMENT,
    /**
     * The ending location of the level.
     */
    FINISH_LOCATION;
}
