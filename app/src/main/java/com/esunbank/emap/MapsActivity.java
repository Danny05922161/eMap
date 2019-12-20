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
    private FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        getSupportActionBar().hide();
        fm = getSupportFragmentManager();

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
                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_place,new MapsFragment());
                    fragmentTransaction.commit();
                    return true;
                } else if (id == R.id.appoint) {
                    // 按下「預約」要做的事
                    System.out.println("Change!");
                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_place,new AppointFragment());
                    fragmentTransaction.commit();
                    return true;
                } else {
                    changeActivity(MainActivity.class);
                }
                return false;
            }
        });
        initButton();


    }

    private void changeActivity(Class classObject){
        Intent intent = new Intent(this, classObject);
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


}
