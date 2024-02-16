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
    private final double width;
    private final double heigth;

    /**
     * Constructor of the ViewImpl.
     * @param controller the controller of the game
     * @param width the width of the map level
     * @param heigth the heigth of the map level
     */
    public ViewImpl(final Controller controller, final double width, final double heigth) {
        this.controller = controller;
        this.width = width;
        this.heigth = heigth;
    }

    @Override
    public void displayStart() {
        new TitleScreen(this.controller, this.width, this.heigth).setVisible();
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
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                new LevelViewImpl(controller, width, heigth).render(entities);
            }
        });
    }
}
