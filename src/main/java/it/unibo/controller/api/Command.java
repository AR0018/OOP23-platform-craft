package it.unibo.controller.api;

import java.util.function.Consumer;

import it.unibo.model.engine.api.Engine;
import it.unibo.model.physics.api.Direction;

/**
 * Models a Command to the LevelRunner.
 */
public enum Command {
    /**
     * Moves the character in the game upwards.
     */
    MOVE_UP(e -> e.moveCharacter(Direction.UP)),
    /**
     * Moves the character in the game downwards.
     */
    MOVE_DOWN(e -> e.moveCharacter(Direction.DOWN)),
    /**
     * Moves the character in the game to the left.
     */
    MOVE_LEFT(e -> e.moveCharacter(Direction.LEFT)),
    /**
     * Moves the character in the game to the right.
     */
    MOVE_RIGHT(e -> e.moveCharacter(Direction.RIGHT));

    private final Consumer<Engine> operation;

    Command(final Consumer<Engine> operation) {
        this.operation = operation;
    }

    /**
     * Executes this command.
     * @param e the engine on which to execute the command
     */
    public void execute(final Engine e) {
        this.operation.accept(e);
    }
}
