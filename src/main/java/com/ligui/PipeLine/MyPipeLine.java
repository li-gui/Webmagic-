package com.ligui.PipeLine;

import com.ligui.pojo.JobInfo;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.io.*;

@Component
public class MyPipeLine implements Pipeline {
    JobInfo jobInfo=null;
    int i=0;
    public void process(ResultItems resultItems, Task task) {
        //通过resultItems获取爬取的信息
        jobInfo = resultItems.get("jobInfo");
        if (jobInfo != null) {
            i++;
                File(i);
        }
    }
    public  void File(int i) {
        try {
            // 打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
            FileWriter writer = new FileWriter("F:/Youxun/爬虫.txt", true);
            writer.write("第："+i+"个数据：\n"+jobInfo.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


