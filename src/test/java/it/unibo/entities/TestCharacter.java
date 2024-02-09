package it.unibo.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import it.unibo.model.entities.api.Character;
import it.unibo.model.entities.api.GameEntity;
import it.unibo.model.entities.impl.CharacterImpl;
import it.unibo.model.entities.impl.EnemyImpl;
import it.unibo.model.entities.impl.MapElementImpl;
import it.unibo.model.entities.impl.SimpleEnemyImpl;
import it.unibo.model.entities.impl.TrapImpl;
import it.unibo.model.level.api.Level;
import it.unibo.model.physics.api.Direction;
import it.unibo.model.physics.api.Physics;
import it.unibo.model.physics.api.PhysicsBuilder;
import it.unibo.model.physics.api.Position;
import it.unibo.model.physics.api.SpeedLevels;
import it.unibo.model.physics.impl.Position2D;
import it.unibo.model.collisions.api.Boundaries;
import it.unibo.model.collisions.api.Collision;
import it.unibo.model.collisions.api.CollisionBox;

//TODO: bisogna attendere prima le collisioni e la fisica
/**
 * Class for testing the behaviour of the character.
 */
public class TestCharacter {

    private Character player;
    private EnemyImpl enemy;
    private MapElementImpl mapElement;
    private Level level;
    private Set<GameEntity> entities = new HashSet<>();
    private CollisionBox box = new Box();
    private Set<Collision> collision;

    @Test
    void testCharacter() {
        this.player = new CharacterImpl(new Position2D(0, 1), level);
        this.enemy = new SimpleEnemyImpl(new Position2D(1, 1), level);
        this.mapElement = new MapElementImpl(new Position2D(0, 0), level);
        this.level.addGameEntity(player);
        this.level.addGameEntity(enemy);
        this.level.addGameEntity(mapElement);
        this.player.getCollisions();
        assertEquals(false, this.player.isAlive());
    }

    @Test
    void testEnemiesCollision() {
        
    }

    private class Coll implements Collision {

        private Direction dir;
        private GameEntity entity;

        private  Coll(final Direction dir, final GameEntity entity) {
            this.dir = dir;
            this.entity = entity;
        }

        @Override
        public GameEntity getGameEntity() {
            return this.entity;
        }

        @Override
        public Direction getDirection() {
            return this.dir;
        }

    }

    private class Box implements CollisionBox {

        @Override
        public Set<Collision> getCollisions(Set<GameEntity> entities) {
            return Set.of(new Coll(Direction.RIGHT, new SimpleEnemyImpl(null, level)));
        }

        @Override
        public boolean isCollidingWith(GameEntity object) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'isCollidingWith'");
        }

        @Override
        public Boundaries getBoundaries() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'isCollidingWith'");
        }

    }

    private class ph implements PhysicsBuilder {

        @Override
        public PhysicsBuilder setGameObject(GameEntity obj) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'setGameObject'");
        }

        @Override
        public PhysicsBuilder setSpeedOnX(SpeedLevels speed) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'setSpeedOnX'");
        }

        @Override
        public PhysicsBuilder setSpeedOnY(SpeedLevels speed) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'setSpeedOnY'");
        }

        @Override
        public PhysicsBuilder addBouncingOnX() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'addBouncingOnX'");
        }

        @Override
        public PhysicsBuilder addBouncingOnY() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'addBouncingOnY'");
        }

        @Override
        public PhysicsBuilder addAccelerationOnX() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'addAccelerationOnX'");
        }

        @Override
        public PhysicsBuilder addFallingPhysics() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'addFallingPhysics'");
        }

        @Override
        public Physics create() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'create'");
        }

    }
}
