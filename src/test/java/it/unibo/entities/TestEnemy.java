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
import it.unibo.model.physics.api.SpeedLevels;
import it.unibo.model.physics.impl.Position2D;
import it.unibo.common.EntityType;
import it.unibo.model.collisions.api.MapBoundaries;
import it.unibo.model.entities.api.Character;

/**
 * Class for testing the behaviour of enemies.
 */

class TestEnemy {

    private static final double ACCELERATION = 0.1;
    private static final double MAPBOUNDS = 500f;
    private Character player;
    private Enemy enemy;
    private Level level = new Lv();

    @Test
    void testEnemyPos() {

        this.level = new Lv();
        this.enemy = new SimpleEnemyImpl(new Position2D(ACCELERATION, ACCELERATION), this.level);
        //this.player = new CharacterImpl(new Position2D(2, 0), this.level);
        this.player = new CharacterImpl(new Position2D(2 * EntityType.CHARACTER.getWidth(), 0), this.level);
        this.level.addGameEntity(this.enemy);
        this.level.addGameEntity(this.player);
        this.enemy.updateState();
        assertEquals(new Position2D(SpeedLevels.SLOW.getValue() + ACCELERATION, ACCELERATION), this.enemy.getPosition());

        this.level = new Lv();
        this.enemy = new SimpleEnemyImpl(new Position2D(ACCELERATION, 0), this.level);
        //this.player = new CharacterImpl(new Position2D(SpeedLevels.SLOW.getValue(), 0), this.level);
        this.player = new CharacterImpl(new Position2D(ACCELERATION + EntityType.SIMPLE_ENEMY.getWidth() - 1, 0), this.level);
        this.level.addGameEntity(this.enemy);
        this.level.addGameEntity(this.player);
        this.enemy.updateState();
        this.player.updateState();
        assertEquals(new Position2D(ACCELERATION, 0), this.enemy.getPosition());
        assertTrue(this.enemy.isAlive());
        assertFalse(this.player.isAlive());

        this.level = new Lv();
        this.enemy = new SimpleEnemyImpl(new Position2D(1, 0), this.level);
        //this.player = new CharacterImpl(new Position2D(2 + SpeedLevels.SLOW.getValue() + ACCELERATION, 0), this.level);
        this.player = new CharacterImpl(new Position2D(1 + EntityType.ENEMY.getWidth() 
            + 2 * SpeedLevels.SLOW.getValue(), 0), this.level);
        this.level.addGameEntity(this.enemy);
        this.level.addGameEntity(this.player);
        this.enemy.updateState();
        //assertEquals(new Position2D(1 + SpeedLevels.SLOW.getValue(), 0), this.enemy.getPosition());
        assertEquals(new Position2D(1 + SpeedLevels.SLOW.getValue(), 0), this.enemy.getPosition());
        this.enemy.updateState();
        this.player.updateState();
        assertFalse(this.player.isAlive());
        assertEquals(new Position2D(1 + 2 * SpeedLevels.SLOW.getValue(), ACCELERATION), this.enemy.getPosition());

        this.level = new Lv();
        this.enemy = new StrongEnemyImpl(new Position2D(0, ACCELERATION), this.level);
        this.player = new CharacterImpl(new Position2D(1, 0), this.level);
        this.level.addGameEntity(this.player);
        this.level.addGameEntity(this.enemy);
        this.enemy.updateState();
        this.player.updateState();
        assertFalse(this.player.isAlive());
        assertEquals(new Position2D(0, ACCELERATION), this.enemy.getPosition());

        this.level = new Lv();
        this.enemy = new StrongEnemyImpl(new Position2D(ACCELERATION, 0), this.level);
        this.player = new CharacterImpl(new Position2D(EntityType.ENEMY.getWidth() + ACCELERATION 
            + SpeedLevels.FAST.getValue(), 0), this.level);
        this.level.addGameEntity(this.enemy);
        this.level.addGameEntity(this.player);
        this.enemy.updateState();
        this.player.updateState();
        //pos = ACCELERATION + SpeedLevels.FAST.getValue();                //The enemy doesn't have acceleration on x.
        assertEquals(new Position2D(ACCELERATION + SpeedLevels.FAST.getValue(), 0), this.enemy.getPosition());
        assertFalse(this.player.isAlive());

        this.level = new Lv();
        this.player = new CharacterImpl(new Position2D(1 + EntityType.ENEMY.getWidth(), 0), this.level);
        this.enemy = new StrongEnemyImpl(new Position2D(1, 0), this.level);
        this.level.addGameEntity(this.player);
        this.level.addGameEntity(this.enemy);
        this.enemy.updateState();
        assertEquals(new Position2D(1, 0), this.enemy.getPosition());
    }

