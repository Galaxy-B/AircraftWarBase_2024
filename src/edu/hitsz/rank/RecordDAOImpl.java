package edu.hitsz.rank;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Map;

import java.io.*;

public class RecordDAOImpl implements RecordDAO
{
    // 已分配的id总数(从文件中读取)
    private int id_count;
    // 从文件中读出的所有记录
    private List<Record> records;
    
    // 文件的路径
    private String path;

    // 对日期进行格式化
    DateFormat df = new SimpleDateFormat("MM-dd HH:mm");

    // 在构造函数中执行读文件的操作
    public RecordDAOImpl(String path)
    {
        this.path = path;
        // 实例化records
        records = new LinkedList<>();

        try     
        {
            File file = new File(path);
            // 建立输入流对象
            FileInputStream fip = new FileInputStream(file);
            // 建立reader
            InputStreamReader reader = new InputStreamReader(fip, "UTF-8");
            // 建立缓冲区对象
            BufferedReader br = new BufferedReader(reader);

            // line用于接收readLine的结果
            String line = br.readLine();
            // 读取id_count
            id_count = Integer.parseInt(line);
            line = br.readLine();
            // 每次读入一行记录
            while (!(line.equals("end")))
            {
                // 分隔符为一个逗号
                String[] split_res = line.split(",", 4);
                
                // 读入内容: id name score date
                Record record = new Record(0, null, 0, null);
                record.set_record_id(Integer.parseInt(split_res[0]));
                record.set_player_name(split_res[1]);
                record.set_score(Integer.parseInt(split_res[2]));
                record.set_record_date(df.parse(split_res[3]));

                records.add(record);
                // 读入下一行
                line = br.readLine();
            }
            
            // 关闭读取流
            reader.close();
            fip.close();
        } 
        catch (Exception e)
        {
            System.out.println("fail to open the file!");
        }
    }

    @Override
    public Record find_record(int id)
    {
        for (Record record : records)
        {
            if (record.get_record_id() == id)
            {
                return record;
            }
        }
        // 如果没有查找到对应记录
        System.out.println("can not find this record!");
        return null;
    }

    @Override
    public List<Record> get_all_record()
    {
        // 将records按照score降序排序
        Comparator<Record> scoreComparator = Comparator.comparing(Record::get_score); 
        records.sort(scoreComparator.reversed());
        
        return records;
    }

    @Override
    public void add_record(String player_name, int score)
    {
        // 获取本次成绩的记录时间
        Date date = new Date();

        id_count++;
        Record record = new Record(id_count + 1000, player_name, score, date);
        records.add(record);
    }

    @Override
    public void delete_record(int id)
    {
        Record record = find_record(id);
        // 可能查找不到对应的记录
        if (record != null)
        {
            records.remove(record);
        }
        else
        {
            System.out.println("can not find this record!");
        }
    }

    @Override
    public void save_record()
    {
        try
        {
            File file = new File(path);
            // 建立输出流对象
            FileOutputStream fop = new FileOutputStream(file);
            // 建立写入流对象
            OutputStreamWriter writer = new OutputStreamWriter(fop, "UTF-8");

            // 写入id_count
            writer.append(id_count + "\r\n");
            // 将records中的记录覆写入文件
            for (Record record : records)
            {
                // 写入顺序: id name score date
                writer.append(record.get_record_id() + ",");
                writer.append(record.get_player_name() + ",");
                writer.append(record.get_score() + ",");
                writer.append(df.format(record.get_record_date()) + ",");
                writer.append("\r\n");
            }
            // 写入end标记符
            writer.append("end\r\n");

            // 关闭写入流与输出流
            writer.close();
            fop.close();
        }
        catch (Exception e)
        {
            System.out.println("fail to open the file!");
        }
    }
}
