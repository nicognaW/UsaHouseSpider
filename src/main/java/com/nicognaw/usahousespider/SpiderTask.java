package com.nicognaw.usahousespider;

public interface SpiderTask {
    /**
     * 初始化数据库
     */
    void initRepo();

    /**
     * 定时爬取数据
     */
    void periodSpider();

    /**
     * 定时显示心跳消息
     */
    void heartBeat();
}
