package it.unibo.model.entities.impl;

import it.unibo.common.EntityType;
import it.unibo.model.entities.api.MapElement;
import it.unibo.model.level.api.Level;
import it.unibo.model.physics.api.Position;

/**
 * Class that represents the MapElement in the level.
 */
public class MapElementImpl extends GameEntityImpl implements MapElement {

    /**
     * Constructor for the MapElements that needs the first position.
     * @param position the first position of the MapElement
     * @param level the level of the game
     */
    public MapElementImpl(final Position position, final Level level) {
        super(position, level, EntityType.MAP_ELEMENT.getWidth(), EntityType.MAP_ELEMENT.getHeigth());
    }

    /**
     * In general the method updateState doesn't do anytihing special
     * but when TrapImpl calls this method (since it is a subclass of mapElemnt)
     * needs to check every call of the method if the trap has encountered
     * the player. 
     */
    @Override
    public void updateState() {
    }

    /**
     * The method return a MAP_ELEMENT but the trap is a particular map
     * element defined as TRAP.
     */
    @Override
    public EntityType getType() {
        return EntityType.MAP_ELEMENT;
    }
}
