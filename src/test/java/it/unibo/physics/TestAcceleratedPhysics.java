package it.unibo.physics;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import org.junit.jupiter.api.Test;

import it.unibo.common.EntityType;
import it.unibo.model.collisions.api.Boundaries;
import it.unibo.model.collisions.api.Collision;
import it.unibo.model.entities.api.GameEntity;
import it.unibo.model.physics.api.Direction;
import it.unibo.model.physics.api.Physics;
import it.unibo.model.physics.api.Position;
import it.unibo.model.physics.api.SpeedLevels;
import it.unibo.model.physics.impl.AcceleratedPhysics;
import it.unibo.model.physics.impl.Position2D;

/**
 * Test class for accelerated physics.
 */
public class TestAcceleratedPhysics {

    private Physics physics;
    private static final double ACCELERATION = 0.1; //Value may change

    @Test
    void testAcceleratedMovement() {
        EntityNoCollisions entity = new EntityNoCollisions(new Position2D(0, 0));
        this.physics = new AcceleratedPhysics(entity, SpeedLevels.FAST, SpeedLevels.FAST, false, false, true, false);
        /**
         * Test acceleration
         */
        int timesAccel = 0;
        this.physics.setMovement(Direction.RIGHT);
        entity.updateState();
        timesAccel++;
        assertEquals(new Position2D(timesAccel * ACCELERATION, 0), entity.getPosition());
        this.physics.setMovement(Direction.RIGHT);
        entity.updateState();
        timesAccel += 2;
        assertEquals(new Position2D(timesAccel * ACCELERATION, 0), entity.getPosition());
        /*
         * Test deceleration
         */
        timesAccel += 2;
        entity.updateState();       //Position = 5 * ACCELERATION
        assertEquals(new Position2D(timesAccel * ACCELERATION, 0), entity.getPosition());
        entity.updateState();       //Position = 6 * ACCELERATION
        timesAccel++;
        assertEquals(new Position2D(timesAccel * ACCELERATION, 0), entity.getPosition());
        //Velocity on x = 0
        entity.updateState();
        entity.updateState();
        //The physics should have decelerated to 0, so it shouldn't have moved
        assertEquals(new Position2D(timesAccel * ACCELERATION, 0), entity.getPosition());
        //Sets the speed to 6 * ACCELERATION
        for (int i = 0; i < timesAccel; i++) {
            this.physics.setMovement(Direction.RIGHT);
        }
        this.physics.setMovement(Direction.LEFT);   //Accelerates to the left, decreasing the velocity on x
        entity.updateState();
        assertEquals(new Position2D((2 * timesAccel - 1) * ACCELERATION, 0), entity.getPosition());
        /*
         * Test maximum speed
         */
        this.physics.stopOnX();
        //Tries increasing the speed above the maximum value
        for (int i = 0; i < (SpeedLevels.FAST.getValue() / ACCELERATION) + 1; i++) {
            this.physics.setMovement(Direction.LEFT);
        }
        entity.updateState();
        assertEquals(
            new Position2D(((2 * timesAccel - 1) * ACCELERATION) - SpeedLevels.FAST.getValue(), 0),
            entity.getPosition());
        /*
         * Test deceleration in the opposite direction
         */
        entity.position = new Position2D(0, 0);
        this.physics.stopOnX();
        this.physics.setMovement(Direction.LEFT);
        this.physics.setMovement(Direction.LEFT);
        this.physics.setMovement(Direction.LEFT); //Velocity = 3 * ACCELERATION
        this.physics.setMovement(Direction.RIGHT); //Velocity = 2 * ACCELERATION
        entity.updateState();
        timesAccel = 2;
        assertEquals(new Position2D(-(timesAccel * ACCELERATION), 0), entity.getPosition());
        entity.updateState();   //Position = -(4 * ACCELERATION)
        entity.updateState();   //Position = -(5 * ACCELERATION)
        entity.updateState();   //Position = -(5 * ACCELERATION)
        timesAccel += 3;
        assertEquals(new Position2D(-(timesAccel * ACCELERATION), 0), entity.getPosition());
    }

