package it.unibo.model.entities.api;

/**
 * Interface that models a trap.
 */
public interface Trap extends GameEntity {

    /**
     * @return the state of the trap
     */
    TrapState getTrapState();

}
