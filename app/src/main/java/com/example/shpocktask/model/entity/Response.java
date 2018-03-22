package com.example.shpocktask.model.entity;

/**
 * Created by vinod on 3/21/2018.
 */

public class Response<T> {
    private T items;

    public void setItems(T items) {
        this.items = items;
    }

    public T getItems() {

        return items;
    }

}
