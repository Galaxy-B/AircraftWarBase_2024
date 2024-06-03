package edu.hitsz.aircraft;

import edu.hitsz.application.Main;
import edu.hitsz.application.games.Game;
import edu.hitsz.prop.AbstractProp;
import edu.hitsz.propFactory.PropFactory;
import edu.hitsz.shootStrategy.*;

import java.util.LinkedList;
import java.util.List;

/**
 * 普通敌机
 * 不可射击
 *
 * @author hitsz
 */
public class MobEnemy extends AbstractAircraft {

    // 该敌机类型的分数
    private int score = 10;

    public MobEnemy(int locationX, int locationY, int speedX, int speedY, int hp, int power) {
        super(locationX, locationY, speedX, speedY, hp, 0, power, 1, new DirectShoot());
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
    public List<AbstractProp> dropProp(List<PropFactory> propFactory)
    {
        // 普通机不会掉落道具
        return new LinkedList<>();
    }

    @Override
    public int getScore()
    {
        return score;
    }

    @Override
    public void update()
    {
        // 普通敌机被炸毁
        this.vanish();
        // 英雄机获取其分数
        Game.score += this.score;
    }
}
