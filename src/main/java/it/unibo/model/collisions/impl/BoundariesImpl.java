package it.unibo.model.collisions.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import it.unibo.model.collisions.api.Boundaries;
import it.unibo.model.physics.api.Position;
import it.unibo.model.physics.impl.Position2D;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Polygon;
import org.locationtech.jts.operation.predicate.RectangleContains;
import org.locationtech.jts.operation.predicate.RectangleIntersects;

/**
 * Implementation of the boundaries of an object.
 */
public class BoundariesImpl implements Boundaries {

    // TODO: bisogna fare in modo che contains. return true se il punto
    // appartiene ad uno dei lati del rettangolo perchè JTS non lo fa
    private double height;
    private double width;
    private final Polygon rectangle;
    private final List<Position> vertices = new ArrayList<>();

    /**
     * @param height   the height of this boundaries
     * @param width    the width of this boundaries
     * @param position the position of this bondaries
     */
    public BoundariesImpl(final double height, final double width, final Position position) {
        this.height = height;
        this.width = width;
        vertices.add(position);
        vertices.add(new Position2D(position.getX() + width, position.getY()));
        vertices.add(new Position2D(position.getX() + width, position.getY() + height));
        vertices.add(new Position2D(position.getX(), position.getY() + height));
        vertices.add(position);
        rectangle = new GeometryFactory().createPolygon(this.vertices.toArray(new Coordinate[vertices.size()]));
    }

    @Override
    public final boolean intersectsLine(final Position a, final Position b) {
        List<Position> line = new ArrayList<>();
        line.add(a);
        line.add(b);
        line.add(a);
        Polygon lineRectangle = new GeometryFactory().createPolygon(line.toArray(new Coordinate[line.size()]));
        return RectangleIntersects.intersects(this.rectangle, lineRectangle);
    }

    @Override
    public final boolean intersects(final Boundaries other) {
        Polygon otherRectangle = new GeometryFactory()
                .createPolygon(other.getVertices().toArray(new Coordinate[vertices.size()]));
        return RectangleIntersects.intersects(this.rectangle, otherRectangle);
    }

    @Override
    public final boolean contains(final Position position) {
        if (checkPerimeter(position) || RectangleContains.contains(rectangle,
                new GeometryFactory().createPoint(new Coordinate(position.getX(), position.getY())))) {
            return true;
        }
        return false;
    }

    @Override
    public final List<Position> getVertices() {
        return Collections.unmodifiableList(this.vertices);
    }

    @Override
    public final double getWidth() {
        return this.width;
    }

    @Override
    public final double getHeight() {
        return this.height;
    }

    /**
     * @param position the point to check if it's in the perimeter
     * @return true if point is in the perimeter.
     */
    private boolean checkPerimeter(final Position position) {
        if (position.getX() >= this.vertices.get(0).getX() && position.getX() <= this.vertices.get(1).getX()
                && (position.getY() == this.vertices.get(0).getY() || position.getY() == this.vertices.get(2).getX())) {
            return true;
        } else if (position.getY() >= this.vertices.get(0).getY() && position.getY() <= this.vertices.get(2).getY()
                && (position.getX() == this.vertices.get(0).getX() || position.getX() == this.vertices.get(1).getX())) {
            return true;
        }
        return false;
    }
}
