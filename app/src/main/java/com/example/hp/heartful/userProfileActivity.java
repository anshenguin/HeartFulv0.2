package com.example.hp.heartful;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class userProfileActivity extends AppCompatActivity  {
    TextView user_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_layout);
        user_name=(TextView)findViewById(R.id.User_name);
        user_name.setText(getIntent().getStringExtra("EdiTtEXTvALUE"));

    }
    }

