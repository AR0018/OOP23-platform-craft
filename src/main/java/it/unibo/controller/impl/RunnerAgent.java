package it.unibo.controller.impl;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.IntStream;

import it.unibo.controller.api.Command;
import it.unibo.model.engine.api.Engine;
import it.unibo.model.level.api.GameState;
import it.unibo.view.api.View;

/**
 * Thread that runs the level inside the specified Engine.
 */
public final class RunnerAgent extends Thread {

    //The amount of time (in milliseconds) to wait between a frame and another
    private static final long INTERVAL = 20L;

    private final BlockingQueue<Command> commands;
    private final Engine engine;
    private final View view;

    /**
     * The constructor of this Runner.
     * @param engine the engine in which to run the game
     * @param view the view in which to show the results
     * @throws NullPointerException if engine or view are null
     */
    public RunnerAgent(final Engine engine, final View view) {
        this.engine = Objects.requireNonNull(engine);
        this.view = Objects.requireNonNull(view);
        this.commands = new LinkedBlockingQueue<>();
    }

    @Override
    public void run() {
        while (!isInterrupted() && !isOver()) {
            executeCommands();
            this.engine.updateLevel();
            this.view.render(engine.getLevelEntities());
            waitForNextFrame();
        }
        finish();
    }

    private boolean isOver() {
        return switch (this.engine.getGameState()) {
            case WIN, GAMEOVER -> true;
            case RUNNING -> false;
        };
    }

    /*
     * If the state of the game is WIN or GAMEOVER, tells the View to show the right window.
     */
    private void finish() {
        if (isOver()) {
            if (this.engine.getGameState() == GameState.WIN) {
                this.view.displayWin();
            } else {
                this.view.displayGameOver();
            }
        }
    }

    private void executeCommands() {
        final int numCommands = this.commands.size();
        IntStream.range(0, numCommands)
            .forEach(i -> {
                try {
                this.commands.take().execute(this.engine);
                } catch (final InterruptedException e) {
                    interrupt();
                }
            });
    }

    /*
     * Puts this thread in a sleep until the next frame needs to start.
     * The sleep time in milliseconds is specified in constant INTERVAL.
     */
    private void waitForNextFrame() {
        try {
            Thread.sleep(INTERVAL);
        } catch (final InterruptedException e) {
            interrupt();
        }
    }

    /**
     * Adds a command to this runner's command queue.
     * @param command the command to add
     * @throws NullPointerException if command is null
     */
    protected void notifyCommand(final Command command) {
        try {
            this.commands.put(Objects.requireNonNull(command));
        } catch (final InterruptedException e) {
            interrupt();
        }
    }
}
