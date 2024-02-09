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
import it.unibo.model.level.api.GameState;
import it.unibo.model.level.api.Level;
import it.unibo.model.physics.api.Direction;
import it.unibo.model.physics.api.Position;
import it.unibo.model.physics.impl.Position2D;

//TODO: bisogna attendere prima le collisioni e la fisica
/**
 * Class for testing the behaviour of the character.
 */
public class TestCharacter {

    private Character player;
    private EnemyImpl enemy;
    private MapElementImpl mapElement;
    private Level level = new Lv();

    @Test
    void testCharacter() {
        this.player = new CharacterImpl(new Position2D(0, 0), level); //y> scendi 
        this.enemy = new SimpleEnemyImpl(new Position2D(1, 0), level);
        this.level.addGameEntity(player);
        this.level.addGameEntity(enemy);
        this.player.updateState();
        assertEquals(false, this.player.isAlive());

        this.level = new Lv();
        this.player = new CharacterImpl(new Position2D(1, 0), level);
        this.enemy = new SimpleEnemyImpl(new Position2D(1, 1), level);
        this.level.addGameEntity(player);
        this.level.addGameEntity(enemy);
        this.player.updateState();
        assertEquals(true, this.player.isAlive());

        this.level = new Lv();
        this.player = new CharacterImpl(new Position2D(0, 0), level);
        this.enemy = new SimpleEnemyImpl(new Position2D(0, 1), level);
        this.level.addGameEntity(player);
        this.level.addGameEntity(enemy);
        this.player.getCollisions();
        assertEquals(true, this.player.isAlive());

        this.level = new Lv();
        this.player = new CharacterImpl(new Position2D(0, 1), level);
        this.enemy = new SimpleEnemyImpl(new Position2D(0, 0), level);
        this.level.addGameEntity(player);
        this.level.addGameEntity(enemy);
        this.player.getCollisions();
        assertEquals(false, this.player.isAlive());
    }

    private class Lv implements Level {

        private Set<GameEntity> st = new HashSet<>();

        @Override
        public Set<GameEntity> getGameEntities() {
            return st; 
        }

        @Override
        public void addGameEntity(GameEntity entity) {
            this.st.add(entity);
        }

        @Override
        public void computeChanges() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'computeChanges'");
        }

        @Override
        public void moveCharacter(Direction dir) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'moveCharacter'");
        }

        @Override
        public void addFinishLocation(Position position) {
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
