package edu.hitsz.propFactory;

import edu.hitsz.prop.AbstractProp;
import edu.hitsz.prop.HealProp;

public class HealPropFactory extends PropFactory
{
    @Override
    public AbstractProp createProp(int locationX, int locationY, int speedX, int speedY)
    {
        return new HealProp(locationX, locationY, speedX, speedY);
    }
}
