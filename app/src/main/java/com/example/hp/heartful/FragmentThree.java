package com.example.hp.heartful;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

import static com.crashlytics.android.beta.Beta.TAG;
import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by HP INDIA on 08-Apr-17.
 */
public class FragmentThree extends Fragment implements View.OnClickListener {

        private TwitterLoginButton TwitterloginButton;
        private EditText email_Id;
        private EditText password;
        private EditText User_Name;
        private Button Sign_Up;
        private TextView login_Text;
    private FirebaseAuth firebaseAuth;
        Intent intent;
      private ProgressDialog progressDialog;
    @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        CallbackManager callbackManager= CallbackManager.Factory.create();
        View view = inflater.inflate(R.layout.tab_three, container, false);
        firebaseAuth=FirebaseAuth.getInstance();
//        if(firebaseAuth.getCurrentUser()!=null){
//             directly start user profile activity
//          getActivity().finish();
//        startActivity(new Intent(getActivity(),userProfileActivity.class));
//        }
        progressDialog=new ProgressDialog(getActivity());
        email_Id=(EditText)view.findViewById(R.id.email_id);
        password=(EditText)view.findViewById(R.id.password);
        User_Name=(EditText)view.findViewById(R.id.User_name);
        Sign_Up=(Button)view.findViewById(R.id.sign_up);
        login_Text=(TextView)view.findViewById(R.id.login_text);
        Sign_Up.setOnClickListener(this);
        login_Text.setOnClickListener(this);
        LoginButton loginButton = (LoginButton) view.findViewById(R.id.login_button);
       loginButton.setBackgroundResource(R.drawable.fb_singup_button);
        loginButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_fb,0, 0, 0);
        loginButton.setPadding(70,5,5,10);
        loginButton.setReadPermissions("email");
        // If using in a fragment
        loginButton.setFragment(this);
        // Other app specific specialization
        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
            //    Log.v("FragmentThree","ab kya hota hai "+loginResult);
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
        twitterloginButton.setPadding(70,0,10,0);
        twitterloginButton.setText(" ");
        twitterloginButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_twitter,0, 0, 0);
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

    @Override
    public void onClick(View view) {
   if(view==Sign_Up){
       // user will register here
       registerUser();
   }
   if(view==login_Text){
   //    login user
       login();

   }
    }

    private void login(){
         Log.v("FragmentThree","dekhate hai yeh chal rha hai ki nhi");
       try{
        getActivity().finish();
        startActivity(new Intent(getActivity(), loginActivity.class));
    }catch (Exception e) {
           Log.e(TAG,Log.getStackTraceString(e));
       }

    }


    private  void registerUser(){
        String email=email_Id.getText().toString().trim();
        String pass_word=password.getText().toString().trim();
        String user_name=User_Name.getText().toString();
        if(TextUtils.isEmpty(user_name)){
            // email is empty
            Toast.makeText(getActivity(),"please selsect a name",Toast.LENGTH_SHORT).show();
            return;// to stop the function from executation.
        }


        if(TextUtils.isEmpty(email)){
            // email is empty
            Toast.makeText(getActivity(),"please enter email",Toast.LENGTH_SHORT).show();
            return;
            // to stop the function from executation.
        }
        if(TextUtils.isEmpty(pass_word)){
            // email is empty
            Toast.makeText(getActivity(),"please enter password",Toast.LENGTH_SHORT).show();
            return;
        }
        // here if everything ok the user will be register
        progressDialog.setMessage("Registering User,please wait...");
        progressDialog.show();
        firebaseAuth.createUserWithEmailAndPassword(email,pass_word)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>(){
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        //show user profile
                        Toast.makeText(getActivity(),"Registerd successfully",Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                        //  startActivity(new Intent(getActivity(),userProfileActivity.class));
                        intent = new Intent(getApplicationContext(),userProfileActivity.class);
                        intent.putExtra("EdiTtEXTvALUE", User_Name.getText().toString());
                        startActivity(intent);
                    }else {
                        Toast.makeText(getActivity(),"could not register, pls try again Error is"+ task.getException(),Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                    }
                });

    }
    }