package edu.hitsz.application.games;

import edu.hitsz.aircraft.*;
import edu.hitsz.application.HeroController;
import edu.hitsz.application.Main;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.prop.AbstractProp;
import edu.hitsz.prop.BombProp;
import edu.hitsz.basic.AbstractFlyingObject;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import edu.hitsz.propFactory.*;
import edu.hitsz.enemyFactory.*;
import edu.hitsz.enemyFactory.enemyCreator.EnemyCreator;
import edu.hitsz.rank.*;
import edu.hitsz.shootStrategy.DirectShoot;
import edu.hitsz.swing.RankList;
import edu.hitsz.swing.StartMenu;
import edu.hitsz.thread.*;

import javax.swing.*;
import java.util.*;
import java.util.concurrent.*;

/**
 * 游戏主面板，游戏启动
 *
 * @author hitsz
 */
public class Game extends JPanel {

    protected int backGroundTop = 0;

    /**
     * Scheduled 线程池，用于任务调度
     */
    private final ScheduledExecutorService executorService;

    /**
     * 时间间隔(ms)，控制刷新频率
     */
    private int timeInterval = 40;

    protected final HeroAircraft heroAircraft;
    protected final List<BaseBullet> heroBullets;
    public static final List<AbstractAircraft> enemyAircrafts = new LinkedList<>();
    public static final List<BaseBullet> enemyBullets = new LinkedList<>();
    // 道具列表
    protected final List<AbstractProp> props;

    // 敌机工厂
    private final List<EnemyFactory> enemyFactory;
    
    // 道具工厂
    public final List<PropFactory> propFactory;

    // 用于生成敌机的类 (在子类中进行实例化)
    protected EnemyCreator enemyCreator;

    // 游戏记录的文件名
    protected String path;

    // 获取游戏记录的类
    private RecordDAO recordDAO;

    // 排行榜UI
    private RankList rankList;

    // 主背景音效线程
    public static BackgroundMusicThread main_bgm;

    // boss敌机背景音效线程
    public static BackgroundMusicThread boss_bgm;

    // 控制英雄机弹道线程
    public static ShootStrategyThread shootStrategyThread;

    // 模式名
    protected String modeName;

    /**
     * 屏幕中出现的敌机最大数量
     */
    protected int enemyMaxNumber;

    /**
     * 当前得分
     */
    public static int score = 0;
    /**
     * 当前时刻
     */
    private int time = 0;

    /**
     * 周期（ms)
     * 指示子弹的发射、敌机的产生频率
     */
    private int cycleDuration = 600;
    private int cycleTime = 0;

    /**
     * 游戏结束标志
     */
    private static boolean gameOverFlag = false;

    public Game() {
        heroAircraft = HeroAircraft.getInstance();

        heroBullets = new LinkedList<>();
        props = new LinkedList<>();             // 实例化道具列表

        // 实例化敌机工厂
        enemyFactory = new LinkedList<>();
        enemyFactory.add(new MobEnemyFactory());
        enemyFactory.add(new EliteEnemyFactory());
        enemyFactory.add(new ElitePlusEnemyFactory());
        enemyFactory.add(new BossEnemyFactory());

        // 实例化道具工厂
        propFactory = new LinkedList<>();
        propFactory.add(new HealPropFactory());
        propFactory.add(new BombPropFactory());
        propFactory.add(new FirePropFactory());
        propFactory.add(new FirePlusPropFactory());

        // 实例化各线程
        shootStrategyThread = new ShootStrategyThread(heroAircraft, new DirectShoot());
        main_bgm = new BackgroundMusicThread(Main.bgm_path);
        boss_bgm = new BackgroundMusicThread(Main.boss_bgm_path);

        /**
         * Scheduled 线程池，用于定时任务调度
         * 关于alibaba code guide：可命名的 ThreadFactory 一般需要第三方包
         * apache 第三方库： org.apache.commons.lang3.concurrent.BasicThreadFactory
         */
        this.executorService = new ScheduledThreadPoolExecutor(1,
                new BasicThreadFactory.Builder().namingPattern("game-action-%d").daemon(true).build());

        //启动英雄机鼠标监听
        new HeroController(this, heroAircraft);

    }

    /**
     * 游戏启动入口，执行游戏逻辑
     */
    public void action() {

        // 循环播放背景音乐
        if (Main.startMenu.getMusicSetting() == StartMenu.MusicOn)
        {
            // 启动主背景音效线程
            main_bgm.start();
        }

        // 定时任务：绘制、对象产生、碰撞判定、击毁及结束判定
        Runnable task = () -> {

            time += timeInterval;


            // 周期性执行（控制频率）
            if (timeCountAndNewCycleJudge()) {
                System.out.println(time);
                // 新敌机产生
                if (enemyAircrafts.size() < enemyMaxNumber) 
                {
                    AbstractAircraft newEnemy = enemyCreator.createEnemy(enemyFactory, time, score);
                    enemyAircrafts.add(newEnemy);
                }
                // 飞机射出子弹
                shootAction();
            }

            // 子弹移动
            bulletsMoveAction();

            // 飞机移动
            aircraftsMoveAction();

            // 道具移动
            propsMoveAction();

            // 撞击检测
            crashCheckAction();

            // 后处理
            postProcessAction();

            //每个时刻重绘界面
            repaint();

            // 游戏结束检查英雄机是否存活
            if (heroAircraft.getHp() <= 0) 
            {
                main_bgm.stopMusic();
                boss_bgm.stopMusic();
                // 播放游戏结束音效
                if (Main.startMenu.getMusicSetting() == StartMenu.MusicOn)
                {
                    new MusicThread(Main.gameover_bgm_path).start();
                }
                
                // 实例化记录管理系统
                recordDAO = new RecordDAOImpl(path);
                
                // 记录本次成绩
                String player_name = JOptionPane.showInputDialog(this, "游戏结束, 你的得分为" + score + ".\n请输入名字记录得分: ");
                if (player_name != null)
                {
                    recordDAO.add_record(player_name, score);
                    // 覆写分数信息
                    recordDAO.save_record();
                }
                
                // 实例化排行榜UI
                rankList = new RankList(recordDAO, modeName);
                // 切换至排行榜
                Main.cardPanel.add(rankList.getMainPanel());
                Main.cardLayout.last(Main.cardPanel);
                
                // 游戏结束
                executorService.shutdown();
                gameOverFlag = true;
                System.out.println("Game Over!");
            }

        };

        /**
         * 以固定延迟时间进行执行
         * 本次任务执行完成后，需要延迟设定的延迟时间，才会执行新的任务
         */
        executorService.scheduleWithFixedDelay(task, timeInterval, timeInterval, TimeUnit.MILLISECONDS);

    }

