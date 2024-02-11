package it.unibo.controller.impl;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
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
    private static final long INTERVAL = 20;

    private final BlockingQueue<Command> commands;
    private final Engine engine;    //TODO: fix spotbugs "May expose internal representation by storing mutable object"
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
        /*if(!this.engine.levelCreated()) {
            this.engine.createLevel();    EXCEPTION if the configuration is invalid (this method returns false)
        }*/
        while (!isInterrupted() && !isOver()) {
            long time = System.nanoTime();
            executeCommands();
            this.engine.updateLevel();
            this.view.render(engine.getLevelEntities());
            waitForNextFrame(System.nanoTime() - time);
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
        int numCommands = this.commands.size();
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
     * The parameter is the time already elapsed since the beginning of the frame.
     */
    private void waitForNextFrame(final long elapsed) {
        long waitTime = INTERVAL - TimeUnit.NANOSECONDS.toMillis(elapsed);
        try {
            Thread.sleep(waitTime);
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

    //protected void interruptRunning() : method to stop this thread, ending its execution
}