    @Test
    void testEnemies() {

        MapElement map;
        MapElement map1;
        MapElement map2;
        MapElement map3;
        this.level = new Lv();
        //this.enemy = new StrongEnemyImpl(new Position2D(MULTIPLIER2, 0), this.level);
        this.enemy = new StrongEnemyImpl(new Position2D(1 + 3 * EntityType.ENEMY.getWidth(), 0), this.level);
        this.player = new CharacterImpl(new  Position2D(1, 0), this.level);
        this.level.addGameEntity(this.enemy);
        this.level.addGameEntity(this.player);
        this.enemy.updateState();
        this.player.move(Direction.RIGHT);
        this.player.updateState();
        //this.pos = MULTIPLIER2 - SpeedLevels.FAST.getValue();
        //assertEquals(new Position2D(MULTIPLIER2 - SpeedLevels.FAST.getValue(), 0), this.enemy.getPosition());
        //assertEquals(new Position2D(1 + ACCELERATION, 0), this.player.getPosition());
        assertEquals(new Position2D(1 + 3 * EntityType.ENEMY.getWidth() - SpeedLevels.FAST.getValue(),
             0), this.enemy.getPosition());
        assertEquals(new Position2D(1 + ACCELERATION, 0), this.player.getPosition());
        assertTrue(this.player.isAlive());
        this.enemy.updateState();
        this.player.move(Direction.LEFT);
        this.player.updateState();
        //this.pos = this.pos - SpeedLevels.FAST.getValue();
        assertEquals(new Position2D(1 + 3 * EntityType.ENEMY.getWidth() - 2 * SpeedLevels.FAST.getValue(),
             ACCELERATION), this.enemy.getPosition());       //0.1 because it falls
        assertEquals(new Position2D(1 + ACCELERATION, ACCELERATION), this.player.getPosition());
        //1.1 because I change instantly the direction

        this.level = new Lv();
        map = new MapElementImpl(new Position2D(1 + EntityType.MAP_ELEMENT.getWidth(),
             EntityType.CHARACTER.getHeigth()), this.level);
        map1 = new MapElementImpl(new Position2D(1, EntityType.CHARACTER.getHeigth()), this.level);
        map2 = new MapElementImpl(new Position2D(1 + 2 * EntityType.MAP_ELEMENT.getWidth(),
             EntityType.CHARACTER.getHeigth()), this.level);
        map3 = new MapElementImpl(new Position2D(4 + ACCELERATION, 1), this.level);
        this.enemy = new SimpleEnemyImpl(new Position2D(1 + 2 * EntityType.CHARACTER.getWidth(), 0), this.level);
        this.player = new CharacterImpl(new  Position2D(1, 0), this.level);
        this.level.addGameEntity(map);
        this.level.addGameEntity(map1);
        this.level.addGameEntity(map2);
        this.level.addGameEntity(this.enemy);
        this.level.addGameEntity(map3);
        this.level.addGameEntity(this.player);
        this.enemy.updateState();
        //this.player.move(Direction.LEFT);
        this.player.updateState();
        //assertEquals(new Position2D(3 + SpeedLevels.SLOW.getValue(), 0), this.enemy.getPosition());
        assertEquals(new Position2D(1 + 2 * EntityType.CHARACTER.getWidth() + SpeedLevels.SLOW.getValue(), 0),
             this.enemy.getPosition());

        this.enemy.updateState();
        assertEquals(new Position2D(1 + 2 * EntityType.CHARACTER.getWidth() + 2 * SpeedLevels.SLOW.getValue(),
            0), this.enemy.getPosition());
        /*this.enemy.updateState();
        assertEquals(new Position2D(3 + 2 * SpeedLevels.SLOW.getValue(), ACCELERATION), this.enemy.getPosition());
        assertEquals(new Position2D(1 - ACCELERATION, 0), this.player.getPosition());
        assertTrue(this.player.isAlive());*/
    }

    private static final class Lv implements Level {

        private final Set<GameEntity> st = new HashSet<>();
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
            return new MapBoundariesimpl(MAPBOUNDS, MAPBOUNDS);
        }

        @Override
        public void setCharacter(final Character character) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'setCharacter'");
        }

        @Override
        public void removeGameEntity(final GameEntity entity) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'removeGameEntity'");
        }
    }
}
