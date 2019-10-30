package com.task;

import com.core.pipeline.YangGuangPagePipeline;
import com.core.processor.YangGuangPageProcessor;
import com.dao.YangGuangPageContentDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Spider;

/**
 * @author wei
 * @description
 * @date 2019/10/30
 */
@Component
@EnableScheduling
public class SpingBootWebmagicJob {

    private Logger logger = LoggerFactory.getLogger(SpingBootWebmagicJob.class);

    public static final String BASE_URL = "http://58.210.114.86/bbs/forum.php?mod=forumdisplay&fid=2&page=1";

    @Autowired
    private YangGuangPageContentDao yangGuangContentDao;

    @Autowired
    YangGuangPagePipeline yangGuangPagePipeline;

    @Scheduled(cron = "${webmagic.job.cron}")
    //@PostConstruct启动项目则开启
    public void job() {

        long startTime, endTime;
        System.out.println("【爬虫开始】");
        startTime = System.currentTimeMillis();
        logger.info("爬取地址：" + BASE_URL);
        try {
            yangGuangContentDao.deleteAll();
            Spider spider = Spider.create(new YangGuangPageProcessor());
            spider.addUrl(BASE_URL);
            spider.addPipeline(yangGuangPagePipeline);
            // .addPipeline(new YangGuangFilePipline());
            spider.thread(5);
            spider.setExitWhenComplete(true);
            spider.start();
            spider.stop();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        endTime = System.currentTimeMillis();
        System.out.println("【爬虫结束】");

        System.out.println("阳光便民任务抓取耗时约" + ((endTime - startTime) / 1000) + "秒，已保存到数据库.");

    }


}

