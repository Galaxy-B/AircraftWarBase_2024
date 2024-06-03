package edu.hitsz.prop;

import edu.hitsz.application.Main;
import edu.hitsz.swing.StartMenu;
import edu.hitsz.thread.MusicThread;

import edu.hitsz.aircraft.AbstractAircraft;

/**
 * 加血道具
 * @author 220111004
 */
public class HealProp extends AbstractProp
{
    public HealProp(int locationX, int locationY, int speedX, int speedY)
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
        aircraft.decreaseHp(-30);   // -(-30) hp = +30 hp

        // 播放普通道具生效音效
        if (Main.startMenu.getMusicSetting() == StartMenu.MusicOn)
        {
            new MusicThread(Main.prop_bgm_path).start();
        }
    }
}
