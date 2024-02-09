package it.unibo.model.entities.impl;

import it.unibo.model.entities.api.Character;
import it.unibo.model.entities.api.GameEntity;
import it.unibo.model.level.api.Level;
import it.unibo.model.entities.api.EntitySize;
import it.unibo.model.physics.api.Direction;
import it.unibo.model.physics.api.Physics;
import it.unibo.model.physics.api.PhysicsBuilder;
import it.unibo.model.physics.api.Position;
import it.unibo.model.physics.api.SpeedLevels;
import it.unibo.model.physics.impl.PhysicsBuilderImpl;
import java.util.List;

/**
 * Models an different type of enemy that can follow the player.
 */
public final class StrongEnemyImpl extends EnemyImpl {

    private static final float VISIBLE_DISTANCE = 10f;
    private final Physics physics;
    private PhysicsBuilder builder = new PhysicsBuilderImpl();

    /**
     * Constructor of the StrongEnemy.
     * @param position indicates the initial position of the enemy
     * @param size indicates the size of the enemy
     * @param speed indicates the enemy's movement speed
     */
    public StrongEnemyImpl(final Position position, final EntitySize size
            , final SpeedLevels speed, final Level level) {
        super(position, size, level);                                  //TODO: modificare il costruttore
        this.physics = this.builder.setGameEntity(this)         //per essere un po più liberi anche scegliere
                .addAccelerationOnX()                           //la velocità
                .addFallingPhysics()
                .setSpeedOnX(speed)
                .create();
    }

    /*@Override
    public void updateState() {
        moveEnemy();
        this.physics.calculateMovement();
        checkEnemyCollisions();
    }*/

    protected void moveEnemy() {
        if (playerIsVisible(getCharacter()) && playerInRange(getCharacter())) {
            if (getCharacter().getPosition().getX() > getPosition().getX()) {
                setDirection(Direction.RIGHT);
            } else {
                setDirection(Direction.LEFT);
            }
        } else {
            this.physics.setMovement(getDirection());
        }
        this.physics.calculateMovement();
    }

    private boolean playerIsVisible(final Character character) {
        Position charPos = character.getPosition();

        List<GameEntity> list = getLevel().getGameEntities()
                .stream()
                .filter(x -> x.getBoundaries().intersectsLine(getPosition(), charPos))
                .filter(x -> !x.equals(this))
                .filter(x -> !(x instanceof Character))
                .toList();

        if (list.isEmpty()) {
            return false;
        }
        return true;
    }

    private boolean playerInRange(final Character character) {
        Position charPos = getCharacter().getPosition();
        var distance = Math.sqrt(Math.pow(charPos.getX() - getPosition().getX(), 2) 
                + Math.pow(charPos.getY() - getPosition().getY(), 2));
        if (distance <= VISIBLE_DISTANCE) {
            return true;
        }
        return false;
    }

    private Character getCharacter() {
        return (Character) getLevel().getCharacter();
    }
}
