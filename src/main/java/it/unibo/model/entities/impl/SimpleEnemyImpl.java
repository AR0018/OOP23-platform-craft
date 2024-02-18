package it.unibo.model.entities.impl;

import it.unibo.common.EntityType;
import it.unibo.model.level.api.Level;
import it.unibo.model.physics.api.Physics;
import it.unibo.model.physics.api.PhysicsBuilder;
import it.unibo.model.physics.api.Position;
import it.unibo.model.physics.api.SpeedLevels;
import it.unibo.model.physics.impl.PhysicsBuilderImpl;

/**
 * Models the concept of SimpleEnemy extending from EnemyImpl.
 */
public final class SimpleEnemyImpl extends EnemyImpl {

    private final Physics physics; 
    private final PhysicsBuilder builder = new PhysicsBuilderImpl();

    /**
     * Constructor for simpleEnemy.
     * @param position is the beginning position of the simpleEnemy
     * @param level the level of the game
     */
    public SimpleEnemyImpl(final Position position, final Level level) {
        super(position, level, EntityType.SIMPLE_ENEMY.getWidth(), EntityType.SIMPLE_ENEMY.getHeigth());
        this.physics = this.builder.addGameEntity(this)
                .addFallingPhysics()
                .addSpeedOnX(SpeedLevels.SLOW)
                .create();
    }

    /**
     * Carrise out checks on the enemy's movement.
     */
    @Override
    protected void moveEnemy() {
        this.physics.setMovement(getDirection());
        this.setPosition(this.physics.calculateMovement());
    }

    @Override
    public EntityType getType() {
        return EntityType.SIMPLE_ENEMY;
    }
}
