package com.cgtrc.wzq.rss;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String RSS = "http://blog.sina.com.cn/rss/1267454277.xml";

    private ListView lv_rss_content;
    private NewsListAdapter adapter;
    private List<RssItem> newsLists;


    private static class ViewHolder{
        public TextView tv_titile;
        public TextView tv_updateTime;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    public void initView(){
        lv_rss_content = (ListView) findViewById(R.id.lv_rss_content);
        adapter = new NewsListAdapter();
        newsLists = new ArrayList<>();
        lv_rss_content.setAdapter(adapter);
        Log.i("更新listView","准备联网开始更新listView");

        loadNewsList(RSS);

    }

    private void loadNewsList(String url) {

        new MyAsyncTask().execute(url);

    }

    private class MyAsyncTask extends AsyncTask<String,Integer,RssFeed>{
        private RssFeed rssFeed = new RssFeed();


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected RssFeed doInBackground(String... params) {
            String path = params[0];
            Log.i("MyAsyncTask",path);
            rssFeed = RssParseUtil.getRssFeed(path);
            return rssFeed;
        }

        @Override
        protected void onPostExecute(RssFeed rssFeed) {
            super.onPostExecute(rssFeed);
            List<RssItem> rssList = rssFeed.getAllItem();
            for(RssItem item : rssList) {
                Log.i("得到的title",item.getTitle());
                newsLists.add(item);
            }

        }
    }


    private class NewsListAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return newsLists.size();
        }

        @Override
        public Object getItem(int position) {
            return newsLists.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder = null;
            if(convertView == null){
                LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
                viewHolder = new ViewHolder();
                convertView = inflater.inflate(R.layout.list_item_layout,null);
                viewHolder.tv_titile = (TextView) convertView.findViewById(R.id.tv_title);
                viewHolder.tv_updateTime = (TextView) convertView.findViewById(R.id.tv_update_time);
                convertView.setTag(viewHolder);

            } else{
                viewHolder = (ViewHolder) convertView.getTag();
            }
            RssItem rssItem = newsLists.get(position);
            viewHolder.tv_titile.setText(rssItem.getTitle());
            viewHolder.tv_updateTime.setText(rssItem.getPubDate());

            return convertView;
        }
    }
}











