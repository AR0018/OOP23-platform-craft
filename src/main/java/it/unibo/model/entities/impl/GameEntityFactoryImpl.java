package it.unibo.model.entities.impl;

import it.unibo.common.EntityType;
import it.unibo.model.entities.api.GameEntity;
import it.unibo.model.entities.api.GameEntityFactory;
import it.unibo.model.level.api.Level;
import it.unibo.model.physics.api.Position;

import java.lang.IllegalArgumentException;

/**
 * Class used to delegate the creation of the different entities using a type
 * to distinguish them.
 */
public final class GameEntityFactoryImpl implements GameEntityFactory {

    @Override
    public GameEntity createGameEntity(final EntityType type, final Position position, final Level level) {
        switch (type) {
            case CHARACTER:
                return new CharacterImpl(position, level);
            case ENEMY:
                return new StrongEnemyImpl(position, level);
            case SIMPLE_ENEMY:
                return new SimpleEnemyImpl(position, level);
            case TRAP:
                return new TrapImpl(position, level);
            case MAP_ELEMENT:
                return new MapElementImpl(position, level);
            case FINISH_LOCATION:
                return new FinishLocationImpl(position, level);
            default:
                throw new IllegalArgumentException();        //Controllare che non sia nullo
        }
    }
}
