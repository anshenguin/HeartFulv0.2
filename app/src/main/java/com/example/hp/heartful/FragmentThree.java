package com.example.hp.heartful;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

/**
 * Created by HP INDIA on 08-Apr-17.
 */
public class FragmentThree extends Fragment {

    private TwitterLoginButton TwitterloginButton;
    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        CallbackManager callbackManager= CallbackManager.Factory.create();
        View view = inflater.inflate(R.layout.tab_three, container, false);
    LoginButton loginButton = (LoginButton) view.findViewById(R.id.login_button);
        loginButton.setBackgroundResource(R.drawable.fb_singup_button);
        loginButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_fb, 0, 0, 0);
        loginButton.setPadding(70,15,10,15);
        loginButton.setReadPermissions("email");
        // If using in a fragment
        loginButton.setFragment(this);
        // Other app specific specialization

        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                // App code
            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
            }
        });
        TwitterLoginButton twitterloginButton = (TwitterLoginButton)view. findViewById(R.id.twitter_login);
        twitterloginButton.setBackgroundResource(R.drawable.twitter_sign_up_button);
        twitterloginButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_twitter, 0, 0, 0);
        twitterloginButton.setPadding(80,15,10,15);
        twitterloginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {

            }
            @Override

            public void failure(TwitterException exception) {
            }
        });
        return view;
    }

    @Override
    public void setMenuVisibility(final boolean visible) {
        if (visible) {
            FragmentManager fm = getFragmentManager();
            login_dialogbox dialogFragment = new login_dialogbox ();
            dialogFragment.show(fm,"LoginPopup");
        }
        super.setMenuVisibility(visible);
    }
    }