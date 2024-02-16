package it.unibo.controller.api;

import it.unibo.common.SimpleEntity;
import java.io.File;
import java.util.Set;

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
     * Tells whether the current configuration of the level is valid and can be created.
     * @return true if the level configuration is valid and can be saved
     */
    boolean canBeSaved();

    /**
     * Saves a level created by the player on file.
     * @param file the file used to save the level
     * @return true if the level has been correctly saved, false if there has been an error in saving the file
     */
    boolean saveLevel(File file);

    /**
     * Loads a level that has been saved on file.
     * @param file the file used to load the level created
     * @return true if the level has been correctly loaded, false if there has been an error in loading the file
     */
    boolean loadLevel(File file);

    /**
     * Returns all the entities in the level that is being created.
     * @return the entities in the current configuration of the level
     */
    Set<SimpleEntity> getCurrentEntities();

    /**
     * @return the width of the level
     */
    double getLevelWidth();

    /**
     * @return the heigth of the level
     */
    double getLevelHeight();
}
