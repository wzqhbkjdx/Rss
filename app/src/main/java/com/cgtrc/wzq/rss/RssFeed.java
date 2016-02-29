package com.cgtrc.wzq.rss;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by bym on 16/2/29.
 */
public class RssFeed {

    private String title = null; //标题
    private String pubDate = null; //发布日期
    //private int itemCount = 0;//用于计算列表数目
    private List<RssItem> itemList; //声明一个RSSItem类型的泛型集合类List对象itemlist，用于描述列表 item

    public RssFeed(){
        itemList = new ArrayList<>();
    }

    public int addItem(RssItem item){
        itemList.add(item);
        //itemCount++;
        return itemList.size();
    }

    public RssItem getItem(int location){
        return itemList.get(location);
    }

    public List getAllItem(){
        return itemList;
    }

    public List getAllItemsForListView(){
        List<Map<String ,Object>> data = new ArrayList<>();
        int size = itemList.size();
        for(int i = 0; i < size; i++){
            HashMap<String ,Object> item = new HashMap<>();
            item.put(RssItem.TITLE,itemList.get(i).getTitle());
            item.put(RssItem.PUBDATE,itemList.get(i).getPubDate());
            data.add(item);
        }
        return data;
    }

    public int getItemCount(){
        return itemList.size();
    }

    public void setTitle(){
        this.title = title;
    }

    public void setPubDate(String pubDate)
    {
        this.pubDate = pubDate;
    }
    public String getTitle()
    {
        return title;
    }
    public String getPubDate()
    {
        return pubDate;
    }



}
