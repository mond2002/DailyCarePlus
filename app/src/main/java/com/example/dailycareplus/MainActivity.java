package com.example.dailycareplus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private EditText editText;
    private Button button;
    private Button btNext;



    class User_data
    {
        String profile_name;
        String location_temp;
        int user_number;
    }

    DatabaseReference mRootRef = FirebaseDatabase.getInstance("https://dailycareplus-5b84c-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference(); // 데이터베이스 주소 입력
    DatabaseReference conditionRef = mRootRef.child("test");

//    String[] datatest = new String[10000];
//    String

    @Override //액티비티 만들면서 xml 레이아웃 지정.
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        editText = (EditText) findViewById(R.id.editText);
        button = (Button) findViewById(R.id.button);


        btNext.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), DPassMainActivity.class);
            startActivity(intent);
        });


    }

    @Override
    protected void onStart() {
        super.onStart();

        conditionRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String text = dataSnapshot.getValue(String.class);
                textView.setText(text);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                conditionRef.setValue(editText.getText().toString());

            }
        });
    }
}