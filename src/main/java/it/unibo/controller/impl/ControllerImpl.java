package it.unibo.controller.impl;

import it.unibo.controller.api.Controller;
import it.unibo.controller.api.LevelEditor;
import it.unibo.controller.api.LevelRunner;
import it.unibo.view.impl.ViewImpl;

/**
 * Models the Controller of the game where it decides wethever
 * to start the TitleScreen, the Editor or the Level.
 */
public final class ControllerImpl implements Controller {

    private final LevelEditor levelEditor;
    //private final LevelRunner levelRunner;
    private boolean startIsValid = true;    //true se si può invocare start, false no

    /**
     * Constructor of the Controller.
     */
    public ControllerImpl() {   //final Engine engine nel costruttore?
        //this.engine = engine;
        this.levelEditor = new LevelEditorImpl();
        //this.levelRunner = new LevelRunnerImpl();
        //this.entities = this.engine.getLevelEntities();
    }

    @Override
    public void start() {
        if (this.startIsValid) {
            new ViewImpl(this).displayStart();
            this.startIsValid = false;
        } else {
            throw new IllegalStateException("The start method cannot be invoked anymore");
        }
    }

    @Override
    public LevelRunner getRunner() {        //TODO: runner
        //  Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getRunner'");
    }

    @Override
    public LevelEditor getEditor() {
        return this.levelEditor;
    }
}
