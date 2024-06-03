package edu.hitsz.enemyFactory;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.aircraft.ElitePlusEnemy;
import edu.hitsz.application.Main;
import edu.hitsz.application.ImageManager;

public class ElitePlusEnemyFactory extends EnemyFactory
{
    @Override
    public AbstractAircraft createAircraft(int speedX, int speedY, int hp, int power)
    {
        return new ElitePlusEnemy(
            (int) (Math.random() * (Main.WINDOW_WIDTH - ImageManager.ELITE_PLUS_ENEMY_IMAGE.getWidth())),
            (int) (Math.random() * Main.WINDOW_HEIGHT * 0.05),
            (int) (Math.random() * 10 - 5),
            speedY,
            hp,
            power
        );
    } 
}
