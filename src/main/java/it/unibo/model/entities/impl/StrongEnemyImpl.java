package it.unibo.model.entities.impl;

import it.unibo.common.EntityType;
import it.unibo.model.entities.api.Character;
import it.unibo.model.entities.api.GameEntity;
import it.unibo.model.level.api.Level;
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

    private static final float VISIBLE_DISTANCE = (float) (EntityType.ENEMY.getWidth() * 3);
    private final Physics physics;
    private PhysicsBuilder builder = new PhysicsBuilderImpl();

    /**
     * Constructor of the StrongEnemy.
     * @param position indicates the initial position of the enemy
     * @param level the level of the game
     */
    public StrongEnemyImpl(final Position position, final Level level) {
        super(position, level, EntityType.ENEMY.getWidth(), EntityType.ENEMY.getHeigth());
        this.physics = this.builder.setGameEntity(this)
                //.addAccelerationOnX()
                .addFallingPhysics()
                .setSpeedOnX(SpeedLevels.FAST)
                .create();
    }

    @Override
    public EntityType getType() {
        return EntityType.ENEMY;
    }

    /**
     * Checks if during the movement the has encountered the player.
     */
    protected void moveEnemy() {
        if (playerIsVisible(getCharacter()) && playerInRange(getCharacter())) {
            if (getCharacter().getPosition().getX() > getPosition().getX()) {
                setDirection(Direction.RIGHT);
            } else {
                setDirection(Direction.LEFT);
            }
        } /*else {
            this.physics.setMovement(getDirection());
        }*/
        this.physics.setMovement(getDirection());
        this.setPosition(this.physics.calculateMovement());
    }

    private boolean playerIsVisible(final Character character) {
        Position charPos = character.getPosition();

        List<GameEntity> list = getLevel().getGameEntities()
                .stream()
                .filter(x -> x.getBoundaries().intersectsLine(this.getPosition(), charPos))
                .filter(x -> !x.equals(this))
                .filter(x -> !(x instanceof Character))
                .toList();

        if (list.isEmpty()) {
            return true;
        }
        return false;
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
