package it.unibo.model.collisions.impl;

import java.util.List;

import it.unibo.model.collisions.api.Boundaries;
import it.unibo.model.physics.api.Position;

<<<<<<< HEAD
public class BoundariesImpl implements Boundaries{

    @Override
    public boolean intersectsLine(Position a, Position b) {
=======
/**
 * 
 */
public final class BoundariesImpl implements Boundaries {

    @Override
    public boolean intersectsLine(final Position a, final Position b) {
>>>>>>> master
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'intersectsLine'");
    }

    @Override
<<<<<<< HEAD
    public boolean intersects(Boundaries other) {
=======
    public boolean intersects(final Boundaries other) {
>>>>>>> master
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'intersects'");
    }

    @Override
<<<<<<< HEAD
    public boolean contains(Position position) {
=======
    public boolean contains(final Position position) {
>>>>>>> master
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'contains'");
    }

    @Override
    public List<Position> getVertices() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getVertices'");
    }

    @Override
<<<<<<< HEAD
    public int getWidth() {
=======
    public double getWidth() {
>>>>>>> master
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getWidth'");
    }

    @Override
<<<<<<< HEAD
    public int getHeight() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getHeight'");
    }

    @Override
    public Position getLevelBoundaries() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getLevelBoundaries'");
    }
    
=======
    public double getHeight() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getHeight'");
    }
>>>>>>> master
}
