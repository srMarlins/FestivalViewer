package com.srmarlins.vertex.home;

import android.graphics.Bitmap;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.srmarlins.vertex.R;
import com.srmarlins.vertex.common.AppBarStateChangeListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;

    @BindView(R.id.toolbar_background)
    ImageView toolbarBackground;

    @BindView(R.id.toolbar_collapsed_logo)
    ImageView toolbarLogo;

    @BindView(R.id.appbar)
    AppBarLayout appBarLayout;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        appBarLayout.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                toolbar.setVisibility(state == State.EXPANDED ? View.GONE : View.VISIBLE);
                toolbarLogo.setVisibility(state != State.EXPANDED ? View.VISIBLE : View.GONE);
            }
        });
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appBarLayout.setExpanded(true);
            }
        });
        changeColor();
    }

    protected void changeColor() {
        Glide.with(this)
                .load(R.drawable.vertex_logo)
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
