package edu.hitsz.aircraft;

import edu.hitsz.prop.AbstractProp;
import edu.hitsz.propFactory.PropFactory;
import edu.hitsz.application.*;
import edu.hitsz.shootStrategy.*;

import java.util.LinkedList;
import java.util.List;

/**
 * 英雄飞机，游戏玩家操控
 * @author hitsz
 */
public class HeroAircraft extends AbstractAircraft {

    // 懒汉式实现单例模式
    private static HeroAircraft instance = null;

    /**
     * 将构造函数设置为私有函数
     * @param locationX 英雄机位置x坐标
     * @param locationY 英雄机位置y坐标
     * @param speedX 英雄机射出的子弹的基准速度（英雄机无特定速度）
     * @param speedY 英雄机射出的子弹的基准速度（英雄机无特定速度）
     * @param hp    初始生命值
     */
    private HeroAircraft(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp, 1, 30, -1, new DirectShoot());
    }

    // 公开的获取唯一实例的接口
    public static synchronized HeroAircraft getInstance()
    {
        if (instance == null)
        {
            instance = new HeroAircraft(Main.WINDOW_WIDTH / 2,
                                        Main.WINDOW_HEIGHT - ImageManager.HERO_IMAGE.getHeight(),
                                 0, 0, 100);
        }
        return instance;
    }

    @Override
    public void forward() {
        // 英雄机由鼠标控制，不通过forward函数移动
    }

    @Override
    public List<AbstractProp> dropProp(List<PropFactory> propFactory)
    {
        // 英雄机不会掉落道具
        return new LinkedList<>();
    }

    @Override
    public int getScore()
    {
        // 英雄机不存在被击落的可能
        return 0;
    }

    @Override
    public void update()
    {
        // 英雄机不受炸弹影响
        return;
    }
}
