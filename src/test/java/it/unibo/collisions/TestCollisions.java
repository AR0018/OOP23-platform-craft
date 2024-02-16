package it.unibo.collisions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import it.unibo.model.physics.api.Direction;
import it.unibo.model.physics.api.Position;
import it.unibo.common.EntityType;
import it.unibo.model.collisions.api.Boundaries;
import it.unibo.model.collisions.api.Collision;
import it.unibo.model.collisions.api.CollisionBox;
import it.unibo.model.collisions.api.MapBoundaries;
import it.unibo.model.collisions.impl.BorderCollisionImpl;
import it.unibo.model.collisions.impl.BoundariesImpl;
import it.unibo.model.collisions.impl.CollisionBoxImpl;
import it.unibo.model.collisions.impl.EntityCollisionImpl;
import it.unibo.model.entities.api.Character;
import it.unibo.model.entities.api.GameEntity;
import it.unibo.model.entities.impl.GameEntityFactoryImpl;
import it.unibo.model.entities.impl.SimpleEnemyImpl;
import it.unibo.model.level.api.GameState;
import it.unibo.model.level.api.Level;
import it.unibo.model.physics.impl.Position2D;
import it.unibo.model.collisions.impl.MapBoundariesimpl;
import it.unibo.model.collisions.api.MapBoundaries;

/**
 * Class for testing the Collisions.
 */
public class TestCollisions {

  @Test
  public void testBuoundaries() {
    Position p = new Position2D(10, 10);
    Position p1 = new Position2D(16, 5);
    Position p2 = new Position2D(0, 3);
    List<Position> vertici1 = new ArrayList<>();
    vertici1.add(p2);
    vertici1.add(new Position2D(1, 3));
    vertici1.add(new Position2D(1, 4));
    vertici1.add(new Position2D(0, 4));
    vertici1.add(p2);
    List<Position> vertici = new ArrayList<>();
    vertici.add(p);
    vertici.add(new Position2D(16, 10));
    vertici.add(new Position2D(16, 15));
    vertici.add(new Position2D(10, 15));
    vertici.add(p);

    Boundaries boundaries = new BoundariesImpl(5, 6, p);
    Boundaries boundaries2 = new BoundariesImpl(11, 4, p1);
    Boundaries boundaries3 = new BoundariesImpl(1, 1, p2);
    assertEquals(true, boundaries.contains(new Position2D(16, 11))); 
    assertEquals(true, boundaries2.intersects(boundaries));
    assertEquals(true, boundaries3.intersectsLine(new Position2D(1, 1), new Position2D(1, 5)));
    assertEquals(true, boundaries.contains(new Position2D(11, 11)));
    assertEquals(vertici, boundaries.getVertices());
  }

  @Test
  public void testCollisionsBox() {
    Level level = new LevelImpl();
    GameEntity gameEntity = new SimpleEnemyImpl(new Position2D(1, 3), level);
    GameEntity otherEntity = new SimpleEnemyImpl(new Position2D(0, 3), level);
    CollisionBox box = new CollisionBoxImpl(2, 4, gameEntity, level.getBoundaries());
    assertEquals(true, box.isCollidingWith(otherEntity));
    Set<GameEntity> other = Set.of(new SimpleEnemyImpl(new Position2D(4, 5), level), otherEntity);
    assertEquals(Set.of(new EntityCollisionImpl(otherEntity, Direction.LEFT)), box.getCollisions(other));

    GameEntity gameEntity1 = new SimpleEnemyImpl(new Position2D(-1, 3), level);
    GameEntity otherEntity1 = new SimpleEnemyImpl(new Position2D(0, 3), level);
    CollisionBox box1 = new CollisionBoxImpl(1, 1, gameEntity1, level.getBoundaries());
    assertEquals(true, box1.isCollidingWith(otherEntity1));
    other = Set.of(new SimpleEnemyImpl(new Position2D(4, 5), level), otherEntity1);
    assertEquals(
        Set.of(new EntityCollisionImpl(otherEntity1, Direction.RIGHT), new BorderCollisionImpl(Direction.LEFT)),
        box1.getCollisions(other));
  }

  private static class LevelImpl implements Level {

    Set<GameEntity> gameEntities = new HashSet<>();

    @Override
    public Set<GameEntity> getGameEntities() {
      return gameEntities;
    }

    @Override
    public void addGameEntity(GameEntity entity) {
      gameEntities.add(entity);

    }

    @Override
    public void computeChanges() {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'computeChanges'");
    }

    @Override
    public void moveCharacter(Direction dir) {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'moveCharacter'");
    }

    @Override
    public GameState getGameState() {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'getGameState'");
    }

    @Override
    public GameEntity getCharacter() {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'getCharacter'");
    }

    @Override
    public MapBoundaries getBoundaries() {
      return new MapBoundariesimpl(50, 50);
    }

    @Override
    public void setCharacter(Character character) {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'setCharacter'");
    }

    @Override
    public void removeGameEntity(GameEntity entity) {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'removeGameEntity'");
    }
  }
}
