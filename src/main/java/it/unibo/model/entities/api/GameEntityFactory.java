package it.unibo.model.entities.api;

import it.unibo.common.EntityType;
import it.unibo.model.level.api.Level;
import it.unibo.model.physics.api.Position;

/**
 * The interface that models a GameEntity with a Factory.
 */
public interface GameEntityFactory {
    
    /**
     * Creates a GameEntity from a type assigning a position and the level.
     * @param type of the GameEntity
     * @param position the first position
     * @param level of the game
     * @return a GameEntity
     */
    GameEntity createGameEntity(EntityType type, Position position, Level level);
}
