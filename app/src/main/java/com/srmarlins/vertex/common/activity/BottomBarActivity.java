package com.srmarlins.vertex.common.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.cleveroad.loopbar.adapter.SimpleCategoriesAdapter;
import com.cleveroad.loopbar.widget.LoopBarView;
import com.cleveroad.loopbar.widget.OnItemClickListener;
import com.srmarlins.vertex.R;
import com.srmarlins.vertex.common.adapter.BottomBarPagerAdapter;
import com.srmarlins.vertex.common.model.BottomBarItemFactory;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BottomBarActivity extends BaseActivity implements OnItemClickListener {


    @BindView(R.id.endlessView)
    LoopBarView bottomBar;

    @BindView(R.id.viewpager_content)
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_bar);
        ButterKnife.bind(this);
        initBottomBar();
        initViewPager();
    }

    public void initBottomBar() {
        SimpleCategoriesAdapter simpleCategoriesAdapter = new SimpleCategoriesAdapter(BottomBarItemFactory.getCategoryItems());
        bottomBar.setCategoriesAdapter(simpleCategoriesAdapter);
        bottomBar.addOnItemClickListener(this);
    }

    public void initViewPager() {
        BottomBarPagerAdapter bottomBarPagerAdapter = new BottomBarPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(bottomBarPagerAdapter);
    }

    @Override
    public void onItemClicked(int position) {
        if(position < 2) {
            viewPager.setCurrentItem(position);
        }
    }
}
