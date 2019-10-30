package com.dao;

import com.entity.YangGuangPageContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;

/**
 * @author wei
 * @description
 * @date 2019/10/30
 */
@Repository
public interface YangGuangPageContentDao extends JpaRepository<YangGuangPageContent, Long> {

    //根据url查询正文
    YangGuangPageContent findByContentUrl(String url);

    //更新部分字段
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update YangGuangPageContent set content = ?1 , updated_time = ?2 , updated_by = ?3 where content_url = ?4")
    int updateContent(String content, Date updatedTime,
                      String updatedBy, String contentUrl);
}

