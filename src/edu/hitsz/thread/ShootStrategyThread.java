package edu.hitsz.thread;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.shootStrategy.DirectShoot;
import edu.hitsz.shootStrategy.ScatteredShoot;
import edu.hitsz.shootStrategy.ShootStrategy;

public class ShootStrategyThread extends Thread
{
    // 英雄机
    private AbstractAircraft heroAircraft;
    // 要切换为的射击策略
    private ShootStrategy shootStrategy;
    // 是否中断该线程
    private boolean stopThread = false;

    public ShootStrategyThread(AbstractAircraft heroAircraft, ShootStrategy shootStrategy)
    {
        this.heroAircraft = heroAircraft;
        this.shootStrategy = shootStrategy;
    }
    
    @Override
    public void run()
    {
        // 更改射击策略
        if (shootStrategy instanceof ScatteredShoot)
        {
            heroAircraft.setShootNum(3);
        }
        else
        {
            heroAircraft.setShootNum(20);
        }
        heroAircraft.setShootStrategy(shootStrategy);
        // 将该线程挂起 每100ms检查一次是否被中断
        for (int i = 1; i <= 100; i++)
        {
            try
            {
                Thread.sleep(100);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            // 如果线程被中断 一定是其他火力道具导致的 不还原射击策略
            if (stopThread)
                return;
        }
        heroAircraft.setShootNum(1);
        heroAircraft.setShootStrategy(new DirectShoot());
    }

    public void stopThread()
    {
        this.stopThread = true;
    }
}
