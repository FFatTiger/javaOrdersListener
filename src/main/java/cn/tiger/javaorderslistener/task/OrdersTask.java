package cn.tiger.javaorderslistener.task;

import cn.tiger.javaorderslistener.constant.UrlConstant;
import cn.tiger.javaorderslistener.parse.HtmlParse;
import cn.tiger.javaorderslistener.pubsub.Pusher;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * @author tiger
 * @date 2021/6/26
 */
@Component
@Slf4j
public class OrdersTask {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HtmlParse<Document> itudeParse;

    @Autowired
    private Pusher severPush;

    private String cacheMsg = "";

    @Scheduled(fixedRate = 3 * 60 * 60 * 1000)
    public void ordersScan() throws IOException {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(UrlConstant.ITUDE_ORDERS, String.class);

        Document doc = Jsoup.parse(Objects.requireNonNull(responseEntity.getBody()));

        String result = itudeParse.parse(doc);
        if (cacheMsg.equals(result)) {
            log.info("消息无更改，不推送");
        } else {
            log.info("消息更改，推送");
            severPush.push(result);
            cacheMsg = result;
        }

    }
}
