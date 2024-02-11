package it.unibo.controller.impl;

import java.io.File;

import it.unibo.common.SimpleEntity;
import it.unibo.controller.api.LevelEditor;

/**
 * Models the controller of the game's editor.
 */
public final class LevelEditorImpl implements LevelEditor{

    //private EditorView editorView;

    @Override
    public void start() {
        //TODO: new EditorViewImpl.show();
    }

    @Override
    public void reset() {
        //this.editorView
        //TODO: new EditorViewImpl.show();
    }

    @Override
    public boolean addEntity(final SimpleEntity entity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addEntity'");
    }

    @Override
    public boolean removeEntity(final double x, final double y) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeEntity'");
    }

    @Override
    public boolean saveLevel(final File file) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveLevel'");
    }

    @Override
    public void loadLevel(final File file) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'loadLevel'");
    }
}
