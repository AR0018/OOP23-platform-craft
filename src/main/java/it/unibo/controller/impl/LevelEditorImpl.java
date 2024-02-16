package it.unibo.controller.impl;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import it.unibo.common.SimpleEntity;
import it.unibo.controller.api.LevelEditor;
import it.unibo.controller.api.LevelSerializer;
import it.unibo.model.engine.api.Engine;

/**
 * Models the controller of the game's editor.
 */
public final class LevelEditorImpl implements LevelEditor {
    //TODO: tipo di oggetto da aggiungere
    private Engine engine;
    private LevelSerializer serializer = new SerializerImpl();

    /**
     * Constructor of the LevelEditorImpl.
     * It's empty.
     */
    public LevelEditorImpl() {
    }

    @Override
    public void reset() {
        //TODO: this.engine = new Engine(); 
    }

    @Override
    public boolean addEntity(final SimpleEntity entity) {
        //return this.engine.addGameEntity(entity);
        return false; //TODO: fix
    }

    @Override
    public boolean removeEntity(final double x, final double y) {
        //return this.engine.removeGameEntity(x, y);
        return false; //TODO: fix
    }

    @Override
    public boolean saveLevel(final File file) {
        try {
            this.serializer.saveLevel(this.engine.getLevelEntities(), file);
            return true;
        } catch (IOException e){
            return false;
        }
    }

    @Override
    public boolean loadLevel(final File file) {
        try {
            this.serializer.loadLevel(file);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public boolean canBeSaved() {
        //TODO: aspettare editor model
        return false;
    }

    @Override
    public Set<SimpleEntity> getCurrentEntities() {
        //return this.engine.getLevelEntities();            //TODO: engine
        return null;
    }
}
