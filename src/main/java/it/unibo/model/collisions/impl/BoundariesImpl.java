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

/**
<<<<<<< HEAD
 * Implements the interface Boundaries,
 * 
 */
public class BoundariesImpl implements Boundaries {

=======
 * Implementation of the boundaries of an object.
 */
public class BoundariesImpl implements Boundaries {

    //TODO: bisogna fare in modo che contains. return true se il punto
    //appartiene ad uno dei lati del rettangolo perchè JTS non lo fa
>>>>>>> ca4618b0015d9037dd967bd31962e414ae752ebf
    private double height;
    private double width;
    private final Polygon rectangle;
    private final List<Position> vertices = new ArrayList<>();

    /**
<<<<<<< HEAD
     * Constructor of this class.
     * @param height of the boundaries
     * @param width of the boundaries
     * @param position of the entity
=======
     * @param height the height of this boundaries
     * @param width the width of this boundaries
     * @param position the position of this bondaries
>>>>>>> ca4618b0015d9037dd967bd31962e414ae752ebf
     */
    public BoundariesImpl(final double height, final double width, final Position position) {
        this.height = height;
        this.width = width;
        vertices.add(position);
<<<<<<< HEAD
        vertices.add(new Position2D(position.getX() + width,position.getY()));
        vertices.add(new Position2D(position.getX() + width,position.getY() + height));
        vertices.add(new Position2D(position.getX(),position.getY() + height));
=======
        vertices.add(new Position2D(position.getX() + width, position.getY()));
        vertices.add(new Position2D(position.getX() + width, position.getY() + height));
        vertices.add(new Position2D(position.getX(), position.getY() + height));
>>>>>>> ca4618b0015d9037dd967bd31962e414ae752ebf
        vertices.add(position);
        rectangle = new GeometryFactory().createPolygon(this.vertices.toArray(new Coordinate[vertices.size()]));
    }

    @Override
<<<<<<< HEAD
    public boolean intersectsLine(Position a, Position b) {
=======
    public final boolean intersectsLine(final Position a, final Position b) {
>>>>>>> ca4618b0015d9037dd967bd31962e414ae752ebf
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
        return RectangleContains.contains(rectangle,
            new GeometryFactory().createPoint(new Coordinate(position.getX(), position.getY())));
    }

    @Override
    public final List<Position> getVertices() {
       return this.vertices;
    }

    @Override
    public final double getWidth() {
        return this.width;
    }

    @Override
    public final double getHeight() {
       return this.height;
    }
}
