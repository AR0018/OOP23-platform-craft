package it.unibo.view.api;

import java.util.Set;

import it.unibo.common.SimpleEntity;

/*
 * TODO: decide whether to keep as interface or just use a class
 */
public interface PaintPanel {

    /**
     * Displays on screen all the entities in the set.
     * @param entities the entities to be shown
     */
    void render(Set<SimpleEntity> entities);
}
