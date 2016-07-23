package com.srmarlins.vertex.home;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.srmarlins.vertex.R;
import com.srmarlins.vertex.common.BaseActivity;
import com.srmarlins.vertex.home.instagram.InstagramApi;
import com.srmarlins.vertex.home.instagram.InstagramTagFactory;
import com.srmarlins.vertex.home.instagram.model.InstagramMedia;
import com.srmarlins.vertex.home.view.InstagramRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends BaseActivity implements InstagramApi.InstagramPhotoListener {

    @BindView(R.id.instagram_recycler_view)
    RecyclerView instagramView;

    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefreshLayout;

    private InstagramApi vertexApi;
    private InstagramRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        vertexApi = InstagramApi.getInstance();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                vertexApi.getInstagramTagUrls(HomeActivity.this, HomeActivity.this, InstagramTagFactory.getTags());
            }
        });
        instagramView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new InstagramRecyclerAdapter(new ArrayList<InstagramMedia>());
        instagramView.setAdapter(adapter);
        setRefreshing(true);
        vertexApi.getInstagramTagUrls(HomeActivity.this, HomeActivity.this, InstagramTagFactory.getTags());
    }

    @Override
    public void onPhotosReceived(List<InstagramMedia> photoUrl) {
        Toast.makeText(HomeActivity.this, "Photos received :" + photoUrl.size(), Toast.LENGTH_SHORT).show();
        setRefreshing(false);
        adapter.setData(photoUrl);
        instagramView.post(new Runnable() {
            @Override
            public void run() {
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onError(Throwable t) {
        Toast.makeText(HomeActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
        setRefreshing(false);
    }

    public void setRefreshing(final boolean refreshing) {
        swipeRefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(refreshing);
            }
        }, 50);
    }
}
