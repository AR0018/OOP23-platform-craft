package it.unibo.controller;

/**
 * Controller component responsible of running a game
 */
public interface LevelRunner {

    /**
     * Starts the trial level or a level created by the player
     */
    void run();     //TODO: the input type

    /**
     * Adds the command passed by input in a list of command that have to be processed
     */
    void notifyCommand(); //TODO: the input type
}
