package com.srmarlins.vertex.common;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.cleveroad.loopbar.adapter.SimpleCategoriesAdapter;
import com.cleveroad.loopbar.widget.LoopBarView;
import com.srmarlins.vertex.R;
import com.srmarlins.vertex.common.model.BottomBarItemFactory;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BaseActivity extends AppCompatActivity {

    @BindView(R.id.content_container)
    FrameLayout container;

    @BindView(R.id.endlessView)
    LoopBarView bottomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(R.layout.activity_base);
        ButterKnife.bind(this);
        View view = LayoutInflater.from(this).inflate(layoutResID, null);
        container.addView(view);
        initBottomBar();
    }

    public void initBottomBar() {
        SimpleCategoriesAdapter simpleCategoriesAdapter = new SimpleCategoriesAdapter(BottomBarItemFactory.getCategoryItems());
        bottomBar.setCategoriesAdapter(simpleCategoriesAdapter);
    }
}
