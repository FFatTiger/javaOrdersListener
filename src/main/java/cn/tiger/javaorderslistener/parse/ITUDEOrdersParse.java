package cn.tiger.javaorderslistener.parse;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author tiger
 * @date 2021/6/27
 */
@Component
public class ITUDEOrdersParse implements HtmlParse<Document> {


    @Override
    public String parse(Document sourceData) {
        ArrayList<String> filterInfo = new ArrayList<>();
        Iterator<Element> p = sourceData.getElementsByTag("p").iterator();
        boolean findFlag = false;
        StringBuilder frame = new StringBuilder();
        while (p.hasNext()) {
            Element element = p.next();
            String html = element.html();
            if (!findFlag && html.matches("编程语言[\\s\\S]*java")) {
                findFlag = true;
            }

            if (findFlag && !element.getElementsByClass("ql-divide").isEmpty()) {
                findFlag = false;
            }

            if (findFlag) {
                frame.append(element.text() + "\n");
            }

        }
        return frame.toString();
    }
}
