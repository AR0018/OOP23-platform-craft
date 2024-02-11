package it.unibo.controller.api;

import java.io.File;

/**
 * Controller component responsible of running a game.
 */
public interface LevelRunner {

    /**
     * Starts the trial level or a level created by the player.
     */
    void run();

    /**
     * Adds the command passed by input in a list of command that have to be processed.
     * @param command the type of command received
     */
    void notifyCommand(Command command);

    /**
     * Loads a level from the specified file.
     * @param file the file where the level is saved
     */
    void loadLevel(File file);
}
