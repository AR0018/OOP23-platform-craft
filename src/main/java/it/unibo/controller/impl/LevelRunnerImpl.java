package it.unibo.controller.impl;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import it.unibo.common.SimpleEntity;
import it.unibo.controller.api.Command;
import it.unibo.controller.api.LevelRunner;
import it.unibo.model.engine.api.Engine;
import it.unibo.model.engine.impl.EditorImpl;
import it.unibo.view.api.View;

/**
 * Implementation of a LevelRunner.
 */
public final class LevelRunnerImpl implements LevelRunner {
    private final View view;
    private Optional<RunnerAgent> runner;
    private Engine engine;
    /*
     * A buffer used to store the initial configuration of the level to be played.
     */
    private Set<SimpleEntity> entityBuffer;
    //True if a level has been correctly loaded, false otherwise.
    private boolean hasLoaded;

    /**
     * The constructor of this LevelRunner.
     * @param view the application view
     */
    public LevelRunnerImpl(final View view) {
        this.view = view;
        this.hasLoaded = false;
        this.runner = Optional.empty();
        this.entityBuffer = new HashSet<>();
    }

    @Override
    public void run() {
        if (this.hasLoaded) {
            //TODO: test System.out.println("Runner: starto l'Agent");
            this.runner = Optional.of(new RunnerAgent(this.engine, this.view));
            this.runner.get().start();
        } else {
            throw new IllegalStateException("No level has been correctly loaded in this LevelRunner");
        }
    }

    @Override
    public void stopLevel() {
        this.runner.get().interrupt();
    }

    @Override
    public void notifyCommand(final Command command) {
        if (this.runner.isPresent()) {
            this.runner.get().notifyCommand(Objects.requireNonNull(command));
        } else {
            throw new IllegalStateException("Cannot invoke notifyCommand: no level has been loaded yet");
        }
    }

    @Override
    public boolean loadLevel(final File file) {
        try {
            Set<SimpleEntity> levelEntities = new SerializerImpl().loadLevel(file);
            Optional<Engine> created = new EditorImpl(levelEntities).createLevel();
            if (created.isPresent()) {
                this.engine = created.get();
                /*
                 * Saves the current configuration for the restart.
                 */
                this.entityBuffer = engine.getLevelEntities();
                this.hasLoaded = true;
                return true;
            }
            return false;
        } catch (final IOException e) {
            return false;
        }
    }

    @Override
    public void restart() {
        if (this.hasLoaded) {
            Optional<Engine> created = new EditorImpl(entityBuffer).createLevel();
            if (created.isPresent()) {  //TODO: probably remove (if the configuration was valid in the beginning, it still is)
                this.engine = created.get();
            } else {
                throw new IllegalStateException("Failed to reload the level");
            }
        } else {
            throw new IllegalStateException("Cannot invoke this method since no level has been loaded");
        }
    }
}
