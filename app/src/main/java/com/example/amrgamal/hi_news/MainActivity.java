package com.example.amrgamal.hi_news;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.amrgamal.hi_news.Data.Articles;
import com.example.amrgamal.hi_news.Data.User;
import com.example.amrgamal.hi_news.Loader.ArticleLoader;
import com.example.amrgamal.hi_news.UI.NewsActivity;
import com.example.amrgamal.hi_news.UI.Register;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button register;
    Button login;
    EditText user_name,password;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user_name=findViewById(R.id.user_name);
        password=findViewById(R.id.password);
        //ads
        MobileAds.initialize(this,getString(R.string.ads_key));
        AdView mAdView = (AdView) findViewById(R.id.adViewLogin);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        final AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference().child(getString(R.string.users));

    register= findViewById(R.id.register);
    login=findViewById(R.id.login);
    //register
    register.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent= new Intent(MainActivity.this, Register.class);
            startActivity(intent);
        }
    });
    //login
    login.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            databaseReference.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                   User user=  dataSnapshot.getValue(User.class);
                   if ((user.getName().equals(user_name.getText().toString()))&&(user.getPass().equals(password.getText().toString())))
                   {

                       Intent intent = new Intent(MainActivity.this, NewsActivity.class);
                       News.country = user.getCountry();
                       startActivity(intent);
                   }


                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }
    });
    }

}
