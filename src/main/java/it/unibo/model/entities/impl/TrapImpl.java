package it.unibo.model.entities.impl;

import it.unibo.common.EntityType;
import it.unibo.model.entities.api.Character;
import it.unibo.model.entities.api.Trap;
import it.unibo.model.entities.api.TrapState;
import it.unibo.model.level.api.Level;
import it.unibo.model.physics.api.Position;

/**
 * Class to represent a trap that can damage the player.
 */
public final class TrapImpl extends GameEntityImpl implements Trap {

    private static final long TIMER = 3000;
    private Long time = 0L;
    private TrapState state;

    /**
     * Constructor of the trap.
     * @param position the first position assigned to the trap
     * @param level the level of the game
     */
    public TrapImpl(final Position position, final Level level) {
        super(position, level);
        this.state = TrapState.INACTIVE;
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
    public TrapState getTrapState() {
        return this.state;
    }

    private void checkExplosion() {
        if (checkPlayer(getCharacter()) && isAlive()) {
            if (time == 0L) {
                time = System.currentTimeMillis();
                this.state = TrapState.ACTIVE;
            }
            if (checkTimer()) {
                time = System.currentTimeMillis();
                this.state = TrapState.DEAD;
            }
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
