package edu.hitsz.enemyFactory;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.aircraft.MobEnemy;
import edu.hitsz.application.Main;
import edu.hitsz.application.ImageManager;

public class MobEnemyFactory extends EnemyFactory
{
    @Override
    public AbstractAircraft createAircraft(int speedX, int speedY, int hp, int power)
    {
        return new MobEnemy(
            (int) (Math.random() * (Main.WINDOW_WIDTH - ImageManager.MOB_ENEMY_IMAGE.getWidth())),
            (int) (Math.random() * Main.WINDOW_HEIGHT * 0.05),
            speedX,
            speedY,
            hp,
            power
        );
    }  
}
