package edu.hitsz.enemyFactory;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.aircraft.EliteEnemy;
import edu.hitsz.application.Main;
import edu.hitsz.application.ImageManager;

public class EliteEnemyFactory extends EnemyFactory
{
    @Override
    public AbstractAircraft createAircraft(int speedX, int speedY, int hp, int power)
    {
        return new EliteEnemy(
            (int) (Math.random() * (Main.WINDOW_WIDTH - ImageManager.ELITE_ENEMY_IMAGE.getWidth())),
            (int) (Math.random() * Main.WINDOW_HEIGHT * 0.05),
            (int) (Math.random() * 10 - 5),
            speedY,
            hp,
            power
        );
    } 
}
