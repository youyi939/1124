package com.example.a1124;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NewsFragment extends Fragment {


    public NewsFragment() {

    }


    private ListViewAdapter adapter;
    private LoadMoreListView listView;
    private List<News> newsList = new ArrayList<>();
    private OkHttpClient okHttpClient;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        listView = view.findViewById(R.id.listView);



        okHttpClient = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(20,TimeUnit.SECONDS)
                .build();
        String url = "https://api.myjson.com/bins/wsoyq";
        getJson(url);
        listView.setOnLoadMoreListener(new LoadMoreListView.OnLoadMoreListener() {
            @Override
            public void onloadMore() {
                loadMore();
            }
        });


        return view;
    }

    private void getJson(String url){
        Request request = new Request.Builder()
                .url(url)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                Log.d("chen",json);
                showInListview(json);
            }
        });
    }               //okhttp

    private void showInListview(final String responce){
        try {
            JSONArray jas = new JSONArray(responce);
            for (int i = 0; i < jas.length(); i++) {
                JSONObject jsonObject = jas.getJSONObject(i);
                String title = jsonObject.getString("name");
                String message = jsonObject.getString("message");
                final String imageJson = jsonObject.getString("imageUrl");

                newsList.add(new News(title, message, imageJson));

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter = new ListViewAdapter(getContext(), R.layout.item, newsList);
                        adapter.notifyDataSetChanged();
                        listView.setAdapter(adapter);
                    }
                });
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void loadMore() {
        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                String url2 = "https://api.myjson.com/bins/vd2xu";
                getJson(url2);

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                        listView.setLoadCompleted();
                    }
                });
            }
        }.start();
    }
}
