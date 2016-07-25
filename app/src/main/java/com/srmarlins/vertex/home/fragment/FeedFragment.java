package com.srmarlins.vertex.home.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.srmarlins.vertex.R;
import com.srmarlins.vertex.home.instagram.InstagramApi;
import com.srmarlins.vertex.home.instagram.InstagramTagFactory;
import com.srmarlins.vertex.home.instagram.model.InstagramMedia;
import com.srmarlins.vertex.home.view.InstagramRecyclerAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FeedFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FeedFragment extends Fragment implements InstagramApi.InstagramPhotoListener {

    private static final String DATA = "data";

    @BindView(R.id.instagram_recycler_view)
    RecyclerView instagramView;

    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefreshLayout;


    private InstagramRecyclerAdapter adapter;
    private ArrayList<InstagramMedia> photoList;
    private InstagramApi instagramApi;

    public FeedFragment() {
        // Required empty public constructor
    }

    public static FeedFragment newInstance(ArrayList<InstagramMedia> data) {
        FeedFragment fragment = new FeedFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(DATA, data);
        fragment.setArguments(args);
        return fragment;
    }

    public static FeedFragment newInstance() {
        return new FeedFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            photoList = getArguments().getParcelableArrayList(DATA);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feed, container, false);
        ButterKnife.bind(this, view);
        instagramApi = InstagramApi.getInstance();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                instagramApi.getInstagramTagUrls(getContext(), FeedFragment.this, InstagramTagFactory.getTags());
            }
        });
        instagramView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new InstagramRecyclerAdapter(new ArrayList<InstagramMedia>());
        instagramView.setAdapter(adapter);
        if (photoList != null) {
            adapter.setData(photoList);
            adapter.notifyDataSetChanged();
        } else {
            swipeRefreshLayout.setRefreshing(true);
            instagramApi.getInstagramTagUrls(getContext(), this, InstagramTagFactory.getTags());
        }
        return view;
    }

    @Override
    public void onPhotosReceived(ArrayList<InstagramMedia> photoUrl) {
        Toast.makeText(getContext(), "Photos received :" + photoUrl.size(), Toast.LENGTH_SHORT).show();
        setRefreshing(false);
        updateData(photoUrl);
    }

    @Override
    public void onError(Throwable t) {
        Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
        setRefreshing(false);
    }

    public void updateData(ArrayList<InstagramMedia> data) {
        this.photoList = data;
        adapter.setData(data);
        instagramView.post(new Runnable() {
            @Override
            public void run() {
                adapter.notifyDataSetChanged();
            }
        });
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
