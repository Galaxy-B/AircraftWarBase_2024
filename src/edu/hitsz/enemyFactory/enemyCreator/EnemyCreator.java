package edu.hitsz.enemyFactory.enemyCreator;

import java.util.List;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.enemyFactory.EnemyFactory;

public abstract class EnemyCreator 
{
    // 当前的boss轮次
    protected int round = 0;
    // 刷新精英plus敌机的时间阈值
    protected int threshold = 6000;
    // 精英敌机刷新概率的提升值
    double probability_increase = 0.0;

    // 由子类实现具体的敌机生成逻辑
    public abstract AbstractAircraft createEnemy(List<EnemyFactory> enemyFactory, int time, int score);
}
