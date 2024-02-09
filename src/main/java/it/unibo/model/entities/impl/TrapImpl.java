package it.unibo.model.entities.impl;

import it.unibo.common.EntityType;
import it.unibo.model.entities.api.Character;
import it.unibo.model.level.api.Level;
import it.unibo.model.physics.api.Position;

/**
 * Class to represent a trap that can damage the player.
 */
public final class TrapImpl extends MapElementImpl {

    private static final long TIMER = 3000;
    private Long time = 0L;

    /**
     * Constructor of the trap.
     * @param position
     */
    public TrapImpl(final Position position, final Level level) {
        super(position, level);
    }

    @Override
    public EntityType getType() {
        return EntityType.TRAP;
    }

    @Override
    public void updateState() {
        checkExplosion();
    }


    private void checkExplosion() {
        if (checkPlayer(getCharacter()) && isAlive()) {
            if (time == 0L) {
                time = System.currentTimeMillis();
            }
            if (System.currentTimeMillis() - time >= TIMER) {
                this.setAlive(false);
                //getCharacter().setAlive(false);           //TODO: aspettare che venga implementato il personaggio nel level
            }
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
}
