package com.example.explorer.en.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.example.explorer.en.R;
import com.example.explorer.en.db.WeatherDB;
import com.example.explorer.en.model.City;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;


/**
 * Created by explorer on 16-3-17.
 */
public class ChooseAreaActivity extends AppCompatActivity {
    private EditText editText;
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private WeatherDB weatherDB;
    private List<String> dataList = new ArrayList<String>();

    private List<City> cityList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_area);

        listView = (ListView) findViewById(R.id.list_view);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dataList);
        listView.setAdapter(adapter);

        editText = (EditText) findViewById(R.id.search_edit);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                queryCites();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String cityName = dataList.get(position);
                for(City city : cityList) {
                    if(city.getCityName().equals(cityName)) {
                        Intent intent = new Intent();
                        Bundle bundle = city.getBundle();
                        intent.putExtra("city",bundle);
                        setResult(RESULT_OK, intent);
                        finish();
                        break;
                    }
                }
            }
        });

        weatherDB = WeatherDB.getInstance(this);
        cityList = weatherDB.loadCities();
    }


    private void queryCites() {
        String inputText = editText.getText().toString();
        Pattern p = Pattern.compile("^"+inputText);
        dataList.clear();
        for (City city : cityList) {
            if (p.matcher(city.getCityName()).matches()) {
                dataList.add(city.getCityName());
            }
        }

        adapter.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_CANCELED);
        finish();
    }

}
