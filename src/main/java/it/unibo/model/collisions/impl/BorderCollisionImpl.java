package it.unibo.model.collisions.impl;

import it.unibo.model.collisions.api.BorderCollision;
import it.unibo.model.physics.api.Direction;

public class BorderCollisionImpl implements BorderCollision {

   private final Direction direction;

   public BorderCollisionImpl(final Direction direction) {
      this.direction = direction;
   }

   @Override
   public Direction getDirection() {
      return this.direction;
   }

   @Override
   public boolean equals(Object obj) {
      if (this.getClass().equals(obj.getClass())) {
         BorderCollision other = (BorderCollision) obj;
         if (this.getDirection().equals(other.getDirection())) {
            return true;
         }
      }
      return false;
   }
}
