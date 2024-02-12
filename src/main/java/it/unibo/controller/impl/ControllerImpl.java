package it.unibo.controller.impl;

import it.unibo.common.SimpleEntity;
import it.unibo.controller.api.Controller;
import it.unibo.controller.api.LevelEditor;
import it.unibo.controller.api.LevelRunner;
import it.unibo.model.engine.api.Engine;
import it.unibo.view.impl.ViewImpl;
import java.lang.IllegalStateException;

import java.util.Set;

/**
 * Models the Controller of the game where it decides wethever
 * to start the TitleScreen, the Editor or the Level.
 */
public final class ControllerImpl implements Controller {

    private final LevelEditor levelEditor;
    //private final LevelRunner levelRunner;
    private Set<SimpleEntity> entities;
    private boolean startIsValid = true;    //true se si può invocare start, false no
    private Engine engine;

    public ControllerImpl() {   //final Engine engine newl costruttore?
        //this.engine = engine;
        this.levelEditor = new LevelEditorImpl(this);
        //this.levelRunner = new LevelRunnerImpl();
        //this.entities = this.engine.getLevelEntities();
    }

    @Override
    public void start() {       //TODO: controllare che venga invocato solo una volta boolean tira eccezione
        if (this.startIsValid) {
            new ViewImpl(this).displayStart();
            this.startIsValid = false;
        } else {
            throw new IllegalStateException("The start method cannot be invoked anymore");
        }
    }

    @Override
    public LevelRunner getRunner() {        //TODO: return new LevelRunnerImpl.run();
        //  Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getRunner'");
    }

    @Override
    public LevelEditor getEditor() {        //TODO: return new LevelEditorImpl.run(this);
        return this.levelEditor;
    }
}
