package edu.hitsz.shootStrategy;

import edu.hitsz.bullet.*;
import edu.hitsz.aircraft.*;

import java.util.List;
import java.util.LinkedList;

public class CircleShoot implements ShootStrategy
{
    @Override
    public List<BaseBullet> shoot(AbstractAircraft aircraft)
    {
        List<BaseBullet> res = new LinkedList<>();
        int x = aircraft.getLocationX();
        int y = aircraft.getLocationY() + aircraft.getDirection() * 2;
        int power = aircraft.getPower();
        int shootNum = aircraft.getShootNum();
        final double pi = 3.1415926;
        
        BaseBullet bullet;
        if (aircraft instanceof HeroAircraft)   // 英雄机
        {
            for (int i = 0; i < shootNum; i++)
            {
                bullet = new HeroBullet(x, y,
                                        (int)(Math.cos(i * pi * 2 / shootNum) * 10),
                                        (int)(Math.sin(i * pi * 2 / shootNum) * 10),
                                        power);
                res.add(bullet);
            }
        }
        else                                    // 敌机
        {
            for (int i = 0; i < shootNum; i++)
            {
                bullet = new EnemyBullet(x, y,
                                        (int)(Math.cos(i * pi * 2 / shootNum) * 10),
                                        (int)(Math.sin(i * pi * 2 / shootNum) * 10),
                                        power);
                res.add(bullet);
            }
        }
        
        return res;
    }
}
