package edu.hitsz.swing;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import edu.hitsz.application.*;
import edu.hitsz.application.games.EasyGame;
import edu.hitsz.application.games.Game;
import edu.hitsz.application.games.HardGame;
import edu.hitsz.application.games.MediumGame;

public class StartMenu
{
    private JPanel MainPanel;

    private JButton EasyModeButton;
    private JButton HardModeButton;
    private JButton MediumModeButton;
    private JComboBox MusicSettings;
    private JLabel MusicSettingLabel;
    private JPanel ButtonPanel;
    private JPanel SettingPanel;
    private JPanel LabelPanel;

    private Game game;
    // 0->音效关 1->音效开
    private int MusicSetting = 1;
    public static int MusicOn = 1;
    public static int MusicOff = 0;

    public StartMenu()
    {
        EasyModeButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                // 简单模式
                game = new EasyGame();
                // 进入游戏
                Main.cardPanel.add(game);
                Main.cardLayout.last(Main.cardPanel);
                game.action();
            }
        });

        MediumModeButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                game = new MediumGame();
                // 进入游戏
                Main.cardPanel.add(game);
                Main.cardLayout.last(Main.cardPanel);
                game.action();
            }
        });

        HardModeButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                game = new HardGame();
                // 进入游戏
                Main.cardPanel.add(game);
                Main.cardLayout.last(Main.cardPanel);
                game.action();
            }
        });

        MusicSettings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                // 音效开
                if (MusicSettings.getSelectedIndex() == 0)
                {
                    MusicSetting = MusicOn;
                }
                // 音效关
                else
                {
                    MusicSetting = MusicOff;
                }
            }
        });
    }

    public int getMusicSetting()
    {
        return MusicSetting;
    }

    public JPanel getMainPanel()
    {
        return MainPanel;
    }
}
