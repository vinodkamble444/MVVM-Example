package com.example.shpocktask.view;

import android.arch.lifecycle.LifecycleActivity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;

import com.example.shpocktask.R;
import com.example.shpocktask.databinding.ActivityItemBinding;
import com.example.shpocktask.model.entity.Item;
import com.example.shpocktask.viewModel.MainViewModel;

import java.util.List;


public class ItemActivity extends LifecycleActivity implements SwipeRefreshLayout.OnRefreshListener, LoadingCompletedListener {
    private static final String TAG = "ItemActivity";
    private MainViewModel viewModel;
    private ActivityItemBinding activityItemBinding;

    private ItemAdapter itemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityItemBinding = DataBindingUtil.setContentView(this, R.layout.activity_item);
        initData();
    }

    private void initData() {
        Log.d(TAG, "initData: ");
        itemAdapter = new ItemAdapter(this);
        activityItemBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        activityItemBinding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        activityItemBinding.recyclerView.setAdapter(itemAdapter);
        activityItemBinding.swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary, R.color.colorPrimaryDark);
        activityItemBinding.swipeRefreshLayout.setOnRefreshListener(this);
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        activityItemBinding.setViewModel(viewModel);
        viewModel.setLoadingCompletedListener(this);

        viewModel.getItemsList().observe(this, itemList -> {
            Log.d(TAG, "onChanged: " + itemList.size());
            itemAdapter.setData(itemList);
            itemAdapter.notifyDataSetChanged();

        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (viewModel.errorInfoLayoutVisibility.get() == View.VISIBLE)
            viewModel.refreshData();

    }

    @Override
    public void onRefresh() {
        itemAdapter.clearItems();
        viewModel.refreshData();
    }


    @Override
    public void onLoadingCompleted() {
        if (activityItemBinding.swipeRefreshLayout.isRefreshing()) {
            activityItemBinding.swipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewModel.setLoadingCompletedListener(null);
    }

}
