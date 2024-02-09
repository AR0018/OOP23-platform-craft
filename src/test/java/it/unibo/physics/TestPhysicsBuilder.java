package it.unibo.physics;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.common.EntityType;
import it.unibo.model.collisions.api.Boundaries;
import it.unibo.model.collisions.api.Collision;
import it.unibo.model.entities.api.GameEntity;
import it.unibo.model.physics.api.Physics;
import it.unibo.model.physics.api.PhysicsBuilder;
import it.unibo.model.physics.api.Position;
import it.unibo.model.physics.impl.PhysicsBuilderImpl;

public class TestPhysicsBuilder {

    PhysicsBuilder builder;

    @BeforeEach
    void setUp() {
        this.builder = new PhysicsBuilderImpl();
    }

    @Test
    void testCorrectBehaviour() {

    }

    @Test
    void testIntantiation() {
        //Physics linear = this.builder.
    }

    @Test
    void testExceptions() {
        assertThrows(IllegalStateException.class, () -> this.builder.create());
        assertThrows(NullPointerException.class, () -> this.builder.setGameObject(null));
        assertThrows(NullPointerException.class, () -> this.builder.setSpeedOnX(null));
        assertThrows(NullPointerException.class, () -> this.builder.setSpeedOnX(null));
    }

    private final class Entity implements GameEntity {

        @Override
        public Position getPosition() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'getPosition'");
        }

        @Override
        public boolean isAlive() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'isAlive'");
        }

        @Override
        public void updateState() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'updateState'");
        }

        @Override
        public EntityType getType() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'getType'");
        }

        @Override
        public Set<Collision> getCollisions() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'getCollisions'");
        }

        @Override
        public Boundaries getBoundaries() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'getBoundaries'");
        }

    }

}