    //***********************
    //      Action 各部分
    //***********************

    private boolean timeCountAndNewCycleJudge() {
        cycleTime += timeInterval;
        if (cycleTime >= cycleDuration && cycleTime - timeInterval < cycleTime) {
            // 跨越到新的周期
            cycleTime %= cycleDuration;
            return true;
        } else {
            return false;
        }
    }

    private void shootAction() {
        // 敌机射击
        for (AbstractAircraft enemyAircraft : enemyAircrafts)
        {
            enemyBullets.addAll(enemyAircraft.executeShootStrategy());
        }
        // 英雄射击
        heroBullets.addAll(heroAircraft.executeShootStrategy());
    }

    private void bulletsMoveAction() {
        for (BaseBullet bullet : heroBullets) {
            bullet.forward();
        }
        for (BaseBullet bullet : enemyBullets) {
            bullet.forward();
        }
    }

    private void aircraftsMoveAction() {
        for (AbstractAircraft enemyAircraft : enemyAircrafts) {
            enemyAircraft.forward();
        }
    }

    private void propsMoveAction()
    {
        for (AbstractProp prop : props)
        {
            prop.forward();
        }
    }

    /**
     * 碰撞检测：
     * 1. 敌机攻击英雄
     * 2. 英雄攻击/撞击敌机
     * 3. 英雄获得补给
     */
    private void crashCheckAction() {
        // 敌机子弹攻击英雄
        for (BaseBullet bullet : enemyBullets)
        {
            if (bullet.notValid())
                continue;
            
            if (heroAircraft.crash(bullet))
            {
                heroAircraft.decreaseHp(bullet.getPower());
                bullet.vanish();
            }
        }
        // 英雄子弹攻击敌机
        for (BaseBullet bullet : heroBullets) {
            if (bullet.notValid()) {
                continue;
            }
            for (AbstractAircraft enemyAircraft : enemyAircrafts) {
                if (enemyAircraft.notValid()) {
                    // 已被其他子弹击毁的敌机，不再检测
                    // 避免多个子弹重复击毁同一敌机的判定
                    continue;
                }
                if (enemyAircraft.crash(bullet)) {
                    // 敌机撞击到英雄机子弹
                    // 播放子弹击中敌机音效
                    if (Main.startMenu.getMusicSetting() == StartMenu.MusicOn)
                    {
                        new MusicThread(Main.bullet_bgm_path).start();  
                    }
                    // 敌机损失一定生命值
                    enemyAircraft.decreaseHp(bullet.getPower());
                    bullet.vanish();
                    // 敌机被击落时 执行道具掉落逻辑
                    if (enemyAircraft.notValid()) 
                    {
                        // 掉落道具
                        props.addAll(enemyAircraft.dropProp(propFactory));
                        // 执行加分
                        score += enemyAircraft.getScore();
                        // 击落boss敌机时切换回主背景音效
                        if (enemyAircraft instanceof BossEnemy)
                        {
                            // 结束boss敌机背景音效 切换回主背景音效
                            boss_bgm.stopMusic();
                            main_bgm = new BackgroundMusicThread(Main.bgm_path);
                            main_bgm.start();
                        }  
                    }
                }
                // 英雄机 与 敌机 相撞，均损毁
                if (enemyAircraft.crash(heroAircraft) || heroAircraft.crash(enemyAircraft)) {
                    enemyAircraft.vanish();
                    heroAircraft.decreaseHp(Integer.MAX_VALUE);
                }
            }
        }

        // 如果道具与我方战机相撞 立刻生效
        for (AbstractProp prop : props)
        {
            if (!prop.crash(heroAircraft))
                continue;
            // 炸弹道具需特殊处理
            if (prop instanceof BombProp)
            {
                ((BombProp)prop).addAircraft(enemyAircrafts);
            }
            prop.takeEffect(heroAircraft);
            prop.vanish();
        }
    }

    /**
     * 后处理：
     * 1. 删除无效的子弹
     * 2. 删除无效的敌机
     * 3. 删除无效的道具
     * <p>
     * 无效的原因可能是撞击或者飞出边界
     */
    private void postProcessAction() {
        enemyBullets.removeIf(AbstractFlyingObject::notValid);
        heroBullets.removeIf(AbstractFlyingObject::notValid);
        enemyAircrafts.removeIf(AbstractFlyingObject::notValid);
        // 删除无效的道具
        props.removeIf(AbstractFlyingObject::notValid);
    }

    public static boolean isGameOver()
    {
        return gameOverFlag;
    }

    //***********************
    //      Paint 各部分在子类中实现
    //***********************
}
