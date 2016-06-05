package com.example.rajat.samplelist;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.Transition;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends Activity {

    private static final String TAG = "MainActivity";
    forecastListAdapter mForecastAdapter;
    ImageView drawableAnim;
    AnimationDrawable frameAnim;
    Context mContext;
    ImageView sharedImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//
//        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
//        getWindow().requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);
//        getWindow().setAllowEnterTransitionOverlap(false);
//        getWindow().setAllowReturnTransitionOverlap(false);
//        getWindow().setSharedElementExitTransition(exitTransition());
//        getWindow().setSharedElementReenterTransition(reenterTransition());




        setContentView(R.layout.activity_main);
        mContext = this;

        drawableAnim = (ImageView) findViewById(R.id.drawable_anim);
        drawableAnim.setBackgroundResource(R.drawable.anim);
        frameAnim = (AnimationDrawable) drawableAnim.getBackground();
        sharedImage = (ImageView) findViewById(R.id.shared_image);


        String[] forecastArray = {
                "Today - Sunny",
                "Wed - Sunny11",
                "Thu - Sunny12",
                "Fri - Sunny3",
                "Sat - Sunny4",
                "Sun - Sunny5"
        };

        List<String> weekForecast = new ArrayList<String>(Arrays.asList(forecastArray));

//        ArrayAdapter<String> mForecastAdapter = new ArrayAdapter<String>(this,
//                R.layout.list_item_forecast,
//                R.id.list_item_forecast_textView,
//                forecastArray);

        mForecastAdapter = new forecastListAdapter(this,
                R.layout.list_item_forecast,
                weekForecast);

        ListView forecastList = (ListView) findViewById(R.id.listview_forecast);
        forecastList.setAdapter(mForecastAdapter);




        forecastList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // Start new activity
                Log.d(TAG, "List Item clicked: " + i);

                ImageView clickedImg = (ImageView)view.findViewById(R.id.list_item_forecast_imageView);
                clickedImg.setTransitionName("sharedImageView");

                Intent intent = new Intent(mContext, Main3Activity.class);

                ActivityOptions options = ActivityOptions
                        .makeSceneTransitionAnimation((Activity)mContext, clickedImg, "sharedImageView");
                startActivity(intent, options.toBundle());
//                startActivity(intent);

            }
        });


        sharedImage.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                Intent intent = new Intent(mContext, Main3Activity.class);

                                                ActivityOptions options = ActivityOptions
                                                        .makeSceneTransitionAnimation((Activity)mContext, v, "sharedImageView");
                                                startActivity(intent, options.toBundle());
                                            }
                                       }
        );


    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if (true == hasFocus) {
            frameAnim.start();
        } else {
            frameAnim.stop();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_row:
                Log.d("MainActivity.java", "Add Row Clicked");
                mForecastAdapter.add("Hello");
                return true;
            case R.id.clear_all:
                mForecastAdapter.clear();
                break;
            case R.id.new_act:

                Intent intent = new Intent(mContext, Main2Activity.class);
                startActivity(intent);

            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }


    private Transition exitTransition() {
        ChangeBounds bounds = new ChangeBounds();
        bounds.setInterpolator(new BounceInterpolator());
        bounds.setDuration(2000);

        return bounds;
    }

    private Transition reenterTransition() {
        ChangeBounds bounds = new ChangeBounds();
        bounds.setInterpolator(new OvershootInterpolator());
        bounds.setDuration(2000);

        return bounds;
    }



}
