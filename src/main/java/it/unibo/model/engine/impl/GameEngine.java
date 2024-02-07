package it.unibo.model.engine.api;

import it.unibo.model.collisions.api.CollisionBox;
import it.unibo.model.entities.api.FinishLocation;
import it.unibo.model.entities.api.GameEntity;
import it.unibo.model.entities.api.MapElement;
import it.unibo.model.entities.api.Character;
import it.unibo.model.entities.api.StartLocation;
import it.unibo.model.level.api.GameState;
import it.unibo.model.physics.api.Direction;
import it.unibo.common.SimpleEntiete;

import java.util.HashSet;
import java.util.Set;

import javax.swing.text.html.parser.Entity;

public class GameEngine implements Engine {

    private Set<GameEntity> gameConfiguration;

    private boolean hasCharacter = false;
    private boolean hasStartLocation = false;
    private boolean hasFinishLocation = false;

    public GameEngine() {
        this.gameConfiguration = new HashSet<>();
    }

    @Override
    public boolean createLevel() {
        // Return true if all required entities are present
        return this.hasCharacter && this.hasStartLocation && this.hasFinishLocation;
    }

    @Override
    public boolean addGameEntity(SimpleEntiete entity) {
        GameEntity gameEntity = null;

        if (entity instanceof Character) {
            this.hasCharacter = true;
            gameEntity = new Character();
        } else if (entity instanceof StartLocation) {
            this.hasStartLocation = true;
            gameEntity = new StartLocation();
        } else if (entity instanceof FinishLocation) {
            this.hasFinishLocation = true;
            gameEntity = new FinishLocation();
        } else if (entity instanceof MapElement) {
            gameEntity = new MapElement();
        }

        if (gameEntity != null && gameEntity.checkCollision()) {
            gameConfiguration.add(gameEntity);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean removeGameEntity(double x, double y) {
        // check
        for (GameEntity entity : gameConfiguration) {
            if (entity.getPosition().getX() == x && entity.getPosition().getY() == y) {
                gameConfiguration.remove(entity);
                return true;
            }
        }
        return false;
    }

    // @Override
    // public void updateLevel() {
    // // TODO Auto-generated method stub
    // throw new UnsupportedOperationException("Unimplemented method
    // 'updateLevel'");
    // }

    // @Override
    // public void moveCharacter(Direction dir) {
    // // TODO Auto-generated method stub
    // throw new UnsupportedOperationException("Unimplemented method
    // 'moveCharacter'");
    // }

    // @Override
    // public GameState getGameState() {
    // // TODO Auto-generated method stub
    // throw new UnsupportedOperationException("Unimplemented method
    // 'getGameState'");
    // }

    public Set<GameEntity> getLevelEntities() {
        return this.gameConfiguration;
    }

}
