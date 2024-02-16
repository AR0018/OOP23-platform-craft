package it.unibo.model.collisions.impl;

import java.util.ArrayList;
import java.util.List;

import it.unibo.model.collisions.api.Boundaries;
import it.unibo.model.physics.api.Position;
import it.unibo.model.physics.impl.Position2D;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Polygon;
import org.locationtech.jts.operation.predicate.RectangleContains;
import org.locationtech.jts.operation.predicate.RectangleIntersects;

public class BoundariesImpl implements Boundaries {

    private double height;
    private double width;
    private final Polygon rectangle;
    private final List<Position> vertices = new ArrayList<>();

    public BoundariesImpl(final double height, final double width, final Position position) {
        this.height = height;
        this.width = width;
        vertices.add(position);
        vertices.add(new Position2D(position.getX() + width,position.getY()));
        vertices.add(new Position2D(position.getX() + width,position.getY() + height));
        vertices.add(new Position2D(position.getX(),position.getY() + height));
        vertices.add(position);
        rectangle = new GeometryFactory().createPolygon(this.vertices.toArray(new Coordinate[vertices.size()]));
    }

    @Override
    public boolean intersectsLine(Position a, Position b) {
        List<Position> line = new ArrayList<>();
        line.add(a);
        line.add(b);
        line.add(a);
        Polygon lineRectangle = new GeometryFactory().createPolygon(line.toArray(new Coordinate[line.size()]));
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
