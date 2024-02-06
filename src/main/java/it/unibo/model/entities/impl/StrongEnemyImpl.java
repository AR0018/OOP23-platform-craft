package it.unibo.model.entities.impl;

import it.unibo.model.entities.api.Character;
import it.unibo.model.entities.api.EntitySize;
import it.unibo.model.level.api.Level;
import it.unibo.model.physics.api.Physics;
import it.unibo.model.physics.api.PhysicsBuilder;
import it.unibo.model.physics.api.Position;
import it.unibo.model.physics.api.SpeedLevels;

/**
 * Models an different type of enemy that can follow the player.
 */
public final class StrongEnemyImpl extends EnemyImpl {

    private final static float visibleDistance = 5f;
    private final Physics physics;
    private Level level;
    private PhysicsBuilder builder;

    /**
     * Constructor of the StrongEnemy.
     * @param position indicates the initial position of the enemy
     * @param size indicates the size of the enemy
     */
    public StrongEnemyImpl(final Position position, final EntitySize size) {
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

    }

    private boolean playerIsVisible(final Character character) {    //TODO: metodo per capire se avviene una collisione prima del character
        return false;
    }

    private boolean playerInRange(final Character character) {
        Position charPos = this.level.getCharacter().getPosition();
        var distance = Math.sqrt(Math.pow(charPos.getX() - getPosition().getX(), 2) + 
                Math.pow(charPos.getY() - getPosition().getY(), 2));
        if (distance <= visibleDistance) {
            return true;
        }
        return false;
    }

}
