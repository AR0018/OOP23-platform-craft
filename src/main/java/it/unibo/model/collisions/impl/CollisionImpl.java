package it.unibo.model.collisions.impl;

import it.unibo.model.collisions.api.Collision;

import java.util.ArrayList;
import java.util.List;

import it.unibo.model.collisions.api.Boundaries;
import it.unibo.model.entities.api.GameEntity;
import it.unibo.model.physics.api.Direction;
import it.unibo.model.physics.api.Position;

public class CollisionImpl implements Collision {

    private GameEntity gameEntity;
    private GameEntity gameEntity2;

    public CollisionImpl(final GameEntity gameEntity, final GameEntity gameEntity2){
        this.gameEntity=gameEntity;
        this.gameEntity2=gameEntity2;
    }

    @Override
    public GameEntity getGameEntity() {
       return this.gameEntity2;
    }
    
    @Override
    public Direction getDirection() {
       
      return this.CalcDirection();
    }

    private Direction CalcDirection(){
        if (gameEntity.getPosition().getX()==gameEntity2.getPosition().getX()){
            if(gameEntity.getPosition().getY()>gameEntity2.getPosition().getY()){
               return Direction.DOWN;
            }
            else{
               return Direction.UP;
            }
        }
        else if(gameEntity2.getPosition().getX()>gameEntity.getPosition().getX()){
            if(gameEntity.getPosition().getY()>gameEntity2.getPosition().getY()){
               return fourthQuadrant();
            }
            else{
               return secondQuadrant();
            }
        }
        else{
            if(gameEntity.getPosition().getY()>gameEntity2.getPosition().getY()){
               return thirdQuadrant();
            }
            else{
               return firstQuadrant();
            }
        }
    }

    private Direction secondQuadrant(){
      if(gameEntity2.getPosition().getX()-gameEntity.getPosition().getX()>gameEntity2.getPosition().getY()-gameEntity.getPosition().getY()){
         return Direction.RIGHT;
      }
      else{
         return Direction.UP;
      }
    }

    private Direction firstQuadrant(){
      if(gameEntity.getPosition().getX()-gameEntity2.getPosition().getX()>gameEntity2.getPosition().getY()-gameEntity.getPosition().getY()){
         return Direction.LEFT;
      }
      else{
         return Direction.UP;
      }
   }

    private Direction thirdQuadrant(){
         if(gameEntity.getPosition().getX()-gameEntity2.getPosition().getX()>gameEntity.getPosition().getY()-gameEntity2.getPosition().getY()){
            return Direction.LEFT;
         }
         else{
            return Direction.DOWN;
         }
   }
    
   private Direction fourthQuadrant(){
      if(gameEntity2.getPosition().getX()-gameEntity.getPosition().getX()>gameEntity.getPosition().getY()-gameEntity2.getPosition().getY()){
         return Direction.RIGHT;
      }
      else{
         return Direction.DOWN;
      }
    }
}
