package com.core.pipeline;

import com.core.processor.YangGuangPageContentProcessor;
import com.dao.YangGuangPageContentDao;
import com.entity.YangGuangPageContent;
import com.vo.YangGuangVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author wei
 * @description
 * @date 2019/10/30
 */
@Component
public class YangGuangPagePipeline implements Pipeline {



    @Autowired
    private YangGuangPageContentDao yangGuangContentDao;

    @Autowired
    private YangGuangPageContentPipeline yangGuangPageContentPipeline;

    private Logger logger = LoggerFactory.getLogger(YangGuangPagePipeline.class);

    @Override
    public void process(ResultItems resultItems, Task task) {
        YangGuangVo yangGuangVo = (YangGuangVo) resultItems.get("yangGuang");

        if(yangGuangVo != null){

            System.out.println(yangGuangVo);
            List<YangGuangPageContent> list = new ArrayList<>();
            if(yangGuangVo.getPageList()!=null && yangGuangVo.getPageList().size()>0) {
//                list = yangGuangContentDao.save(yangGuangVo.getPageList());
                System.out.println(Arrays.toString(list.toArray()));
            }
            if(list.size()>0) {
                for(YangGuangPageContent yangGuangPage : yangGuangVo.getPageList()){
                    logger.info("开始正文内容的抓取");
                    //这里我们对后面的页面进行了深度的抓取,获取新闻的二级页面信息
                    Spider spider = Spider.create(new YangGuangPageContentProcessor());
                    spider.addUrl(yangGuangPage.getContentUrl());
                    logger.info("抓取正文的URL："+yangGuangPage.getContentUrl());
                    spider.addPipeline(yangGuangPageContentPipeline);
//                            .addPipeline(new YangGuangFilePipline());
                    spider.thread(1);
                    spider.setExitWhenComplete(true);
                    spider.start();
                    spider.stop();
                    logger.info("正文内容抓取结束");
                }
            }

        }
    }
}

