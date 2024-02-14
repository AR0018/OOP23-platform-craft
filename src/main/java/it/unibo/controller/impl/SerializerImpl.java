package it.unibo.controller.impl;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.unibo.common.SimpleEntity;
import it.unibo.controller.api.LevelSerializer;

/**
 * Implementation of LevelSerializer which saves json files using Jackson library.
 */
public final class SerializerImpl implements LevelSerializer {

    @Override
    public void saveLevel(final Set<SimpleEntity> entities, final File file) throws IOException {
        /*
        if (!file.exists()) {
            file.createNewFile(); TODO: does file need to be created?
        }*/
        new ObjectMapper().writeValue(file, entities);
    }

    @Override
    public Set<SimpleEntity> loadLevel(final File file) throws IOException {
        return new ObjectMapper().readValue(file, new TypeReference<Set<SimpleEntity>>() { } );
    }

}
