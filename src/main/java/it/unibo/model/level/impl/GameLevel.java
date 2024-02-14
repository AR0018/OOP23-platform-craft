package it.unibo.model.level.impl;

import java.util.Set;

import it.unibo.common.api.EntityType;
import it.unibo.model.engine.impl.EngineImpl;
import it.unibo.model.entities.api.GameEntity;
import it.unibo.model.entities.impl.CharacterImpl;
import it.unibo.model.physics.api.Direction;
import it.unibo.model.physics.api.Position;
import it.unibo.model.level.api.Level;
import it.unibo.model.level.api.GameState;
import it.unibo.model.level.impl.MapBoundaries;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


public class GameLevel implements Level {

    private Set<GameEntity> levelConfiguration; // set of GameEntity contains all game entity
    private Set<GameEntity> enemyEntities; // set of enemy
    private Set<GameEntity> trapEntities; //set of trap
    private GameEntity finishLocation; // Save the finish location
    private GameEntity characterEntity; // save the Charachter
    private GameEntity startLocation;// save the Start Location
    private GameState gameState = GameState.STATE_UNKNOW; // save the game state
    private CharacterImpl character;
    private MapBoundaries boundaries = new MapBoundaries();

    public GameLevel() {

        this.levelConfiguration = new HashSet<>();
        this.enemyEntities = new HashSet<>();
        this.trapEntities = new HashSet<>();
        this.finishLocation = null;
        this.characterEntity = null;
        this.startLocation = null;
        this.character= new CharacterImpl(null);   
         
    }

    @Override
    public void addGameEntity(GameEntity entity) {
        if(entity.getType()==EntityType.CHARACTER){ //Check if the entity is the charachter to save it in a variable
            this.characterEntity=entity;
            this.levelConfiguration.add(entity);
        }else if( entity.getType()==EntityType.FINISH_LOCATION){ //Check if the entity is the Finish Location to save it in a variable
            this.finishLocation=entity;
            this.levelConfiguration.add(entity);
        }else if( entity.getType()==EntityType.ENEMY){ // Check if the entity is a enemy
            this.enemyEntities.add(entity);
            this.levelConfiguration.add(entity);
        }else if(entity.getType()==EntityType.TRAP){ // Check if the entity is a trap
            this.trapEntities.add(entity);
            this.levelConfiguration.add(entity);
        }else if(entity.getType()==EntityType.START_LOCATION){
            this.startLocation=entity;
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
        if(finishLocation.getPosition().getX()==characterEntity.getPosition().getX() 
                && finishLocation.getPosition().getY()==characterEntity.getPosition().getY()){
                    this.gameState = GameState.WIN;
        } 
        /**
         * Check the Character state
         * isAlive() -> true -> gameState RUNNING;
         * isAlive()-> false -> gamestate GAMEOVER;
         */
        if(characterEntity.isAlive()){
            this.gameState=GameState.RUNNING;
        }else{
            this.gameState=GameState.GAMEOVER;
        }
        // Check if the charachter colliding with the low limit of the map.
        if(characterEntity.getPosition().getY()==0){
            this.gameState=GameState.GAMEOVER;
        }
        this.gameState=GameState.RUNNING;
    }

    @Override
    public void moveCharacter(Direction dir) {
        // check if the dir is null
        if(dir!=null){
            this.character.move(dir);
        }else{
            throw new UnsupportedOperationException("Invalid direction");
        }
    }

    @Override
    public GameState getGameState() {
        //return the game state.
        return this.gameState;
    }

    @Override
    public GameEntity getCharacter() {
        return this.characterEntity;
    }

    /**
     * Adds a finish location to the Level.
     * If the Character touches this location, the game must end.
     * @param position the position in which to put the finish location
     */
    public void addFinishLocation(Position position) {
        // Check if the finish location already exists at the specified position
        boolean finishExists = levelConfiguration.stream()
                .anyMatch(finishLocation -> finishLocation.getPosition().equals(position));
    
        // If the finish location doesn't already exist, add it
        if (!finishExists) {
              
             levelConfiguration.add(finishLocation);
        }
    }

    @Override
    public void removeEnemyEntity(GameEntity entity) {
       if(entity!=null && entity.getType()==EntityType.ENEMY){
            for (GameEntity gameEntity : enemyEntities) {
                if(gameEntity.getPosition().getX()==entity.getPosition().getX() &&  //check the enemy in the set.
                     gameEntity.getPosition().getY()==entity.getPosition().getY()){
                        this.enemyEntities.remove(entity);
                        this.levelConfiguration.remove(entity); 
                }
            }
        }
    }

    @Override
    public void removeEntity(GameEntity entity) {
        if(entity!=null && levelConfiguration!=null && trapEntities!=null &&
            characterEntity!=null && finishLocation!=null && startLocation!=null){
                for (GameEntity gameEntity : levelConfiguration) {
                    if(gameEntity.getPosition().getX()==entity.getPosition().getY() && 
                        gameEntity.getPosition().getY()==entity.getPosition().getY()){
                            if(gameEntity.getType()==EntityType.ENEMY){
                                removeEnemyEntity(entity);
                            }else if(gameEntity.getType()==EntityType.TRAP){
                                this.levelConfiguration.remove(entity);
                                this.trapEntities.remove(entity);
                            }else if(gameEntity.getType()==EntityType.CHARACTER){
                                this.characterEntity=null;
                                this.levelConfiguration.remove(entity);
                            }else if(gameEntity.getType()==EntityType.FINISH_LOCATION){
                                this.finishLocation=null;
                                this.levelConfiguration.remove(entity);
                            }else if(gameEntity.getType()==EntityType.START_LOCATION){
                                this.startLocation=null;
                                this.levelConfiguration.remove(entity);
                            
                            }else{
                                this.levelConfiguration.remove(entity);
                            }
                    }
                
                }
        }
    }

    @Override
    public MapBoundaries getlevelBoundaries() {
        return boundaries;
    }


    
}
