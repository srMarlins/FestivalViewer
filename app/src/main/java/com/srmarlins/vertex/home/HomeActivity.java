package com.srmarlins.vertex.home;

import android.graphics.Bitmap;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.srmarlins.vertex.R;

import butterknife.BindView;

public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;

    @BindView(R.id.toolbar_background)
    ImageView toolbarBackground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

    }

    protected void changeColor() {
        Glide.with(this)
                .load("yourUrl")
                .asBitmap()
                .into(new BitmapImageViewTarget(toolbarBackground) {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        super.onResourceReady(resource, glideAnimation);
                        Palette.from(resource).generate(new Palette.PaletteAsyncListener() {
                            @Override
                            public void onGenerated(Palette palette) {
                                collapsingToolbarLayout.setContentScrimColor(palette.getMutedColor(ContextCompat.getColor(HomeActivity.this, R.color.colorPrimary)));
                                collapsingToolbarLayout.setStatusBarScrimColor(palette.getDarkMutedColor(ContextCompat.getColor(HomeActivity.this, R.color.colorPrimaryDark)));
                            }
                        });
                    }
                });
    }
}
