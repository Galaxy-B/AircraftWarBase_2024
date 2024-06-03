package edu.hitsz.shootStrategy;

import edu.hitsz.bullet.*;
import edu.hitsz.aircraft.*;

import java.util.List;
import java.util.LinkedList;

public class DirectShoot implements ShootStrategy
{
    @Override
    public List<BaseBullet> shoot(AbstractAircraft aircraft)
    {
        List<BaseBullet> res = new LinkedList<>();
        int x = aircraft.getLocationX();
        int y = aircraft.getLocationY() + aircraft.getDirection() * 2;
        int speedX = 0;
        int speedY = aircraft.getSpeedY() + aircraft.getDirection() * 5;
        int power = aircraft.getPower();
        int shootNum = aircraft.getShootNum();
        
        BaseBullet bullet;
        if (aircraft instanceof HeroAircraft)   // 英雄机
        {
            for(int i = 0; i < shootNum; i++)
            {
                // 子弹发射位置相对飞机位置向前偏移
                // 多个子弹横向分散
                bullet = new HeroBullet(x + (i * 2 - shootNum + 1) * 10, y, speedX, speedY, power);
                res.add(bullet);
            }
        }
        else                                    // 敌机
        {
            for(int i = 0; i < shootNum; i++)
            {
                // 子弹发射位置相对飞机位置向前偏移
                // 多个子弹横向分散
                bullet = new EnemyBullet(x + (i * 2 - shootNum + 1) * 10, y, speedX, speedY, power);
                res.add(bullet);
            }
        }
        
        return res;
    }
}
