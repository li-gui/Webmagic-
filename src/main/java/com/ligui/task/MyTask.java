package com.ligui.task;

import com.ligui.PipeLine.MyPipeLine;
import com.ligui.pojo.JobInfo;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.BloomFilterDuplicateRemover;
import us.codecraft.webmagic.scheduler.QueueScheduler;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;

@Component
public class MyTask implements PageProcessor {
	
	private static String url = "https://jobs.51job.com";
            public static void main(String[] args) {
            	process();
}
    Boolean bool=false;
    public void process(Page page) {
       // List<String> list = page.getHtml().css("div.filter div.e4>p").regex(".*要城.*").all();
         if (bool==false){
                 List<String> list2 = page.getHtml().css("div.filter>div.e4:nth-child(2)").links().all();
                 for (String url:list2){
                     page.addTargetRequest(url);
                     bool=true;
                 }
                 }else {
             List<Selectable> list = page.getHtml().css("div.detlist div.e").nodes();
             System.out.println("加入队列的URL长度:"+list.size());
             if (list.size() == 0) {
                 //此时是详情页面，直接获取信息
                 this.saveJobInfo(page);
             } else {
                 //此时是列表页面，需要获取到链接进入到详情页面
                 for (Selectable selectable : list) {
                     String jobInfoUrl = selectable.links().toString();
                     if (StringUtils.isNotBlank(jobInfoUrl)) {
                         //通过url进入详情页面并加入到任务中
                         page.addTargetRequest(jobInfoUrl);
                     }
                 }
                 //获取下一页按钮的url，并将其内容加入到任务中
                 String buttonUrl = page.getHtml().css("div.p_in li.bk").nodes().get(1).links().get();
                 page.addTargetRequest(buttonUrl);
             }

         }
    }

    private void saveJobInfo(Page page) {
        JobInfo jobInfo = new JobInfo();
        Html html = page.getHtml();
        String s = html.css("div.cn p.msg", "text").regex(".*发布").toString();
        jobInfo.setTime(s.trim().substring(s.length() - 8, s.length() - 3));
       // String s2 = html.css("div.cn p.msg", "text").regex("(?<=经验).*(?=招)").toString();
        String s2= html.css("div.cn p.msg", "text").regex(".*人").toString();
        jobInfo.setOther(s2);
        jobInfo.setUrl(page.getUrl().toString());
        jobInfo.setCompanyName(Jsoup.parse(html.css("div.cn p.cname a[title]").toString()).text());
        jobInfo.setCompanyInfo(Jsoup.parse(html.css("div.tmsg").toString()).text());
        String addr = Jsoup.parse(html.css("div.tBorderTop_box p.fp").nodes().get(1).toString()).text();
        jobInfo.setCompanyAddr(addr.substring(addr.indexOf("：") + 1));
        jobInfo.setJobAddr(addr.substring(addr.indexOf("：") + 1));
        jobInfo.setJobInfo(Jsoup.parse(html.css("div.job_msg").toString()).text());
        jobInfo.setJobName(Jsoup.parse(html.css("h1").toString()).text());
        String salary = html.css("div.cn strong", "text").toString();
        jobInfo.setSalaryMax(salary);
        page.putField("jobInfo", jobInfo);
    }
    //通过site配置爬虫的配置信息
    private Site site = Site.me()
            .setCharset("gbk")
            .setTimeOut(10 * 1000)
            .setRetrySleepTime(3000)
            .setRetryTimes(3);

    public Site getSite() {
        return site;
    }
    
    
    @Scheduled(initialDelay = 3000, fixedDelay = 100 * 1000)
    public static void process() {
        Spider.create(new MyTask())
                .addUrl(url)
                //设置线程数为10
                .thread(10)
                //利用布隆过滤器设置网页去重
                .setScheduler(new QueueScheduler()
                        .setDuplicateRemover(new BloomFilterDuplicateRemover(1000000)))
                //通过设置自定义pipeline将爬取到的数据存入mysql中
               .addPipeline(new MyPipeLine())
                .run();
    }

}
