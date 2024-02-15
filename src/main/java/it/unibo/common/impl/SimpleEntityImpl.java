package it.unibo.common.impl;

<<<<<<< HEAD
import it.unibo.common.api.EntityType;
import it.unibo.common.api.SimpleEntity;

public class SimpleEntityImpl implements SimpleEntity{

    private EntityType type;
    private double x;
    private double y;

    public SimpleEntityImpl(){
        this.type=null;
        this.x=0;
        this.y=0;
    }

    @Override
    public EntityType getType(EntityType e) {
      return type;
    }

    @Override
    public double getX(double x) {
       return x;
    }

    @Override
    public double getY(double y) {
        return y;
    }
    
=======
import com.fasterxml.jackson.annotation.JsonProperty;

import it.unibo.common.EntityType;
import it.unibo.common.SimpleEntity;

/**
 * Serializable implementation of a SimpleEntity.
 */
public final class SimpleEntityImpl implements SimpleEntity {

    private final EntityType type;
    private final double x;
    private final double y;

    /**
     * @param type the type of this entity
     * @param x the position of this entity on the x axis (inside the Level)
     * @param y the position of this entity on the y axis (inside the Level)
     */
    public SimpleEntityImpl(
        @JsonProperty("Type") final EntityType type,
        @JsonProperty("X") final double x,
        @JsonProperty("Y") final double y) {
        this.type = type;
        this.x = x;
        this.y = y;
    }

    @Override
    public EntityType getType() {
        return this.type;
    }

    @Override
    public double getX() {
        return this.x;
    }

    @Override
    public double getY() {
        return this.y;
    }

>>>>>>> master
}
