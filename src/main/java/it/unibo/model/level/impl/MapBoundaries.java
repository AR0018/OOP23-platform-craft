package it.unibo.model.level.impl;

import it.unibo.model.level.api.Level;

/**
 * 
 */
public class MapBoundaries  extend Level{
    // Metodo per ottenere le posizioni (x, y) che delimitano la mappa
    public static List<int[]> getMapBoundaries(boolean[][] map) {
        List<int[]> boundaries = new ArrayList<>();

        if (map == null || map.length == 0 || map[0].length == 0) {
            // Mappa vuota o non valida, restituisci una lista vuota
            return boundaries;
        }

        int rows = map.length;
        int cols = map[0].length;

        // Scansiona il bordo superiore
        for (int col = 0; col < cols; col++) {
            if (map[0][col]) {
                boundaries.add(new int[]{0, col});
            }
        }

        // Scansiona il bordo destro
        for (int row = 1; row < rows; row++) {
            if (map[row][cols - 1]) {
                boundaries.add(new int[]{row, cols - 1});
            }
        }

        // Scansiona il bordo inferiore
        for (int col = cols - 2; col >= 0; col--) {
            if (map[rows - 1][col]) {
                boundaries.add(new int[]{rows - 1, col});
            }
        }

        // Scansiona il bordo sinistro
        for (int row = rows - 2; row > 0; row--) {
            if (map[row][0]) {
                boundaries.add(new int[]{row, 0});
            }
        }

        return boundaries;
    }
