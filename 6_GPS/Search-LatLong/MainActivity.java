package com.example.gps;

import androidx.appcompat.app.AppCompatActivity;

import android.location.Address;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.location.Geocoder;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextView placeLocation;
    Button getPlaceLocation;
    EditText placeName;
    double latitude, longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        placeLocation = findViewById(R.id.placeLoc);
        placeName = findViewById(R.id.placeName);
        getPlaceLocation = findViewById(R.id.getGeoLocButton);

        getPlaceLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Geocoder geoCoder = new Geocoder(getBaseContext(), Locale.getDefault());
                placeLocation.setText("");
                try {
                    List<Address> addressList;
                    addressList = geoCoder.getFromLocationName(placeName.getText().toString(), 5);
                    System.out.println("Size is: "+addressList.size());
                    if(addressList.size()>0) {
                        for(int i=0; i<addressList.size(); i++) {
                            latitude = addressList.get(i).getLatitude();
                            longitude = addressList.get(i).getLongitude();
                            placeLocation.append("\nLatitude: "+latitude+"\nLongitude: "+longitude+"\n");
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }
}