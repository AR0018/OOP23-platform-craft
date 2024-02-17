package it.unibo.controller.impl;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.controller.api.Controller;
import it.unibo.controller.api.LevelEditor;
import it.unibo.controller.api.LevelRunner;
import it.unibo.view.api.View;
import it.unibo.view.impl.ViewImpl;

/**
 * Models the Controller of the game where it decides wethever
 * to start the TitleScreen, the Editor or the Level.
 */
public final class ControllerImpl implements Controller {

    private final LevelEditor levelEditor;
    private final LevelRunner levelRunner;
    private final View view;
    private boolean startIsValid = true;

    /**
     * Constructor of the Controller.
     */
    public ControllerImpl() {
        this.levelEditor = new LevelEditorImpl();
        this.view = new ViewImpl(this, 
                this.levelEditor.getLevelWidth(), 
                this.levelEditor.getLevelHeight());
        this.levelRunner = new LevelRunnerImpl(this.view);
    }

    @Override
    public void start() {
        if (this.startIsValid) {
            this.view.displayStart();
            this.startIsValid = false;
        } else {
            throw new IllegalStateException("The start method cannot be invoked anymore");
        }
    }

    @Override
    public LevelRunner getRunner() {
        return this.levelRunner;
    }

    @SuppressFBWarnings(
        value = {
            "EI_EXPOSE_REP"
        },
        justification = "By design, we want other parts of the application to be able to see the distinction "
            + "between the two parts of the Controller: the one designed to run the Level, and the one designed "
            + "to run the Level editor, and access each part separately. This way, a class that is trying to use "
            + "the services provided by the LevelEditor will access directly to it by using its reference."
    )

    @Override
    public LevelEditor getEditor() {
        return this.levelEditor;
    }
}
