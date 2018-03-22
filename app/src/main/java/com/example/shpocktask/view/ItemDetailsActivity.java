package com.example.shpocktask.view;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.shpocktask.R;
import com.example.shpocktask.databinding.ActivityItemDetailsBinding;
import com.example.shpocktask.viewModel.TitleViewModel;

public class ItemDetailsActivity extends AppCompatActivity {
    private static final String TAG = "ItemDetailsActivity";
    private static final String EXTRA_TITLE = "EXTRA_TITLE";
    private ActivityItemDetailsBinding activityItemDetailsBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
        activityItemDetailsBinding = DataBindingUtil.setContentView(this, R.layout.activity_item_details);
        getExtrasFromIntent();
    }

    public static Intent launchDetail(Context context, String title) {
        Intent intent = new Intent(context, ItemDetailsActivity.class);
        intent.putExtra(EXTRA_TITLE, title);
        return intent;
    }

    private void getExtrasFromIntent() {
        if(getIntent()!=null){
        String title = getIntent().getStringExtra(EXTRA_TITLE);
        TitleViewModel titleViewModel = new TitleViewModel(title);
        activityItemDetailsBinding.setTitleViewModel(titleViewModel);
        }

    }
}
