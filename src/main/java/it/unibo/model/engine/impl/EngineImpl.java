package it.unibo.model.engine.impl;

import java.util.HashSet;
import java.util.Set;

import it.unibo.common.api.SimpleEntity;
import it.unibo.model.engine.api.Engine;
import it.unibo.model.entities.api.GameEntity;
import it.unibo.model.entities.impl.CharacterImpl;
import it.unibo.model.level.impl.GameLevel;
import it.unibo.model.physics.api.Direction;

public class EngineImpl implements Engine{

    private EditorImpl editor;
    private GameLevel level;

    
    public EngineImpl(){
        this.characterEditor = new CharacterImpl(null);
        this.editor = new EditorImpl();

    }
    

    @Override
     public void updateLevel() {
        if(editor.createLevel()){
           editor.getGameState();     
        }else{
            throw new UnsupportedOperationException("Invalid Level");
        }
     }

    @Override
     public void moveCharacter(Direction dir) {
       this.level.moveCharacter(dir);
    }

    @Override
    public Set<SimpleEntity> getLevelEntities() {
        return editor.getLevelEntities();
        
    }

    
}
