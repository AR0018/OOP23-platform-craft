package it.unibo.model.collisions.impl;

import it.unibo.model.collisions.api.MapBoundaries;
import it.unibo.model.physics.impl.Position2D;

public class MapBoundariesimpl extends BoundariesImpl implements MapBoundaries {

    public MapBoundariesimpl(final double height, final double width) {
        super(height, width, new Position2D(0, 0));
    }
}