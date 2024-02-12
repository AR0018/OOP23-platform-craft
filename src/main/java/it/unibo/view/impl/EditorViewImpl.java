package it.unibo.view.impl;

import it.unibo.controller.api.Controller;
import it.unibo.view.api.EditorView;

public final class EditorViewImpl implements EditorView{

    private final Editor editor;

    public EditorViewImpl(final Controller controller) {
        //this.controller = controller;
        this.editor = new Editor(controller);
    }

    @Override
    public void show() {
        this.editor.show();
    }

    @Override
    public void hide() {
        this.editor.hide();
    }

    @Override
    public boolean isShown() {
        return this.editor.isShown();
    }
    
}
