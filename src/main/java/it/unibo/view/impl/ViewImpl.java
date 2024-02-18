package it.unibo.view.impl;

import java.util.Set;
import it.unibo.common.SimpleEntity;
import it.unibo.controller.api.Controller;
import it.unibo.view.api.View;
import javax.swing.SwingUtilities;

/**
 * General View class to manage other views.
 */
public final class ViewImpl implements View {

    //private final Controller controller;
    private final TitleScreen titleScreen;
    //private final double width;
    //private final double heigth;

    /**
     * Constructor of the ViewImpl.
     * @param controller the controller of the game
     */
    public ViewImpl(final Controller controller) {
        this.titleScreen = new TitleScreen(controller, this);
    }

    @Override
    public void displayStart() {
        this.titleScreen.setVisible();
    }

    @Override
    public void displayWin() {
        this.titleScreen.getLevelView().displayWin();
    }

    @Override
    public void displayGameOver() {
        this.titleScreen.getLevelView().displayGameOver();
    }

    @Override
    public void render(final Set<SimpleEntity> entities) {
        SwingUtilities.invokeLater(() -> titleScreen.render(entities));
    }
}
