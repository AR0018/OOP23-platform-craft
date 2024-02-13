package it.unibo.model.entities.api;

/**
 * Interface that models a trap.
 */
public interface Trap extends GameEntity {

    /**
     * Checks if the trap is lethal for the player.
     * @return true if it's lethal, false otherwise
     */
    boolean isLethal();     //TODO: usa l'enum interno e se è dead è true

}
