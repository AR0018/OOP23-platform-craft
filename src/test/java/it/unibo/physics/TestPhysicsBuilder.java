package it.unibo.physics;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
import it.unibo.model.physics.impl.LinearPhysics;
import it.unibo.model.physics.impl.PhysicsBuilderImpl;
import it.unibo.model.physics.impl.Position2D;

/**
 * Test class for PhysicsBuilder.
 */
public class TestPhysicsBuilder {

    private static final double ACCELERATION = 0.1; //Value may change

    @Test
    void testCorrectBehaviour() {
        /*
         * Test linear movement with default speed.
         */
        Physics linear = new PhysicsBuilderImpl().setGameEntity(new Entity(new Position2D(0, 0))).create();
        linear.setMovement(Direction.RIGHT);
        linear.setMovement(Direction.DOWN);
        assertEquals(new Position2D(SpeedLevels.MEDIUM.getValue(), SpeedLevels.MEDIUM.getValue()), linear.calculateMovement());
        /*
         * Test linear movement with modified speed.
         */
        linear = new PhysicsBuilderImpl()
            .setGameEntity(new Entity(new Position2D(0, 0)))
            .setSpeedOnX(SpeedLevels.FAST)
            .setSpeedOnY(SpeedLevels.SLOW)
            .create();
        linear.setMovement(Direction.RIGHT);
        linear.setMovement(Direction.DOWN);
        assertEquals(new Position2D(SpeedLevels.FAST.getValue(), SpeedLevels.SLOW.getValue()), linear.calculateMovement());
        /*
         * Test accelerated movement without falling.
         */
        Physics accelerated = new PhysicsBuilderImpl()
            .setGameEntity(new Entity(new Position2D(0, 0)))
            .addAccelerationOnX()
            .create();
        accelerated.setMovement(Direction.RIGHT);
        accelerated.setMovement(Direction.DOWN);
        assertEquals(new Position2D(ACCELERATION, SpeedLevels.MEDIUM.getValue()), accelerated.calculateMovement());
        /*
         * Test accelerated movement with falling, but no acceleration on X.
         */
        accelerated = new PhysicsBuilderImpl()
            .setGameEntity(new Entity(new Position2D(0, 0)))
            .addFallingPhysics()
            .create();
        accelerated.setMovement(Direction.RIGHT);
        assertEquals(new Position2D(SpeedLevels.MEDIUM.getValue(), 0), accelerated.calculateMovement());
        assertEquals(new Position2D(SpeedLevels.MEDIUM.getValue(), ACCELERATION), accelerated.calculateMovement());
         /*
         * Test accelerated movement with falling and acceleration on X.
         */
        Entity entity = new Entity(new Position2D(0, 0));
        accelerated = new PhysicsBuilderImpl()
            .setGameEntity(entity)
            .addAccelerationOnX()
            .addFallingPhysics()
            .create();
        accelerated.setMovement(Direction.RIGHT);
        entity.position = accelerated.calculateMovement();
        assertEquals(new Position2D(ACCELERATION, 0), entity.position);
        accelerated.stopOnX();
        assertEquals(new Position2D(ACCELERATION, ACCELERATION), accelerated.calculateMovement());
    }

    @Test
    void testWithCollisions() {
        /*
         * Test stopping on x and y
         */
        Physics physics = new PhysicsBuilderImpl()
            .setGameEntity(new EntityWithCollisions(new Position2D(0, 0)))
            .create();
        physics.setMovement(Direction.RIGHT);
        physics.setMovement(Direction.UP);
        assertEquals(new Position2D(0, 0), physics.calculateMovement()); //The position doesn't change
        /*
         * Test bouncing on x and y
         */
        physics = new PhysicsBuilderImpl()
            .setGameEntity(new EntityWithCollisions(new Position2D(0, 0)))
            .addBouncingOnX()
            .addBouncingOnY()
            .create();
        physics.setMovement(Direction.RIGHT);
        physics.setMovement(Direction.UP);
        assertEquals(new Position2D(-SpeedLevels.MEDIUM.getValue(), SpeedLevels.MEDIUM.getValue()), physics.calculateMovement());
    }

    @Test
    void testIntantiation() {
        final Physics linear = new PhysicsBuilderImpl().setGameEntity(new Entity()).create();
        assertTrue(linear instanceof LinearPhysics);
        final Physics accelerated = new PhysicsBuilderImpl().setGameEntity(new Entity()).addAccelerationOnX().create();
        assertTrue(accelerated instanceof AcceleratedPhysics);
    }

    @Test
    void testExceptions() {
        assertThrows(IllegalStateException.class, () -> new PhysicsBuilderImpl().create());
        assertThrows(NullPointerException.class, () -> new PhysicsBuilderImpl().setGameEntity(null));
        assertThrows(NullPointerException.class, () -> new PhysicsBuilderImpl().setSpeedOnX(null));
        assertThrows(NullPointerException.class, () -> new PhysicsBuilderImpl().setSpeedOnX(null));
    }

    private static final class Entity implements GameEntity {

        private Position position;

        private Entity() {
            this(null);
        }

        private Entity(final Position pos) {
            this.position = pos;
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

    private static final class EntityWithCollisions implements GameEntity {

        private Position position;

        private EntityWithCollisions(final Position pos) {
            this.position = pos;
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
            }

            @Override
            public EntityType getType() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'getType'");
            }

            /*
             * Sets a collision to the right and one upwards.
             */
            @Override
            public Set<Collision> getCollisions() {
                return Set.of(new Coll(Direction.RIGHT), new Coll(Direction.UP));
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
        public GameEntity getGameEntity() {
            return new Entity(null);
        }

        @Override
        public Direction getDirection() {
            return this.dir;
        }

    }

}
