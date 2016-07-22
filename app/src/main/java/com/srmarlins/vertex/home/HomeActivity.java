package com.srmarlins.vertex.home;

import android.os.Bundle;
import android.widget.Toast;

import com.srmarlins.vertex.R;
import com.srmarlins.vertex.common.BaseActivity;
import com.srmarlins.vertex.home.api.InstagramTagFactory;
import com.srmarlins.vertex.home.api.InstagramApi;
import com.srmarlins.vertex.home.api.model.InstagramMedia;

import java.util.List;

public class HomeActivity extends BaseActivity implements InstagramApi.InstagramPhotoListener{

    private InstagramApi vertexApi;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        vertexApi = InstagramApi.getInstance();
        vertexApi.getInstagramTagUrls(this, this, InstagramTagFactory.getTags());
    }

    @Override
    public void onPhotosReceived(List<InstagramMedia> photoUrl) {
        Toast.makeText(HomeActivity.this, "Photos received :" + photoUrl.size(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(Throwable t) {
        Toast.makeText(HomeActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
