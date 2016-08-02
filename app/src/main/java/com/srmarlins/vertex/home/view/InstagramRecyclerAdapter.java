package com.srmarlins.vertex.home.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.srmarlins.vertex.R;
import com.srmarlins.vertex.home.instagram.model.InstagramMedia;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by JaredFowler on 7/22/2016.
 */
public class InstagramRecyclerAdapter extends RecyclerView.Adapter<InstagramRecyclerAdapter.MediaViewHolder> {

    private List<InstagramMedia> data;

    public InstagramRecyclerAdapter(List<InstagramMedia> data) {
        this.data = data;
    }

    @Override
    public MediaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return new MediaViewHolder(inflater.inflate(R.layout.feed_item, parent, false));
    }

    @Override
    public void onBindViewHolder(MediaViewHolder holder, int position) {
        holder.populate(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<InstagramMedia> data) {
        this.data = data;
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

        public void populate(final InstagramMedia media) {
            image.post(new Runnable() {
                @Override
                public void run() {
                    Glide.with(image.getContext()).load(media.getUrl()).into(image);
                    description.setText(media.getDescription());
                }
            });
        }
    }
}
