package cn.tiger.javaorderslistener.pubsub;

import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * @author tiger
 * @date 2021/6/27
 */
public interface Pusher {

    boolean push(Object msg) throws JsonProcessingException;
}
