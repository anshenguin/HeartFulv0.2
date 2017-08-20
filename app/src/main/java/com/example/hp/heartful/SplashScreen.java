package com.example.hp.heartful;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import io.fabric.sdk.android.Fabric;
public class SplashScreen extends AppCompatActivity {
    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "yT5qnTGcTxY4zuDN92M5MUSn5";
    private static final String TWITTER_SECRET = "5vLepAbX0urqjt3hpSAJWVvpycOOGXhmfsS1We505RCooYGzSL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
        finish();
    }
}