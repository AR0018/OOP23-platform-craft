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
    private final double width;
    private final double heigth;

    /**
     * Constructor of the LevelViewImpl.
     * @param controller the controller of the game
     * @param width of the map level
     * @param height of the map level
     */
    public LevelViewImpl(final Controller controller, final double width, final double height) {
        this.controller = controller;
        this.width = width;
        this.heigth = height;
        this.levelGUI = new LevelGUI(controller, width, height);
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
    public void render(final Set<SimpleEntity> entities) {          //chiama paintPanel
        this.levelGUI.render(entities);
    }

    @Override
    public void displayWin() {
        //hide();                 //new Win(controller).display();    display fa hide di LevelView
        new WinView(this.controller, this, this.width, this.heigth); //prendo il level view per poterlo chiudere insieme
    }

    @Override
    public void displayGameOver() {
        //hide();
        new GameOverView(this.controller, this, this.width, this.heigth);
    }
}
