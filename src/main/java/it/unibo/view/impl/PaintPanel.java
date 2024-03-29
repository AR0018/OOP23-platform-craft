package it.unibo.view.impl;

import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import javax.swing.JPanel;

import it.unibo.common.EntityType;
import it.unibo.common.SimpleEntity;
import it.unibo.common.impl.SimpleEntityImpl;
import it.unibo.controller.api.Controller;

/**
 * This Panel acts as the container of Canvas, modifying its size
 * so that the aspect ratio of the level remains the same.
 */
public class PaintPanel extends JPanel { //NOPMD suppressed since this class is not meant for serialization

    private final double levelWidth;
    private final double levelHeight;
    private final Canvas canvas;

    private transient Optional<EntityType> selected;
    private boolean remove;

    /**
     * The constructor of this PaintPanel.
     * @param controller the controller of the application
     * @param defWidth the default width of the inner Canvas
     * @param defHeight the default height of the inner Canvas
     * @param editor the parent editor frame; if set to Optional.empty(), this Panel does not enable the mouse listener
     */
    public PaintPanel(
        final Controller controller,
        final int defWidth,
        final int defHeight,
        final Optional<EditorGUI> editor) {
        this.levelWidth = controller.getEditor().getLevelWidth();
        this.levelHeight = controller.getEditor().getLevelHeight();
        this.canvas = new Canvas(levelWidth, levelHeight, defWidth, defHeight);
        this.add(canvas);
        this.selected = Optional.empty();
        this.remove = false;
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(final ComponentEvent e) {
                resizeCanvas();
            }
        });
        if (editor.isPresent()) {
            this.addMouseListener(new MouseListener() {

                @Override
                public void mouseClicked(final MouseEvent e) {
                    boolean success = false;
                    if (remove) {
                        success = controller.getEditor()
                        .removeEntity(convertToModelX(e.getX()), convertToModelY(e.getY()));
                    } else if (selected.isPresent()) {
                        success = controller.getEditor().addEntity(
                            new SimpleEntityImpl(
                                selected.get(),
                                convertToModelX(e.getX()),
                                convertToModelY(e.getY())));
                        if (!success) {
                            editor.get().youCannotAdd();
                        }
                    }
                    if (success) {
                        canvas.setDisplayed(
                            controller.getEditor().getCurrentEntities()
                        );
                    }
                    canvas.repaint();
                }

                @Override
                public void mousePressed(final MouseEvent e) { }

                @Override
                public void mouseReleased(final MouseEvent e) { }

                @Override
                public void mouseEntered(final MouseEvent e) { }

                @Override
                public void mouseExited(final MouseEvent e) { }

            });
        }
    }

    /*
     * Converts the specified dimension in the Panel to the dimension in the model.
     * The converted dimension must be on the X axis.
     */
    private double convertToModelX(final int dimensionX) {
        return this.levelWidth * dimensionX / this.canvas.getWidth();
    }

    /*
     * Converts the specified dimension in the Panel to the dimension in the model.
     * The converted dimension must be on the Y axis.
     */
    private double convertToModelY(final int dimensionY) {
        return this.levelHeight * dimensionY / this.canvas.getHeight();
    }

    private void resizeCanvas() {
        final int cwidth = this.getWidth();
        final int cheight = this.getHeight();
        int pwidth;
        int pheight;
        final int correctWidth = (int) (cheight * levelWidth / levelHeight);
        if (cwidth <= correctWidth) {
            pwidth = cwidth;
            pheight = (int) (pwidth * levelHeight / levelWidth);
        } else {
            pheight = cheight;
            pwidth = (int) (levelWidth * pheight / levelHeight);
        }
        this.canvas.setPreferredSize(new Dimension(pwidth, pheight));
    }

    /**
     * Sets the selected type of entity to add to the level at the next mouse click.
     * Also, after calling this method, this Panel will no longer try to remove an entity until
     * the next call of setRemove()
     * @param type the selected type of entity
     * @throws NullPointerException if type is null
     */
    public void setSelectedEntity(final EntityType type) {
        this.selected = Optional.of(Objects.requireNonNull(type));
        this.remove = false;
    }

    /**
     * Sets this Panel so that the next mouse clicks will try to remove an entity from the level.
     */
    public void setRemove() {
        this.remove = true;
    }

    /**
     * Shows the specified entity on the inner canvas.
     * @param displayed the entities to be shown
     * @throws NullPointerException if displayed is null
    */
    public void render(final Set<SimpleEntity> displayed) {
        this.canvas.setDisplayed(Objects.requireNonNull(displayed));
        this.repaint();
    }
}
