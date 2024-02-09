package it.unibo.model.entities.impl;

import it.unibo.common.EntityType;
import it.unibo.model.entities.api.MapElement;
import it.unibo.model.physics.api.Position;

public class MapElementImpl extends GameEntityImpl implements MapElement{

    public MapElementImpl(Position position) {
        super(position);
    }

    @Override
    public void updateState() {
    
    }

    @Override
    public EntityType getType() {
        return EntityType.MAP_ELEMENT;
    }
    
}
