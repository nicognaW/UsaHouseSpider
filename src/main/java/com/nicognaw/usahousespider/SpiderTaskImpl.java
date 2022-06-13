package com.nicognaw.usahousespider;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nicognaw.usahousespider.entity.request.RequestData;
import com.nicognaw.usahousespider.entity.request.SearchParameters;
import com.nicognaw.usahousespider.entity.response.DataRecord;
import com.nicognaw.usahousespider.entity.response.ResponseData;
import com.nicognaw.usahousespider.status.SystemStatus;
import com.nicognaw.usahousespider.status.SystemStatusRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class SpiderTaskImpl implements SpiderTask {

    private static final Logger log = LoggerFactory.getLogger(SpiderTaskImpl.class);
    private final SpiderProperties spiderProperties;
    private final SpiderRepository spiderRepository;
    private final SystemStatusRepository systemStatusRepository;

    @Autowired
    public SpiderTaskImpl(SpiderRepository spiderRepository, SystemStatusRepository systemStatusRepository, SpiderProperties spiderProperties) throws IOException, InterruptedException {
        this.spiderRepository = spiderRepository;
        this.systemStatusRepository = systemStatusRepository;
        this.spiderProperties = spiderProperties;
        SystemStatus status = systemStatusRepository.getLatestStatus();
        boolean needInit = status.getNeedInit();
        if (needInit) initRepo();
    }

    private ResponseData getAllData() throws IOException, InterruptedException {
        ObjectMapper objectMapper = new ObjectMapper();
        SearchParameters searchParameter = new SearchParameters(100000);
        RequestData requestData = new RequestData(searchParameter);
        String serializedRequestData = objectMapper.writeValueAsString(requestData);
        String stringData = this.sendRequest(serializedRequestData);
        return objectMapper.readValue(stringData, ResponseData.class);
    }

    private String sendRequest(String body) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://www.affordablehousing.com/v4/AjaxRW.aspx?message=SearchListings")).header("content-type", "application/json").method("POST", HttpRequest.BodyPublishers.ofString(body)).build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    @Override
    public void initRepo() {
        int retryCount = spiderProperties.getRetryCount();
        while (retryCount >= 0) {
            try {
                log.info("初始化数据库...");
                spiderRepository.deleteAll();
                log.info("爬取全部数据...");
                ResponseData responseData = getAllData();
                log.info("获得 {} 条数据，写入数据库...", responseData.listings.size());
                spiderRepository.saveAllAndFlush(responseData.listings);
                log.info("将 {} 条数据写入数据库...", spiderRepository.count());
                log.info("设置系统状态...");
                SystemStatus status = systemStatusRepository.getLatestStatus();
                status.setNeedInit(false);
                systemStatusRepository.saveAndFlush(status);
                log.info("初始化结束");
            } catch (IOException ioException) {
                log.warn("发生IO错误({})，准备重试，还剩 {} 次重试。", ioException.getLocalizedMessage(), --retryCount);
            } catch (InterruptedException interruptedException) {
                log.warn("发生中断错误({})，准备重试，还剩 {} 次重试。", interruptedException.getLocalizedMessage(), --retryCount);
            }
        }
    }

    @Override
    @Scheduled(cron = "${spider.cron}")
    public void periodSpider() {
        int retryCount = spiderProperties.getRetryCount();
        while (retryCount >= 0) {
            try {
                log.info("定时执行开始...");
                log.info("爬取全部数据...");
                ResponseData responseData = getAllData();
                log.info("获得 {} 条数据，写入数据库...", responseData.listings.size());
                int saveCount = 0;
                for (DataRecord dataRecord : responseData.listings) {
                    if (!spiderRepository.existsById(dataRecord.CommunityId)) {
                        spiderRepository.save(dataRecord);
                        saveCount++;
                    }
                }
                retryCount = -1;
                log.info("将 {} 条新数据写入数据库，当前数据库共有 {} 条数据，定时任务结束", saveCount, spiderRepository.count());
            } catch (IOException ioException) {
                log.warn("发生IO错误({})，准备重试，还剩 {} 次重试。", ioException.getLocalizedMessage(), --retryCount);
            } catch (InterruptedException interruptedException) {
                log.warn("发生中断错误({})，准备重试，还剩 {} 次重试。", interruptedException.getLocalizedMessage(), --retryCount);
            }
        }
    }

    @Override
    @Scheduled(fixedRate = 60 * 1000)
    public void heartBeat() {
        log.info("🫀系统心跳🫀");
    }
}
