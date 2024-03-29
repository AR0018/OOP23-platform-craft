package it.unibo.view.impl;

import it.unibo.controller.api.Controller;
import it.unibo.view.api.EditorView;
import it.unibo.view.api.View;

/**
 * Models the GUI of the game's Editor.
 */
public final class EditorViewImpl implements EditorView {

    private final EditorGUI editor;

    /**
     * Constructor of the EditorViewImpl.
     * @param controller the controller of the game
     * @param view the main view of the game
     */
    public EditorViewImpl(final Controller controller, final View view) {
        this.editor = new EditorGUI(controller, view);
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
