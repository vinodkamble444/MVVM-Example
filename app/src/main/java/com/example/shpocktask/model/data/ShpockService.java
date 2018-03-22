package com.example.shpocktask.model.data;

import com.example.shpocktask.model.entity.Item;
import com.example.shpocktask.model.entity.Response;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;


/**
 * Created by vinod on 3/21/2018.
 */

public interface ShpockService {
    String BASE_URL = "http://nsode.at/";

    @GET("items")
    Observable<Response<List<Item>>> getItems();

}
