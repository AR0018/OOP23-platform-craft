package it.unibo.view.impl;

import java.util.Set;

import it.unibo.common.SimpleEntity;
import it.unibo.controller.api.Controller;
import it.unibo.view.api.LevelView;

/**
 * Handles the building of the GUI's level.
 */
public final class LevelViewImpl implements LevelView {

    private final LevelGUI levelGUI;
    private final Controller controller;

    /**
     * Constructor of the LevelViewImpl.
     * @param controller the controller of the game
     */
    public LevelViewImpl(final Controller controller) {
        this.controller = controller;
        this.levelGUI = new LevelGUI(controller);
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'render'");
    }

    @Override
    public void displayWin() {
        //hide();                 //new Win(controller).display();    display fa hide di LevelView
        new WinView(controller, this);      //prendo il level view per poterlo chiudere insieme
    }

    @Override
    public void displayGameOver() {
        //hide();
        new GameOverView(controller, this);
    }
}
