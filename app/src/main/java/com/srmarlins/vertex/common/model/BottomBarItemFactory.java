package com.srmarlins.vertex.common.model;

import android.support.annotation.DrawableRes;

import com.cleveroad.loopbar.adapter.ICategoryItem;
import com.srmarlins.vertex.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jared on 7/21/2016.
 */
public class BottomBarItemFactory {
    public static List<ICategoryItem> getCategoryItems() {
        List<ICategoryItem> items = new ArrayList<>();
        items.add(new CategoryItem(R.drawable.enls_ic_local_taxi, "Home"));
        items.add(new CategoryItem(R.drawable.enls_ic_account_balance, "balance"));
        items.add(new CategoryItem(R.drawable.enls_ic_alarm, "alarm"));
        items.add(new CategoryItem(R.drawable.enls_vector_brush_white_24dp, "brush"));
        items.add(new CategoryItem(R.drawable.enls_vector_camera_alt_white_24dp, "camera"));
        items.add(new CategoryItem(R.drawable.enls_vector_landscape_white_24dp, "rock"));
        items.add(new CategoryItem(R.drawable.enls_vector_palette_white_24dp, "palette"));
        items.add(new CategoryItem(R.drawable.enls_vector_moon_white_24dp, "moon"));
        return items;
    }

    public static class CategoryItem implements ICategoryItem {
        @DrawableRes
        int drawable;
        String name;

        public CategoryItem(@DrawableRes int drawable, String name) {
            this.drawable = drawable;
            this.name = name;
        }

        @Override
        public int getCategoryIconDrawable() {
            return drawable;
        }

        @Override
        public String getCategoryName() {
            return name;
        }
    }
}
