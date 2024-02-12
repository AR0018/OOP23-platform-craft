package it.unibo;

import it.unibo.controller.impl.ControllerImpl;
import it.unibo.view.impl.EditorViewImpl;


/**
 * Represents the main of the program.
 */
public class PlatformCraft {

    public static void main(String[] args) {
        new EditorViewImpl(new ControllerImpl()).show();
    }
}
