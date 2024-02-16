package it.unibo.model.collisions.impl;

import it.unibo.model.collisions.api.BorderCollision;
import it.unibo.model.physics.api.Direction;

/**
 * Implements a collision with a map boundaries.
 */
public final class BorderCollisionImpl implements BorderCollision {

    private final Direction direction;

    /**
     * @param direction the direction of collision with the map boundaries
     */
    public BorderCollisionImpl(final Direction direction) {
        this.direction = direction;
    }

    @Override
    public Direction getDirection() {
        return this.direction;
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        BorderCollision other = (BorderCollision) obj;
        if (this.getDirection().equals(other.getDirection())) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
