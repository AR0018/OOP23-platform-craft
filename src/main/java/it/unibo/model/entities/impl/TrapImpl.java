package it.unibo.model.entities.impl;

import it.unibo.common.EntityType;
import it.unibo.model.entities.api.Character;
import it.unibo.model.entities.api.Trap;
import it.unibo.model.level.api.Level;
import it.unibo.model.physics.api.Position;

/**
 * Class to represent a trap that can damage the player.
 */
public final class TrapImpl extends GameEntityImpl implements Trap {

    private static final long TIMER = 3000;
    private boolean isLethal;
    private Long time = 0L;
    private TrapState state;

    /**
    * Sets out the condition of the trap.
    */
    private enum TrapState {
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

    /**
     * Constructor of the trap.
     * @param position the first position assigned to the trap
     * @param level the level of the game
     */
    public TrapImpl(final Position position, final Level level) {
        super(position, level, EntityType.TRAP.getX(), EntityType.TRAP.getY());
        this.state = TrapState.INACTIVE;
        this.isLethal = false;
    }

    @Override
    public EntityType getType() {
        return EntityType.TRAP;
    }

    @Override
    public void updateState() {
        checkExplosion();
    }

    @Override
    public boolean isLethal() {
        return this.isLethal;
    }

    private void checkExplosion() {
        if (checkPlayer(getCharacter())) {     //TODO: controllare se può funzionare
            if (this.isAlive() && this.state.equals(TrapState.INACTIVE)) {
                time = System.currentTimeMillis();
                this.state = TrapState.ACTIVE;
            }
        }
        if (this.state.equals(TrapState.ACTIVE) && checkTimer()) {
            time = System.currentTimeMillis();
            this.state = TrapState.DEAD;
            this.isLethal = true;
        }
        if (checkTimer() && this.state.equals(TrapState.DEAD)) {
            this.setAlive(false);
        }
    }

    private boolean checkPlayer(final Character character) {
        Position playerPosition = getLevel().getCharacter().getPosition();
        if (this.getBoundaries().contains(playerPosition)) {
            return true;
        }
        return false;
    }

    private Character getCharacter() {
        return (Character) getLevel().getCharacter();
    }

    private boolean checkTimer() {
        return System.currentTimeMillis() - time >= TIMER;
    }
}
