package edu.hitsz.prop;

import edu.hitsz.application.Main;
import edu.hitsz.application.games.Game;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.swing.StartMenu;
import edu.hitsz.thread.MusicThread;

import java.util.LinkedList;
import java.util.List;

import edu.hitsz.aircraft.AbstractAircraft;

/**
 * 炸弹道具
 * @author 220111004
 */
public class BombProp extends AbstractProp
{
    // 观察者列表
    private List<AbstractAircraft> aircraftList;
    
    public BombProp(int locationX, int locationY, int speedX, int speedY)
    {
        super(locationX, locationY, speedX, speedY);
        aircraftList = new LinkedList<>();
    }

    @Override
    public void forward() {
        super.forward();
        // 判定 y 轴向下飞行出界
        if (locationY >= Main.WINDOW_HEIGHT ) {
            vanish();
        }
    }

    // 增加观察者
    public void addAircraft(List<AbstractAircraft> aircrafts)
    {
        aircraftList.addAll(aircrafts);
    }

    // 通知所有观察者
    public void notifyAllAircraft()
    {
        // 敌机响应
        for (AbstractAircraft aircraft : aircraftList)
        {
            aircraft.update();
        }
        // 敌机子弹响应
        for (BaseBullet enemyBullet : Game.enemyBullets)
        {
            enemyBullet.vanish();
        }
    }

    @Override
    public void takeEffect(AbstractAircraft aircraft)
    {
        // 播放炸弹道具生效音效
        if (Main.startMenu.getMusicSetting() == StartMenu.MusicOn)
        {
            new MusicThread(Main.bomb_bgm_path).start();
        }
        // 通知所有观察者
        notifyAllAircraft();
    }
}
