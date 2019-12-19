package com.esunbank.emap;

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
 */
public class RegisterFragment extends Fragment {
    private View view;
    private TextView messageBox;
    private TextView passwordTextView;
    private TextView rePasswordTextView;
    private TextView nameTextView;
    private TextView emailTextView;

    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_register, container, false);
        messageBox = (TextView) view.findViewById(R.id.tv_subtitle);
        nameTextView = (TextView) view.findViewById(R.id.et_name);
        emailTextView = (TextView) view.findViewById(R.id.et_email);
        passwordTextView = (TextView) view.findViewById(R.id.et_password);
        rePasswordTextView = (TextView) view.findViewById(R.id.et_repassword);
        Button button = (Button) view.findViewById(R.id.btn_register);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataStore dataStore = DataStore.getInstance();
                if(!passwordTextView.getText().toString().equals(rePasswordTextView.getText().toString())){
                    messageBox.setText("密碼不相符");
                }else if(checkSameAccount(dataStore, emailTextView.getText().toString())){
                    messageBox.setText("帳號重複");
                }else {
                    messageBox.setText("註冊成功");
                    LoginUser loginUser = new LoginUser();
                    loginUser.setAccount(nameTextView.getText().toString());
                    loginUser.setEmail(emailTextView.getText().toString());
                    loginUser.setPassword(passwordTextView.getText().toString());
                    dataStore.appendLoginUsers(loginUser);
                }
            }
        });


        return view;
    }
    private boolean checkSameAccount(DataStore dataStore,String account){
        for(LoginUser user :dataStore.getLoginUsers()){
            if(user.getAccount().equals(account)){
                return true;
            }
        }
        return false;
    }

}
