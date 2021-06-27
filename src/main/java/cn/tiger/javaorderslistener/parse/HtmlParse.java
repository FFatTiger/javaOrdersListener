package cn.tiger.javaorderslistener.parse;

import java.util.List;

/**
 * @author tiger
 * @date 2021/6/27
 */
public interface HtmlParse<T> {

    String parse(T sourceData);
}
