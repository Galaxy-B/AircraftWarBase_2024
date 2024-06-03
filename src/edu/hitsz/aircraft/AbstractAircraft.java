package edu.hitsz.aircraft;

import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.basic.AbstractFlyingObject;
import edu.hitsz.prop.AbstractProp;
import edu.hitsz.propFactory.PropFactory;
import edu.hitsz.shootStrategy.*;

import java.util.List;

/**
 * 所有种类飞机的抽象父类：
 * 敌机（BOSS, ELITEPLUS, ELITE, MOB），英雄飞机
 *
 * @author hitsz
 */
public abstract class AbstractAircraft extends AbstractFlyingObject {
    /**
     * 生命值
     */
    protected int maxHp;
    protected int hp;

    // 射击参数
    protected int shootNum;
    protected int power;
    protected int direction;

    // 射击策略
    protected ShootStrategy shootStrategy;

    public AbstractAircraft(int locationX, int locationY, int speedX, int speedY, int hp, int shootNum, int power, int direction,
                            ShootStrategy shootStrategy) 
    {
        super(locationX, locationY, speedX, speedY);
        this.hp = hp;
        this.maxHp = hp;
        this.shootNum = shootNum;
        this.power = power;
        this.direction = direction;
        this.shootStrategy = shootStrategy;
    }

    public void decreaseHp(int decrease){
        hp -= decrease;
        if(hp <= 0){
            hp=0;
            vanish();
        }
        // 拾取治疗道具后 保证英雄机血量不超过上限
        if (hp > maxHp)
        {
            hp = maxHp;
        }
    }

    public int getHp() {
        return hp;
    }

    // 获取飞机方向方法
    public int getDirection()
    {
        return direction;
    }

    // 获取飞机子弹数量方法
    public int getShootNum()
    {
        return shootNum;
    }

    // 获取飞机子弹伤害方法
    public int getPower()
    {
        return power;
    }

    // 设置飞机子弹数量方法
    public void setShootNum(int num)
    {
        this.shootNum = num;
    }

    // 设置射击策略
    public void setShootStrategy(ShootStrategy shootStrategy)
    {
        this.shootStrategy = shootStrategy;
    }

    // 执行射击策略
    public List<BaseBullet> executeShootStrategy()
    {
        return this.shootStrategy.shoot(this);
    }

    // 掉落道具方法
    // 只在精英敌机类中实现
    // 其余飞机子类返回一个空list
    public abstract List<AbstractProp> dropProp(List<PropFactory> propFactory);

    // 查询分数方法
    // 只在敌机类中实现 返回击落该敌机获得的分数
    // 英雄机类返回0
    public abstract int getScore();

    // 对炸弹生效的响应
    public abstract void update();
}


