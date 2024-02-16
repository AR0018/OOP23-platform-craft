package it.unibo.model.engine.impl;

import it.unibo.model.entities.api.GameEntity;
import it.unibo.model.entities.impl.GameEntityFactoryImpl;
import it.unibo.model.entities.api.Character;
import it.unibo.model.entities.api.FinishLocation;
import it.unibo.model.level.api.Level;
import it.unibo.model.level.impl.GameLevel;
import it.unibo.model.level.impl.UnmodifiableLevel;
import it.unibo.model.physics.impl.Position2D;
import it.unibo.common.EntityType;
import it.unibo.common.SimpleEntity;
import it.unibo.common.impl.SimpleEntityImpl;
import it.unibo.model.engine.api.Editor;
import it.unibo.model.engine.api.Engine;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * An implementation of Editor.
 */
public final class EditorImpl implements Editor {
    /*
     * The width and height of the created Level.
     */
    private static final double WIDTH = 1600;
    private static final double HEIGHT = 900;

    private Level level;

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
        this.level = new GameLevel(WIDTH, HEIGHT);
        if (isValid(Objects.requireNonNull(entities))) {
            //Adds to the Level every entity in the configuration passed
            entities.stream()
                .map(e -> createFromSimpleEntity(e))
                .forEach(e -> this.level.addGameEntity(e));
            this.hasCharacter = true;
            this.hasFinishLocation = true;
        }
    }

    /*
     * Checks if the configuration passed in input is valid.
     * A configuration is valid if it has exactly one Character and one FinishLocation.
     */
    private boolean isValid(final Set<SimpleEntity> entities) {
        return entities.stream().filter(e -> e.getType().equals(EntityType.CHARACTER)).count() == 1
            && entities.stream().filter(e -> e.getType().equals(EntityType.FINISH_LOCATION)).count() == 1;
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
            return Optional.of(new EngineImpl(new UnmodifiableLevel(level)));
        }
        return Optional.empty();
    }

    @Override
    public boolean addGameEntity(final SimpleEntity entity) {
        Objects.requireNonNull(entity);
        if (entity.getType().equals(EntityType.FINISH_LOCATION) && this.hasFinishLocation) {
            return false;
        }
        if (entity.getType().equals(EntityType.CHARACTER) && this.hasCharacter) {
            return false;
        }
        GameEntity gameEntity = createFromSimpleEntity(entity);
        if (canBeAdded(gameEntity)) {
            this.level.addGameEntity(gameEntity);
            if (gameEntity instanceof FinishLocation) {
                this.hasFinishLocation = true;
            }
            if (gameEntity instanceof Character) {
                this.hasCharacter = true;
                this.level.setCharacter((Character) gameEntity);
            }
            return true;
        }
        return false;
    }

    /*
     * Tells if the input entity can be added to the Level.
     */
    private boolean canBeAdded(final GameEntity gameEntity) {
        return gameEntity.getCollisions().size() == 0;
    }

    /*
     * Creates a new GameEntity based on the input SimpleEntity.
     */
    private GameEntity createFromSimpleEntity(final SimpleEntity entity) {
        return new GameEntityFactoryImpl()
            .createGameEntity(
                entity.getType(),
                new Position2D(entity.getX(), entity.getY()),
                new UnmodifiableLevel(this.level));
    }

    @Override
    public boolean removeGameEntity(final double x, final double y) {
        /**
         * Check if there is a game entity at the specified coordinates (x, y)
         * and remove the entity found.
         */
        Optional<GameEntity> entityToRemove = this.level.getGameEntities().stream()
                .filter(e -> e.getBoundaries().contains(new Position2D(x, y)))
                .findFirst();
        if (entityToRemove.isPresent()) {
            this.level.removeGameEntity(entityToRemove.get());
            if (entityToRemove.get() instanceof Character) {
                this.hasCharacter = false;
            }
            if (entityToRemove.get() instanceof FinishLocation) {
                this.hasFinishLocation = false;
            }
            return true;
        }
        return false;
    }

    @Override
    public Set<SimpleEntity> getLevelEntities() {
        /*
         * Generates a set of SimpleEntity from the set of GameEntity in the current level
         * and returns it.
         */
        return this.level.getGameEntities().stream()
            .map(e -> new SimpleEntityImpl(e.getType(), e.getPosition().getX(), e.getPosition().getY()))
            .collect(Collectors.toSet());
    }

    private void resetAll() {
        this.level = new GameLevel(WIDTH, HEIGHT);
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