    @Test
    void testMovementWithFalling() {
        EntityNoCollisions entity = new EntityNoCollisions(new Position2D(0, 0));
        this.physics = new AcceleratedPhysics(entity, SpeedLevels.FAST, SpeedLevels.FAST, false, false, false, true);
        entity.updateState();
        assertEquals(new Position2D(0, 0), entity.getPosition());
        entity.updateState();
        assertEquals(new Position2D(0, ACCELERATION), entity.getPosition());
        physics.setMovement(Direction.RIGHT); //Sets linear movement to the right
        entity.updateState();
        assertEquals(new Position2D(SpeedLevels.FAST.getValue(), 3 * ACCELERATION), entity.getPosition());
        entity.position = new Position2D(0, 0);
        this.physics.stopOnX();
        this.physics.stopOnY();
        this.physics.setMovement(Direction.UP);
        entity.updateState();
        assertEquals(new Position2D(0, -SpeedLevels.FAST.getValue()), entity.getPosition());
        entity.updateState();
        assertEquals(new Position2D(0, -(2 * SpeedLevels.FAST.getValue() - ACCELERATION)), entity.getPosition());
    }

    @Test
    void testWithCollisions() {
        EntityWithCollisions entity = new EntityWithCollisions(new Position2D(0, 0));
        this.physics = new AcceleratedPhysics(entity, SpeedLevels.FAST, SpeedLevels.FAST, false, false, true, true);
        this.physics.setMovement(Direction.RIGHT);
        entity.updateState();
        //The physics must not decelerate downwards because of the collision, but it still needs to move right
        assertEquals(new Position2D(ACCELERATION, 0), entity.getPosition());
        this.physics.setMovement(Direction.RIGHT);
        entity.updateState();
        assertEquals(new Position2D(3 * ACCELERATION, 0), entity.getPosition());
        entity.updateState();
        //Causes a collision to the right, so the position must not change
        assertEquals(new Position2D(3 * ACCELERATION, 0), entity.getPosition());
    }

    private final class EntityNoCollisions implements GameEntity {

        private Position position;

        private EntityNoCollisions(final Position pos) {
            this.position = pos;
            physics = new AcceleratedPhysics(this, SpeedLevels.SLOW, SpeedLevels.SLOW, false, false, true, false);
        }

            @Override
            public Position getPosition() {
                return this.position;
            }

            @Override
            public boolean isAlive() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'isAlive'");
            }

            @Override
            public void updateState() {
                this.position = physics.calculateMovement();
            }

            @Override
            public EntityType getType() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'getType'");
            }

            @Override
            public Set<Collision> getCollisions() {
                return Set.of();
            }

            @Override
            public Boundaries getBoundaries() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'getBoundaries'");
            }
    }

    private final class EntityWithCollisions implements GameEntity {

        private Position position;
        private int counter;

        private EntityWithCollisions(final Position pos) {
            this.position = pos;
            this.counter = 0;
            physics = new AcceleratedPhysics(this, SpeedLevels.SLOW, SpeedLevels.SLOW, false, false, true, false);
        }

            @Override
            public Position getPosition() {
                return this.position;
            }

            @Override
            public boolean isAlive() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'isAlive'");
            }

            @Override
            public void updateState() {
                this.position = physics.calculateMovement();
                counter++;
            }

            @Override
            public EntityType getType() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'getType'");
            }

            /*
             * After two updateState calls there is a collision to the right and one upwards.
             */
            @Override
            public Set<Collision> getCollisions() {
                return counter >= 2 
                    ? Set.of(new Coll(Direction.DOWN), new Coll(Direction.RIGHT))
                    : Set.of(new Coll(Direction.DOWN));
            }

            @Override
            public Boundaries getBoundaries() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'getBoundaries'");
            }
    }

    private static final class Coll implements Collision {

        private Direction dir;

        private Coll(final Direction dir) {
            this.dir = dir;
        }

        @Override
        public Direction getDirection() {
            return this.dir;
        }
    }
}
