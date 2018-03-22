package com.example.shpocktask.view;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.shpocktask.R;
import com.example.shpocktask.databinding.ItemBinding;
import com.example.shpocktask.model.entity.Item;
import com.example.shpocktask.viewModel.ItemViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vinod on 3/21/2018.
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.BindingHolder> {
    private List<Item> items;
    private static final String TAG = "ItemAdapter";
    private Context context;
    public ItemAdapter(Context context) {
        this.context=context;
        Log.d(TAG, "ItemAdapter: ");
        items = new ArrayList<>();
    }

    @Override
    public BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemBinding itemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item, parent, false);
        return new BindingHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(BindingHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: " + items.get(position));
        ItemViewModel itemViewModel = new ItemViewModel(items.get(position),context);
        holder.itemBinding.setViewModel(itemViewModel);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setData(List<Item> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public void clearItems() {
        items.clear();
        notifyDataSetChanged();
    }


    public static class BindingHolder extends RecyclerView.ViewHolder {
        private ItemBinding itemBinding;

        public BindingHolder(ItemBinding itemBinding) {
            super(itemBinding.cardView);
            this.itemBinding = itemBinding;
        }
    }
}
