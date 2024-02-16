package it.unibo.controller.impl;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import it.unibo.common.SimpleEntity;
import it.unibo.controller.api.LevelEditor;
import it.unibo.controller.api.LevelSerializer;

/**
 * Models the controller of the game's editor.
 */
public final class LevelEditorImpl implements LevelEditor {
    //TODO:
    //private final Editor editor;
    private LevelSerializer serializer = new SerializerImpl();

    /**
     * Constructor of the LevelEditorImpl.
     * It's empty.
     */
    public LevelEditorImpl() {
        //this.editor = new EditorImpl(Set.of());
    }

    @Override
    public void reset() {
        //TODO: this.editor.clearAll(); 
    }

    @Override
    public boolean addEntity(final SimpleEntity entity) {
        //return this.editor.addGameEntity(entity);
        return false; //TODO: fix
    }

    @Override
    public boolean removeEntity(final double x, final double y) {
        //return this.editor.removeGameEntity(x, y);
        return false; //TODO: fix
    }

    @Override
    public boolean saveLevel(final File file) {
        /*try {
            this.serializer.saveLevel(this.editor.getLevelEntities(), file);
            return true;
        } catch (IOException e){
            return false;
        }*/
        return false;
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
        //TODO: return this.editor.createLevel().isEmpty() ? false : true;
        return false;
    }

    @Override
    public Set<SimpleEntity> getCurrentEntities() {
        //return this.editor.getLevelEntities();
        return null;
    }

    @Override
    public double getLevelWidth() {
        //this.editor.getLevelWidth();
        return 0;
    }

    @Override
    public double getLevelHeight() {
        //this.editor.getLevelHeight();
        return 0;
    }
}
