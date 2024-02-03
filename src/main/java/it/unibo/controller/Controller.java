package it.unibo.controller;

/**
 * The Controller of this application that starts the game.
 */
public interface Controller {

    /**
     * Launch the application.
     */
    void start();

    /**
     * @return the LevelRunner
     */
    LevelRunner getRunner();

    /**
     * @return the LevelEditor
     */
    LevelEditor getEditor();

}
