package com.example.f1driverapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManger;
    private DriverAdapter adapter;
    private List<Driver> drivers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drivers = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        layoutManger = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManger);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        RecyclerViewClickListener listener = new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                if(view.getId() == R.id.driverLastName) {
                    Toast.makeText(MainActivity.this, "You clicked on the title!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, drivers.get(position).toString(), Toast.LENGTH_SHORT).show();
                }
            }
        };
        adapter = new DriverAdapter(MainActivity.this, drivers, listener);
        recyclerView.setAdapter(adapter);


        //JSON request from server
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(DriverApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        DriverApi api = retrofit.create(DriverApi.class);

        Call<DriverResponse> call = api.getDrivers(2016);

        call.enqueue(new Callback<DriverResponse>() {
            @Override
            public void onResponse(Call<DriverResponse> call, Response<DriverResponse> response) {
                DriverResponse DriverResponse = response.body();
                Log.d(TAG, "onResponse: " + response.body().getDrivers().toString());
                drivers = DriverResponse.getDrivers();
                if(drivers.size() == 0) {
                    Toast.makeText(MainActivity.this, "No matches found!", Toast.LENGTH_SHORT).show();
                    //add in some fake drivers if it finds none for testing purposes
                    drivers.add(new Driver("hsd", "sd", "fjdf", "dffd", "dfdsf","fdfdfdd", "dsf"));
//                    drivers.add(new Driver("test2", "test description1", "https://i.imgur.com/62Pmk5i.png"));
//                    drivers.add(new Driver("test3", "test description1", "https://i.imgur.com/62Pmk5i.png"));
                }

                for(Driver r : drivers) {
                    Log.d(TAG, r.toString());
                }

                adapter.setDrivers(drivers);
                adapter.notifyDataSetChanged();
                //ListView listView = (ListView) findViewById(R.id.listView);
                //listView.setAdapter(new ArrayAdapter<Driver>(MainActivity.this,android.R.layout.simple_expandable_list_item_1,drivers));
            }

            @Override
            public void onFailure(Call<DriverResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }
}
