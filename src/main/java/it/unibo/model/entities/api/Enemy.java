package it.unibo.model.entities.api;

/**
 * Interface that model the concept of an enemy.
 */
public interface Enemy extends GameEntity {

    /**
     * @return the size of the enemy
     */
    EntitySize getSize();
}
