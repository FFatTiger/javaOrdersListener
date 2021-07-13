package cn.tiger.javaorderslistener.parse;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author tiger
 * @date 2021/6/27
 */
@Component
public class ITUDEOrdersParse implements HtmlParse<Document> {

    private static final Pattern pattern = Pattern.compile("编程语言[\\s\\S]*java", Pattern.CASE_INSENSITIVE);
    private static final Pattern pattern1 = Pattern.compile("编号：[A-Za-z0-9]+", Pattern.CASE_INSENSITIVE);


    @Override
    public String parse(Document sourceData) {
        ArrayList<String> filterInfo = new ArrayList<>();
        Iterator<Element> p = sourceData.getElementsByTag("p").iterator();
        boolean findFlag = false;
        StringBuilder frame = new StringBuilder();
        String tag = "";
        while (p.hasNext()) {
            Element element = p.next();
            String html = element.html();
            Matcher matcher = pattern1.matcher(html);
            if (matcher.find()) {
                tag = matcher.group();
            }
            if (!findFlag && pattern.matcher(html).find()) {
                frame.append("## " + tag + "  \n");
                findFlag = true;
            }

            if (findFlag && !element.getElementsByClass("ql-divide").isEmpty()) {
                findFlag = false;
            }

            if (findFlag) {
                frame.append( "* " + element.text() + "  \n");
            }

        }
        return frame.toString();
    }
}
