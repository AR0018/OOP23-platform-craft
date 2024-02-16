package it.unibo.view.impl;

import it.unibo.controller.api.Controller;
import it.unibo.view.api.EditorView;

/**
 * Models the GUI of the game's Editor.
 */
public final class EditorViewImpl implements EditorView {

    private final EditorGUI editor;

    /**
     * Constructor of the EditorViewImpl.
     * @param controller the controller of the game
     * @param width of the map level
     * @param heigth of the map level
     */
    public EditorViewImpl(final Controller controller, final double width, final double heigth) {
        //this.controller = controller;
        this.editor = new EditorGUI(controller, width, heigth);
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
