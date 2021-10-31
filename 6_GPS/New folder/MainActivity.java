package com.example.gps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView lat;
    TextView lon;
    Button click;
    LocationManager locMg;
    LocationListener lis;


    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lat = (TextView) findViewById(R.id.lat);
        lon = (TextView) findViewById(R.id.lon);
        click = (Button) findViewById(R.id.gps);
        locMg = (LocationManager) getSystemService(LOCATION_SERVICE);
        lis = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                lat.setText(""+location.getLatitude());
                lon.setText(""+location.getLongitude());
            }
        };
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        click.setOnClickListener(view -> {
            locMg.requestLocationUpdates("gps", 5000, 0, lis);
        });

    }
}

/*
Location listener and location manager
LocationManager locMag = getSystemService(LOCATION_SERVICE)
ActiviyCompat.requestPermission(MainActivity.this, new String[]{Manifest.permission.A}, 1);
ACCESS_FINE_LOCATION
ACCESS_COARSE_LOCATION
manager.requestLoationUpdates(
 */

//
//import androidx.appcompat.app.AppCompatActivity;
//import android.location.Geocoder;
//import android.location.Address;
//import android.content.Context;
//import java.util.List;
//import android.location.LocationManager;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.Button;
//
//public class MainActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        Button getloc = (Button) findViewById(R.id.btnGetLocation);
//        getloc.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                EditText place = (EditText)findViewById(R.id.address);
//                getLocationFromAddress(place.getText().toString());
//            }
//        });
//
//    }
//
//    public void getLocationFromAddress(String strAddress) {
//
//        Geocoder coder = new Geocoder(getApplicationContext());
//        List<Address> address;
//        TextView showloc = (TextView) findViewById(R.id.showLocation);
//        try {
//            address = coder.getFromLocationName(strAddress, 5);
//            if (address == null) {
//                System.out.println("Address not found.");
//            }
//            showloc.setText("Latitude : " + address.get(0).getLatitude() + "\nLongitude : " + address.get(0).getLongitude());
//
//        } catch (Exception ex) {
//
//            ex.printStackTrace();
//            showloc.setText("ERROR");
//        }
//    }
//}
//