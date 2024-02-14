package it.unibo.controller.impl;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import com.fasterxml.jackson.databind.ObjectMapper;

import it.unibo.common.SimpleEntity;
import it.unibo.controller.api.LevelSerializer;

public class SerializerImpl implements LevelSerializer {

    @Override
    public void saveLevel(Set<SimpleEntity> entities, File file) {
        if(!file.exists()) {
            //file.createNewFile(); TODO: does file need to be created?
        }
        //new ObjectMapper().writeValue(file, entities);
    }

    @Override
    public Set<SimpleEntity> loadLevel(File file) {
        throw new UnsupportedOperationException("Unimplemented method 'loadLevel'");
    }

}
