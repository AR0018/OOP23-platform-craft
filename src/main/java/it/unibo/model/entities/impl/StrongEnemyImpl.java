package it.unibo.model.entities.impl;

import it.unibo.model.entities.api.Character;
import it.unibo.model.entities.api.EntitySize;
import it.unibo.model.level.api.Level;
import it.unibo.model.physics.api.Physics;
import it.unibo.model.physics.api.PhysicsBuilder;
import it.unibo.model.physics.api.Position;
import it.unibo.model.physics.api.SpeedLevels;

public class StrongEnemyImpl extends EnemyImpl{

    private final Physics physics;
    private Level level;
    private PhysicsBuilder builder;

    public StrongEnemyImpl(Position position, EntitySize size) {
        super(position, size);
        this.physics = this.builder.setGameObject(this)
                .addAccelerationOnX()
                .addFallingPhysics()
                .setSpeedOnX(SpeedLevels.MEDIUM)
                .create();
    }

    @Override
    public void updateState() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateState'");
    }

    @Override
    public void moveEnemy() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'moveEnemy'");
    }
    
    private boolean playerIsVisible(final Character character) {
        return false;
    }

    private boolean playerInRange(final Character character) {
        return false;
    }

}
