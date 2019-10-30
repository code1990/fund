package com.core;

//import org.jsoup.nodes.Document;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Service;
//import us.codecraft.webmagic.Page;
//import us.codecraft.webmagic.Site;
//import us.codecraft.webmagic.Spider;
//import us.codecraft.webmagic.processor.PageProcessor;
//import us.codecraft.webmagic.selector.Html;

/**
 * @author wei
 * @description
 * @date 2019/10/30
 */
//@Service
//public class GithubRepoPageProcessor implements PageProcessor {
//
//    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(10000);
//
//    @Override
//    public void process(Page page) {
//        Html html = page.getHtml();
//        Document document = html.getDocument();
//        System.out.println(document.toString());
//        System.out.println(document.getElementsByTag("body").text());
//    }
//
//    @Override
//    public Site getSite() {
//        return site;
//    }
//
//    @Scheduled(cron = "0/4 * * * * MON-SAT")  //每4秒执行一次
//    public static void test() {
//        Spider.create(new GithubRepoPageProcessor()).addUrl("http://fund.eastmoney.com/fundhot8.html").thread(5).run();
//    }
//}

