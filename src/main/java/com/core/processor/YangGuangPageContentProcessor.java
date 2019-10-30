package com.core.processor;

import com.core.pipeline.YangGuangPagePipeline;
import com.entity.YangGuangPageContent;
import com.vo.YangGuangVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author wei
 * @description
 * @date 2019/10/30
 */
public class YangGuangPageContentProcessor implements PageProcessor {

    @Autowired
    private static YangGuangPagePipeline yangGuangPagePipeline;

    private static Logger logger = LoggerFactory.getLogger(YangGuangPageProcessor.class);

    // 正则表达式\\. \\转义java中的\ \.转义正则中的.
    // 主域名

    public static final String URL = "http://58.210.114.86/bbs/";

    public static final String BASE_URL = "http://58.210.114.86/bbs/forum.php?mod=forumdisplay&fid=2&page=1";

    public static final String PAGE_URL = "http://58.210.114.86/bbs/forum.php?mod=forumdisplay&fid=2&page=1";

    //设置抓取参数。详细配置见官方文档介绍 抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me()
            .setDomain(BASE_URL)
            .setSleepTime(1000)
            .setRetryTimes(30)
            .setCharset("utf-8")
            .setTimeOut(5000);
    //.setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");


    @Override
    public Site getSite() {
        return site;
    }

    @Override
    public void process(Page page) {
        String[] pages = page.getUrl().toString().split("page=");
        Long size = Long.valueOf(pages[1]);
        if(size !=null && size <=2) {

            YangGuangVo yangGuangVo =  new YangGuangVo();
            //获取所有列表框内容
            List<Selectable> list = page.getHtml().xpath("//div[@class='bm_c']/form/table/tbody").nodes();

            //获取当前页面的所有列表
            if(list != null && list.size() > 0){
                List<YangGuangPageContent> yangGuangPages = new ArrayList<YangGuangPageContent>();

                for(int i = 0; i < list.size(); i++){
                    Selectable s = list.get(i);

                    //正文，地址等信息
                    String contentUrl = s.xpath("//tr/td[@class='icn']/a/@href").toString();
                    String type = s.xpath("//tr/th[@class='common']/em[1]/a/text()").toString();
                    String status = s.xpath("//th[@class='common']/img[1]/@alt").toString();
                    String title = s.xpath("//th[@class='common']/a[@class='s xst']/text()").toString();
                    String author = s.xpath("//td[@class='by']/cite/a/text()").toString();
                    String address = s.xpath("//th[@class='common']/em[2]/text()").toString();
                    String publishTime = s.xpath("//td[@class='by']/em/span/span/@title").toString();
                    if(StringUtils.isEmpty(type)) {
                        type = s.xpath("//tr/th[@class='new']/em[1]/a/text()").toString();
                    }
                    if(StringUtils.isEmpty(status)) {
                        status = s.xpath("//th[@class='new']/img[1]/@alt").toString();
                    }
                    if(StringUtils.isEmpty(title)) {
                        title = s.xpath("//th[@class='new']/a[@class='s xst']/text()").toString();
                    }
                    if(StringUtils.isEmpty(address)) {
                        address = s.xpath("//th[@class='new']/em[2]/text()").toString();
                    }
                    if(StringUtils.isNotEmpty(contentUrl)){
                        YangGuangPageContent  yangGuangPage = new YangGuangPageContent();
                        yangGuangPage.setId(UUID.randomUUID().toString().replace("-",""));
                        yangGuangPage.setContentUrl(URL+contentUrl);
                        yangGuangPage.setCreatedBy("system");
                        yangGuangPage.setCreatedTime(new Date());
                        yangGuangPage.setType(type);
                        yangGuangPage.setStatus(status);
                        yangGuangPage.setTitle(title);
                        yangGuangPage.setAuthor(author);
                        yangGuangPage.setAddress(address);
                        yangGuangPage.setPublishTime(publishTime);

                        logger.info(String.format("页面的正文指向路径为：[%s]",contentUrl));

                        yangGuangPages.add(yangGuangPage);
                    }

                }
                yangGuangVo.setPageList(yangGuangPages);
            }
            page.putField("yangGuang", yangGuangVo);
            //page.putField("yangGuangHtml", page.getHtml());
        }
        page.addTargetRequests(doListUrl());
    }

    /*public static void main(String[] args) {
    	Spider spider = Spider.create(new YangGuangPageProcessor());
        spider.addUrl(BASE_URL);
        spider.addPipeline();
        spider.thread(5);
        spider.setExitWhenComplete(true);
        spider.start();
        spider.stop();
	}*/


    public List<String> doListUrl(){
        List<String> list = new ArrayList<String>();
        for(int i = 2;i<3;i++) {
            list.add("http://58.210.114.86/bbs/forum.php?mod=forumdisplay&fid=2&page=" + i);
        }
        return list;
    }

}


