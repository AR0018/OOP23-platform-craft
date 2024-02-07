package it.unibo.model.level.api;

import java.util.Set;

import it.unibo.common.EntityType;
import it.unibo.model.entities.api.GameEntity;
import it.unibo.model.physics.api.Direction;
import it.unibo.model.collisions.api.CollisionBox;
 

import java.util.HashSet;


public class GameLevel implements Level {

    private Set<GameEntity> levelConfiguration; // set of GameEntity
    private Set<GameEntity> enemyEntity; // set of enemy
    private Set<GameEntity> trapEntity; //set of trap
    private GameEntity finishLocation; // Save the finish location
    private GameEntity charachterEntity; // save the Charachter
    private GameState gameState = GameState.STATE_UNKNOW; // save the game state

    public GameLevel() {

        this.levelConfiguration = new HashSet<>();
    }

    @Override
    public void addGameEntity(GameEntity entity) {
        if(entity.getType()==EntityType.CHARACTER){ //Check if the entity is the charachter to save it in a variable
            this.charachterEntity=entity;
            this.levelConfiguration.add(entity);
        }else if( entity.getType()==EntityType.FINISH_LOCATION){ //Check if the entity is the Finish Location to save it in a variable
            this.finishLocation=entity;
            this.levelConfiguration.add(entity);
        }else if( entity.getType()==EntityType.ENEMY){
            this.enemyEntity.add(entity);
            this.levelConfiguration.add(entity);
        }else if(entity.getType()==EntityType.TRAP){
            this.trapEntity.add(entity);
            this.levelConfiguration.add(entity);
        }
        this.levelConfiguration.add(entity);

    }

    @Override
    public Set<GameEntity> getGameEntities() {
      return this.levelConfiguration;
    }

    

    @Override
    public void computeChanges() {
        /**
         * Check the Character position, if it's the same of the finishLocation
         * gameState = WIN.
         */
        if(finishLocation.getPosition().getX()==charachterEntity.getPosition().getX() 
                && finishLocation.getPosition().getY()==charachterEntity.getPosition().getY()){
                    this.gameState = GameState.WIN;
        } 
        /**
         * Check the Character colliding with a enemyEntity in the level
         * gameState = GAMEOVER
         */
        for (GameEntity charachterEntity : enemyEntity) {
            if(isCollidingWith(charachterEntity, enemyEntity)){
                this.gameState = GameState.GAMEOVER;

            }
        }
        for (GameEntity charachterEntity : trapEntity) {
            if(isCollidingWith(charachterEntity, trapEntity)){
                this.gameState=GameState.GAMEOVER;
            }
        }
        // Check if the charachter colliding with the low limit of the map.
        if(charachterEntity.getPosition().getY()==0){
            this.gameState=GameState.GAMEOVER;
        }
        this.gameState=GameState.RUNNING;
    }

    @Override
    public void moveCharacter(Direction dir) {
        if(gameState == GameState.RUNNING){
            
        }
        
    }

    @Override
    public GameState getGameState() {
        //return the game state.
        return this.gameState;
    }

    @Override
    public GameEntity getCharacter() {
        return this.charachterEntity;
    }
    
}
