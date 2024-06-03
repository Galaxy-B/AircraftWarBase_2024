package edu.hitsz.rank;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Record 
{
    // 主键
    private int record_id;

    // 玩家昵称
    private String player_name;

    // 得分
    private int score;

    // 日期
    private Date record_date;

    public Record(int id, String name, int score, Date date)
    {
        this.record_id = id;
        this.player_name = name;
        this.score = score;
        this.record_date = date;
    }

    public int get_record_id()
    {
        return record_id;
    }

    public String get_player_name()
    {
        return player_name;
    }
    
    public int get_score()
    {
        return score;
    }
    
    public Date get_record_date()
    {
        return record_date;
    }

    public void set_record_id(int id)
    {
        this.record_id = id;
    }

    public void set_player_name(String name)
    {
        this.player_name = name;
    }
    
    public void set_score(int score)
    {
        this.score = score;
    }
    
    public void set_record_date(Date date)
    {
        this.record_date = date;
    }

    // 打印单条记录
    public void print(int rank)
    {
        // 对输出日期格式化
        DateFormat df = new SimpleDateFormat("MM-dd HH:mm");

        // 打印顺序: rank name score date
        System.out.print("第" + rank + "名: ");
        System.out.print(player_name + ",");
        System.out.print(score + ",");
        System.out.println(df.format(record_date));
    }
}
