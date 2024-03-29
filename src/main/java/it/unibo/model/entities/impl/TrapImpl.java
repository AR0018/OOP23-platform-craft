package it.unibo.model.entities.impl;

import it.unibo.common.EntityType;
import it.unibo.model.collisions.api.Boundaries;
import it.unibo.model.collisions.api.CollisionBox;
import it.unibo.model.collisions.impl.CollisionBoxImpl;
import it.unibo.model.entities.api.Character;
import it.unibo.model.entities.api.Trap;
import it.unibo.model.level.api.Level;
import it.unibo.model.physics.api.Position;

/**
 * Class to represent a trap that can damage the player.
 */
public final class TrapImpl extends GameEntityImpl implements Trap {

    private static final long TIMER = 3000;
    private static final double VISIBLE_DISTANCE = 150f;
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
        super(position, level, EntityType.TRAP.getWidth(), EntityType.TRAP.getHeigth());
        this.state = TrapState.INACTIVE;
        this.isLethal = false;
    }

    @Override
    public EntityType getType() {
        if (this.state.equals(TrapState.DEAD)) {
            return EntityType.EXPLOSION;
        }
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
        if (playerInRange() && this.isAlive() && this.state.equals(TrapState.INACTIVE)) {
                time = System.currentTimeMillis();
                this.state = TrapState.ACTIVE;
        }
        if (this.state.equals(TrapState.ACTIVE) && checkTimer(TIMER)) {
            time = System.currentTimeMillis();
            this.state = TrapState.DEAD;
            this.isLethal = true;
        }
        if (checkTimer(TIMER / 3) && this.state.equals(TrapState.DEAD)) {
            this.setAlive(false);
        }
    }

    private boolean playerInRange() {
        final Position charPos = ((Character) getLevel().getCharacter()).getPosition();
        final var distance = Math.sqrt(Math.pow(charPos.getX() - getPosition().getX(), 2) 
                + Math.pow(charPos.getY() - getPosition().getY(), 2));
        return distance <= VISIBLE_DISTANCE;
    }

    private boolean checkTimer(final long timer) {
        return System.currentTimeMillis() - time >= timer;
    }

    @Override
    public CollisionBox getCollisionBox() {
        if (this.state.equals(TrapState.DEAD)) {
            return new CollisionBoxImpl(EntityType.TRAP.getWidth() * 2,
                EntityType.TRAP.getHeigth() * 2, this, getLevel().getBoundaries());
        } else {
            return super.getCollisionBox();
        }
    }

    @Override
    public Boundaries getBoundaries() {
        return this.getCollisionBox().getBoundaries();
    }
}
