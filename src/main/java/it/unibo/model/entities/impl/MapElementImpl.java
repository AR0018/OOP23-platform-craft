package it.unibo.model.entities.impl;

import it.unibo.common.EntityType;
import it.unibo.model.entities.api.MapElement;
import it.unibo.model.physics.api.Position;

/**
 * Class that represents the MapElement in the level.
 */
public class MapElementImpl extends GameEntityImpl implements MapElement {

    /**
     * Constructor for the MapElements that needs the first position.
     * @param position the first position of the MapElement
     */
    public MapElementImpl(final Position position) {
        super(position);
    }

    /**
     * In general the method updateState doesn't do anytihing special
     * but when TrapImpl calls this method (since it is a subclass of mapelemnt)
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
