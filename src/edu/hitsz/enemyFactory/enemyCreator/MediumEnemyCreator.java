package edu.hitsz.enemyFactory.enemyCreator;

import java.util.List;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.application.Main;
import edu.hitsz.application.games.Game;
import edu.hitsz.enemyFactory.EnemyFactory;
import edu.hitsz.swing.StartMenu;
import edu.hitsz.thread.BackgroundMusicThread;

public class MediumEnemyCreator extends EnemyCreator
{
    // speedX=-1表示横向速度是随机的
    // 中等模式生成boss 且难度随boss轮次递增
    // 1. 所有类型敌机血量提升
    // 2. 精英plus敌机刷新频率提升
    // 3. 精英敌机较简单模式刷新概率更高
    // 4. 精英敌机刷新概率随时间提升
    public AbstractAircraft createEnemy(List<EnemyFactory> enemyFactory, int time, int score)
    {
        // 时间达到6000的倍数时 精英敌机的刷新率提高2%
        if (time > 0 && time % 6000 == 0 && probability_increase < 0.22)
        {
            probability_increase += 0.02;
            System.out.println("generating probability of elite enemy has increased 2% !");
        }
        
        // 分数超过500的倍数时生成一架boss敌机
        if (score / 500 > round)
        {
            // 切换为boss敌机背景音效
            if (Main.startMenu.getMusicSetting() == StartMenu.MusicOn)
            {
                // 打断主背景音效 并启动boss敌机背景音效
                Game.main_bgm.stopMusic();
                Game.boss_bgm = new BackgroundMusicThread(Main.boss_bgm_path);
                Game.boss_bgm.start();
            }
            // 所有类型敌机血量提升
            round++;
            System.out.println("all enemies got hitpoint increased!");
            // 提高精英plus敌机的刷新频率
            if (threshold > 3600)
            {
                threshold -= 600;
            }
            return enemyFactory.get(3).createAircraft(-1, 0, 15 * (round + 10), 20);
        }
        
        // 时间达到阈值时生成一架精英plus敌机
        if (time > 0 && time % threshold == 0)
        {
            return enemyFactory.get(2).createAircraft(-1, 9, 15 * (round + 6), 15);
        }

        // 否则随机生成一架普通敌机或精英敌机 
        double randi = Math.random();
        
        if (randi < 0.6 - probability_increase)     // 60%~40%的概率生成普通敌机
        {
            return enemyFactory.get(0).createAircraft(0, 5, 15 * (round + 2), 0);
        }
        else                                        // 40%~60%的概率生成精英敌机
        {
            return enemyFactory.get(1).createAircraft(-1, 7, 15 * (round + 4), 10);
        }
    }
}
