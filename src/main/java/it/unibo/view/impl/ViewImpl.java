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

    private final Controller controller;
    private final TitleScreen titleScreen;
    private final double width;
    private final double heigth;

    /**
     * Constructor of the ViewImpl.
     * @param controller the controller of the game
     * @param width the width of the map level
     * @param height the heigth of the map level
     */
    public ViewImpl(final Controller controller, final double width, final double height) {
        this.controller = controller;
        this.width = width;
        this.heigth = height;
        this.titleScreen = new TitleScreen(controller, width, height);
    }

    @Override
    public void displayStart() {
        this.titleScreen.setVisible();
    }

    @Override
    public void displayWin() {
        new LevelViewImpl(this.controller, this.width, this.heigth).displayWin();
    }

    @Override
    public void displayGameOver() {
        new LevelViewImpl(this.controller, this.width, this.heigth).displayGameOver();
    }

    @Override
    public void render(final Set<SimpleEntity> entities) {
        SwingUtilities.invokeLater(() -> titleScreen.render(entities));
    }
}
