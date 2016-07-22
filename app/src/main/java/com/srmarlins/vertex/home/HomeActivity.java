package com.srmarlins.vertex.home;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.poliveira.parallaxrecyclerview.HeaderLayoutManagerFixed;
import com.srmarlins.vertex.R;
import com.srmarlins.vertex.common.BaseActivity;
import com.srmarlins.vertex.home.instagram.InstagramTagFactory;
import com.srmarlins.vertex.home.instagram.InstagramApi;
import com.srmarlins.vertex.home.instagram.model.InstagramMedia;
import com.srmarlins.vertex.home.view.InstagramRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends BaseActivity implements InstagramApi.InstagramPhotoListener{

    @BindView(R.id.instagram_recycler_view)
    RecyclerView instagramView;

    private InstagramApi vertexApi;
    private InstagramRecyclerAdapter feedAdapter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        vertexApi = InstagramApi.getInstance();
        vertexApi.getInstagramTagUrls(this, this, InstagramTagFactory.getTags());
        feedAdapter = new InstagramRecyclerAdapter(new ArrayList<InstagramMedia>());
        instagramView.setAdapter(feedAdapter);
        HeaderLayoutManagerFixed layoutManagerFixed = new HeaderLayoutManagerFixed(this);
        instagramView.setLayoutManager(layoutManagerFixed);
        ImageView header = new ImageView(this);
        header.setImageResource(R.drawable.vertex_logo);
        header.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 50));
        header.setMaxHeight(50);
        layoutManagerFixed.setHeaderIncrementFixer(header);
        feedAdapter.setShouldClipView(false);
        feedAdapter.setParallaxHeader(header, instagramView);
    }

    @Override
    public void onPhotosReceived(List<InstagramMedia> photoUrl) {
        Toast.makeText(HomeActivity.this, "Photos received :" + photoUrl.size(), Toast.LENGTH_SHORT).show();
        feedAdapter.setData(photoUrl);
        feedAdapter.notifyDataSetChanged();
    }

    @Override
    public void onError(Throwable t) {
        Toast.makeText(HomeActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
