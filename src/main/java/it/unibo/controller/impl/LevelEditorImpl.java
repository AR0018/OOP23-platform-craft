package it.unibo.controller.impl;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import it.unibo.common.EntityType;
import it.unibo.common.SimpleEntity;
import it.unibo.controller.api.Controller;
import it.unibo.controller.api.LevelEditor;
import it.unibo.controller.api.LevelSerializer;
import it.unibo.model.engine.api.Engine;
import it.unibo.view.api.EditorView;

/**
 * Models the controller of the game's editor.
 */
public final class LevelEditorImpl implements LevelEditor {
    //TODO: tipo di oggetto da aggiungere
    private EditorView  editor;
    private Engine engine;
    private LevelSerializer serializer;

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
        return this.engine.addGameEntity(entity);
    }

    @Override
    public boolean removeEntity(final double x, final double y) {
        return this.engine.removeGameEntity(x, y);
    }

    @Override
    public boolean saveLevel(final File file) {
        try{
            this.serializer.saveLevel(this.engine.getLevelEntities(), file);
            return true;
        }catch(IOException e){
            return false;
        }
    }

    @Override
    public boolean loadLevel(final File file) {
        try{
            this.serializer.loadLevel(file);
            return true;
        }catch(IOException e){
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
