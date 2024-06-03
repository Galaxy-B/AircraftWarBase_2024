package edu.hitsz.enemyFactory.enemyCreator;

import java.util.List;

import edu.hitsz.aircraft.AbstractAircraft;

import edu.hitsz.enemyFactory.EnemyFactory;

public class EasyEnemyCreator extends EnemyCreator 
{
    // speedX=-1表示横向速度是随机的
    // 简单模式下不生成boss 且难度不递增
    public AbstractAircraft createEnemy(List<EnemyFactory> enemyFactory, int time, int score)
    {   
        // 时间达到阈值时生成一架精英plus敌机
        if (time > 0 && time % threshold == 0)
        {
            return enemyFactory.get(2).createAircraft(-1, 9, 90, 15);
        }

        // 否则随机生成一架普通敌机或精英敌机 
        int randi = (int)(Math.random() * 10);
        
        if (randi < 7)      // 70%的概率生成普通敌机
        {
            return enemyFactory.get(0).createAircraft(0, 5, 30, 0);
        }
        else                // 30%的概率生成精英敌机
        {
            return enemyFactory.get(1).createAircraft(-1, 7, 60, 10);
        }
    }
}
