package it.unibo.model.engine.impl;

import it.unibo.model.engine.api.Editor;
import it.unibo.model.entities.impl.CharacterImpl;
import it.unibo.model.physics.api.Direction;

public class GameEditor implements Editor{

    private CharacterImpl characterEditor;

    @Override
     public void updateLevel() {
        
     // TODO Auto-generated method stub
     throw new UnsupportedOperationException("Unimplemented method'updateLevel'");
     }

    @Override
     public void moveCharacter(Direction dir) {
        if(dir!=null){
            this.characterEditor.move(dir);
        }else{
            throw new UnsupportedOperationException("Invalid direction");
        }
    }

    
}
