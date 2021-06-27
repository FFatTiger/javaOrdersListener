package cn.tiger.javaorderslistener.pubsub;

import cn.tiger.javaorderslistener.constant.UrlConstant;
import cn.tiger.javaorderslistener.entity.ServerJiangSendEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author tiger
 * @date 2021/6/27
 */
@Component
public class ServerJiangPush implements Pusher{
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public boolean push(Object msg) throws JsonProcessingException {

        if (StringUtils.hasLength((String) msg)) {
            return false;
        }
        ServerJiangSendEntity sendEntity = new ServerJiangSendEntity();
        sendEntity.setTitle("java接单信息")
                .setDesp((String) msg);

        MultiValueMap<String, Object> postParameters = new LinkedMultiValueMap<>();
        postParameters.add("title", sendEntity.getTitle());
        postParameters.add("desp", sendEntity.getDesp());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded");
        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(postParameters, headers);
        String response = restTemplate.postForObject(UrlConstant.SERVER_JIANG_PUSH, httpEntity, String.class);

        return true;
    }
}
