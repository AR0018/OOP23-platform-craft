package it.unibo.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.model.entities.api.Character;
import it.unibo.model.entities.api.Enemy;
import it.unibo.model.entities.impl.CharacterImpl;
import it.unibo.model.entities.impl.SimpleEnemyImpl;
import it.unibo.model.physics.api.Direction;
import it.unibo.model.physics.impl.Position2D;

//TODO: bisogna attendere prima le collisioni e la fisica
/**
 * Class for testing the behaviour of the character.
 */
public class TestCharacter {

    private Character player;
    private Enemy enemy;

    @BeforeEach
    void start() {
        this.player = new CharacterImpl(new Position2D(0, 0));
    }

    @Test
    void testCharacterMovement() {
        this.player = new CharacterImpl(new Position2D(0, 0));
        this.player.move(Direction.RIGHT);
        assertEquals(new Position2D(0.6, 0), this.player.getPosition());
        this.player.move(Direction.LEFT);
        assertEquals(new Position2D(0, 0), this.player.getPosition());
    }

    @Test
    void testEnemiesCollision() {
        this.enemy = new SimpleEnemyImpl(new Position2D(1, 0));
        this.player.move(Direction.RIGHT);
        this.player.updateState();
        assertEquals(1, this.player.getCollisions().size());
    }

}
