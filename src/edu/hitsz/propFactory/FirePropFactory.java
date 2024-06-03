package edu.hitsz.propFactory;

import edu.hitsz.prop.AbstractProp;
import edu.hitsz.prop.FireProp;

public class FirePropFactory extends PropFactory
{
    @Override
    public AbstractProp createProp(int locationX, int locationY, int speedX, int speedY)
    {
        return new FireProp(locationX, locationY, speedX, speedY);
    }
}
