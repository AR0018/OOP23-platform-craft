package it.unibo.view.impl;

import it.unibo.controller.api.Controller;
import it.unibo.view.api.EditorView;

/**
 * Models the GUI of the game's Editor.
 */
public final class EditorViewImpl implements EditorView {

    private final Editor editor;

    /**
     * Constructor of the EditorViewImpl.
     * @param controller the controller of the game
     */
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
