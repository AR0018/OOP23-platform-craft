package it.unibo;

import it.unibo.controller.impl.ControllerImpl;

/*
import it.unibo.controller.impl.ControllerImpl;
import it.unibo.view.impl.EditorViewImpl;
import it.unibo.view.impl.LevelViewImpl;
*/
/**
 * The main class of the application.
 */
public final class PlatformCraft {

    private PlatformCraft() { }

    /**
     * Starts the application.
     * @param args
     */
    public static void main(final String[] args) {
        new ControllerImpl().start();
    }
}
