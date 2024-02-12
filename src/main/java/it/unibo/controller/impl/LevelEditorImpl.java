package it.unibo.controller.impl;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import it.unibo.common.EntityType;
import it.unibo.common.SimpleEntity;
import it.unibo.controller.api.Controller;
import it.unibo.controller.api.LevelEditor;
import it.unibo.controller.api.LevelSerializer;
import it.unibo.model.engine.api.Engine;
import it.unibo.view.api.EditorView;
import it.unibo.view.impl.EditorViewImpl;

/**
 * Models the controller of the game's editor.
 */
public final class LevelEditorImpl implements LevelEditor {
    //TODO: tipo di oggetto da aggiungere
    private EditorView  editor;
    private EntityType type;
    private Engine engine;
    private LevelSerializer serializer;
    private Set<SimpleEntity> entities =  new HashSet<>();
    private Controller controller;

    public LevelEditorImpl(final Controller controller) {
        this.controller = controller;
        this.editor = new EditorViewImpl(controller);
    }

    @Override
    public void start() {   //da controller non da editor
        //TODO: ;
        if (!this.editor.isShown()) {
            this.editor.show();
        }
    }

    @Override
    public void reset() {
        //TODO: new EditorViewImpl.show();
        //if (this.editor.isShown()) {
            this.editor.hide();
            this.editor = new EditorViewImpl(controller);
            this.editor.show();
        //}
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
        //return this.serializer.saveLevel(this.engine.getLevelEntities(), file);
        return true;
    }

    @Override
    public void loadLevel(final File file) {
        //entities = this.serializer.loadLevel(file);        //TODO: bisogna modificare il set in Engine
    }
}
