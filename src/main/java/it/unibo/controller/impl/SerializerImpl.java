package it.unibo.controller.impl;

import java.io.File;
import java.io.IOException;
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

    @Override
    public void saveLevel(final Set<SimpleEntity> entities, final File file) throws IOException {
        new ObjectMapper().writeValue(file, entities);
    }

    @Override
    public Set<SimpleEntity> loadLevel(final File file) throws IOException {
        Set<SimpleEntity> entities = new HashSet<>();
        new ObjectMapper().readValue(file, new TypeReference<Set<SimpleEntityImpl>>() { })
            .stream().forEach(e -> entities.add(e));
        return entities;
    }

}
