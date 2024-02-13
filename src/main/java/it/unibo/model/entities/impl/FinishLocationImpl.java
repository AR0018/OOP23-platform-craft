package it.unibo.model.entities.impl;

import it.unibo.common.EntityType;
import it.unibo.model.entities.api.Character;
import it.unibo.model.entities.api.FinishLocation;
import it.unibo.model.level.api.Level;
import it.unibo.model.physics.api.Position;

/**
 * Models the finish location of the game, 
 * helps to figure out what the end of the is.
 */
public final class FinishLocationImpl extends GameEntityImpl implements FinishLocation {      //TODO: override of the box collision?

    /**
     * Constructor of the finish location.
     * @param position the position of the FinishLocation
     * @param level the level of the game
     */
    public FinishLocationImpl(final Position position, final Level level) {
        super(position, level);
    }

    @Override
    public void updateState() {
    }

    @Override
    public EntityType getType() {
        return EntityType.FINISH_LOCATION;
    }

    @Override
    public boolean theEnd() {
        return getCollisions().stream().anyMatch(x -> x instanceof Character);
    }
}
