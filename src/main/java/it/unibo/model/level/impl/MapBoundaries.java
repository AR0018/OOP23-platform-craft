package it.unibo.model.level.impl;




/**
 * constan width and height of the map
 */
public class MapBoundaries extends GameLevel{

    public static final int BOARD_WIDTH=600;
    public static final int BOARD_HEIGHT=800;
    
    private int mapHeight;
    private int mapWidth;

    public MapBoundaries() {
        this.mapHeight = BOARD_HEIGHT;
        this.mapWidth = BOARD_WIDTH;
    }

    public int getMapHeight() {
        return this.mapHeight;
    }

    public int getMapWidth() {
        return this.mapWidth;
    }
}
