package it.unibo.common.impl;

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
    
}
