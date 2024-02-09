package it.unibo.model.entities.api;

/**
* Sets out the condition of the trap.
*/
public enum TrapState {
    /**
     * The trap is inactive because the player
     * has not been seen yet.
     */
    INACTIVE,
    /**
     * The trap is active because the player 
     * is near.
     */
    ACTIVE,
    /**
     * The trap has exploded.
     */
    DEAD;
}
