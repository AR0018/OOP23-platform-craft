package it.unibo.view.impl;

import java.util.Set;
import it.unibo.common.SimpleEntity;
import it.unibo.view.api.View;
import javax.swing.SwingUtilities;

/**
 * General View class to manage other views.
 */
public final class ViewImpl implements View {

    @Override
    public void displayStart() {
        new TitleScreen().setVisible();
    }

    @Override
    public void displayWin() {
        //: new LevelViewImpl().displayWin();
    }

    @Override
    public void displayGameOver() {
        // : new LevelViewImpl().displayGameOver();
    }

    @Override
    public void render(final Set<SimpleEntity> entities) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                //: new LevelViewImpl().render(entities);s
            }
        });
    }
}
