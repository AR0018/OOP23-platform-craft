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
public class PaintPanel extends JPanel {

    private final double levelWidth;
    private final double levelHeight;
    private final Canvas canvas;

    private Optional<EntityType> selected;
    private boolean remove;

    /**
     * The constructor of this PaintPanel.
     * @param controller the controller of the application
     * @param levelWidth the width of the level
     * @param levelHeight the heigth of the level
     * @param mouseEnabled true if the inner Canvas must accept mouse clicks
     * @param defWidth the default width of the inner Canvas
     * @param defHeight the default height of the inner Canvas
     * @param editor the parent frame
     */
    public PaintPanel(
        final Controller controller,
        final double levelWidth,
        final double levelHeight,
        final boolean mouseEnabled,
        final int defWidth,
        final int defHeight,
        final Editor editor) {
        this.levelHeight = levelHeight;
        this.levelWidth = levelWidth;
        this.canvas = new Canvas(levelWidth, levelHeight, defWidth, defHeight);
        this.add(canvas);
        this.selected = Optional.empty();
        this.remove = false;
        //Component listener that modifies the size of the canvas if this panel is resized.
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(final ComponentEvent e) {
                resizeCanvas();
            }
        });
        if (mouseEnabled) {
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
                            editor.youCannotAdd();
                        }
                    }
                    if (success) {
                        canvas.setDisplayed(
                            controller.getEditor().getCurrentEntities()
                        );
                    }
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
        int cwidth = this.getWidth();
        //System.out.println("Container initial width: "+ cwidth); TODO: remove comments in this method
        int cheight = this.getHeight();
        int pwidth = 0;
        int pheight = 0;
        int correctWidth = (int) (cheight * levelWidth / levelHeight);
        if (cwidth <= correctWidth) {
            pwidth = cwidth;
            pheight = (int) (pwidth * levelHeight / levelWidth);
        } else {
            pheight = cheight;
            pwidth = (int) (levelWidth * pheight / levelHeight);
        }
        //System.out.println("Container dimension: "+ this.getSize());
        //System.out.println("Panel dimension: " + canvas.getSize());

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
