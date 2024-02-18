package it.unibo.collisions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import it.unibo.model.physics.api.Direction;
import it.unibo.model.physics.api.Position;
import it.unibo.model.collisions.api.Boundaries;
import it.unibo.model.collisions.api.CollisionBox;
import it.unibo.model.collisions.api.EntityCollision;
import it.unibo.model.collisions.api.MapBoundaries;
import it.unibo.model.collisions.impl.BoundariesImpl;
import it.unibo.model.collisions.impl.CollisionBoxImpl;
import it.unibo.model.collisions.impl.EntityCollisionImpl;
import it.unibo.model.entities.api.Character;
import it.unibo.model.entities.api.GameEntity;
import it.unibo.model.entities.impl.SimpleEnemyImpl;
import it.unibo.model.level.api.GameState;
import it.unibo.model.level.api.Level;
import it.unibo.model.physics.impl.Position2D;
import it.unibo.model.collisions.impl.MapBoundariesimpl;

/**
 * Class for testing the Collisions.
 */
class TestCollisions {

  private static final double MAP_DIM = 50;

  @Test
  void testBuoundaries() {
    final double x = 10;
    final double y = 10;
    final double x1 = 16;
    final double y1 = 5;
    final double x2 = 0;
    final double y2 = 3;
    final double y3 = 15;
    final Position p = new Position2D(x, y);
    final Position p1 = new Position2D(x1, y1);
    final Position p2 = new Position2D(x2, y2);
    final List<Position> vertici = new ArrayList<>();
    vertici.add(p);
    vertici.add(new Position2D(x1, y));
    vertici.add(new Position2D(x1, y3));
    vertici.add(new Position2D(x, y3));
    vertici.add(p);

    final double h1 = 5;
    final double w1 = 6;
    final double h2 = 11;
    final double w2 = 4;
    final double h3 = 1;
    final double w3 = 1;
    final Boundaries boundaries = new BoundariesImpl(h1, w1, p);
    final Boundaries boundaries2 = new BoundariesImpl(h2, w2, p1);
    final Boundaries boundaries3 = new BoundariesImpl(h3, w3, p2);
    final double x4 = 16;
    final double y4 = 11;
    final double x5 = 1;
    final double y5 = 1;
    final double x6 = 1;
    final double y6 = 5;
    final double x7 = 11;
    final double y7 = 11;
    assertTrue(boundaries.contains(new Position2D(x4, y4)));
    assertTrue(boundaries2.intersects(boundaries));
    assertTrue(boundaries3.intersectsLine(new Position2D(x5, y5), new Position2D(x6, y6)));
    assertTrue(boundaries.contains(new Position2D(x7, y7)));
    assertEquals(vertici, boundaries.getVertices());
  }

  @Test
  void testCollisionsBox() {
    final Level level = new LevelImpl();
    final GameEntity gameEntity = new SimpleEnemyImpl(new Position2D(1, 3), level);
    final GameEntity otherEntity = new SimpleEnemyImpl(new Position2D(0, 3), level);
    final CollisionBox box = new CollisionBoxImpl(2, 4, gameEntity, level.getBoundaries());
    assertTrue(box.isCollidingWith(otherEntity));
    final double x = 4;
    final double y = 5;
    Set<GameEntity> other = Set.of(new SimpleEnemyImpl(new Position2D(x, y), level), otherEntity);
    assertEquals(
      Set.of(new EntityCollisionImpl(otherEntity, Direction.LEFT)),
        box.getCollisions(other).stream().filter(e -> e instanceof EntityCollision).collect(Collectors.toSet()));
    final double x1 = -1;
    final double y1 = 3;
    final double x2 = 0;
    final double y2 = 3;
    final GameEntity gameEntity1 = new SimpleEnemyImpl(new Position2D(x1, y1), level);
    final GameEntity otherEntity1 = new SimpleEnemyImpl(new Position2D(x2, y2), level);
    final CollisionBox box1 = new CollisionBoxImpl(1, 1, gameEntity1, level.getBoundaries());
    assertTrue(box1.isCollidingWith(otherEntity1));
    final double x3 = 4;
    final double y3 = 5;
    other = Set.of(new SimpleEnemyImpl(new Position2D(x3, y3), level), otherEntity1);
    assertEquals(
        Set.of(new EntityCollisionImpl(otherEntity1, Direction.RIGHT)),
        box1.getCollisions(other).stream().filter(e -> e instanceof EntityCollision).collect(Collectors.toSet()));
  }

  private static final class LevelImpl implements Level {

    private final Set<GameEntity> gameEntities = new HashSet<>();

    @Override
    public Set<GameEntity> getGameEntities() {
      return gameEntities;
    }

    @Override
    public void addGameEntity(final GameEntity entity) {
      gameEntities.add(entity);

    }

    @Override
    public void computeChanges() {
      throw new UnsupportedOperationException("Unimplemented method 'computeChanges'");
    }

    @Override
    public void moveCharacter(final Direction dir) {
      throw new UnsupportedOperationException("Unimplemented method 'moveCharacter'");
    }

    @Override
    public GameState getGameState() {
      throw new UnsupportedOperationException("Unimplemented method 'getGameState'");
    }

    @Override
    public GameEntity getCharacter() {
      throw new UnsupportedOperationException("Unimplemented method 'getCharacter'");
    }

    @Override
    public MapBoundaries getBoundaries() {
      return new MapBoundariesimpl(MAP_DIM, MAP_DIM);
    }

    @Override
    public void setCharacter(final Character character) {
      throw new UnsupportedOperationException("Unimplemented method 'setCharacter'");
    }

    @Override
    public void removeGameEntity(final GameEntity entity) {
      throw new UnsupportedOperationException("Unimplemented method 'removeGameEntity'");
    }
  }
}
