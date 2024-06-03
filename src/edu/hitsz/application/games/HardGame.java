package edu.hitsz.application.games;

import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;
import edu.hitsz.basic.AbstractFlyingObject;
import edu.hitsz.enemyFactory.enemyCreator.HardEnemyCreator;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class HardGame extends Game
{
    public HardGame()
    {
        super();
        // 实例化敌机生成器
        enemyCreator = new HardEnemyCreator();
        // 设置敌机数量最大值
        enemyMaxNumber = 8;
        // 设置当前模式名
        modeName = "HARD";
        // 设置记录文件路径
        path = "data/hard_records.txt";
    }

    /**
     * 重写paint方法
     * 通过重复调用paint方法，实现游戏动画
     *
     * @param  g
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        
        // 绘制背景,图片滚动
        g.drawImage(ImageManager.HARD_BACKGROUND_IMAGE, 0, this.backGroundTop - Main.WINDOW_HEIGHT, null);
        g.drawImage(ImageManager.HARD_BACKGROUND_IMAGE, 0, this.backGroundTop, null);
        this.backGroundTop += 1;
        if (this.backGroundTop == Main.WINDOW_HEIGHT) {
            this.backGroundTop = 0;
        }
        
            // 先绘制道具，而后绘制子弹，最后后绘制飞机
        // 道具显示在最下层，而后使子弹，最后是飞机
        paintImageWithPositionRevised(g, props);

        paintImageWithPositionRevised(g, enemyBullets);
        paintImageWithPositionRevised(g, heroBullets);

        paintImageWithPositionRevised(g, enemyAircrafts);

        g.drawImage(ImageManager.HERO_IMAGE, heroAircraft.getLocationX() - ImageManager.HERO_IMAGE.getWidth() / 2,
                heroAircraft.getLocationY() - ImageManager.HERO_IMAGE.getHeight() / 2, null);

        //绘制得分和生命值
        paintScoreAndLife(g);

    }

    private void paintImageWithPositionRevised(Graphics g, List<? extends AbstractFlyingObject> objects) {
        if (objects.size() == 0) {
            return;
        }

        for (AbstractFlyingObject object : objects) {
            BufferedImage image = object.getImage();
            assert image != null : objects.getClass().getName() + " has no image! ";
            g.drawImage(image, object.getLocationX() - image.getWidth() / 2,
                    object.getLocationY() - image.getHeight() / 2, null);
        }
    }

    private void paintScoreAndLife(Graphics g) {
        int x = 10;
        int y = 25;
        g.setColor(new Color(16711680));
        g.setFont(new Font("SansSerif", Font.BOLD, 22));
        g.drawString("SCORE:" + score, x, y);
        y = y + 20;
        g.drawString("LIFE:" + heroAircraft.getHp(), x, y);
    }
}
