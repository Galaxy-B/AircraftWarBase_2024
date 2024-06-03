package edu.hitsz.prop;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.basic.AbstractFlyingObject;

/**
 * 所有种类道具的抽象父类：
 * 治疗道具 火力道具 炸弹道具
 *
 * @author 220111004
 */
public abstract class AbstractProp extends AbstractFlyingObject
{

    public AbstractProp(int locationX, int locationY, int speedX, int speedY)
    {
        super(locationX, locationY, speedX, speedY);
    }

    // 道具生效的接口
    public abstract void takeEffect(AbstractAircraft aircraft);
}
