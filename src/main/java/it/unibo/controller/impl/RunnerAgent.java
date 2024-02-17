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
        //TODO: test System.out.println("Agent: entita' del mio engine: " + this.engine.getLevelEntities());
        //TODO: test System.out.println("Agent: inizio loop");
        while (!isInterrupted() && !isOver()) {
            long time = System.nanoTime();
            //TODO: test System.out.println("Agent: continuo loop"); 
            executeCommands();
            this.engine.updateLevel();
            this.view.render(engine.getLevelEntities());
            waitForNextFrame(System.nanoTime() - time);
        }
        //TODO: test System.out.println("Agent: esco dal loop");
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
        //System.out.println("Agent: elapsed: " + TimeUnit.NANOSECONDS.toMillis(elapsed));
        //TODO: test
        //long waitTime = INTERVAL - TimeUnit.NANOSECONDS.toMillis(elapsed); TODO: causes negative value
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
            //TODO: test System.out.println("Agent: ricevo il comando");
            this.commands.put(Objects.requireNonNull(command));
        } catch (final InterruptedException e) {
            interrupt();
        }
    }

    //protected void interruptRunning() : method to stop this thread, ending its execution
    //TODO: decide whether to add
}
