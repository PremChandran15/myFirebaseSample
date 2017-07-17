package com.schoolofskills.myfirebasesample;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
//
//import com.firebase.client.AuthData;
//import com.firebase.client.Firebase;
//import com.firebase.client.FirebaseError;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.schoolofskills.myfirebasesample.photomultiple.FileUploadActivity;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG= MainActivity.class.getSimpleName();
    private EditText mEditTextEmail, mEditTextPassword;
    private Button mButton;
    private TextView mSignUp;
    private String mEmail, mPassword;
    private ProgressDialog mProgressDialog;
    //private Firebase mFirebase;
    private DatabaseReference mFirebase;
    private FirebaseAuth mAuth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditTextPassword = (EditText) findViewById(R.id.passwordValue);
        mEditTextEmail = (EditText) findViewById(R.id.emailValue);
        mButton = (Button) findViewById(R.id.logInButton);
        mSignUp = (TextView) findViewById(R.id.signUpTextView);

        /* Setup the progress dialog that is displayed later when authenticating with Firebase */
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setTitle(getString(R.string.progress_dialog_loading));
        mProgressDialog.setMessage(getString(R.string.progress_dialog_authenticating_with_firebase));
        mProgressDialog.setCancelable(false);




        //mFirebase = new Firebase("https://crackling-heat-2430.firebaseio.com/");
        //mFirebase = new Firebase("https://webchat-prem.firebaseio.com/");


        //Dont think this is needed
        mFirebase = FirebaseDatabase.getInstance().getReference();

        mAuth = FirebaseAuth.getInstance();

        mEditTextPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || event.getAction() == KeyEvent.ACTION_DOWN) {
                    signInPassword();
                }
                return true;
            }
        });

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInPassword();
            }
        });

    }




    private void signInPassword() {
        mEmail = String.valueOf(mEditTextEmail.getText());
        mPassword = String.valueOf(mEditTextPassword.getText());

        /**
         * If email and password are not empty show progress dialog and try to authenticate
         */
        if (mEmail.equals("")) {
            mEditTextEmail.setError(getString(R.string.error_cannot_be_empty));
            return;
        }

        if (mPassword.equals("")) {
            mEditTextPassword.setError(getString(R.string.error_cannot_be_empty));
            return;
        }

        mProgressDialog.show();

        mAuth.signInWithEmailAndPassword(mEmail, mPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    mProgressDialog.dismiss();
                    Log.i(LOG_TAG, "Authorization Successful");
                    Intent intent = new Intent(MainActivity.this, LocationSelectorActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
                else if (!task.isSuccessful()) {
                    Log.i(LOG_TAG, "signInWithEmail", task.getException());

                    mProgressDialog.dismiss();

                    mAuth.signInWithEmailAndPassword(mEmail, mPassword).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(Exception e) {

                            Toast.makeText(MainActivity.this,e.getMessage() ,
                                    Toast.LENGTH_LONG).show();

                        }
                    });


                }

            }

        });

//        mFirebase.authWithPassword(mEmail, mPassword, new Firebase.AuthResultHandler() {
//            @Override
//            public void onAuthenticated(AuthData authData) {
//                mProgressDialog.dismiss();
//                Log.i(LOG_TAG, "Authorization Successful");
//                Intent intent = new Intent(MainActivity.this, LocationSelectorActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                startActivity(intent);
//                finish();
//            }
//
//            @Override
//            public void onAuthenticationError(FirebaseError firebaseError) {
//                mProgressDialog.dismiss();
//
//                switch (firebaseError.getCode()){
//                    case FirebaseError.INVALID_EMAIL:
//                    case FirebaseError.USER_DOES_NOT_EXIST:
//                        mEditTextEmail.setError(getString(R.string.error_message_email_issue));
//                        break;
//                    case FirebaseError.INVALID_PASSWORD:
//                        mEditTextPassword.setError(firebaseError.getMessage());
//                        break;
//                    case FirebaseError.NETWORK_ERROR:
//                        Toast.makeText(MainActivity.this, "Failed to Log In to Network",Toast.LENGTH_LONG ).show();
//                        break;
//                    default:
//                        Toast.makeText(MainActivity.this, firebaseError.toString(), Toast.LENGTH_LONG).show();
//                }
//
//            }
//        });

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    public void onSignUpPressed(View view) {
        Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
