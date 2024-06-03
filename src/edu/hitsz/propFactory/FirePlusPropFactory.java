package edu.hitsz.propFactory;

import edu.hitsz.prop.AbstractProp;
import edu.hitsz.prop.FirePlusProp;

public class FirePlusPropFactory extends PropFactory
{
    @Override
    public AbstractProp createProp(int locationX, int locationY, int speedX, int speedY)
    {
        return new FirePlusProp(locationX, locationY, speedX, speedY);
    }
}
