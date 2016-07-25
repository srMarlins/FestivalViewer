package com.srmarlins.vertex.common.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.srmarlins.vertex.common.model.BottomBarItemFactory;
import com.srmarlins.vertex.home.fragment.FeedFragment;
import com.srmarlins.vertex.schedule.ScheduleFragment;

/**
 * Created by Jared on 7/23/2016.
 */
public class BottomBarPagerAdapter extends FragmentStatePagerAdapter {

    public static final int NUM_ITEMS = BottomBarItemFactory.getCategoryItems().size();

    public BottomBarPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public int getCount() {
        return 2;
    }

    // Returns the fragment to display for that page
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case BottomBarItemFactory.HOME_POS:
                return FeedFragment.newInstance();
            case BottomBarItemFactory.SCHEDULE_POS:
                return ScheduleFragment.newInstance();
            default:
                return null;
        }
    }}
