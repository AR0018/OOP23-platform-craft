package it.unibo.model.entities.impl;

import it.unibo.common.EntityType;
import it.unibo.model.entities.api.Character;
import it.unibo.model.entities.api.Enemy;
import it.unibo.model.physics.api.Direction;
import it.unibo.model.physics.api.Physics;
import it.unibo.model.physics.api.PhysicsBuilder;
import it.unibo.model.physics.api.Position;
import java.util.stream.Collectors;
import java.util.Objects;
/**
 * Implementation of the Interface Character, 
 * where it cointains all the necessary to create the character.
 * Final because the class doesn't need to be extended
 */
public final class CharacterImpl extends GameEntityImpl implements Character {

    private final Physics physic;
    private PhysicsBuilder physicsBuilder;
    /**
     * It is the constructor of the class to initialize the character itself.
     * @param position the initial coordinate of the character
     */
    public CharacterImpl(final Position position) {
        super(position);
        this.physic = this.physicsBuilder
                .setGameObject(this)
                .addAccelerationOnX()
                .addFallingPhysics()
                .create();
    }

    @Override
    public void updateState() {
        physic.calculateMovement();
        checkEnemyCollision();
    }

    @Override
    public EntityType getType() {
        return EntityType.CHARACTER;
    }

    @Override
    public void move(final Direction dir) {     //TODO: needs to be improved
        if (Objects.nonNull(dir)) {             //i nemici possono modificare la direzione
            physic.setMovement(dir);            //CheckEnemyCollision here?
        }
    }

    private void checkEnemyCollision() {
        /*
        if (this.box.getCollisions().stream().anyMatch(x -> x instanceof Enemy)) { TODO:causes error with new design
            this.isAlive = false;
        }*/
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
