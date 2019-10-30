package com.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author wei
 * @description
 * @date 2019/10/30
 */
@Data
@Accessors(chain = true)
@Entity
@Table(name = "yang_guang_page_content")
public class YangGuangPageContent {

    //新闻内容id
    @Id
    private String id;

    //新闻正文
    private String content;

    //新闻作者
    private String author;

    //列表的新闻类型
    private String type;

    //新闻发表地点
    private String address;

    //新闻标题
    private String title;

    //新闻的被关注状态
    private String status;

    //新闻发表时间
    @Column(name = "publish_time")
    private String publishTime;

    //新闻抓取时间
    @Column(name = "created_time")
    private Date createdTime;

    //新闻抓取者
    @Column(name = "created_by")
    private String createdBy;

    //列表的正文指向url
    @Column(name = "content_url")
    private String contentUrl;

    //新闻抓取时间
    @Column(name = "updated_time")
    private Date updatedTime;

    //新闻抓取者
    @Column(name = "updated_by")
    private String updatedBy;
}

