package it.unibo.controller;

import it.unibo.common.SimpleEntiete;

/**
 * Component of the Controller responsible of the editor and the creation/loading by file of the level
 */
public interface LevelEditor {

    /**
     * Restore the editor and creates a new one by zero
     */
    void reset();

    /**
     * Adds entity on the editor
     * @param entity the entity to be visualized
     */
    void addEntity(SimpleEntiete entity);

    /*
     * Removes the entity chosen by the player via its coordinates X and Y
     */
    void removeEntity(double x, double y);

    /**
     * Saves a level created by the player on file
     */
    void saveLevel();   //TODO: input from File

    /**
     * Loads a level that has been saved on file
     */
    void loadLevel();   //TODO: input from File

}
