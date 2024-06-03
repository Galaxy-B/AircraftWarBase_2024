package edu.hitsz.application;


import edu.hitsz.aircraft.*;
import edu.hitsz.bullet.*;
import edu.hitsz.prop.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 综合管理图片的加载，访问
 * 提供图片的静态访问方法
 *
 * @author hitsz
 */
public class ImageManager {

    /**
     * 类名-图片 映射，存储各基类的图片 <br>
     * 可使用 CLASSNAME_IMAGE_MAP.get( obj.getClass().getName() ) 获得 obj 所属基类对应的图片
     */
    private static final Map<String, BufferedImage> CLASSNAME_IMAGE_MAP = new HashMap<>();

    public static BufferedImage EASY_BACKGROUND_IMAGE;
    public static BufferedImage MEDIUM_BACKGROUND_IMAGE;
    public static BufferedImage HARD_BACKGROUND_IMAGE;
    public static BufferedImage HERO_IMAGE;
    
    public static BufferedImage HERO_BULLET_IMAGE;
    public static BufferedImage ENEMY_BULLET_IMAGE;
    
    public static BufferedImage MOB_ENEMY_IMAGE;
    public static BufferedImage ELITE_ENEMY_IMAGE;          // 精英敌机    
    public static BufferedImage ELITE_PLUS_ENEMY_IMAGE;     // 精英plus敌机    
    public static BufferedImage BOSS_ENEMY_IMAGE;           // BOSS敌机    
    
    public static BufferedImage HEAL_PROP_IMAGE;        // 治疗道具
    public static BufferedImage FIRE_PROP_IMAGE;        // 火力道具
    public static BufferedImage BOMB_PROP_IMAGE;        // 炸弹道具
    public static BufferedImage FIRE_PLUS_PROP_IMAGE;   // 超级火力道具

    static {
        try {

            EASY_BACKGROUND_IMAGE = ImageIO.read(new FileInputStream("src/images/bg.jpg"));
            MEDIUM_BACKGROUND_IMAGE = ImageIO.read(new FileInputStream("src/images/bg2.jpg"));
            HARD_BACKGROUND_IMAGE = ImageIO.read(new FileInputStream("src/images/bg3.jpg"));

            HERO_IMAGE = ImageIO.read(new FileInputStream("src/images/hero.png"));
            MOB_ENEMY_IMAGE = ImageIO.read(new FileInputStream("src/images/mob.png"));
            ELITE_ENEMY_IMAGE = ImageIO.read(new FileInputStream("src/images/elite.png"));
            ELITE_PLUS_ENEMY_IMAGE = ImageIO.read(new FileInputStream("src/images/elitePlus.png"));
            BOSS_ENEMY_IMAGE = ImageIO.read(new FileInputStream("src/images/boss.png"));
            
            HERO_BULLET_IMAGE = ImageIO.read(new FileInputStream("src/images/bullet_hero.png"));
            ENEMY_BULLET_IMAGE = ImageIO.read(new FileInputStream("src/images/bullet_enemy.png"));

            HEAL_PROP_IMAGE = ImageIO.read(new FileInputStream("src/images/prop_blood.png"));
            FIRE_PROP_IMAGE = ImageIO.read(new FileInputStream("src/images/prop_bullet.png"));
            BOMB_PROP_IMAGE = ImageIO.read(new FileInputStream("src/images/prop_bomb.png"));
            FIRE_PLUS_PROP_IMAGE = ImageIO.read(new FileInputStream("src/images/prop_bulletPlus.png"));

            CLASSNAME_IMAGE_MAP.put(HeroAircraft.class.getName(), HERO_IMAGE);
            CLASSNAME_IMAGE_MAP.put(MobEnemy.class.getName(), MOB_ENEMY_IMAGE);
            CLASSNAME_IMAGE_MAP.put(EliteEnemy.class.getName(), ELITE_ENEMY_IMAGE);
            CLASSNAME_IMAGE_MAP.put(ElitePlusEnemy.class.getName(), ELITE_PLUS_ENEMY_IMAGE);
            CLASSNAME_IMAGE_MAP.put(BossEnemy.class.getName(), BOSS_ENEMY_IMAGE);

            CLASSNAME_IMAGE_MAP.put(HeroBullet.class.getName(), HERO_BULLET_IMAGE);
            CLASSNAME_IMAGE_MAP.put(EnemyBullet.class.getName(), ENEMY_BULLET_IMAGE);

            CLASSNAME_IMAGE_MAP.put(HealProp.class.getName(), HEAL_PROP_IMAGE);
            CLASSNAME_IMAGE_MAP.put(FireProp.class.getName(), FIRE_PROP_IMAGE);
            CLASSNAME_IMAGE_MAP.put(BombProp.class.getName(), BOMB_PROP_IMAGE);
            CLASSNAME_IMAGE_MAP.put(FirePlusProp.class.getName(), FIRE_PLUS_PROP_IMAGE);

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public static BufferedImage get(String className){
        return CLASSNAME_IMAGE_MAP.get(className);
    }

    public static BufferedImage get(Object obj){
        if (obj == null){
            return null;
        }
        return get(obj.getClass().getName());
    }

}
