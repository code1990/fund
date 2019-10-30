//package com.core.processor;
//
//import com.FundApplication;
//import us.codecraft.webmagic.Spider;
//import us.codecraft.webmagic.downloader.selenium.SeleniumDownloader;
//import us.codecraft.webmagic.processor.example.GithubRepoPageProcessor;
//
///**
// * @author wei
// * @description
// * @date 2019/10/30
// */
//public class LiuLanQiTest {
//
//    public static void main(String[] args) {
//        String driverClassPath = FundApplication.class.getClassLoader().getResource("chromedriver.exe").getPath();
//        String configIniPath = FundApplication.class.getClassLoader().getResource("config.ini").getPath();
//        //selenium系统配置，其中的路径写自己config文件的路径
//        System.setProperty("selenuim_config", "D:\\Documents\\Downloads\\SpringBoot+MongoDB+Echarts图表数据可视化\\SpringBoot+MongoDB+Echarts图表数据可视化\\后台项目\\fund\\target\\classes\\config.ini");
//        Spider.create(new GithubRepoPageProcessor())//调用一个webmagic中封装好的一个网页爬取类
//                .addUrl("http://www.baidu.com")//要爬取的网页
//                //浏览器驱动（动态网页信息通过模拟浏览器启动获取）
//                .setDownloader(new SeleniumDownloader(driverClassPath))
//                .thread(3)//启动n个线程（此语句表示启动3个线程）
//                .run();//启动爬虫，会阻塞当前线程执行（及n个线程不是同时执行的）
////		。runAsync();//启动爬虫，当前线程继续执行（及n个线程同时执行）
//    }
//}
//
