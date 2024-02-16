package it.unibo.model.entities.impl;

import it.unibo.common.EntityType;
import it.unibo.model.collisions.api.BorderCollision;
import it.unibo.model.collisions.api.Collision;
import it.unibo.model.collisions.api.EntityCollision;
import it.unibo.model.entities.api.Character;
import it.unibo.model.entities.api.Enemy;
import it.unibo.model.level.api.Level;
import it.unibo.model.physics.api.Direction;
import it.unibo.model.physics.api.Physics;
import it.unibo.model.physics.api.PhysicsBuilder;
import it.unibo.model.physics.api.Position;
import it.unibo.model.physics.impl.PhysicsBuilderImpl;
import it.unibo.model.entities.api.Trap;

import java.util.Set;
import java.util.HashSet;

import java.util.stream.Collectors;
import java.util.Objects;
/**
 * Implementation of the Interface Character, 
 * where it cointains all the necessary to create the character.
 * Final because the class doesn't need to be extended
 */
public final class CharacterImpl extends GameEntityImpl implements Character {

    private final Physics physic;
    private PhysicsBuilder physicsBuilder = new PhysicsBuilderImpl();
    /**
     * It is the constructor of the class to initialize the character itself.
     * @param position the initial coordinate of the character
     * @param level the level of the game
     */
    public CharacterImpl(final Position position, final Level level) {
        super(position, level, EntityType.CHARACTER.getWidth(), EntityType.CHARACTER.getHeigth());
        this.physic = this.physicsBuilder
                .setGameEntity(this)
                .addAccelerationOnX()
                .addFallingPhysics()
                .create();
    }

    @Override
    public void updateState() {
        this.setPosition(physic.calculateMovement());
        checkCollision();
    }

    @Override
    public EntityType getType() {
        return EntityType.CHARACTER;
    }

    @Override
    public void move(final Direction dir) {
        physic.setMovement(Objects.requireNonNull(dir));
    }

    private void checkCollision() {
        if (!getCollisions().isEmpty()) {
            checkEnemyCollision();
            checkTrapCollision();
            checkBorderCollision();
        }
    }

    private void checkTrapCollision() {
        var trapCollisions = getEntity(getCollisions())
                    .stream()
                    .filter(x -> x.getGameEntity() instanceof Trap)
                    .collect(Collectors.toSet());
            if (!trapCollisions.isEmpty()) {
                var trap = (Trap) trapCollisions.stream().findFirst().get().getGameEntity();
                if (trap.isLethal()) {           //TODO: controllare se può funzionare
                    setAlive(false);
                }
            }
    }


    private void checkEnemyCollision() {
        var enemySetCollision = getEntity(getCollisions())
                    .stream()
                    .filter(x -> x.getGameEntity() instanceof Enemy)
                    .collect(Collectors.toSet());
            if (!enemySetCollision.isEmpty()) {
                if (!(enemySetCollision.size() == 1 && 
                        enemySetCollision.stream().findFirst().get().getDirection().equals(Direction.DOWN))) {
                            setAlive(false);
                }
                /*if (!enemySetCollision.stream().findFirst().get().getDirection().equals(Direction.DOWN)) {
                    setAlive(false);
                }*/
            }
    }

    private void checkBorderCollision() {
        var borderSetCollision = getBorder(getCollisions())
                .stream()
                .collect(Collectors.toSet());
        if (!borderSetCollision.isEmpty()) {
            if (borderSetCollision.stream().anyMatch(x -> x.getDirection().equals(Direction.DOWN))) {
                setAlive(false);
            }
        }
    }

    private Set<BorderCollision> getBorder(final Set<Collision> collisions) {
        Set<BorderCollision> borderCollision = new HashSet<>();
        getCollisions()
                .stream()
                .filter(x -> x instanceof BorderCollision)
                .forEach(x -> borderCollision.add((BorderCollision) x));
        return borderCollision;
    }

    private Set<EntityCollision> getEntity(final Set<Collision> collision) {
        Set<EntityCollision> entityCollision = new HashSet<>();
        getCollisions()
                .stream()
                .filter(x -> x instanceof EntityCollision)
                .forEach(x -> entityCollision.add((EntityCollision) x));
        return entityCollision;
    }
}
