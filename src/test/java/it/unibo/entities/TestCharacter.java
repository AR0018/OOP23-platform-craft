package it.unibo.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import it.unibo.common.EntityType;
import it.unibo.model.collisions.api.MapBoundaries;
import it.unibo.model.collisions.impl.MapBoundariesimpl;
import it.unibo.model.entities.api.Character;
import it.unibo.model.entities.api.GameEntity;
import it.unibo.model.entities.api.MapElement;
import it.unibo.model.entities.impl.CharacterImpl;
import it.unibo.model.entities.impl.EnemyImpl;
import it.unibo.model.entities.impl.MapElementImpl;
import it.unibo.model.entities.impl.SimpleEnemyImpl;
import it.unibo.model.entities.impl.StrongEnemyImpl;
import it.unibo.model.entities.impl.TrapImpl;
import it.unibo.model.level.api.GameState;
import it.unibo.model.level.api.Level;
import it.unibo.model.physics.api.Direction;
import it.unibo.model.physics.api.Position;
import it.unibo.model.physics.api.SpeedLevels;
import it.unibo.model.physics.impl.Position2D;

/**
 * Class for testing the behaviour of the character.
 */

public class TestCharacter {

    private static final long TIMER = 3000;
    private static final double ACCELERATION = 0.1;
    private static final double MAPBOUNDS = 500f;
    private Character player;
    private MapElement map1;
    private MapElement map2;
    private EnemyImpl enemy;
    private TrapImpl trap;
    private Position pos;
    private Level level = new Lv();

    @Test
    void testCharacterSimpleEnemy() {

        /*
         * The character instantly dies because the dimensions of
         * its collision box are 1,1
         */
        this.player = new CharacterImpl(new Position2D(0, 0), this.level);
        this.enemy = new SimpleEnemyImpl(new Position2D(EntityType.CHARACTER.getWidth() - 1, 0), this.level);
        this.map1 = new MapElementImpl(new Position2D(0, EntityType.CHARACTER.getHeigth()), this.level);
        this.map2 = new MapElementImpl(new Position2D(EntityType.CHARACTER.getWidth(),
            EntityType.SIMPLE_ENEMY.getHeigth()), this.level);
        this.level.addGameEntity(this.player);
        this.level.addGameEntity(this.enemy);
        this.level.addGameEntity(this.map1);
        this.level.addGameEntity(this.map2);
        this.player.updateState();
        assertFalse(this.player.isAlive());
        assertTrue(this.enemy.isAlive());

        this.level = new Lv();
        this.player = new CharacterImpl(new Position2D(1, SpeedLevels.SLOW.getValue()), this.level);
        this.enemy = new SimpleEnemyImpl(new Position2D(1, EntityType.CHARACTER.getHeigth()), this.level);
        this.map1 = new MapElementImpl(new Position2D(1, EntityType.SIMPLE_ENEMY.getHeigth()), this.level);
        this.level.addGameEntity(this.player);
        this.level.addGameEntity(this.enemy);
        this.level.addGameEntity(this.map1);
        this.player.updateState();
        this.enemy.updateState();
        assertTrue(this.player.isAlive());
        assertFalse(this.enemy.isAlive());

        this.level = new Lv();
        //this.player = new CharacterImpl(new Position2D(2 + SpeedLevels.MEDIUM.getValue() - ACCELERATION, 0), this.level);
        this.player = new CharacterImpl(new Position2D(4 * EntityType.SIMPLE_ENEMY.getWidth(), 0), this.level);
        this.enemy = new SimpleEnemyImpl(new Position2D(0, 0), this.level);
        this.map1 = new MapElementImpl(new Position2D(EntityType.SIMPLE_ENEMY.getWidth() + 2, 0), this.level);
        //this.map1 = new MapElementImpl(new Position2D(1 + SpeedLevels.SLOW.getValue(), 0), this.level);
        this.level.addGameEntity(this.player);
        this.level.addGameEntity(this.enemy);
        this.level.addGameEntity(this.map1);
        this.player.updateState();
        assertTrue(this.player.isAlive());
        assertTrue(this.enemy.isAlive());

        this.level = new Lv();
        //this.player = new CharacterImpl(new Position2D(0, 1), this.level);
        //this.enemy = new SimpleEnemyImpl(new Position2D(0, 0), this.level);
        //this.map1 = new MapElementImpl(new Position2D(0, 2), this.level);
        this.player = new CharacterImpl(new Position2D(0, EntityType.SIMPLE_ENEMY.getHeigth()), this.level);
        this.enemy = new SimpleEnemyImpl(new Position2D(0, 0), this.level);
        this.map1 = new MapElementImpl(new Position2D(0, EntityType.SIMPLE_ENEMY.getHeigth() 
            + EntityType.CHARACTER.getHeigth()), this.level);
        this.level.addGameEntity(this.player);
        this.level.addGameEntity(this.enemy);
        this.level.addGameEntity(this.map1);
        this.player.updateState();
        assertFalse(this.player.isAlive());
        assertTrue(this.enemy.isAlive());

        this.level = new Lv();
        this.player = new CharacterImpl(new Position2D(0, 1), this.level);
        this.enemy = new SimpleEnemyImpl(new Position2D(2 * EntityType.CHARACTER.getWidth(), 0), this.level);
        this.level.addGameEntity(this.player);
        this.level.addGameEntity(this.enemy);
        this.player.updateState();
        this.enemy.updateState();
        //this.pos = new Position2D(2 + SpeedLevels.SLOW.getValue(), 0);
        this.pos = new Position2D(2 * EntityType.CHARACTER.getWidth() + SpeedLevels.SLOW.getValue(), 0);
        assertTrue(this.player.isAlive());
        assertEquals(this.pos, this.enemy.getPosition());
        this.pos = new Position2D(0, 1);
        assertEquals(this.pos, this.player.getPosition());          //Without movement direction
        assertTrue(this.enemy.isAlive());
        this.player.move(Direction.RIGHT);
        this.player.updateState();
        //this.pos = new Position2D(ACCELERATION, 1 + ACCELERATION);           //The Character has the accelerated physic 
        this.pos = new Position2D(ACCELERATION, 1 + ACCELERATION);
        assertEquals(this.pos, this.player.getPosition());
    }

