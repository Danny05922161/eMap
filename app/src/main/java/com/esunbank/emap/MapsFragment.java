package com.esunbank.emap;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.esunbank.emap.DTO.LoginUser;
import com.esunbank.emap.DTO.User;
import com.esunbank.emap.database.DataStore;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import androidx.fragment.app.Fragment;

public class MapsFragment extends Fragment implements OnMapReadyCallback {
    private View view;
    private GoogleMap mMap;
    private DataStore dataStore = DataStore.getInstance();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_map, container, false);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);
        return view;
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        FirebaseConnector firebaseConnector = new FirebaseConnector();
        List<User> users = firebaseConnector.getConnect();
        dataStore.setUsers(users);

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
