package com.core;

import org.apache.commons.collections.map.HashedMap;
import org.jsoup.nodes.Document;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Service;
//import us.codecraft.webmagic.Page;
//import us.codecraft.webmagic.Site;
//import us.codecraft.webmagic.Spider;
//import us.codecraft.webmagic.processor.PageProcessor;
//import us.codecraft.webmagic.selector.Html;

import org.jsoup.nodes.Element;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author wei
 * @description
 * @date 2019/10/30
 */
//@Service
public class GithubRepoPageProcessor implements PageProcessor {

    public static final String fundDate = getYesterday();

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(10000);

    @Override
    public void process(Page page) {
        Html html = page.getHtml();
        Document document = html.getDocument();
        System.out.println(document.toString());
//        System.out.println(document.getElementsByTag("body").text());
        Element element = document.getElementsByClass("appTop areaOuter clearfix").first();
        String fundInfo = element.getElementById("qt_fund").getElementsByTag("span").first().text();
        String fundName = fundInfo.split(" ")[0];
        String fundCode = fundInfo.split(" ")[1];
        System.out.println(fundName);
        System.out.println(fundCode);
        Element qtBase = element.getElementById("qt_base");
        String fundValue = qtBase.getElementsByClass("nav").first().getElementsByTag("li").get(1).getElementsByTag("span").text();
        System.out.println(fundValue);
        System.out.println(fundDate);
        String fundType = "";
        String startDate = "";
        String openDate = "";
        String allMoney = "";
        String fundStyle = "";

        System.out.println("============\n");
        System.out.println(element);
    }

    @Override
    public Site getSite() {
        return site;
    }

//    @Scheduled(cron = "0/4 * * * * MON-SAT")  //每4秒执行一次
//    public static void test() {
//    }

    public static void main(String[] args) {
        Spider.create(new GithubRepoPageProcessor()).addUrl("http://cn.morningstar.com/quicktake/0P00016WFU").thread(5).run();

    }

    public static String getYesterday() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY,-24);
        return sdf.format(calendar.getTime());
    }
}

