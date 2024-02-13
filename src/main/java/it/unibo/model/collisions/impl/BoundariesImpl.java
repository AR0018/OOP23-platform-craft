package it.unibo.model.collisions.impl;

import java.util.ArrayList;
import java.util.List;

import it.unibo.model.collisions.api.Boundaries;
import it.unibo.model.entities.api.GameEntity;
import it.unibo.model.physics.api.Position;
import it.unibo.model.physics.impl.Position2D;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Polygon;
import org.locationtech.jts.operation.predicate.RectangleContains;
import org.locationtech.jts.operation.predicate.RectangleIntersects;

public class BoundariesImpl implements Boundaries{

    private double height;
    private double width;
    private GameEntity gameEntity;
    private final Polygon rectangle;
    private final List<Position> vertices=new ArrayList<>();

    public BoundariesImpl(final double height,final double width, GameEntity gameEntity){
        this.height=height;
        this.width=width;
        this.gameEntity=gameEntity;
        vertices.add(gameEntity.getPosition());
        vertices.add(new Position2D(gameEntity.getPosition().getX()+width,gameEntity.getPosition().getY()));
        vertices.add(new Position2D(gameEntity.getPosition().getX()+width, gameEntity.getPosition().getY()-height));
        vertices.add(new Position2D(gameEntity.getPosition().getX(), gameEntity.getPosition().getY()-height));
        vertices.add(gameEntity.getPosition());
        rectangle=new GeometryFactory().createPolygon(this.vertices.toArray(new Coordinate[vertices.size()]));
    }

    @Override
    public boolean intersectsLine(Position a, Position b) {
        List<Position> line= new ArrayList<>();
        line.add(a);
        line.add(b);
        Polygon lineRectangle= new GeometryFactory().createPolygon(line.toArray(new Coordinate[line.size()]));
        return RectangleIntersects.intersects(this.rectangle, lineRectangle);
    }

    @Override
    public boolean intersects(Boundaries other) {
        Polygon otherRectangle = new GeometryFactory().createPolygon(other.getVertices().toArray(new Coordinate[vertices.size()]));
        return RectangleIntersects.intersects(this.rectangle, otherRectangle);
    }

    @Override
    public boolean contains(Position position) {
        return RectangleContains.contains(rectangle, new GeometryFactory().createPoint(new Coordinate(position.getX(), position.getY())));
    }

    @Override
    public List<Position> getVertices() {
       return this.vertices;
    }

    @Override
    public double getWidth() {
        return this.width;
    }

    @Override
    public double getHeight() {
       return this.height;
    }

}
