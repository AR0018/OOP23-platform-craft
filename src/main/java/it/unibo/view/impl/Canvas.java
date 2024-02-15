package it.unibo.view.impl;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import it.unibo.common.EntityType;
import it.unibo.common.SimpleEntity;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Panel that shows all the objects in the level.
 */
public final class Canvas extends JPanel {

    private static final String ROOT = "./it/unibo/images/";
    private static final String BACKGROUND = "XP.jpg"; //TODO: check if jpg works
    /*
     * Map that contains the associations between each entity type and its specified image.
     * To choose another image for the entities, modify the strings inside getSystemResource.
     */
    private static final Map<EntityType, String> TYPE_MAP = Map.of(
        EntityType.CHARACTER, ROOT + "Owlet_Monster.png",
        EntityType.SIMPLE_ENEMY, ROOT + "Pink_Monster/Pink_Monster.png",
        EntityType.ENEMY, ROOT + "Dude_Monster/Dude_Monster.png",
        EntityType.FINISH_LOCATION, ROOT + "R.png",
        EntityType.TRAP, ROOT + "piggy3.png",
        EntityType.EXPLOSION, ROOT + "Explosion.png",
        EntityType.MAP_ELEMENT, ROOT + "Block.png"
    );

    private Map<EntityType, Image> imageMap;

    private final double levelWidth;
    private final double levelHeight;

    private final Image background;

    private Set<SimpleEntity> displayed;

    /**
     * The constructor of this Panel.
     * @param levelWidth the width of the Model level
     * @param levelHeight the height of the Model level
     * @param defWidth the starting width of this panel
     * @param defHeight the starting height of this panel
     */
    public Canvas(final double levelWidth, final double levelHeight, final int defWidth, final int defHeight) {
        super();
        this.levelHeight = levelHeight;
        this.levelWidth = levelWidth;
        this.background = new ImageIcon(ClassLoader.getSystemResource(ROOT + BACKGROUND)).getImage();
        this.displayed = new HashSet<>();
        this.imageMap = TYPE_MAP.entrySet().stream()
            .collect(Collectors.toMap(e -> e.getKey(), e -> new ImageIcon(ClassLoader.getSystemResource(e.getValue())).getImage()));
        this.setPreferredSize(new Dimension(defWidth, defHeight));
    }

    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), this);
        this.displayed.stream()
            .forEach(
                entity -> g.drawImage(
                    imageMap.get(entity.getType()),
                    convertToPanelX(entity.getX()),
                    convertToPanelY(entity.getY()),
                    convertToPanelX(entity.getType().getWidth()),
                    convertToPanelY(entity.getType().getHeigth()),
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
        return (int) (this.getWidth() * dimensionX / this.levelWidth);
    }

    /*
     * Converts the specified dimension in the model to the dimension in the Panel.
     * The converted dimension must be on the Y axis.
     */
    private int convertToPanelY(final double dimensionY) {
        return (int) (this.getHeight() * dimensionY / this.levelHeight);
    }
}
