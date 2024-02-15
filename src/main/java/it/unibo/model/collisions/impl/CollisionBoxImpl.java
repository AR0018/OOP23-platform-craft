package it.unibo.model.collisions.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;

import it.unibo.model.collisions.api.BorderCollision;
import it.unibo.model.collisions.api.Boundaries;
import it.unibo.model.collisions.api.Collision;
import it.unibo.model.collisions.api.CollisionBox;
import it.unibo.model.collisions.api.MapBoundaries;
import it.unibo.model.entities.api.GameEntity;
import it.unibo.model.physics.api.Direction;
import it.unibo.model.physics.api.Position;
import it.unibo.model.physics.impl.Position2D;


public class CollisionBoxImpl implements CollisionBox{

    private static final List<Direction> direction = List.of(Direction.UP, Direction.RIGHT, Direction.DOWN, Direction.LEFT);
    private GameEntity gameEntity;
    private double width;
    private double height;
    private MapBoundaries mapBoundaries; 

    public CollisionBoxImpl(final double width, final double height, final GameEntity gameEntity, final MapBoundaries mapBoundaries) {
        this.width=width;
        this.height=height;
        this.mapBoundaries = mapBoundaries;
        this.gameEntity = gameEntity;
    }

    @Override
    public Set<Collision> getCollisions(final Set<GameEntity> entities) {
        Set<Collision> collisions = new HashSet<>();
        for (GameEntity entity : entities) {
            if (this.isCollidingWith(entity)) {
                collisions.add(checkEntityCollision(entity));
            }
        }
        collisions.addAll(checkBorderCollisions());
        return collisions;
    }

    @Override
    public boolean isCollidingWith(final GameEntity object) {
        return this.getBoundaries().intersects(object.getBoundaries());
    }

    @Override
    public Boundaries getBoundaries() {
       return new BoundariesImpl(height, width, gameEntity.getPosition());
    }

    private Set<BorderCollision> checkBorderCollisions() {
        List<Position> vertices = new ArrayList<>();
        Set<BorderCollision> borderCollisions= new HashSet<>();
        vertices = mapBoundaries.getVertices();
        for (int vertice = 0 ; vertice < vertices.size() - 1 ; vertice++) {
            if (gameEntity.getBoundaries().intersectsLine(vertices.get(vertice), vertices.get(vertice + 1))) {
                borderCollisions.add(new BorderCollisionImpl(direction.get(vertice))); 
            }
        }
        return borderCollisions;
    }

    private Collision checkEntityCollision(final GameEntity other) {
        List<Position> vertices = new ArrayList<>();
        List<Direction> directions = new ArrayList<>(); 
        vertices = gameEntity.getBoundaries().getVertices();
        for (int vertice = 0; vertice < vertices.size() - 1; vertice++) {
            if(other.getBoundaries().intersectsLine(vertices.get(vertice), vertices.get(vertice + 1))){
                directions.add(direction.get(vertice));
            }
        }
        if ((direction.get(0)==Direction.UP && directions.get(1)==Direction.DOWN)||(direction.get(0)==Direction.RIGHT && directions.get(1)==Direction.LEFT)){
            return new EntityCollisionImpl(other,oppositeEdge(directions,other));
        }
        else {
            return new EntityCollisionImpl(other,adjacentEdge(directions, other));
        }
        
    }

    private Direction oppositeEdge(final List<Direction> directions, final GameEntity other) { 
        if (gameEntity.getPosition().getX()>other.getPosition().getX()) {
            return Direction.LEFT;
        } else {
            return Direction.RIGHT;
        }
    }
    
    private Direction adjacentEdge(final List<Direction> directions, final GameEntity other) { 
        Position pointA = new Position2D(0, 0);
        Position pointB = new Position2D(0, 0);
        for (int vertice = 0; vertice < gameEntity.getBoundaries().getVertices().size() - 1; vertice++) {
            if (other.getBoundaries().contains(gameEntity.getBoundaries().getVertices().get(vertice))) {
                pointA = gameEntity.getBoundaries().getVertices().get(vertice);
            }
            if (gameEntity.getBoundaries().contains(other.getBoundaries().getVertices().get(vertice))) {
                pointB = other.getBoundaries().getVertices().get(vertice);
            }
        }
        if (Math.abs(pointA.getX() - pointB.getX()) > Math.abs(pointA.getY() - pointB.getY())) {
            if (directions.contains(Direction.UP)) {
                return Direction.UP;
            } else {
                return Direction.DOWN;
            }
        } else {
            if (directions.contains(Direction.RIGHT)) {
                return Direction.RIGHT;
            } else {
                return Direction.LEFT;
            }
        }
    }  
}

