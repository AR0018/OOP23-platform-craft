package it.unibo.model.collisions.impl;

import it.unibo.model.collisions.api.MapBoundaries;
import it.unibo.model.physics.impl.Position2D;

<<<<<<< HEAD
public class MapBoundariesimpl extends BoundariesImpl implements MapBoundaries {
=======
/**
 * Implementation of map boundaries.
 */
public final class MapBoundariesimpl extends BoundariesImpl implements MapBoundaries {
>>>>>>> ca4618b0015d9037dd967bd31962e414ae752ebf

    /**
     * @param height the height of this map boundaries
     * @param width the width of this map boundaries
     */
    public MapBoundariesimpl(final double height, final double width) {
        super(height, width, new Position2D(0, 0));
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> ca4618b0015d9037dd967bd31962e414ae752ebf
