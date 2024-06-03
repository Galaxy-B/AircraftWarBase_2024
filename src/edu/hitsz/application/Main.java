package edu.hitsz.application;

import edu.hitsz.swing.*;
import javax.swing.*;
import java.awt.*;

/**
 * 程序入口
 * @author hitsz
 */
public class Main {

    public static final int WINDOW_WIDTH = 512;
    public static final int WINDOW_HEIGHT = 768;

    public static final CardLayout cardLayout = new CardLayout(0, 0);
    public static final JPanel cardPanel = new JPanel(cardLayout);
    public static final StartMenu startMenu = new StartMenu();

    public static final String bgm_path = "src/videos/bgm.wav";
    public static final String boss_bgm_path = "src/videos/bgm_boss.wav";
    public static final String prop_bgm_path = "src/videos/get_supply.wav";
    public static final String bomb_bgm_path = "src/videos/bomb_explosion.wav";
    public static final String bullet_bgm_path = "src/videos/bullet_hit.wav";
    public static final String gameover_bgm_path = "src/videos/game_over.wav";

    public static void main(String[] args) {

        System.out.println("Hello Aircraft War");

        // 获得屏幕的分辨率，初始化 Frame
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        JFrame frame = new JFrame("Aircraft War");
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setResizable(false);
        //设置窗口的大小和位置,居中放置
        frame.setBounds(((int) screenSize.getWidth() - WINDOW_WIDTH) / 2, 0,
                WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(cardPanel);

        cardPanel.add(startMenu.getMainPanel());
        frame.setVisible(true);
    }
}
