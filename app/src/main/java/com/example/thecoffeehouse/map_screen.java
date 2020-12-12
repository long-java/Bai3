package com.example.thecoffeehouse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class map_screen extends AppCompatActivity implements OnMapReadyCallback {
GoogleMap map;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_screen);

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.mymap);
        mapFragment.getMapAsync(this);




        ///Bottom
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.store);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.news:
                        startActivity(new Intent(getApplicationContext(),Home.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.delivery:
                        startActivity(new Intent(getApplicationContext(),orderScreen.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.store:
//                        startActivity(new Intent(getApplicationContext(),store_screen.class));
//                        overridePendingTransition(0,0);
                        return true;
                    case R.id.account:
                        startActivity(new Intent(getApplicationContext(),account_screen.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }
    ///////////////////////////////////////

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        LatLng address1 = new LatLng(16.077453, 108.212360);
        LatLng address2 = new LatLng(16.072809, 108.210079);//
        LatLng address3 = new LatLng(16.070092, 108.213088);//
        LatLng address4 = new LatLng(16.073721, 108.214708);//
        LatLng address5 = new LatLng(16.075505, 108.211371);//
        LatLng address6 = new LatLng(16.069711, 108.210920);//
        LatLng address7 = new LatLng(16.068252, 16.068252);//
        LatLng address8 = new LatLng(16.071077, 108.200727);//
        LatLng address9 = new LatLng(16.067073, 108.203481);//
        LatLng address10 = new LatLng(16.071562, 108.222754);


        map.addMarker(new MarkerOptions().position(address1).title("TheCoffeeHouse"));
        map.addMarker(new MarkerOptions().position(address2).title("TheCoffeeHouse"));
        map.addMarker(new MarkerOptions().position(address3).title("TheCoffeeHouse"));
        map.addMarker(new MarkerOptions().position(address4).title("TheCoffeeHouse"));
        map.addMarker(new MarkerOptions().position(address5).title("TheCoffeeHouse"));
        map.addMarker(new MarkerOptions().position(address6).title("TheCoffeeHouse"));
        map.addMarker(new MarkerOptions().position(address7).title("TheCoffeeHouse"));
        map.addMarker(new MarkerOptions().position(address8).title("TheCoffeeHouse"));
        map.addMarker(new MarkerOptions().position(address9).title("TheCoffeeHouse"));
        map.addMarker(new MarkerOptions().position(address10).title("TheCoffeeHouse"));

        map.moveCamera(CameraUpdateFactory.newLatLng(address1));
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}