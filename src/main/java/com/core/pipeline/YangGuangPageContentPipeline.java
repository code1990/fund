package com.core.pipeline;

import com.dao.YangGuangPageContentDao;
import com.entity.YangGuangPageContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * @author wei
 * @description
 * @date 2019/10/30
 */
@Component
public class YangGuangPageContentPipeline implements Pipeline {

    @Autowired
    private YangGuangPageContentDao yangGuangContentDao;

    private static Logger logger = LoggerFactory.getLogger(YangGuangPageContentPipeline.class);

    @Override
    public void process(ResultItems resultItems, Task task) {
        YangGuangPageContent yangGuangPageContent = (YangGuangPageContent) resultItems.get("yangGuangPageContent");
        if (yangGuangPageContent != null && yangGuangPageContent.getContentUrl() != null) {
            YangGuangPageContent dbYangGuangPageContent = yangGuangContentDao.findByContentUrl(yangGuangPageContent.getContentUrl());
            //更新列表的正文内容
            if (dbYangGuangPageContent != null) {
                logger.info(yangGuangPageContent.getContent());
                yangGuangContentDao.updateContent(yangGuangPageContent.getContent(),
                        yangGuangPageContent.getUpdatedTime(),
                        yangGuangPageContent.getUpdatedBy(),
                        dbYangGuangPageContent.getContentUrl());
            }
        } else {
            logger.info("此列表无内容");
        }
    }
}

