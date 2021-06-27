package cn.tiger.javaorderslistener.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author tiger
 * @date 2021/6/27
 */
@Data
@Accessors(chain = true)
public class ServerJiangSendEntity {

    private String title;

    private String desp;
}
