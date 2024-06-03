package edu.hitsz.prop;

import edu.hitsz.application.Main;
import edu.hitsz.application.games.Game;
import edu.hitsz.shootStrategy.CircleShoot;
import edu.hitsz.swing.StartMenu;
import edu.hitsz.thread.MusicThread;
import edu.hitsz.thread.ShootStrategyThread;
import edu.hitsz.aircraft.AbstractAircraft;


public class FirePlusProp extends AbstractProp
{
    public FirePlusProp(int locationX, int locationY, int speedX, int speedY)
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
        // 将射击策略改为环射
        Game.shootStrategyThread.stopThread();
        Game.shootStrategyThread = new ShootStrategyThread(aircraft, new CircleShoot());
        Game.shootStrategyThread.start();

        // 播放普通道具生效音效
        if (Main.startMenu.getMusicSetting() == StartMenu.MusicOn)
        {
            new MusicThread(Main.prop_bgm_path).start();
        }
    }
}
