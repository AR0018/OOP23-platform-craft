package it.unibo.physics;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import it.unibo.common.EntityType;
import it.unibo.model.collisions.api.Boundaries;
import it.unibo.model.collisions.api.Collision;
import it.unibo.model.entities.api.GameEntity;
import it.unibo.model.physics.api.Direction;
import it.unibo.model.physics.api.Physics;
import it.unibo.model.physics.api.Position;
import it.unibo.model.physics.api.SpeedLevels;
import it.unibo.model.physics.impl.LinearPhysics;
import it.unibo.model.physics.impl.Position2D;

/**
 * Class for testing the physics.
 */
public class TestPhysics {

    private Physics linear;
    //private Physics accelerated;
    private enum Speed {
        FAST(1), MEDIUM(0.6), SLOW(0.3); // TODO: actual values may vary

        private final double value;

        Speed(final double value) {
            this.value = value;
        }

        private double getValue() {
            return this.value;
        }
    }

    @Test
    void testLinearPhysics() {
        final EntityNoCollisions entity = new EntityNoCollisions(new Position2D(0, 0));
        this.linear = new LinearPhysics(entity, SpeedLevels.FAST, SpeedLevels.FAST, false, false);
        entity.updateState();
        assertEquals(new Position2D(0, 0), entity.getPosition());
        this.linear.setMovement(Direction.RIGHT);
        entity.updateState();
        assertEquals(new Position2D(Speed.FAST.getValue(), 0), entity.getPosition());
        this.linear.setMovement(Direction.UP);
        this.linear.stopOnX();
        entity.updateState();
        assertEquals(new Position2D(Speed.FAST.getValue(), -Speed.FAST.getValue()), entity.getPosition());
        this.linear.setMovement(Direction.RIGHT);
        entity.updateState();
        assertEquals(new Position2D(2 * Speed.FAST.getValue(), 2 * (-Speed.FAST.getValue())), entity.getPosition());
        this.linear.stopOnX();
        this.linear.stopOnY();
        entity.updateState();
        //Position must not change
        assertEquals(new Position2D(2 * Speed.FAST.getValue(), 2 * (-Speed.FAST.getValue())), entity.getPosition());
        this.linear.setMovement(Direction.LEFT);
        entity.updateState();
        assertEquals(new Position2D(Speed.FAST.getValue(), 2 * -(Speed.FAST.getValue())), entity.getPosition());
        this.linear.setMovement(Direction.DOWN);
        this.linear.stopOnX();
        entity.updateState();
        assertEquals(new Position2D(Speed.FAST.getValue(), -Speed.FAST.getValue()), entity.getPosition());
    }

    @Test
    void testDifferentSpeeds() {
        final EntityNoCollisions entity = new EntityNoCollisions(new Position2D(0, 0));
        this.linear = new LinearPhysics(entity, SpeedLevels.FAST, SpeedLevels.SLOW, false, false);
        this.linear.setMovement(Direction.UP);
        this.linear.setMovement(Direction.RIGHT);
        entity.updateState();
        assertEquals(new Position2D(Speed.FAST.getValue(), -Speed.SLOW.getValue()), entity.getPosition());
        this.linear.setMovement(Direction.DOWN);
        this.linear.setMovement(Direction.LEFT);
        entity.updateState();
        assertEquals(new Position2D(0, 0), entity.getPosition());
    }

    @Test
    void testCollisionsStopping() {
        final EntityWithCollisions e1 = new EntityWithCollisions(new Position2D(0, 0));
        this.linear = new LinearPhysics(e1, SpeedLevels.FAST, SpeedLevels.FAST, false, false);
        /*
         * Test collision to the right
         */
        this.linear.setMovement(Direction.RIGHT);
        e1.updateState();
        assertEquals(new Position2D(Speed.FAST.getValue(), 0), e1.getPosition());
        e1.updateState();
        assertEquals(new Position2D(2 * Speed.FAST.getValue(), 0), e1.getPosition());
        e1.updateState();
        //Here we have a collision, so the position should be the same as the previous
        assertEquals(new Position2D(2 * Speed.FAST.getValue(), 0), e1.getPosition());
        /*
         * Test collision upwards
         */
        final EntityWithCollisions e2 = new EntityWithCollisions(new Position2D(0, 0));
        this.linear = new LinearPhysics(e2, SpeedLevels.FAST, SpeedLevels.FAST, false, false);
        this.linear.setMovement(Direction.UP);
        e2.updateState();
        assertEquals(new Position2D(0, -Speed.FAST.getValue()), e2.getPosition());
        e2.updateState();
        assertEquals(new Position2D(0, 2 * (-Speed.FAST.getValue())), e2.getPosition());
        e2.updateState();
        //Here we have a collision, so the position should be the same as the previous
        assertEquals(new Position2D(0, 2 * (-Speed.FAST.getValue())), e2.getPosition());
        /*
         * Test collision in another direction does not affect movement
         */
        final EntityWithCollisions e3 = new EntityWithCollisions(new Position2D(0, 0));
        this.linear = new LinearPhysics(e3, SpeedLevels.FAST, SpeedLevels.FAST, false, false);
        this.linear.setMovement(Direction.LEFT);
        e3.updateState();
        assertEquals(new Position2D(-Speed.FAST.getValue(), 0), e3.getPosition());
        e3.updateState();
        assertEquals(new Position2D(2 * (-Speed.FAST.getValue()), 0), e3.getPosition());
        e3.updateState();
        //Here we have a collision in a different direction as the current, so the movement should not be affected
        assertEquals(new Position2D(3 * (-Speed.FAST.getValue()), 0), e3.getPosition());
    }

    @Test
    void testCollisionsBouncing() {
        EntityWithCollisions entity = new EntityWithCollisions(new Position2D(0, 0));
        this.linear = new LinearPhysics(entity, SpeedLevels.FAST, SpeedLevels.FAST, true, true);
        linear.setMovement(Direction.RIGHT);
        entity.updateState();
        entity.updateState();
        assertEquals(new Position2D(2 * Speed.FAST.getValue(), 0), entity.getPosition());
        entity.updateState();   //Causes a collision and reverses speed on x
        assertEquals(new Position2D(Speed.FAST.getValue(), 0), entity.getPosition());
        linear.stopOnX();
        linear.setMovement(Direction.UP);
        entity.updateState();   //Causes a collision and reverses speed on y
        assertEquals(new Position2D(Speed.FAST.getValue(), Speed.FAST.getValue()), entity.getPosition());
    }

    private final class EntityNoCollisions implements GameEntity {

        private Position position;

        private EntityNoCollisions(final Position pos) {
            this.position = pos;
            linear = new LinearPhysics(this, SpeedLevels.SLOW, SpeedLevels.SLOW, false, false);
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
                this.position = linear.calculateMovement();
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
            linear = new LinearPhysics(this, SpeedLevels.SLOW, SpeedLevels.SLOW, false, false);
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
                this.position = linear.calculateMovement();
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
                return counter >= 2 ? Set.of(new Coll(Direction.UP), new Coll(Direction.RIGHT)) : Set.of();
            }

            @Override
            public Boundaries getBoundaries() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'getBoundaries'");
            }
    }

    private final class Coll implements Collision {

        private Direction dir;

        private Coll(final Direction dir) {
            this.dir = dir;
        }

        @Override
        public GameEntity getGameEntity() {
            return new EntityNoCollisions(null);
        }

        @Override
        public Direction getDirection() {
            return this.dir;
        }

    }
}
