package com.esunbank.emap;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.esunbank.emap.DTO.User;
import com.esunbank.emap.database.DataStore;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        FirebaseConnector firebaseConnector = new FirebaseConnector();
        List<User> users = firebaseConnector.getConnect();
        DataStore.getInstance().setUsers(users);

        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        for(User user:users){
            LatLng sydney = new LatLng(Double.parseDouble(user.getLat()), Double.parseDouble(user.getLng()));
            mMap.addMarker(new MarkerOptions().position(sydney).title(user.getName()));
        }
        LatLng sydney = new LatLng(25.064500, 121.520398);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(14));
        mMap.getUiSettings().setZoomControlsEnabled(true);
    }


}
