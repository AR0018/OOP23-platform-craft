package it.unibo.model.collisions.impl;

import it.unibo.model.collisions.api.BorderCollision;
import it.unibo.model.collisions.api.Boundaries;
import it.unibo.model.entities.api.GameEntity;
import it.unibo.model.physics.api.Direction;

public class BorderCollisionImpl implements BorderCollision{

    private GameEntity gameEntity;
    private Boundaries mapBoundaries;

    public BorderCollisionImpl(GameEntity gameEntity) {
       this.gameEntity=gameEntity;
    }

    @Override
    public Direction getDirection() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDirection'");
    }

}
