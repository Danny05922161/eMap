package com.esunbank.emap;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;

import com.esunbank.emap.DTO.Appointment;
import com.esunbank.emap.DTO.LoginUser;
import com.esunbank.emap.DTO.User;
import com.esunbank.emap.database.DataStore;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import androidx.fragment.app.Fragment;

public class MapsFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private View view;
    private GoogleMap mMap;
    private Button dateButton;
    private Button timeButton;
    private Button submitButton;
    private ImageButton searchButton;
    private TextView textDate;
    private TextView textTime;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    private EditText searchText;
    private TextView head;
    private TextView name;
    private TextView gender;
    private TextView age;
    private TextView contact;
    private TextView level;
    private TextView product;
    private String location;

    private List<Marker> markerList = new ArrayList<Marker>();
    private BottomSheetBehavior bottomSheetBehavior;
    private DataStore dataStore = DataStore.getInstance();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_map, container, false);
        bottomSheetBehavior = BottomSheetBehavior.from(view.findViewById(R.id.bottomSheetLayout));
        dateButton = (Button) view.findViewById(R.id.date_button);
        timeButton = (Button) view.findViewById(R.id.time_button);
        textDate = (TextView) view.findViewById(R.id.date_text);
        textTime = (TextView) view.findViewById(R.id.time_text);
        submitButton = (Button) view.findViewById(R.id.submit);
        searchButton = (ImageButton) view.findViewById(R.id.search_button);
        searchText = (EditText) view.findViewById(R.id.search_text);
        head = (TextView)view.findViewById(R.id.bottomSheetHeading);
        name = (TextView)view.findViewById(R.id.userName);
        gender = (TextView)view.findViewById(R.id.userGender);
        age = (TextView)view.findViewById(R.id.userAge);
        contact = (TextView)view.findViewById(R.id.userContact);
        level = (TextView)view.findViewById(R.id.userLevel);
        product = (TextView)view.findViewById(R.id.userProduct);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<User> userInfo = dataStore.getUsers();
                for(User tarUser:userInfo) {
                    if(searchText.getText().toString().equals(tarUser.getName())) {
                        name.setText("顧客姓名：" + tarUser.getName());
                        gender.setText("性別：" + tarUser.getGender());
                        age.setText("年齡：" + tarUser.getAge());
                        contact.setText("聯絡資訊：" + tarUser.getContact());
                        level.setText("會員等級：" + tarUser.getLevel());
                        product.setText("往來產品：" + tarUser.getProduct());

                        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    }
                }
            }
        });

        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDate(v);
            }
        });
        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTime(v);
            }
        });
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Appointment appointment = new Appointment();
                appointment.setMember(name.getText().toString());
                appointment.setDate(textDate.getText().toString());
                appointment.setTime(textTime.getText().toString());
                appointment.setLocation(location);
                dataStore.appendAppointmentList(appointment);
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
            }
        });

        GregorianCalendar calendar = new GregorianCalendar();

        // 實作DatePickerDialog的onDateSet方法，設定日期後將所設定的日期show在textDate上
        datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            //將設定的日期顯示出來
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                monthOfYear=monthOfYear+1;
                textDate.setText(year + "/" + monthOfYear + "/" + dayOfMonth);
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        // 實作TimePickerDialog的onTimeSet方法，設定時間後將所設定的時間show在textTime上
        timePickerDialog = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
            //將時間轉為12小時製顯示出來
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                textTime.setText((hourOfDay > 12 ? hourOfDay - 12 : hourOfDay)
                        + ":" + minute + " " + (hourOfDay > 12 ? "PM" : "AM"));
            }
        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(calendar.MINUTE),
                false);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);
        return view;
    }

    // setDate Button onClick 顯示日期設定視窗
    private void setDate(View v) {
        datePickerDialog.show();
    }

    // setTime Button onClick 顯示時間設定視窗
    private void setTime(View v) {
        timePickerDialog.show();
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
            Marker marker = mMap.addMarker(new MarkerOptions().position(sydney).title(user.getName()));
            marker.showInfoWindow();
            markerList.add(marker);
        }
        LatLng sydney = new LatLng(25.064500, 121.520398);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(14));
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.setOnMarkerClickListener(this);
    }


    @Override
    public boolean onMarkerClick (Marker marker){
        for(Marker tarMarker :markerList) {
            if (marker.getPosition().toString().equals(tarMarker.getPosition().toString())) {
                //handle click here

                List<User> userInfo = dataStore.getUsers();
                location = marker.getPosition().toString();

                head.setText("顧客資訊");
                for(User tarUser:userInfo) {
                    if(tarMarker.getTitle().equals(tarUser.getName())) {
                        name.setText("顧客姓名：" + tarUser.getName());
                        gender.setText("性別：" + tarUser.getGender());
                        age.setText("年齡：" + tarUser.getAge());
                        contact.setText("聯絡資訊：" + tarUser.getContact());
                        level.setText("會員等級：" + tarUser.getLevel());
                        product.setText("往來產品：" + tarUser.getProduct());

                        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
