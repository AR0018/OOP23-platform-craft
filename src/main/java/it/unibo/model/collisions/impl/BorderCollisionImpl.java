package it.unibo.model.collisions.impl;

import it.unibo.model.collisions.api.BorderCollision;
import it.unibo.model.physics.api.Direction;

/**
 *  Verify collision with the map boundaries.
 */
public class BorderCollisionImpl implements BorderCollision {

   private final Direction direction;

   /**
    * Constructor of this class.
    * @param direction of collision
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
      if (this.getClass().equals(obj.getClass())) {
         BorderCollision other = (BorderCollision) obj;
         if (this.getDirection().equals(other.getDirection())) {
            return true;
         }
      }
      return false;
   }
}
