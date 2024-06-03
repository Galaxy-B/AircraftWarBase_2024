package edu.hitsz.aircraft;

import edu.hitsz.application.Main;
import edu.hitsz.prop.AbstractProp;
import edu.hitsz.propFactory.PropFactory;
import edu.hitsz.shootStrategy.*;

import java.util.LinkedList;
import java.util.List;

public class BossEnemy extends AbstractAircraft
{
    // 该敌机类型的分数
    private int score = 50;
    
    public BossEnemy(int locationX, int locationY, int speedX, int speedY, int hp, int power)
    {
        super(locationX, locationY, speedX, speedY, hp, 20, power, 1, new CircleShoot());
    }

    @Override
    public void forward()
    {
        super.forward();
        // 判定y轴向下飞行是否出界
        if (locationY >= Main.WINDOW_HEIGHT)
        {
            vanish();
        }
    }

    @Override
    // 坠毁后有概率掉落道具
    public List<AbstractProp> dropProp(List<PropFactory> propFactory)
    {
        List<AbstractProp> res = new LinkedList<>();
        int x = this.getLocationX();
        int y = this.getLocationY();
        int speedX = 0;
        int speedY = 5;
        AbstractProp prop;

        // 执行三次判定 每次均有70%的概率掉落道具
        for (int i = 0; i < 3; i++)
        {
            int randi = (int)(Math.random() * 10);
            if (randi < 7)
            {
                if (randi < 3)          // 治疗道具
                {
                    prop = propFactory.get(0).createProp(x, y, speedX, speedY);
                }
                else if (randi < 5)     // 炸弹道具
                {
                    prop = propFactory.get(1).createProp(x, y, speedX, speedY);
                }
                else if (randi < 8)     // 火力道具
                {
                    prop = propFactory.get(2).createProp(x, y, speedX, speedY);
                }
                else                    // 超级火力道具
                {
                    prop = propFactory.get(3).createProp(x, y, speedX, speedY);
                }
                res.add(prop);
            }
        }
        return res;
    }

    @Override
    public int getScore()
    {
        return score;
    }

    @Override
    public void update()
    {
        // boss敌机不受炸弹影响
        return; 
    }
}
