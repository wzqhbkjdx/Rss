package com.cgtrc.wzq.rss;

import android.util.Log;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import java.net.URL;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by bym on 16/2/29.
 */
public class RssParseUtil {

    public static RssFeed getRssFeed(String urlString) {
        try {
            URL url = new URL(urlString);
            SAXParserFactory factory = SAXParserFactory.newInstance(); // 构建Sax解析工厂
            SAXParser parser = factory.newSAXParser(); //使用Sax解析工厂构建Sax解析器
            XMLReader reader = parser.getXMLReader(); //使用Sax解析器构建xml Reader
            RssHandler rssHandler = new RssHandler(); //构建自定义的RSSHandler作为xml Reader的处理器（或代理）
            reader.setContentHandler(rssHandler);
            InputSource is = new InputSource(url.openStream());// 使用url打开流,并将流作为xml Reader的输入源并解析
            reader.parse(is);
            Log.i("MyAsyncTask","解析成功");
            return rssHandler.getFeed(); // 将解析结果作为 RSSFeed 对象返回

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
