package com.esunbank.emap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.esunbank.emap.DTO.LoginUser;
import com.esunbank.emap.database.DataStore;

import androidx.fragment.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 */public class LoginFragment extends Fragment {

    private View view;
    private Activity activity;


    public LoginFragment() {
        // Required empty public constructor
    }
    public LoginFragment(Activity activity) {
        this.activity = activity;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_login, container, false);
        final TextView eMailTextView = (TextView) view.findViewById(R.id.et_email);
        final TextView passWordTextView = (TextView) view.findViewById(R.id.et_password);
        Button loginButton = (Button) view.findViewById(R.id.btn_login);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(eMailTextView.getText());
                System.out.println(passWordTextView.getText());
                DataStore dataStore= DataStore.getInstance();
                for(LoginUser loginUser :dataStore.getLoginUsers()){
                    if(loginUser.getEmail().equals(eMailTextView.getText().toString())){
                        Intent intent = new Intent(activity, MapsActivity.class);
                        activity.startActivity(intent);
                        activity.finish();
                    }
                }
            }
        });
        return view;
    }


}
