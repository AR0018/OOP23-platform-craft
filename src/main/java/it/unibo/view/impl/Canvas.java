package it.unibo.view.impl;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import it.unibo.common.EntityType;
import it.unibo.common.SimpleEntity;

import java.awt.Graphics;
import java.awt.Image;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * Panel that shows all the objects in the level.
 */
public final class Canvas extends JPanel {

    private static final String ROOT = "./it/unibo/images/";
    private static final String BACKGROUND = ""; //TODO: insert image
    /*
     * Map that contains the associations between each entity type and its specified image.
     * To choose another image for the entities, modify the strings inside getSystemResource.
     * TODO: complete this map with the wanted images
     */
    private static final Map<EntityType, Image> TYPE_MAP = Map.of(
        EntityType.CHARACTER, new ImageIcon(ClassLoader.getSystemResource(ROOT + "Owlet_Monster.png")).getImage()
    );

    private final double levelWidth;
    private final double levelHeight;

    private final Image background;

    private Set<SimpleEntity> displayed;

    /**
     * The constructor of this Panel.
     * @param levelWidth the width of the Model level
     * @param levelHeight the height of the Model level
     */
    public Canvas(final double levelWidth, final double levelHeight) {
        this.levelHeight = levelHeight;
        this.levelWidth = levelWidth;

        this.background = new ImageIcon(ClassLoader.getSystemResource(ROOT + BACKGROUND)).getImage();
        this.displayed = new HashSet<>();
    }

    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), this);
        this.displayed.stream()
            .forEach(
                entity -> g.drawImage(
                    TYPE_MAP.get(entity.getType()),
                    convertToPanelX(entity.getX()),
                    convertToPanelY(entity.getY()),
                    WIDTH,  //TODO: add EntityType.getWidth()
                    HEIGHT,  //TODO: add EntityType.getHeight()
                    this
                ));
    }

    /**
     * Displays all the entities specified in the input set.
     * The x and y coordinates of the entities in this set must be in the
     * reference system of the Level.
     * @param displayed the set of entities to display
     * @throws NullPointerException if displayed is null
     */
    protected void setDisplayed(final Set<SimpleEntity> displayed) {
        this.displayed = Objects.requireNonNull(displayed);
    }

    /*
     * Converts the specified dimension in the model to the dimension in the Panel.
     * The converted dimension must be on the X axis.
     */
    private int convertToPanelX(final double dimensionX) {
        return (int) ((this.getWidth() * dimensionX) / this.levelWidth);
    }

    /*
     * Converts the specified dimension in the model to the dimension in the Panel.
     * The converted dimension must be on the Y axis.
     */
    private int convertToPanelY(final double dimensionY) {
        return (int) ((this.getHeight() * dimensionY) / this.levelHeight);
    }
}
