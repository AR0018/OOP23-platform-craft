package it.unibo.common; 

/**
 * Models the concept of the type of an entity present in the game.
 * 
 */
public enum EntityType {
    /**
     * The Character controlled by the player.
     */
    CHARACTER(1, 1),
    /**
     * A trap in the game.
     */
    TRAP(1, 1),
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
    FINISH_LOCATION(1, 1);

    private final float x;
    private final float y;

    private EntityType(final float x, final float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }
}
