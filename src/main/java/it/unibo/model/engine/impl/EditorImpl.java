package it.unibo.model.engine.impl;
// import it.unibo.model.entities.api.FinishLocation;
import it.unibo.model.entities.api.GameEntity;
// import it.unibo.model.entities.api.MapElement;
import it.unibo.model.entities.api.Character;
// import it.unibo.model.entities.api.StartLocation;
// import it.unibo.model.entities.impl.CharacterImpl;
import it.unibo.model.level.api.GameState;
import it.unibo.model.level.impl.GameLevel;
import it.unibo.model.physics.api.Direction;
import it.unibo.common.api.EntityType;
import it.unibo.common.api.SimpleEntity;
import it.unibo.common.impl.SimpleEntityImpl;
import it.unibo.model.engine.api.Editor;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;



public class EditorImpl implements Editor {

    private Set<GameEntity> gameConfiguration;
    private Set<SimpleEntity> gameSimpleEntity;

    private GameLevel level;
    

    private boolean hasCharacter = false;
    private boolean hasStartLocation = false;
    private boolean hasFinishLocation = false;

    private GameEntity characterEngine;
    private SimpleEntity simpleEntity;
    // private SimpleEntity startLocation;
    // private SimpleEntity mapSimpleEntity;
    // private SimpleEntity trapSimpleEntity;

    private GameState stateGame = GameState.STATE_UNKNOW;

    public EditorImpl() {
        this.gameConfiguration = new HashSet<>();
        this.gameSimpleEntity = new HashSet<>();
        this.characterEngine = null;
        this.simpleEntity = new SimpleEntityImpl();
        this.level = new GameLevel();
       

    }

    @Override
    public boolean createLevel() {
        // Return true if all required entities are present
        return this.hasCharacter && this.hasStartLocation && this.hasFinishLocation;
    }

    @Override
    public boolean addGameEntity(GameEntity entity) {

        if(entity!=null){
            if(entity.getType()==EntityType.FINISH_LOCATION){
                this.hasFinishLocation=true;
                this.gameConfiguration.add(entity);
            }else if(entity.getType()==EntityType.START_LOCATION){
                this.hasStartLocation=true;
                this.gameConfiguration.add(entity);
            }else if(entity.getType()==EntityType.CHARACTER){
                this.hasCharacter=true;
                this.gameConfiguration.add(entity);
                this.characterEngine=entity;
            }
            gameConfiguration.add(entity);
        }
        if(gameConfiguration.contains(entity)){
            return true;
        }else{
            return false;
        }    
    }

    @Override
    public boolean removeGameEntity(double x, double y) {   
    
        /**
        *  Check if there is a game entity at the specified coordinates (x, y)
        *  and remove the entity found.
        *  */
        Optional<GameEntity> entityToRemove = gameConfiguration.stream()
                .filter(entity -> entity.getPosition().getX() == x && entity.getPosition().getY() == y)
                .findFirst();

        if (entityToRemove.isPresent()) {
            GameEntity removedEntity = entityToRemove.get();
            gameConfiguration.remove(removedEntity);
            return true;
        } else {
            return false;
        }
    }


     @Override
     public GameState getGameState() {
      return this.level.getGameState();
    }

    public Set<GameEntity> getLevelEntity() {
        return this.gameConfiguration;
    }

    private SimpleEntity converToSimpleEntity(GameEntity entity){
        if(entity!=null){
           this.simpleEntity.getX(entity.getPosition().getX());
           this.simpleEntity.getY(entity.getPosition().getY());
           this.simpleEntity.getType(entity.getType());
        }
        return simpleEntity;
    }

    @Override
    public Set<SimpleEntity> getLevelEntities() {
        
        for (GameEntity gameEntity : gameConfiguration) {
            simpleEntity = converToSimpleEntity(gameEntity);
            if (simpleEntity != null) {
                gameSimpleEntity.add(simpleEntity);
            }
        }
        return gameSimpleEntity;
    }

    private void resetAll(){
        this.gameConfiguration.removeAll(gameConfiguration);
        this.gameSimpleEntity.removeAll(gameSimpleEntity);
    }

    
    @Override
    public void clearAll() {  
       resetAll();
    }

}
