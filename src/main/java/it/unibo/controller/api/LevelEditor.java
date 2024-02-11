package it.unibo.controller.api;

import it.unibo.common.SimpleEntity;
import java.io.File;

/**
 * Component of the Controller responsible of the editor and the creation/loading by file of the level.
 */
public interface LevelEditor {

    /**
     * Starts the editor necessary to create the level.
     */
    void start(); 

    /**
     * Restore the editor and creates a new one by zero.
     */
    void reset();

    /**
     * Adds entity on the editor.
     * @param entity the entity to be visualized
     * @return true if the entity can be added, false otherwise
     */
    boolean addEntity(SimpleEntity entity);

    /**
     * Removes the entity chosen by the player via its coordinates X and Y.
     * @param x represents the x coordinate
     * @param y represents the y coordinate
     * @return true if the entity can be removed, false otherwise
     */
    boolean removeEntity(double x, double y);

    /**
     * Saves a level created by the player on file.
     * @param file the file used to save the level
     * @return true if the level configuration is valid and can be saved
     */
    boolean saveLevel(File file);

    /**
     * Loads a level that has been saved on file.
     * @param file the file used to load the level created
     */
    void loadLevel(File file);

}
