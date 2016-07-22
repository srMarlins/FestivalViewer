package com.srmarlins.vertex.home.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.poliveira.parallaxrecyclerview.ParallaxRecyclerAdapter;
import com.srmarlins.vertex.R;
import com.srmarlins.vertex.home.instagram.model.InstagramMedia;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by JaredFowler on 7/22/2016.
 */
public class InstagramRecyclerAdapter extends ParallaxRecyclerAdapter<InstagramMedia> {

    public InstagramRecyclerAdapter(List<InstagramMedia> data) {
        super(data);
    }

    @Override
    public void onBindViewHolderImpl(RecyclerView.ViewHolder viewHolder, ParallaxRecyclerAdapter<InstagramMedia> parallaxRecyclerAdapter, int i) {
        MediaViewHolder mediaViewHolder = (MediaViewHolder) viewHolder;
        mediaViewHolder.populate(getData().get(i));
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolderImpl(ViewGroup viewGroup, ParallaxRecyclerAdapter<InstagramMedia> parallaxRecyclerAdapter, int i) {
        LayoutInflater inflater = (LayoutInflater) viewGroup.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return new MediaViewHolder(inflater.inflate(R.layout.feed_item, viewGroup, false));
    }

    @Override
    public int getItemCountImpl(ParallaxRecyclerAdapter<InstagramMedia> parallaxRecyclerAdapter) {
        return getData().size();
    }

    public static class MediaViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.instagram_image)
        ImageView image;

        @BindView(R.id.description_text)
        TextView description;

        public MediaViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void populate(InstagramMedia media) {
            Glide.with(image.getContext()).load(media.getUrl()).into(image);
            description.setText(media.getDescription());
        }
    }
}
