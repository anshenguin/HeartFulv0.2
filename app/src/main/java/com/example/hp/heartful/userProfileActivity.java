package com.example.hp.heartful;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class userProfileActivity extends AppCompatActivity implements View.OnClickListener {
    TextView user_name;
    private ImageButton log_out;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_layout);
        user_name=(TextView)findViewById(R.id.User_name);
        user_name.setText(getIntent().getStringExtra("EdiTtEXTvALUE"));
        firebaseAuth=FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()==null){
            finish();
            startActivity(new Intent(userProfileActivity.this,loginActivity.class));
        }
       log_out=(ImageButton)findViewById(R.id.log_out);
        log_out.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
if (view==log_out){
    firebaseAuth.signOut();
}
    }
}