    @Test
    void testCharacterEnemy() {

        /*
         * The character instantly dies because the dimensions of
         * its collision box are 1,1
         */
        this.level = new Lv();
        this.player = new CharacterImpl(new Position2D(0, 0), this.level);
        this.enemy = new StrongEnemyImpl(new Position2D(EntityType.CHARACTER.getWidth(), 0), this.level);
        this.map1 = new MapElementImpl(new Position2D(0, EntityType.CHARACTER.getHeigth()), this.level);
        this.map2 = new MapElementImpl(new Position2D(EntityType.MAP_ELEMENT.getHeigth(),
            EntityType.ENEMY.getHeigth()), this.level);
        this.level.addGameEntity(this.player);
        this.level.addGameEntity(this.enemy);
        this.level.addGameEntity(this.map1);
        this.level.addGameEntity(this.map2);
        this.player.updateState();
        assertFalse(this.player.isAlive());
        assertTrue(this.enemy.isAlive());

        this.level = new Lv();
        this.player = new CharacterImpl(new Position2D(1, SpeedLevels.SLOW.getValue()), this.level);
        this.enemy = new StrongEnemyImpl(new Position2D(1 + ACCELERATION, EntityType.CHARACTER.getHeigth()), this.level);
        this.map1 = new MapElementImpl(new Position2D(1, EntityType.CHARACTER.getHeigth() 
            + EntityType.ENEMY.getHeigth()), this.level);
        this.level.addGameEntity(this.player);
        this.level.addGameEntity(this.enemy);
        this.level.addGameEntity(this.map1);
        this.player.updateState();
        this.enemy.updateState();
        assertTrue(this.player.isAlive());
        assertFalse(this.enemy.isAlive());

        this.level = new Lv();
        //this.player = new CharacterImpl(new Position2D(2 + SpeedLevels.MEDIUM.getValue() - ACCELERATION, 0), this.level);
        this.player = new CharacterImpl(new Position2D(EntityType.ENEMY.getWidth() 
            + SpeedLevels.MEDIUM.getValue(), 0), this.level);
        this.enemy = new StrongEnemyImpl(new Position2D(0, 0), this.level);
        this.level.addGameEntity(this.player);
        this.level.addGameEntity(this.enemy);
        this.player.updateState();
        assertTrue(this.player.isAlive());
        assertTrue(this.enemy.isAlive());
        this.enemy.updateState();
        this.enemy.updateState();
        this.player.updateState();
        assertFalse(this.player.isAlive());


        this.level = new Lv();
        this.player = new CharacterImpl(new Position2D(0, EntityType.ENEMY.getHeigth()), this.level);
        this.enemy = new StrongEnemyImpl(new Position2D(0, 0), this.level);
        this.map1 = new MapElementImpl(new Position2D(0, EntityType.ENEMY.getHeigth() 
            + EntityType.CHARACTER.getHeigth()), this.level);
        this.level.addGameEntity(this.player);
        this.level.addGameEntity(this.enemy);
        this.level.addGameEntity(this.map1);
        this.player.updateState();
        assertFalse(this.player.isAlive());
        assertTrue(this.enemy.isAlive());

        this.level = new Lv();
        this.player = new CharacterImpl(new Position2D(0, 1), this.level);
        //this.enemy = new StrongEnemyImpl(new Position2D(2 + ACCELERATION, 0), this.level);
        this.enemy = new StrongEnemyImpl(new Position2D(2 * EntityType.CHARACTER.getWidth() + ACCELERATION, 0), this.level);
        this.level.addGameEntity(this.player);
        this.level.addGameEntity(this.enemy);
        this.player.updateState();
        this.enemy.updateState();
        //this.pos = new Position2D(1 + ACCELERATION, 0);
        this.pos = new Position2D(2 * EntityType.CHARACTER.getWidth() + ACCELERATION - SpeedLevels.FAST.getValue(), 0);
        assertTrue(this.player.isAlive());
        assertEquals(this.pos, this.enemy.getPosition());
        this.pos = new Position2D(0, 1);
        assertEquals(this.pos, this.player.getPosition());          //Without movement direction
        assertTrue(this.enemy.isAlive());
        this.player.move(Direction.RIGHT);
        this.player.updateState();
        this.pos = new Position2D(ACCELERATION, 1 + ACCELERATION);           //The Character has the accelerated physic 
        assertEquals(this.pos, this.player.getPosition());
    }

