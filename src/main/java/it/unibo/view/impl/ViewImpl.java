package it.unibo.view.impl;

import java.util.Set;
import it.unibo.common.SimpleEntity;
import it.unibo.controller.api.Controller;
import it.unibo.view.api.View;
import javax.swing.SwingUtilities;

/**
 * General View class to manage other views.
 */
public final class ViewImpl implements View {           //TODO: metodi aggiuntivi per chiamare LevelEditor?

    private final Controller controller;

    public ViewImpl(final Controller controller) {
        this.controller = controller;
    }

    @Override
    public void displayStart() {
        new TitleScreen(this.controller).setVisible();
    }

    @Override
    public void displayWin() {
        //TODO: new LevelViewImpl().displayWin();
    }

    @Override
    public void displayGameOver() {
        //TODO: new LevelViewImpl().displayGameOver();
    }

    @Override
    public void render(final Set<SimpleEntity> entities) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                //TODO: new LevelViewImpl().render(entities);s
            }
        });
    }
}
