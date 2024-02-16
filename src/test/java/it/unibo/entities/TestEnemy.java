package it.unibo.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import it.unibo.model.entities.api.Enemy;
import it.unibo.model.entities.api.GameEntity;
import it.unibo.model.entities.api.MapElement;
import it.unibo.model.entities.impl.CharacterImpl;
import it.unibo.model.entities.impl.MapElementImpl;
import it.unibo.model.collisions.impl.MapBoundariesimpl;
import it.unibo.model.entities.impl.SimpleEnemyImpl;
import it.unibo.model.entities.impl.StrongEnemyImpl;
import it.unibo.model.level.api.GameState;
import it.unibo.model.level.api.Level;
import it.unibo.model.physics.api.Direction;
import it.unibo.model.physics.api.Position;
import it.unibo.model.physics.api.SpeedLevels;
import it.unibo.model.physics.impl.Position2D;
import it.unibo.model.collisions.api.MapBoundaries;
import it.unibo.model.entities.api.Character;

/**
 * Class for testing the behaviour of enemies.
 */

public class TestEnemy {

    private static final double ACCELERATION = 0.1;
    private Character player;
    private Enemy enemy;
    private MapElement map;
    private MapElement map1;
    private MapElement map2;
    private Level level = new Lv();
    private double pos;

    @Test
    void testEnemyPos() {
        this.enemy = new SimpleEnemyImpl(new Position2D(0.1, 0.1), this.level);
        this.player = new CharacterImpl(new Position2D(2, 0), this.level);
        this.level.addGameEntity(this.enemy);
        this.level.addGameEntity(this.player);
        this.enemy.updateState();
        assertEquals(new Position2D(SpeedLevels.SLOW.getValue() + 0.1, 0.1), this.enemy.getPosition());

        this.level = new Lv();
        this.enemy = new SimpleEnemyImpl(new Position2D(0.1, 0), this.level);
        this.player = new CharacterImpl(new Position2D(0.3, 0), this.level);
        this.level.addGameEntity(this.enemy);
        this.level.addGameEntity(this.player);
        this.enemy.updateState();
        this.player.updateState();
        assertEquals(new Position2D(0.1, 0), this.enemy.getPosition());
        assertTrue(this.enemy.isAlive());
        assertFalse(this.player.isAlive());

        this.level = new Lv();
        this.enemy = new SimpleEnemyImpl(new Position2D(1, 0), this.level);
        this.player = new CharacterImpl(new Position2D(2.4, 0), this.level);
        this.level.addGameEntity(this.enemy);
        this.level.addGameEntity(this.player);
        this.enemy.updateState();
        this.pos = 1 + SpeedLevels.SLOW.getValue();
        assertEquals(new Position2D(this.pos, 0), this.enemy.getPosition());
        this.enemy.updateState();
        this.player.updateState();
        assertTrue(this.player.isAlive());
        this.pos = this.pos - SpeedLevels.SLOW.getValue();            //Changes the movement direction due the 
        assertEquals(new Position2D(this.pos, 0.1), this.enemy.getPosition());      //collision with the player

        this.level = new Lv();
        this.enemy = new StrongEnemyImpl(new Position2D(0, 0.1), this.level);
        this.player = new CharacterImpl(new Position2D(1, 0), this.level);
        this.level.addGameEntity(this.player);
        this.level.addGameEntity(this.enemy);
        this.enemy.updateState();
        this.player.updateState();
        assertFalse(this.player.isAlive());
        assertEquals(new Position2D(0, ACCELERATION), this.enemy.getPosition());

        this.level = new Lv();
        this.enemy = new StrongEnemyImpl(new Position2D(0.1, 0), this.level);
        this.player = new CharacterImpl(new Position2D(2.1, 0), this.level);
        this.level.addGameEntity(this.enemy);
        this.level.addGameEntity(this.player);
        this.enemy.updateState();
        this.player.updateState();
        pos = 0.1 + SpeedLevels.FAST.getValue();                //The enemy doesn't have acceleration on x.
        assertEquals(new Position2D(this.pos, 0), this.enemy.getPosition());
        assertFalse(this.player.isAlive());

        this.level = new Lv();
        this.player = new CharacterImpl(new Position2D(2, 0), this.level);
        this.enemy = new StrongEnemyImpl(new Position2D(1, 0), this.level);
        this.level.addGameEntity(this.player);
        this.level.addGameEntity(this.enemy);
        this.enemy.updateState();
        this.pos = 1;
        assertEquals(new Position2D(this.pos, 0), this.enemy.getPosition());
    }

