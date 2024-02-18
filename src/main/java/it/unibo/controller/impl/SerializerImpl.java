package it.unibo.controller.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.unibo.common.SimpleEntity;
import it.unibo.common.impl.SimpleEntityImpl;
import it.unibo.controller.api.LevelSerializer;

/**
 * Implementation of LevelSerializer which serializes json files using Jackson library.
 */
public final class SerializerImpl implements LevelSerializer {

    private static final String DEMO_DIR = System.getProperty("user.home")
        + System.getProperty("file.separator")
        + "PlatformCraft";
    private static final String DEMO_NAME = DEMO_DIR 
        + System.getProperty("file.separator")
        + "demo_level.json";
    private static final String DEMO_RESOURCE = "it/unibo/level/demo_level.json";

    @Override
    public void saveLevel(final Set<SimpleEntity> entities, final File file) throws IOException {
        new ObjectMapper().writeValue(file, entities);
    }

    @Override
    public Set<SimpleEntity> loadLevel(final File file) throws IOException {
        final Set<SimpleEntity> entities = new HashSet<>();
        new ObjectMapper().readValue(file, new TypeReference<Set<SimpleEntityImpl>>() { })
            .stream().forEach(e -> entities.add(e));
        return entities;
    }

    @Override
    public void loadDemoLevel() throws IOException {
        final File demoDir = new File(DEMO_DIR);
        if (!demoDir.exists()) {
            if (demoDir.mkdirs()) {
                createFile();
            }
        } else {
            createFile();
        }
    }

    /*
     * Creates the file with the demo level.
     */
    private void createFile() throws IOException {
        final File demoFile = new File(DEMO_NAME);
        if (demoFile.createNewFile()) {
            saveLevel(loadLevelFromStream(ClassLoader.getSystemResourceAsStream(DEMO_RESOURCE)), demoFile);
        }
    }

    /*
     * Loads a level from the specified stream.
     */
    private Set<SimpleEntity> loadLevelFromStream(final InputStream stream) throws IOException {
        final Set<SimpleEntity> entities = new HashSet<>();
        new ObjectMapper().readValue(stream, new TypeReference<Set<SimpleEntityImpl>>() { })
            .stream().forEach(e -> entities.add(e));
        return entities;
    }
}
