package it.unibo.controller.api;

import java.io.File;

/**
 * Controller component responsible of running a game.
 */
public interface LevelRunner {

    /**
     * Starts the trial level or a level created by the player.
     * @throws IllegalStateException if loadLevel has never been called successfully,
     *  so if a level has never been correctly loaded in this Runner
     */
    void run();

    /**
     * Interrupts the running of the Level.
     */
    void stopLevel();

    /**
     * Adds the command passed by input in a list of command that have to be processed.
     * @param command the type of command received
     * @throws IllegalStateException if loadLevel has never been called successfully,
     *  so if a level has never been correctly loaded in this Runner
     */
    void notifyCommand(Command command);

    /**
     * Loads a level from the specified file.
     * @param file the file where the level is saved
     * @return true if the level has been loaded, false if there has been an error when loading the file
     */
    boolean loadLevel(File file);
}
