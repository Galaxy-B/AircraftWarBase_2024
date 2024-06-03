package edu.hitsz.propFactory;

import edu.hitsz.prop.AbstractProp;

public abstract class PropFactory 
{
    public abstract AbstractProp createProp(int locationX, int locationY, int speedX, int speedY);
}
