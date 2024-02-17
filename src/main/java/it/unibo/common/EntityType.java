package it.unibo.common; 

/**
 * Models the concept of the type of an entity present in the game.
 * 
 */
public enum EntityType {
    /**
     * The Character controlled by the player.
     */
    CHARACTER(100, 100),
    /**
     * A trap in the game.
     */
    TRAP(100, 100),
    /**
     * Trap Explosion.
     */
    EXPLOSION(300, 300),
    /**
     * A simple enemy.
     */
    SIMPLE_ENEMY(80, 100),
    /**
     * An enemy in the game that follows the player.
     */
    ENEMY(80, 100),
    /**
     * A walkable element of the map.
     */
    MAP_ELEMENT(100, 100),
    /**
     * The ending location of the level.
     */
    FINISH_LOCATION(100, 100);

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
}
