package it.unibo;

import it.unibo.controller.impl.ControllerImpl;

/*
import it.unibo.controller.impl.ControllerImpl;
import it.unibo.view.impl.EditorViewImpl;
import it.unibo.view.impl.LevelViewImpl;
*/
/**
 * Represents the main of the program.
 */
public class PlatformCraft {

    public static void main(final String[] args) {
        new ControllerImpl().start();
    }
}
