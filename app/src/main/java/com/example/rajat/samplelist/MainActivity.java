package com.example.rajat.samplelist;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    forecastListAdapter mForecastAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] forecastArray = {
                "Today - Sunny",
                "Wed - Sunny11",
                "Thu - Sunny12",
                "Fri - Sunny3",
                "Sat - Sunny4",
                "Sun - Sunny5",
                "Mon - Sunny6",
                "Tomorrow - Windy",
                "Wed - Sunny1",
                "Thu - Sunny2",
                "Fri - Sunny3",
                "Sat - Sunny4",
                "Sun - Sunny5",
                "Mon - Sunny6"
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
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
