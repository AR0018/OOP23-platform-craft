package it.unibo.controller;

import java.io.File;

import it.unibo.common.SimpleEntity;

/**
 * Component of the Controller responsible of the editor and the creation/loading by file of the level.
 */
public interface LevelEditor {

    /**
     * Restore the editor and creates a new one by zero.
     */
    void reset();

    /**
     * Adds entity on the editor.
     * @param entity the entity to be visualized
     */
    void addEntity(SimpleEntity entity);

    /**
     * Removes the entity chosen by the player via its coordinates X and Y.
     * @param x represents the x coordinate
     * @param y represents the y coordinate
     */
    void removeEntity(double x, double y);

    /**
     * Saves a level created by the player on file.
     * @param file the file used to save the level
     */
    void saveLevel(File file);

    /**
     * Loads a level that has been saved on file.
     * @param file the file used to load the level created
     */
    void loadLevel(File file);

}
