package it.unibo.view.impl;

import java.util.Set;

import it.unibo.common.SimpleEntity;
import it.unibo.controller.api.Controller;
import it.unibo.view.api.LevelView;
import it.unibo.view.api.View;

/**
 * Handles the building of the GUI's level.
 */
public final class LevelViewImpl implements LevelView {

    private final LevelGUI levelGUI;
    private final Controller controller;
    private final View view;

    /**
     * Constructor of the LevelViewImpl.
     * @param controller the controller of the game
     * @param view main view of the level
     */
    public LevelViewImpl(final Controller controller, final View view) {
        this.controller = controller;
        this.view = view;
        this.levelGUI = new LevelGUI(controller, view);
    }

    @Override
    public void show() {
        this.levelGUI.show();
    }

    @Override
    public void hide() {
        this.levelGUI.hide();
    }

    @Override
    public boolean isShown() {
        return this.levelGUI.isShown();
    }

    @Override
    public void render(final Set<SimpleEntity> entities) {
        this.levelGUI.render(entities);
    }

    @Override
    public void displayWin() {
        new WinView(this.levelGUI, this.view);
    }

    @Override
    public void displayGameOver() {
        new GameOverView(this.controller, this.levelGUI, this.view);
    }
}
