package com.esunbank.emap;

import com.esunbank.emap.DTO.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class FirebaseConnector {
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private String json = "[{\"name\":\"曾雅慈\",\"gender\":\"女\",\"age\":32,\"contact\":0928640437,\"level\":\"新富\",\"product\":\"保險\",\"lat\":\"25.049514\",\"lng\" : \"121.580459\"},\n" +
            "{\"name\":\"洪秀玲\",\"gender\":\"女\",\"age\":55,\"contact\":0936341282,\"level\":\"菁英\",\"product\":\"基金、ETF\",\"lat\":\"25.030448\",\"lng\" : \"121.555048\"},\n" +
            "{\"name\":\"馬淑珍\",\"gender\":\"女\",\"age\":47,\"contact\":0932868333,\"level\":\"菁英\",\"product\":\"黃金存摺、外幣\",\"lat\":\"25.039814\",\"lng\" : \"121.519533\"},\n" +
            "{\"name\":\"嚴惠宣\",\"gender\":\"女\",\"age\":64,\"contact\":0988212559,\"level\":\"菁英\",\"product\":\"基金、房貸\",\"lat\":\"25.009826\",\"lng\" : \"121.455240\"},\n" +
            "{\"name\":\"林孝真\",\"gender\":\"女\",\"age\":53,\"contact\":0915091517,\"level\":\"登峰\",\"product\":\"黃金存摺、組合式商品\",\"lat\":\"25.082552\",\"lng\" : \"121.558026\"},\n" +
            "{\"name\":\"吳碧婷\",\"gender\":\"女\",\"age\":42,\"contact\":0958031169,\"level\":\"登峰\",\"product\":\"基金、外幣\",\"lat\":\"24.977110\",\"lng\" : \"121.546163\"},\n" +
            "{\"name\":\"蘇碧容\",\"gender\":\"女\",\"age\":58,\"contact\":0939827695,\"level\":\"登峰\",\"product\":\"基金、保險\",\"lat\":\"25.059380\",\"lng\" : \"121.513858\"},\n" +
            "{\"name\":\"簡婉文\",\"gender\":\"女\",\"age\":67,\"contact\":0913365545,\"level\":\"極致\",\"product\":\"保險、外幣\",\"lat\":\"25.034785\",\"lng\" : \"121.567794\"},\n" +
            "{\"name\":\"黃秀妍\",\"gender\":\"女\",\"age\":62,\"contact\":0961117820,\"level\":\"極致\",\"product\":\"ETF、組合式商品\",\"lat\":\"25.035602\",\"lng\" : \"121.565970\"},\n" +
            "{\"name\":\"游進祥\",\"gender\":\"男\",\"age\":28,\"contact\":0917608719,\"level\":\"新富\",\"product\":\"基金\",\"lat\":\"25.081318\",\"lng\" : \"121.555518\"},\n" +
            "{\"name\":\"李佳進\",\"gender\":\"男\",\"age\":40,\"contact\":0938216037,\"level\":\"新富\",\"product\":\"基金、保險\",\"lat\":\"25.065506\",\"lng\" : \"121.533395\"},\n" +
            "{\"name\":\"吳東彥\",\"gender\":\"男\",\"age\":32,\"contact\":0933439590,\"level\":\"新富\",\"product\":\"ETF、外幣\",\"lat\":\"25.037796\",\"lng\" : \"121.563796\"},\n" +
            "{\"name\":\"陳威翔\",\"gender\":\"男\",\"age\":55,\"contact\":0929752548,\"level\":\"菁英\",\"product\":\"基金、保險\",\"lat\":\"24.977110\",\"lng\" : \"121.546163\"},\n" +
            "{\"name\":\"林清鴻\",\"gender\":\"男\",\"age\":51,\"contact\":0913128879,\"level\":\"菁英\",\"product\":\"保險\",\"lat\":\"25.037985\",\"lng\" : \"121.506171\"},\n" +
            "{\"name\":\"周朝宇\",\"gender\":\"男\",\"age\":47,\"contact\":0935093929,\"level\":\"菁英\",\"product\":\"基金、ETF\",\"lat\":\"25.014800\",\"lng\" : \"121.464435\"},\n" +
            "{\"name\":\"董繼廷\",\"gender\":\"男\",\"age\":63,\"contact\":0968835247,\"level\":\"菁英\",\"product\":\"基金、房貸、黃金存摺\",\"lat\":\"25.034785\",\"lng\" : \"121.567794\"},\n" +
            "{\"name\":\"施昱業\",\"gender\":\"男\",\"age\":60,\"contact\":0914327513,\"level\":\"登峰\",\"product\":\"基金\",\"lat\":\"25.026108\",\"lng\" : \"121.543704\"},\n" +
            "{\"name\":\"周碩博\",\"gender\":\"男\",\"age\":59,\"contact\":0986297720,\"level\":\"登峰\",\"product\":\"保險、ETF\",\"lat\":\"25.014689\",\"lng\" : \"121.463798\"},\n" +
            "{\"name\":\"王中傑\",\"gender\":\"男\",\"age\":69,\"contact\":0963264727,\"level\":\"極致\",\"product\":\"基金、外幣\",\"lat\":\"25.040935\",\"lng\" : \"121.564849\"}]\n" +
            "\n";

    public List<User> getConnect(){
//        database = FirebaseDatabase.getInstance();
//        myRef = database.getReference("member");

//        initData();
        Gson gson = new Gson();
        // Read from the database
//        myRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                // This method is called once with the initial value and again
//                // whenever data at this location is updated.
//                for(DataSnapshot childDataSnapshot : dataSnapshot.getChildren()){
//                    for(DataSnapshot grandChildDataSnapshot : childDataSnapshot.getChildren()){
////                        User user = grandChildDataSnapshot.getValue(User.class);
////                        System.out.println(grandChildDataSnapshot.getValue().toString());
//                    }
//                }
//            }

//            @Override
//            public void onCancelled(DatabaseError error) {
//                // Failed to read value
//            }
//        });

        List<User> arr = gson.fromJson(json, new TypeToken<List<User>>() {}.getType());
        return arr;
    }

    private void initData(){
        Gson gson = new Gson();

        List<User> arr = gson.fromJson(json, new TypeToken<List<User>>() {}.getType());

        myRef.child("member_list").setValue(arr);
    }

}
