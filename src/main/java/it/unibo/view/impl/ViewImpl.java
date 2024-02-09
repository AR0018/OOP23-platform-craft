package it.unibo.view.impl;

import java.util.Set;
import it.unibo.common.SimpleEntity;
import it.unibo.view.api.View;
import javax.swing.SwingUtilities;

/**
 * General View class to manage other views.
 */
public class ViewImpl implements View {

    @Override
    public void displayStart() {
        new TitleScreen().setVisible();
    }

    @Override
    public void displayWin() {
        //TODO: new LevelViewImpl().displayWin();
    }

    @Override
    public void displayGameOver() {
        // TODO: new LevelViewImpl().displayGameOver();
    }

    @Override
    public void render(Set<SimpleEntity> entities) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                //TODO: new LevelViewImpl().render(entities);s
            }
        });
    }
}
