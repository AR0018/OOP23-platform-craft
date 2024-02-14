package it.unibo.model.level.api;

/**
 * Specifies the state of the game.
 */
public enum GameState {
    /**
     * the initial state of the var gameState.
     */
    STATE_UNKNOW,
    /**
     * The game is still running.
     */
    RUNNING,
    /**
     * The player reached the end of the level and won.
     */
    WIN,
    /**
     * The player lost.
     */
    GAMEOVER;
}
