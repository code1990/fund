package com.vo;

import com.entity.YangGuangPageContent;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;
import java.util.List;

/**
 * @author wei
 * @description
 * @date 2019/10/30
 */
@Data
public class YangGuangVo {
    //新闻内容id
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
    private String publishTime;

    //新闻抓取时间
    private Date createdTime;

    //新闻抓取者
    private String createdBy;

    //列表的正文指向url
    private String contentUrl;

    //新闻抓取时间
    private Date updatedTime;

    //新闻抓取者
    private String updatedBy;

    private List<YangGuangPageContent> pageList;
}

