package edu.hitsz.swing;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import edu.hitsz.application.*;
import edu.hitsz.rank.*;
import edu.hitsz.rank.Record;

public class RankList
{
    private JPanel MainPanel;

    private JTextField ModeName;
    private JPanel TopPanel;
    private JTable RecordTable;
    private JPanel BottomPanel;
    private JButton DeleteButton;
    private JButton ReturnButton;
    private JScrollPane RecordTableScrollPanel;

    public RankList(RecordDAO recordDAO, String name)
    {
        ModeName.setText("难度: " + name);
        
        List<Record> records = recordDAO.get_all_record();

        String[] columnName = {"名次", "玩家名", "得分", "记录时间"};
        String[][] rankData = new String[records.size()][4];

        // 对records中的日期格式化
        DateFormat df = new SimpleDateFormat("MM-dd HH:mm");

        for (int i = 0; i < records.size(); i++)
        {
            rankData[i][0] = String.valueOf(i + 1);                         // 名次
            rankData[i][1] = records.get(i).get_player_name();              // 玩家名
            rankData[i][2] = String.valueOf(records.get(i).get_score());    // 得分
            rankData[i][3] = df.format(records.get(i).get_record_date());   // 记录时间
        }

        // 表格模型
        DefaultTableModel model = new DefaultTableModel(rankData, columnName)
        {
            @Override
            public boolean isCellEditable(int row, int col) { return false; }
        };

        // 从表格模型中获取数据
        RecordTable.setModel(model);
        RecordTableScrollPanel.setViewportView(RecordTable);

        DeleteButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                int row = RecordTable.getSelectedRow();

                int result = JOptionPane.showConfirmDialog(MainPanel, "是否确定删除?");
                if (result == JOptionPane.YES_OPTION && row != -1)
                {
                    model.removeRow(row);
                    
                    // 同步删除records中对应的记录并覆写 
                    int id = records.get(row).get_record_id();
                    recordDAO.delete_record(id);
                    recordDAO.save_record();
                }
            }
        });
        
        ReturnButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                Main.cardLayout.first(Main.cardPanel);
            }
        });
    }

    public JPanel getMainPanel()
    {
        return MainPanel;
    }

    public void setTitle()
    {
        
    }
}
