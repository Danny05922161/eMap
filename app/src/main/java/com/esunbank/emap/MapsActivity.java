package com.esunbank.emap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.esunbank.emap.DTO.User;
import com.esunbank.emap.database.DataStore;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

public class MapsActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private DataStore dataStore = DataStore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
//        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
//                .findFragmentById(R.id.map);
//        assert mapFragment != null;
//        mapFragment.getMapAsync(this);
        FragmentManager fm = getSupportFragmentManager();
        final FragmentTransaction fragmentTransaction = fm.beginTransaction();
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        // 為navigatin_view設置點擊事件
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // 點選時收起選單
                drawerLayout.closeDrawer(GravityCompat.START);
                // 取得選項id
                int id = item.getItemId();
                // 依照id判斷點了哪個項目並做相應事件
                if (id == R.id.home) {
                    // 按下「首頁」要做的事
                    fragmentTransaction.replace(R.id.fragment_place,new MapsFragment());
                    return true;
                } else if (id == R.id.appoint) {
                    // 按下「預約」要做的事
                    return true;
                } else {
                    changeActivity();
                }
                return false;
            }
        });
        initButton();


    }

    private void changeActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void initButton() {
        ImageButton expandMenuButton = findViewById(R.id.expanded_menu);
        expandMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }



//    @Override
//    public void onMapReady(GoogleMap googleMap) {
//        FirebaseConnector firebaseConnector = new FirebaseConnector();
//        List<User> users = firebaseConnector.getConnect();
//        dataStore.setUsers(users);
//
//        mMap = googleMap;
//
//        // Add a marker in Sydney and move the camera
//        for(User user:users){
//            LatLng sydney = new LatLng(Double.parseDouble(user.getLat()), Double.parseDouble(user.getLng()));
//            mMap.addMarker(new MarkerOptions().position(sydney).title(user.getName()));
//        }
//        LatLng sydney = new LatLng(25.064500, 121.520398);
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
//        mMap.animateCamera(CameraUpdateFactory.zoomTo(14));
//        mMap.getUiSettings().setZoomControlsEnabled(true);
//    }


}
