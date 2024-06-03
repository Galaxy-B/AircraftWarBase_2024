package edu.hitsz.enemyFactory;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.aircraft.BossEnemy;
import edu.hitsz.application.Main;
import edu.hitsz.application.ImageManager;

public class BossEnemyFactory extends EnemyFactory
{
    @Override
    public AbstractAircraft createAircraft(int speedX, int speedY, int hp, int power)
    {
        // boss机必须具有横向速度
        int boss_speedX = 0;
        while (boss_speedX == 0)
        {
            boss_speedX = (int)(Math.random() * 10 - 5);
        }
        
        return new BossEnemy(
            (int) (Math.random() * (Main.WINDOW_WIDTH - ImageManager.BOSS_ENEMY_IMAGE.getWidth())),
            (int) (Math.random() * Main.WINDOW_HEIGHT * 0.05),
            boss_speedX,
            speedY,
            hp,
            power
        );
    } 
}
