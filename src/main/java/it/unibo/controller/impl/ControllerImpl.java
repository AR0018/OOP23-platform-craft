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

    @Override
    public void start() {
        new ViewImpl().displayStart();
    }

    @Override
    public LevelRunner getRunner() {        //TODO: return new LevelRunnerImpl.run();
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getRunner'");
    }

    @Override
    public LevelEditor getEditor() {        //TODO: return new LevelEditorImpl.run();
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getEditor'");
    }
}
