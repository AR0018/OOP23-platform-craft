 package it.unibo.common;

import org.locationtech.jts.geom.CoordinateXY;

public interface SimpleEntiete {

    EntityType getType();

    CoordinateXY getPosition();

}