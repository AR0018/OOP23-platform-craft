package it.unibo.model.entities.impl;

import it.unibo.common.EntityType;
import it.unibo.model.entities.api.MapElement;
import it.unibo.model.level.api.Level;
import it.unibo.model.physics.api.Position;

/**
 * A longer version of MapElement.
 */
public class LongMapElement extends GameEntityImpl implements MapElement {

    /**
     * Constructor for this MapElement.
     * @param position the first position of the MapElement
     * @param level the level of the game
     */
    public LongMapElement(final Position position, final Level level) {
        super(position, level, EntityType.LONG_MAP_ELEMENT.getWidth(), EntityType.LONG_MAP_ELEMENT.getHeigth());
    }

    /**
     * Does nothing.
     */
    @Override
    public void updateState() {
    }

    /**
     * Returns LONG_MAP_ELEMENT as a type.
     */
    @Override
    public EntityType getType() {
        return EntityType.LONG_MAP_ELEMENT;
    }

}
