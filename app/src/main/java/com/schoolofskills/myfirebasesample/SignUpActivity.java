package com.schoolofskills.myfirebasesample;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

//import com.firebase.client.Firebase;
//import com.firebase.client.FirebaseError;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Map;

public class SignUpActivity extends AppCompatActivity {

    private static final String LOG_TAG = SignUpActivity.class.getSimpleName();
    private EditText mEditTextName, mEditTextEmail, mEditTextPassword;
    private String mEmail, mName, mPassword;
    private Button mButton;
    private TextView mLogIn;
    private ProgressDialog mProgressDialog;
    private DatabaseReference mFirebase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mEditTextName = (EditText) findViewById(R.id.nameValue);
        mEditTextEmail = (EditText) findViewById(R.id.newEmailValue);
        mEditTextPassword = (EditText) findViewById(R.id.newPasswordValue);
        mButton = (Button) findViewById(R.id.newSignUpButton);
        mLogIn = (TextView) findViewById(R.id.logInTextView);

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setTitle(getResources().getString(R.string.progress_dialog_loading));
        mProgressDialog.setMessage(getResources().getString(R.string.progress_dialog_creating_user_with_firebase));
        mProgressDialog.setCancelable(false);

        //final Firebase ref = new Firebase("https://crackling-heat-2430.firebaseio.com/");
        //final Firebase ref = new Firebase("https://webchat-prem.firebaseio.com/");

        //DOnt think this is needed
        mFirebase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();


        //You dont need this la i think.. just waste of resources
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(LOG_TAG, "onAuthStateChanged:signed_in:" + user.getUid());

                    Toast.makeText(SignUpActivity.this, "You are signed in, please proceed", Toast.LENGTH_LONG).show();
                } else {
                    // User is signed out
                    Log.d(LOG_TAG, "onAuthStateChanged:signed_out");
                }
            }
        };



        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mName = String.valueOf(mEditTextName.getText());
                mEmail = String.valueOf(mEditTextEmail.getText());
                mPassword = String.valueOf(mEditTextPassword.getText());

                /**
                 * Check that email and user name are okay
                 */
                boolean validEmail = isEmailValid(mEmail);
                boolean validUserName = isUserNameValid(mName);
                boolean validPassword = isPasswordValid(mPassword);
                if (!validEmail || !validUserName || !validPassword) return;

                /**
                 * If everything was valid show the progress dialog to indicate that
                 * account creation has started
                 */
                mProgressDialog.show();

                mAuth.createUserWithEmailAndPassword(mEmail, mPassword)
                        .addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()) {
                                    Log.i(LOG_TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                                    mProgressDialog.dismiss();
                                    showToast("Sign In Successful, Please Log In");

                                }

                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                else if (!task.isSuccessful()) {

                                    mProgressDialog.dismiss();

                                    Log.i(LOG_TAG,"sign In Not Successful", task.getException());

                                    //I am just simply adding this part, if not working, remove it!!
                                    mAuth.createUserWithEmailAndPassword(mEmail, mPassword).addOnFailureListener(SignUpActivity.this, new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {

                                            showToast(e.getMessage());

                                        }
                                    });
                                    //showToast("Sign In Unsuccessful");

                                }

                                // ...
                            }
                        });

//                ref.createUser(mEmail, mPassword, new Firebase.ValueResultHandler<Map<String, Object>>() {
//                    @Override
//                    public void onSuccess(Map<String, Object> stringObjectMap) {
//                        /* Dismiss the progress dialog */
//                        mProgressDialog.dismiss();
//                        Log.i(LOG_TAG, "Authorization Successful!");
//
//                    }
//
//                    @Override
//                    public void onError(FirebaseError firebaseError) {
//
//                        /* Error occurred, log the error and dismiss the progress dialog */
//                        Log.d(LOG_TAG, "Error Occured: " +
//                                firebaseError);
//                        mProgressDialog.dismiss();
//                /* Display the appropriate error message */
//                        if (firebaseError.getCode() == FirebaseError.EMAIL_TAKEN) {
//                            mEditTextEmail.setError(getString(R.string.error_email_taken));
//                        } else {
//                            showErrorToast(firebaseError.getMessage());
//                        }
//
//                    }
//                });



            }
        });
    }

    /**
     * Creates a new user in Firebase from the Java POJO
     */
    private void createUserInFirebaseHelper(final String encodedEmail) {
    }

    private boolean isEmailValid(String email) {
        boolean isGoodEmail =
                (email != null && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches());
        if (!isGoodEmail) {
            mEditTextEmail.setError(String.format(getString(R.string.error_invalid_email_not_valid),
                    email));
            return false;
        }
        return isGoodEmail;
    }

    private boolean isUserNameValid(String userName) {
        if (userName.equals("")) {
            mEditTextName.setError(getResources().getString(R.string.error_cannot_be_empty));
            return false;
        }
        return true;
    }

    private boolean isPasswordValid(String password) {
        if (password.length() < 6) {
            mEditTextPassword.setError(getResources().getString(R.string.error_invalid_password_not_valid));
            return false;
        }
        return true;
    }

    /**
     * Show error toast to users
     */
    private void showToast(String message) {
        Toast.makeText(SignUpActivity.this, message, Toast.LENGTH_LONG).show();
    }

    public void onLogInPressed(View view) {
        Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }


    //These parts can be removed later if you dont want to attach the state listener
    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}
