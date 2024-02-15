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

    /**
     * Constructor of the ViewImpl.
     * @param controller the controller of the game
     */
    public ViewImpl(final Controller controller) {
        this.controller = controller;
    }

    @Override
    public void displayStart() {
        new TitleScreen(this.controller).setVisible();
    }

    @Override
    public void displayWin() {
        new LevelViewImpl(this.controller).displayWin();
    }

    @Override
    public void displayGameOver() {
        new LevelViewImpl(this.controller).displayGameOver();
    }

    @Override
    public void render(final Set<SimpleEntity> entities) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                new LevelViewImpl(controller).render(entities);
            }
        });
    }
}
