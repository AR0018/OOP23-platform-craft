package it.unibo.model.entities.impl;

import it.unibo.common.EntityType;
import it.unibo.model.entities.api.Character;
import it.unibo.model.entities.api.Enemy;
import it.unibo.model.level.api.Level;
import it.unibo.model.physics.api.Direction;
import it.unibo.model.physics.api.Physics;
import it.unibo.model.physics.api.PhysicsBuilder;
import it.unibo.model.physics.api.Position;
import it.unibo.model.physics.impl.PhysicsBuilderImpl;
import it.unibo.model.entities.api.Trap;
import it.unibo.model.entities.api.TrapState;

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
     */
    public CharacterImpl(final Position position, final Level level) {
        super(position, level);
        this.physic = this.physicsBuilder
                .setGameEntity(this)
                .addAccelerationOnX()
                .addFallingPhysics()
                .create();
    }

    @Override
    public void updateState() {
        physic.calculateMovement();
        checkEnemyCollision();
        checkTrapCollision();
    }

    @Override
    public EntityType getType() {
        return EntityType.CHARACTER;
    }

    @Override
    public void move(final Direction dir) {     //TODO: needs to be improved
        physic.setMovement(Objects.requireNonNull(dir));       //i nemici possono modificare la direzione
                                                             //CheckEnemyCollision here?
    }

    private void checkTrapCollision() {
        if (!getCollisions().isEmpty()) {
            var trapCollisions = getCollisions()
                    .stream()
                    .filter(x -> x.getGameEntity() instanceof Trap)
                    .collect(Collectors.toSet());
            if(!trapCollisions.isEmpty()) {
                var trap = (Trap)trapCollisions.stream().findFirst().get().getGameEntity();
                if (trap.getTrapState().equals(TrapState.DEAD)) {           //TODO: controllare se può funzionare
                    setAlive(false);
                }
            }
        }
    }


    private void checkEnemyCollision() {
        if (!getCollisions().isEmpty()) {
            var enemySetCollision = getCollisions()
                    .stream()
                    .filter(x -> x.getGameEntity() instanceof Enemy)
                    .collect(Collectors.toSet());
            if (!enemySetCollision.isEmpty()) {
                if (!enemySetCollision.stream().findFirst().get().getDirection().equals(Direction.UP)) {
                    setAlive(false);
                }
            }
        }
    }
}
