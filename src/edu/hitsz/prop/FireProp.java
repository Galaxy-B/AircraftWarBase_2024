package edu.hitsz.prop;

import edu.hitsz.application.Main;
import edu.hitsz.application.games.Game;
import edu.hitsz.shootStrategy.ScatteredShoot;
import edu.hitsz.swing.StartMenu;
import edu.hitsz.thread.MusicThread;
import edu.hitsz.thread.ShootStrategyThread;
import edu.hitsz.aircraft.AbstractAircraft;

/**
 * 火力道具
 * @author 220111004
 */
public class FireProp extends AbstractProp
{
    public FireProp(int locationX, int locationY, int speedX, int speedY)
    {
        super(locationX, locationY, speedX, speedY);
    }

    @Override
    public void forward() {
        super.forward();
        // 判定 y 轴向下飞行出界
        if (locationY >= Main.WINDOW_HEIGHT ) {
            vanish();
        }
    }
    
    @Override
    public void takeEffect(AbstractAircraft aircraft)
    {
        // 将射击策略改为散射
        Game.shootStrategyThread.stopThread();
        Game.shootStrategyThread = new ShootStrategyThread(aircraft, new ScatteredShoot());
        Game.shootStrategyThread.start();

        // 播放普通道具生效音效
        if (Main.startMenu.getMusicSetting() == StartMenu.MusicOn)
        {
            new MusicThread(Main.prop_bgm_path).start();
        }
    }
}
