package it.unibo.controller.impl;

import java.io.File;
import java.util.Objects;

import it.unibo.controller.api.Command;
import it.unibo.controller.api.LevelRunner;
import it.unibo.view.api.View;

/**
 * Implementation of a LevelRunner.
 */
public final class LevelRunnerImpl implements LevelRunner {
    //private Engine engine;
    private RunnerAgent runner;

    /**
     * The constructor of this LevelRunner.
     */
    public LevelRunnerImpl(final View view) {
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'run'");
    }

    @Override
    public void notifyCommand(final Command command) {
        this.runner.notifyCommand(Objects.requireNonNull(command));
    }

    @Override
    public void loadLevel(final File file) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'loadFile'");
    }

}
