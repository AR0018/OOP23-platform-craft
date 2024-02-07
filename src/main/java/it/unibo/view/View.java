package it.unibo.view;

import it.unibo.common.SimpleEntiete;
import java.util.Set;
/**
 * Models the GUI of the project.
 */
public interface View {

    /**
     * DisplayStart is responsible of the TitleScreen that appears once the game is started.
     * Useful to select what the player needs to do like creates its own project or plays one that has been 
     * created.
     */
    void displayStart();

    /**
     * The method defines the display that appears once the player reaches the end and wins the game.
     */
    void displayWin();

    /**
     * Defines the display that appears once the Character die and the player loses the game.
     */
    void displayGameOver();

    /**
     * Checks the Set of SimpleEntiete and lets visualize on screen all the elements of the Set.
     * @param entities is a set of SimpleEntiete
     */
    void render(Set<SimpleEntiete> entities);

}