    @Test
    void testEnemies() {
        this.level = new Lv();
        this.enemy = new StrongEnemyImpl(new Position2D(6, 0), this.level);
        this.player = new CharacterImpl(new  Position2D(1, 0), this.level);
        this.level.addGameEntity(this.enemy);
        this.level.addGameEntity(this.player);
        this.enemy.updateState();
        this.player.move(Direction.RIGHT);
        this.player.updateState();
        this.pos = 6 - SpeedLevels.FAST.getValue();
        assertEquals(new Position2D(pos, 0), this.enemy.getPosition());
        assertEquals(new Position2D(1.1, 0), this.player.getPosition());
        assertTrue(this.player.isAlive());
        this.enemy.updateState();
        this.player.move(Direction.LEFT);
        this.player.updateState();
        this.pos = this.pos - SpeedLevels.FAST.getValue();
        assertEquals(new Position2D(pos, 0.1), this.enemy.getPosition());       //0.1 because it falls
        assertEquals(new Position2D(1.1, 0.1), this.player.getPosition());    //1.1 because I change instantly the direction

        this.level = new Lv();
        this.map = new MapElementImpl(new Position2D(2, 1), this.level);
        this.map1 = new MapElementImpl(new Position2D(1, 1), this.level);
        this.map2 = new MapElementImpl(new Position2D(3, 1), this.level);
        this.enemy = new SimpleEnemyImpl(new Position2D(3.1, 0), this.level);
        this.player = new CharacterImpl(new  Position2D(1, 0), this.level);
        this.level.addGameEntity(this.map);
        this.level.addGameEntity(this.map1);
        this.level.addGameEntity(this.map2);
        this.level.addGameEntity(this.enemy);
        this.level.addGameEntity(this.player);
        this.enemy.updateState();
        this.player.move(Direction.LEFT);
        this.player.updateState();
        this.pos = 3.4;
        assertEquals(new Position2D(this.pos, 0), this.enemy.getPosition());
        this.enemy.updateState();
        assertEquals(new Position2D(this.pos, ACCELERATION), this.enemy.getPosition());
        this.pos = 0.9;
        assertEquals(new Position2D(this.pos, 0), this.player.getPosition());
        assertTrue(this.player.isAlive());
    }

    private static final class Lv implements Level {

        private Set<GameEntity> st = new HashSet<>();
        private Character ch;
        /*Lv(final Set<GameEntity> entities) {
            st = entities;
        }*/

        @Override
        public Set<GameEntity> getGameEntities() {
            return st; 
        }

        @Override
        public void addGameEntity(final GameEntity entity) {
            if (entity instanceof Character) {
                ch = (CharacterImpl) entity;
            }
            this.st.add(entity);
        }

        @Override
        public void computeChanges() {
            //Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'computeChanges'");
        }

        @Override
        public void moveCharacter(final Direction dir) {
            //Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'moveCharacter'");
        }

        @Override
        public void addFinishLocation(final Position position) {
            //Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'addFinishLocation'");
        }

        @Override
        public GameState getGameState() {
            //Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'getGameState'");
        }

        @Override
        public GameEntity getCharacter() {
            return ch;
        }

        @Override
        public MapBoundaries getBoundaries() {
            return new MapBoundariesimpl(50, 50);
        }
    }
}
