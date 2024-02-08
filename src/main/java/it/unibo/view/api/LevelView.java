package it.unibo.view.api;

import java.util.Set;

import it.unibo.common.SimpleEntity;

/**
 * The screen of the level.
 */
public interface LevelView {

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

    /**
     * Shows on screen all the entities in the set.
     * @param entities the entities to be shown
     */
    void render(Set<SimpleEntity> entities);

    /**
     * Shows the winning screen.
     */
    void displayWin();

    /**
     * Shows the Game Over screen.
     */
    void displayGameOver();
}
