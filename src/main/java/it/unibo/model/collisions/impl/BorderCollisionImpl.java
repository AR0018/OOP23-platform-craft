package it.unibo.model.collisions.impl;

import it.unibo.model.collisions.api.BorderCollision;
import it.unibo.model.physics.api.Direction;

/**
<<<<<<< HEAD
 *  Verify collision with the map boundaries.
 */
public class BorderCollisionImpl implements BorderCollision {
=======
 * Implements a collision with a map boundaries.
 */
public final class BorderCollisionImpl implements BorderCollision {
>>>>>>> ca4618b0015d9037dd967bd31962e414ae752ebf

    private final Direction direction;

<<<<<<< HEAD
   /**
    * Constructor of this class.
    * @param direction of collision
    */
   public BorderCollisionImpl(final Direction direction) {
      this.direction = direction;
   }
=======
    /**
     * @param direction the direction of collision with the map boundaries
     */
    public BorderCollisionImpl(final Direction direction) {
        this.direction = direction;
    }
>>>>>>> ca4618b0015d9037dd967bd31962e414ae752ebf

    @Override
    public Direction getDirection() {
        return this.direction;
    }

<<<<<<< HEAD
   public boolean equals(final Object obj) {
      if (this.getClass().equals(obj.getClass())) {
         BorderCollision other = (BorderCollision) obj;
         if (this.getDirection().equals(other.getDirection())) {
=======
    @Override
    public boolean equals(final Object obj) {
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        BorderCollision other = (BorderCollision) obj;
        if (this.getDirection().equals(other.getDirection())) {
>>>>>>> ca4618b0015d9037dd967bd31962e414ae752ebf
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
