package com.srmarlins.vertex.home;

import android.os.Bundle;
import android.widget.Toast;

import com.srmarlins.vertex.R;
import com.srmarlins.vertex.common.BaseActivity;
import com.srmarlins.vertex.vertex.api.InstagramTagFactory;
import com.srmarlins.vertex.vertex.api.VertexApi;
import com.srmarlins.vertex.vertex.api.model.InstagramMedia;

import java.util.List;

public class HomeActivity extends BaseActivity implements VertexApi.InstagramPhotoListener{

    private VertexApi vertexApi;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        vertexApi = VertexApi.getInstance();
        vertexApi.getInstagramTagUrls(this, this, InstagramTagFactory.getTags());
    }

    @Override
    public void onPhotosReceived(List<InstagramMedia> photoUrl) {
        Toast.makeText(HomeActivity.this, "Photos received", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(Throwable t) {
        Toast.makeText(HomeActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
