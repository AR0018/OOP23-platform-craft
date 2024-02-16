package it.unibo.model.engine.impl;

// import it.unibo.model.entities.api.FinishLocation;
import it.unibo.model.entities.api.GameEntity;
// import it.unibo.model.entities.api.MapElement;
import it.unibo.model.entities.api.Character;
// import it.unibo.model.entities.api.StartLocation;
// import it.unibo.model.entities.impl.CharacterImpl;
import it.unibo.model.level.api.GameState;
import it.unibo.model.level.api.Level;
import it.unibo.model.level.impl.GameLevel;
import it.unibo.model.level.impl.MapBoundaries;
import it.unibo.model.physics.api.Direction;
import it.unibo.common.EntityType;
import it.unibo.common.SimpleEntity;
import it.unibo.common.impl.SimpleEntityImpl;
import it.unibo.model.engine.api.Editor;
import it.unibo.model.engine.api.Engine;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

/**
 * An implementation of Editor.
 */
public class EditorImpl implements Editor {

    /*
     * The width and height of the created Level.
     */
    private static final double WIDTH = 1600;
    private static final double HEIGHT = 900;

    private Set<SimpleEntity> gameConfiguration;
    private Set<GameEntity> gameEntities;
    private Character character;

    private boolean hasCharacter = false;
    private boolean hasFinishLocation = false;

    /**
     * Constructs an Editor with the given input configuration.
     * If the level must be created from scratch, the user of this class should pass
     * an empty Set.
     * 
     * @param entities the entities that this Editor will contain at the beginning
     */
    public EditorImpl(final Set<SimpleEntity> entities) {
        this.gameConfiguration = Objects.requireNonNull(entities);
    }

    /*
     * Return true if all required entities are present.
     */
    private boolean validLevel() {
        return this.hasCharacter && this.hasFinishLocation;
    }

    @Override
    public Optional<Engine> createLevel() {
        if (validLevel()) {
            Level level = new GameLevel(this.gameEntities, this.character, WIDTH, HEIGHT);
            return Optional.of(new EngineImpl(level));
        }
        return Optional.empty();
    }

    /*
     * @Override
     * public boolean addGameEntity(final SimpleEntity entity) {
     * if(entity!=null){
     * if(entity.getType()==EntityType.FINISH_LOCATION){
     * this.hasFinishLocation=true;
     * this.gameConfiguration.add(entity);
     * }else if(entity.getType()==EntityType.START_LOCATION){
     * this.hasStartLocation=true;
     * this.gameConfiguration.add(entity);
     * }else if(entity.getType()==EntityType.CHARACTER){
     * this.hasCharacter=true;
     * this.gameConfiguration.add(entity);
     * this.characterEngine=entity;
     * }
     * gameConfiguration.add(entity);
     * }
     * if(gameConfiguration.contains(entity)){
     * return true;
     * }else{
     * return false;
     * }
     * }
     */

    public boolean addGameEntity(final SimpleEntity entity) {
        Objects.requireNonNull(entity);
        if (entity.getType().equals(EntityType.FINISH_LOCATION)) {
            if (this.gameConfiguration.stream().anyMatch(e -> e.getType().equals(EntityType.FINISH_LOCATION))) {
                return false;
            }
        }
        if (entity.getType().equals(EntityType.CHARACTER)) {
            if (this.gameConfiguration.stream().anyMatch(e -> e.getType().equals(EntityType.CHARACTER))) {
                return false;
            }
        }
        gameConfiguration.add(entity);
        if (gameConfiguration.contains(entity)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean removeGameEntity(final double x, final double y) {

        /**
         * Check if there is a game entity at the specified coordinates (x, y)
         * and remove the entity found.
         */
        Optional<GameEntity> entityToRemove = gameConfiguration.stream()
                .filter(entity -> entity.getPosition().getX() == x && entity.getPosition().getY() == y)
                .findFirst();

        if (entityToRemove.isPresent()) {
            GameEntity removedEntity = entityToRemove.get();
            gameConfiguration.remove(removedEntity);
            return true;
        } else {
            return false;
        }
    }

    /*
     * private SimpleEntity converToSimpleEntity(GameEntity entity){
     * if(entity!=null){
     * this.simpleEntity.getX(entity.getPosition().getX());
     * this.simpleEntity.getY(entity.getPosition().getY());
     * this.simpleEntity.getType(entity.getType());
     * }
     * return simpleEntity;
     * }
     */

    @Override
    public Set<SimpleEntity> getLevelEntities() {
        return Collections.unmodifiableSet(this.gameConfiguration);
    }

    private void resetAll() {
        this.gameConfiguration = new HashSet<>();
    }

    @Override
    public void clearAll() {
        resetAll();
    }

    @Override
    public double getLevelWidth() {
        return WIDTH;
    }

    @Override
    public double getLevelHeight() {
        return HEIGHT;
    }
}
