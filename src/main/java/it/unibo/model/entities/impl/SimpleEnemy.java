package it.unibo.model.entities.impl;

import it.unibo.common.EntityType;
import it.unibo.model.collisions.api.CollisionBox;
import it.unibo.model.entities.api.Enemy;
import it.unibo.model.entities.api.Character;
import it.unibo.model.entities.api.EntityCondition;
import it.unibo.model.entities.api.GameEntity;
import it.unibo.model.entities.api.MapElement;
import it.unibo.model.level.api.Level;
import it.unibo.model.physics.api.Direction;
import it.unibo.model.physics.api.Physics;
import it.unibo.model.physics.api.PhysicsBuilder;
import it.unibo.model.physics.api.Position;

public class SimpleEnemy implements Enemy{

    private final Physics physics;
    private final Character player;
    private Level level;
    private CollisionBox box;
    private Position position;
    private PhysicsBuilder physicsBuilder;
    private EntityCondition condition;
    private boolean CollisionUP;
    private Direction direction;
    
    public SimpleEnemy(final Position position, final Character player){
        this.player = player;
        this.position = position;
        this.direction = Direction.RIGHT;
        this.condition = EntityCondition.ALIVE;
        this.physics = this.physicsBuilder.setGameObject(this).create();
    }

    @Override
    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public Position getPosition() {
        return this.position;
    }

    @Override
    public boolean isAlive() {
        return this.condition.equals(EntityCondition.ALIVE);
    }

    @Override
    public void updateState() {
        moveEnemy();
        this.physics.calculateMovement();
        checkEnemyCollisions();
    }

    @Override
    public EntityType getType() {
        return EntityType.ENEMY;
    }

    @Override
    public CollisionBox getCollisionBox() {
        return this.box;
    }

    private void moveEnemy() {
        this.physics.setMovement(this.direction);
    }

    private void checkEnemyCollisions(){
        this.box.checkCollisions(this.level.getGameEntities());     //TODO: understand what checkCollisions does
        if(!this.box.getCollisions().isEmpty()) {
            if (this.box.isCollidingWith(this.player)) {
                checkEnemyIsDead();
            } else {
                
            }
        }
    }

    private void checkEnemyIsDead() {
        this.CollisionUP = this.box.getCollisions().stream()
                    .filter(x -> x instanceof Character)
                    .anyMatch(x -> x.getDirection().equals(Direction.UP));
        if (this.CollisionUP) {
            this.condition = EntityCondition.DEAD;
        }
    }
}
