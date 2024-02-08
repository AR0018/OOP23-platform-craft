package it.unibo.view.api;

import it.unibo.common.SimpleEntity;
import java.util.Set;
/**
 * Models the GUI of the project.
 */
public interface View {

    /**
     * DisplayStart is responsible of the TitleScreen that appears once the game is started.
     * Useful to select what the player needs to do like creates its own project or plays one that has been 
     * created. WHen called, sets all the other views as invisible.
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
     * Checks the Set of SimpleEntity and shows on screen all the elements of the Set.
     * @param entities is a set of SimpleEntity
     */
    void render(Set<SimpleEntity> entities);

}
