package com.example.shpocktask.viewModel;

import android.databinding.BaseObservable;

/**
 * Created by 20080055 on 22-03-2018.
 */

public class TitleViewModel extends BaseObservable {
    private String title;

    public TitleViewModel(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