    @Test
    void testCharacterTrap() throws InterruptedException {

        /*
         * The trap sees the player, starts the countdown and explode.
         */
        this.level = new Lv();
        //this.player = new CharacterImpl(new Position2D(2 + 2 * ACCELERATION, 1 + ACCELERATION), this.level);
        //this.trap = new TrapImpl(new Position2D(2, 1), this.level);
        //this.map1 = new MapElementImpl(new Position2D(3, 2), this.level);
        //this.map2 = new MapElementImpl(new Position2D(2, 2), this.level);
        this.player = new CharacterImpl(new Position2D(2 + 2 * ACCELERATION, 1 + ACCELERATION), this.level);
        this.trap = new TrapImpl(new Position2D(2, 1), this.level);
        this.map1 = new MapElementImpl(new Position2D(2 + EntityType.MAP_ELEMENT.getWidth(),
            EntityType.TRAP.getHeigth()), this.level);
        this.map2 = new MapElementImpl(new Position2D(2, EntityType.CHARACTER.getHeigth()), this.level);
        this.level.addGameEntity(this.player);
        this.level.addGameEntity(this.trap);
        this.level.addGameEntity(this.map1);
        this.level.addGameEntity(this.map2);
        this.trap.updateState();
        this.player.updateState();
        assertFalse(this.trap.isLethal());
        Thread.sleep(TIMER);
        this.trap.updateState();
        this.player.updateState();
        assertFalse(this.player.isAlive());
        assertEquals(EntityType.EXPLOSION, this.trap.getType());
        assertTrue(this.trap.isAlive());
        assertTrue(this.trap.isLethal());
        Thread.sleep(TIMER / 3);
        this.trap.updateState();
        assertFalse(this.trap.isAlive());

        /*
         * The trap doesn't see the player.
         */
        this.level = new Lv();
        this.player = new CharacterImpl(new Position2D(1, 1), this.level);
        this.trap = new TrapImpl(new Position2D(2, 1), this.level);
        this.map1 = new MapElementImpl(new Position2D(1, EntityType.CHARACTER.getHeigth()), this.level);
        this.map2 = new MapElementImpl(new Position2D(1 + EntityType.MAP_ELEMENT.getWidth(),
            EntityType.TRAP.getHeigth()), this.level);
        this.level.addGameEntity(this.player);
        this.level.addGameEntity(this.trap);
        this.level.addGameEntity(this.map1);
        this.level.addGameEntity(this.map2);
        this.trap.updateState();
        this.player.updateState();
        assertTrue(this.trap.isAlive());
        Thread.sleep(TIMER);
        this.trap.updateState();
        this.player.updateState();
        assertTrue(this.player.isAlive());
        assertTrue(this.trap.isAlive());

        /*
         * The trap sees the player but the value of TIMER is to low.
         */
        this.level = new Lv();
        this.player = new CharacterImpl(new Position2D(1 + SpeedLevels.MEDIUM.getValue(), 1), this.level);
        this.trap = new TrapImpl(new Position2D(1, 1), this.level);
        this.map1 = new MapElementImpl(new Position2D(1, EntityType.TRAP.getHeigth()), this.level);
        this.map2 = new MapElementImpl(new Position2D(1 + EntityType.MAP_ELEMENT.getWidth(),
            EntityType.CHARACTER.getHeigth()), this.level);
        this.level.addGameEntity(this.player);
        this.level.addGameEntity(this.trap);
        this.level.addGameEntity(this.map1);
        this.level.addGameEntity(this.map2);
        this.trap.updateState();
        this.player.updateState();
        assertTrue(this.trap.isAlive());
        Thread.sleep(TIMER / 2);
        this.trap.updateState();
        this.player.updateState();
        assertTrue(this.player.isAlive());
        assertTrue(this.trap.isAlive());
    }

    @Test
    void testBorder() {

        /*
         * Check the collision with the bottom border.
         */
        this.level = new Lv();
        this.player = new CharacterImpl(new Position2D(2, MAPBOUNDS - 1), this.level);
        this.level.addGameEntity(this.player);
        this.player.updateState();
        assertFalse(this.player.isAlive());

    }

    /**
     * Implementation of the level.
     */
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
