package it.unibo.collisions;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import it.unibo.model.physics.api.Direction;
import it.unibo.model.physics.api.Position;
import it.unibo.model.collisions.api.Boundaries;
import it.unibo.model.collisions.api.Collision;
import it.unibo.model.collisions.impl.BoundariesImpl;
import it.unibo.model.collisions.impl.CollisionImpl;
import it.unibo.model.entities.api.GameEntity;
import it.unibo.model.entities.impl.SimpleEnemyImpl;
import it.unibo.model.physics.impl.Position2D;


 /**
  * Class for testing the Collisions.
  */
public class TestCollisions {

  @Test
  public void testBuoundaries(){
    Position p=new Position2D(10, 10);
    Position p1=new Position2D(16, 5);
    List<Position> vertici=new ArrayList<>();
    vertici.add(p);
    vertici.add(new Position2D(16,10));
    vertici.add(new Position2D(16,5));
    vertici.add(new Position2D(10,5));
    vertici.add(p);

    Boundaries boundaries=new BoundariesImpl(5,6,p);
    Boundaries boundaries2 =new BoundariesImpl(2, 2,p1);
    assertEquals(true, boundaries.contains(new Position2D(13, 8)));
    assertEquals(true, boundaries2.intersects(boundaries));
    assertEquals(false, boundaries.intersectsLine(new Position2D(17, 10), new Position2D(19, 6)));
    assertEquals(true, boundaries.contains(new Position2D(11, 9)));
    assertEquals(vertici, boundaries.getVertices());
  }

  @Test
  public void TestCollisions(){
    GameEntity gameEntity=new SimpleEnemyImpl(new Position2D(5, 5), null);
    GameEntity gameEntity2=new SimpleEnemyImpl(new Position2D(5, 7), null);
    Collision collision =new CollisionImpl(gameEntity, gameEntity2);
    assertEquals(Direction.UP, collision.getDirection());
  }



}
