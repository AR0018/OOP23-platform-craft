package it.unibo.model.level.api;

/**
 * Specifies the state of the game
 */
public enum GameState {
    /**
     * The game is still running
     */
    RUNNING,
    /**
     * The player reached the end of the level and won 
     */
    WIN,
    /**
     * The player lost
     */
    GAMEOVER;
}
