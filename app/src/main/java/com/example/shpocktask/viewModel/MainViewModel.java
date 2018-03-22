package com.example.shpocktask.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;

import com.example.shpocktask.model.data.RetrofitHelper;
import com.example.shpocktask.model.entity.Item;
import com.example.shpocktask.view.LoadingCompletedListener;

import java.util.List;


import rx.Subscriber;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by vinod on 3/21/2018.
 */

public class MainViewModel extends AndroidViewModel {
    private static final String TAG = "MainViewModel";
    public ObservableField<Integer> contentViewVisibility;
    public ObservableField<Integer> progressBarVisibility;
    public ObservableField<Integer> errorInfoLayoutVisibility;
    public ObservableField<String> exception;
    private Subscriber<List<Item>> subscriber;
    private MutableLiveData<List<Item>> mItemList = new MutableLiveData<>();
    private CompositeSubscription compositeSubscription;
    private LoadingCompletedListener loadingCompletedListener;
    public MainViewModel(@NonNull Application application) {
        super(application);
        initData();
        getItems();

    }

    public LiveData<List<Item>> getItemsList() {
        if (mItemList == null) {
            mItemList = new MutableLiveData<>();
            getItems();
        }
        return mItemList;
    }


    private void getItems() {
        subscriber = new Subscriber<List<Item>>() {
            @Override
            public void onCompleted() {
                hideAll();
                contentViewVisibility.set(View.VISIBLE);
                if (loadingCompletedListener!=null)
                loadingCompletedListener.onLoadingCompleted();
            }

            @Override
            public void onError(Throwable e) {
                hideAll();
                errorInfoLayoutVisibility.set(View.VISIBLE);
                exception.set(e.getMessage());
                loadingCompletedListener.onLoadingCompleted();
            }

            @Override
            public void onNext(List<Item> item) {
                mItemList.setValue(item);
                Log.d(TAG, "onNext: " + item.size());
                Log.d(TAG, "onNext: " + Thread.currentThread().getName());
            }
        };
        RetrofitHelper.getInstance().getItems(subscriber);

        compositeSubscription.add(subscriber);


    }

    public void refreshData() {
        getItems();
    }

    private void initData() {
        contentViewVisibility = new ObservableField<>();
        progressBarVisibility = new ObservableField<>();
        errorInfoLayoutVisibility = new ObservableField<>();
        compositeSubscription=new CompositeSubscription();
        exception = new ObservableField<>();
        contentViewVisibility.set(View.GONE);
        errorInfoLayoutVisibility.set(View.GONE);
        progressBarVisibility.set(View.VISIBLE);
    }

    public void hideAll() {
        contentViewVisibility.set(View.GONE);
        errorInfoLayoutVisibility.set(View.GONE);
        progressBarVisibility.set(View.GONE);


    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d(TAG, "onCleared: ");
        if (compositeSubscription!=null)
            compositeSubscription.clear();
    }


    public void setLoadingCompletedListener(LoadingCompletedListener loadingCompletedListener) {
        this.loadingCompletedListener = loadingCompletedListener;
    }
}
