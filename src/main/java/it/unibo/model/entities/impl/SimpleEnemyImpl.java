package it.unibo.model.entities.impl;

import it.unibo.model.collisions.api.Boundaries;
import it.unibo.model.entities.api.EntitySize;
import it.unibo.model.physics.api.Physics;
import it.unibo.model.physics.api.PhysicsBuilder;
import it.unibo.model.physics.api.Position;
import it.unibo.model.physics.api.SpeedLevels;

/**
 * Models the concept of SimpleEnemy extending from EnemyImpl.
 */
public final class SimpleEnemyImpl extends EnemyImpl {

    private final Physics physics; 
    private PhysicsBuilder builder;

    /**
     * Constructor for simpleEnemy.
     * @param position is the beginning position of the simpleEnemy
     */
    public SimpleEnemyImpl(final Position position) {
        super(position, EntitySize.NORMAL);
        this.physics = this.builder.setGameObject(this)
                .setSpeedOnX(SpeedLevels.SLOW)
                .addAccelerationOnX().create();
    }

    private void moveEnemy() {
        this.physics.setMovement(getDirection());
    }

    @Override
    public void updateState() {
        moveEnemy();
        this.physics.calculateMovement();
        checkEnemyCollisions();
    }
}
