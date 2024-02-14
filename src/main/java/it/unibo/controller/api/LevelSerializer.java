package it.unibo.controller.api;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import it.unibo.common.SimpleEntity;

/**
 * Controller component responsible for saving and loading a level from a file.
 * In this case, a level is represented as a series of SimpleEntities
 */
public interface LevelSerializer {

    /**
     * Saves level on the specified file.
     * @param entities the set of entities to be saved
     * @param file the file in which to save the level
     * @throws IOException in case of an error with the format the file or in case reading fails
    */
    void saveLevel(Set<SimpleEntity> entities, File file) throws IOException;

    /**
     * Loads the level from the specified file.
     * @param file the file from which to load the level
     * @return the entities of the level
     * @throws IOException in case of an error with the format the file or in case reading fails
     */
    Set<SimpleEntity> loadLevel(File file) throws IOException;

}
