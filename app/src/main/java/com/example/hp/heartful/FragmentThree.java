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
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;
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

        private EditText email_Id;
        private EditText password;
        private SignInButton mgoogleSign;
        private FirebaseAuth mAuth;
        private static final int RC_SIGN_IN=1;
        private GoogleApiClient mGoogleApiClient;
        private EditText User_Name;
        private Button Sign_Up;
        private TextView login_Text;
        private FirebaseAuth.AuthStateListener mAuthStateListener;
        private ProgressDialog progress;
        private FirebaseAuth firebaseAuth;
        private LoginButton mFbLogin;
        CallbackManager callbackManager;
        private TwitterLoginButton twitterloginButton;
        Intent intent;
        private ProgressDialog progressDialog;
    @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tab_three, container, false);
        firebaseAuth=FirebaseAuth.getInstance();
        FacebookSdk.sdkInitialize(getActivity().getApplicationContext());
        callbackManager= CallbackManager.Factory.create();
//        if(firebaseAuth.getCurrentUser()!=null){
//             directly start user profile activity
//          getActivity().finish();
//        startActivity(new Intent(getActivity(),userProfileActivity.class));
//        }
        progressDialog=new ProgressDialog(getActivity());
        progress=new ProgressDialog(getActivity());
        email_Id=(EditText)view.findViewById(R.id.email_id);
        progress.setMessage("Connecting to google account,please wait...");
        password=(EditText)view.findViewById(R.id.password);
        User_Name=(EditText)view.findViewById(R.id.User_name);
        Sign_Up=(Button)view.findViewById(R.id.sign_up);
        login_Text=(TextView)view.findViewById(R.id.login_text);
        Sign_Up.setOnClickListener(this);
        login_Text.setOnClickListener(this);
        mFbLogin=(LoginButton)view.findViewById(R.id.fb_login);
        mAuth=FirebaseAuth.getInstance();
//        mAuthStateListener= new FirebaseAuth.AuthStateListener() {
//            @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//                if (firebaseAuth.getCurrentUser()!=null){
//                getActivity().finish();
//                    startActivity(new Intent(getActivity(),userProfileActivity.class));
//                }
//            }
//        };
        mFbLogin.setFragment(this);
        mFbLogin.setReadPermissions("public_profile", "email", "user_friends");
        mFbLogin.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
             public void onSuccess(LoginResult loginResult) {
                // App code
                 userProfile();
//
//                Intent intent=new Intent(getActivity(),userProfileActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivity(intent);

            }

            @Override
            public void onCancel() {
                // App code
                Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
            }
        });
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mgoogleSign=(SignInButton)view.findViewById(R.id.google_login);
        mGoogleApiClient=new GoogleApiClient.Builder(getApplicationContext())
                .enableAutoManage(getActivity(), new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                        Toast.makeText(getActivity(),"You get get error",Toast.LENGTH_SHORT).show();
                    }
                })
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build();
        mgoogleSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress.show();
                signIn();
            }
        });
        twitterloginButton = (TwitterLoginButton)view. findViewById(R.id.twitter_login);
        twitterloginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                userProfile();
            }
            @Override

            public void failure(TwitterException exception) {

            }
        });



        return view;

    }
//    @Override
//    public void onStart() {
//        super.onStart();
//        mAuth.addAuthStateListener(mAuthStateListener);
//    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void userProfile() {
        Intent intent=new Intent(getActivity(),userProfileActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
               startActivity(intent);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        twitterloginButton.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = result.getSignInAccount();
                firebaseAuthWithGoogle(account);
                //progress.dismiss();
            }

        }
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
    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            userProfile();
                            Log.v(TAG, "signInWithCredential:success");
                            progress.dismiss();

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(getActivity(), "Authentication failed.",Toast.LENGTH_SHORT).show();
                            progress.dismiss();

                        }

                        // ...
                    }
                });
    }
    }