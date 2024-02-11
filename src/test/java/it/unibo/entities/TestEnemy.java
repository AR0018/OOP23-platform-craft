package it.unibo.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import it.unibo.model.entities.api.Enemy;
import it.unibo.model.entities.api.GameEntity;
import it.unibo.model.entities.api.MapElement;
import it.unibo.model.entities.impl.CharacterImpl;
import it.unibo.model.entities.impl.MapElementImpl;
import it.unibo.model.entities.impl.SimpleEnemyImpl;
import it.unibo.model.entities.impl.StrongEnemyImpl;
import it.unibo.model.level.api.GameState;
import it.unibo.model.level.api.Level;
import it.unibo.model.physics.api.Direction;
import it.unibo.model.physics.api.Position;
import it.unibo.model.physics.impl.Position2D;
import it.unibo.model.entities.api.Character;

/**
 * Class for testing the behaviour of enemies.
 */
public class TestEnemy {        //TODO: modificare le posizioni perche devono essere double
                                //dipendono dalla velocità settata
    private Character player;
    private Enemy enemy;
    private MapElement map;
    private MapElement map1;
    private MapElement map2;
    private Level level = new Lv();

    @Test
    void testEnemyPos() {
        this.enemy = new SimpleEnemyImpl(new Position2D(0, 0), level);
        this.level.addGameEntity(enemy);
        this.enemy.updateState();
        assertEquals(new Position2D(1, 0), this.enemy.getPosition());

        this.enemy = new SimpleEnemyImpl(new Position2D(0, 0), level);
        this.map = new MapElementImpl(new Position2D(1, 0), level);
        this.level.addGameEntity(enemy);
        this.level.addGameEntity(this.map);
        this.enemy.updateState();
        assertEquals(new Position2D(0, 0), this.enemy.getPosition());

        this.level = new Lv();
        this.map = new MapElementImpl(new Position2D(2, 1), level);
        this.map1 = new MapElementImpl(new Position2D(1, 1), level);
        this.enemy = new SimpleEnemyImpl(new Position2D(1, 0), level);
        this.level.addGameEntity(map);
        this.level.addGameEntity(map1);
        this.level.addGameEntity(enemy);
        this.enemy.updateState();
        assertEquals(new Position2D(2, 0), this.enemy.getPosition());
        this.map2 = new MapElementImpl(new Position2D(3, 0), level);
        this.level.addGameEntity(map2);
        this.enemy.updateState();
        assertEquals(new Position2D(1, 0), this.enemy.getPosition());

        this.level = new Lv();
        this.enemy = new StrongEnemyImpl(new Position2D(0, 0), level);
        this.level.addGameEntity(enemy);
        this.enemy.updateState();
        assertEquals(new Position2D(1, 0), this.enemy.getPosition());

        this.level = new Lv();
        this.enemy = new StrongEnemyImpl(new Position2D(0, 0), level);
        this.map = new MapElementImpl(new Position2D(1, 0), level);
        this.level.addGameEntity(enemy);
        this.level.addGameEntity(map);
        this.enemy.updateState();
        assertEquals(new Position2D(0, 0), this.enemy.getPosition());

        this.level = new Lv();
        this.map = new MapElementImpl(new Position2D(2, 1), level);
        this.map1 = new MapElementImpl(new Position2D(1, 1), level);
        this.enemy = new StrongEnemyImpl(new Position2D(1, 0), level);
        this.level.addGameEntity(map);
        this.level.addGameEntity(map1);
        this.level.addGameEntity(enemy);
        this.enemy.updateState();
        assertEquals(new Position2D(2, 0), this.enemy.getPosition());
        this.map2 = new MapElementImpl(new Position2D(3, 0), level);
        this.level.addGameEntity(map2);
        this.enemy.updateState();
        assertEquals(new Position2D(1, 0), this.enemy.getPosition());
    }

    @Test
    void testEnemies() {
        this.map = new MapElementImpl(new Position2D(2, 1), level);
        this.map1 = new MapElementImpl(new Position2D(1, 1), level);
        this.map2 = new MapElementImpl(new Position2D(3, 1), level);
        this.enemy = new SimpleEnemyImpl(new Position2D(2, 0), level);
        this.player = new CharacterImpl(new  Position2D(1, 0), level);
        this.level.addGameEntity(map);
        this.level.addGameEntity(map1);
        this.level.addGameEntity(map2);
        this.level.addGameEntity(enemy);
        this.level.addGameEntity(player);
        this.enemy.updateState();
        assertEquals(new Position2D(3, 0), this.enemy.getPosition());

        this.map = new MapElementImpl(new Position2D(2, 1), level);
        this.map1 = new MapElementImpl(new Position2D(1, 1), level);
        this.map2 = new MapElementImpl(new Position2D(3, 1), level);
        this.enemy = new StrongEnemyImpl(new Position2D(3, 0), level);
        this.player = new CharacterImpl(new  Position2D(1, 0), level);
        this.level.addGameEntity(map);
        this.level.addGameEntity(map1);
        this.level.addGameEntity(map2);
        this.level.addGameEntity(enemy);
        this.level.addGameEntity(player);
        this.enemy.updateState();
        assertEquals(new Position2D(2, 0), this.enemy.getPosition());
        this.player.move(Direction.RIGHT);
        this.enemy.updateState();
        this.player.updateState();
        assertFalse(this.player.isAlive());
    }

    private static final class Lv implements Level {

        private Set<GameEntity> st = new HashSet<>();

        @Override
        public Set<GameEntity> getGameEntities() {
            return st; 
        }

        @Override
        public void addGameEntity(final GameEntity entity) {
            this.st.add(entity);
        }

        @Override
        public void computeChanges() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'computeChanges'");
        }

        @Override
        public void moveCharacter(final Direction dir) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'moveCharacter'");
        }

        @Override
        public void addFinishLocation(final Position position) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'addFinishLocation'");
        }

        @Override
        public GameState getGameState() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'getGameState'");
        }

        @Override
        public GameEntity getCharacter() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'getCharacter'");
        }

    }
}
