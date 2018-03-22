package com.example.shpocktask.model.data;

import com.example.shpocktask.model.entity.Item;
import com.example.shpocktask.model.entity.Response;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;


import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by vinod on 3/21/2018.
 */

public class RetrofitHelper {
    private static final int DEFAULT_TIMEOUT = 10;
    private Retrofit retrofit;
    private ShpockService shpockService;
    OkHttpClient.Builder builder;

    private static class Singleton {
        private static final RetrofitHelper INSTANCE = new RetrofitHelper();
    }

    public static RetrofitHelper getInstance() {
        return Singleton.INSTANCE;
    }

    private RetrofitHelper() {
        builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        retrofit = new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(ShpockService.BASE_URL)
                .build();
        shpockService = retrofit.create(ShpockService.class);
    }

    public void getItems(Subscriber<List<Item>> subscriber) {
        shpockService.getItems()
                .map(new Func1<Response<List<Item>>, List<Item>>() {
                    @Override
                    public List<Item> call(Response<List<Item>> listResponse) {
                        List<Item> items=listResponse.getItems();
                        items.removeAll(Collections.singleton(null));
                        return items;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }



}
