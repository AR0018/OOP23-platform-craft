package it.unibo.model.entities.impl;

import it.unibo.model.entities.api.EntitySize;
import it.unibo.model.physics.api.Physics;
import it.unibo.model.physics.api.PhysicsBuilder;
import it.unibo.model.physics.api.Position;
import it.unibo.model.physics.api.SpeedLevels;

public class SimpleEnemy2Impl extends EnemyImpl{

    private final Physics physics; 
    private PhysicsBuilder builder;

    public SimpleEnemy2Impl(Position position) {
        super(position, EntitySize.NORMAL);
        this.physics = this.builder.setGameObject(this)
                .setSpeedOnX(SpeedLevels.SLOW)
                .addAccelerationOnX().create();
    }

    @Override
    public void moveEnemy() {
        this.physics.setMovement(getDirection());
    }

    @Override
    public void updateState() {
        moveEnemy();
        this.physics.calculateMovement();
        checkEnemyCollisions();
    }

}
