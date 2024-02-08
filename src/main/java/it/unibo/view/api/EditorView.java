package it.unibo.view.api;

/**
 * The screen of the level editor.
 */
public interface EditorView {

    /**
     * Sets this view as visible.
     */
    void show();

    /**
     * Hides this view, making it invisible.
     */
    void hide();

    /**
     * @return true if this view is visible, false otherwise
     */
    boolean isShown();

}
