package com.example.shpocktask.viewModel;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.shpocktask.model.entity.Item;
import com.example.shpocktask.view.ItemActivity;
import com.example.shpocktask.view.ItemDetailsActivity;

/**
 * Created by vinod on 3/21/2018.
 */

public class ItemViewModel extends BaseObservable {
    private Item item;
    private Context context;
    public ItemViewModel(Item item,Context context) {
        this.item = item;
        this.context=context;
    }

    private static final String TAG = "ItemViewModel";

    public String getTitle() {
        return item.getTitle();
    }

    public String getDescription() {
        return item.getDescription();
    }


    public String getImageUrl() {
        return item.getImage();
    }

    @BindingAdapter({"app:imageUrl"})
    public static void loadImage(ImageView imageView, String url) {
        Glide.with(imageView.getContext())
                .load(url)
                .into(imageView);

    }

    public void onClick(String title) {
        context.startActivity(ItemDetailsActivity.launchDetail(context, title));
        Log.d(TAG, "onClick: " + title);


    }


}

