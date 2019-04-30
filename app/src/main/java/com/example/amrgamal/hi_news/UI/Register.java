package com.example.amrgamal.hi_news.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.amrgamal.hi_news.Data.User;
import com.example.amrgamal.hi_news.MainActivity;
import com.example.amrgamal.hi_news.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    Spinner spinner;
    EditText userName,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        userName = findViewById(R.id.user_name);
        password = findViewById(R.id.password);
        spinner=findViewById(R.id.spinner);

        ArrayAdapter<CharSequence> arrayAdapter= ArrayAdapter.createFromResource(this,R.array.countries,android.R.layout.simple_list_item_1);
        spinner.setAdapter(arrayAdapter);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("Users");
        Button next= findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(userName.getText().toString())||TextUtils.isEmpty(password.getText().toString()))
                {
                    if (TextUtils.isEmpty(userName.getText().toString())){
                        userName.setError("can not be empty");
                    }else{
                        password.setError("can not be empty");
                    }
                }
                else {
                    databaseReference.child(userName.getText().toString()).setValue(new User(userName.getText().toString(),password.getText().toString(),spinner.getSelectedItem().toString()));
                    Intent intent = new Intent(Register.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
