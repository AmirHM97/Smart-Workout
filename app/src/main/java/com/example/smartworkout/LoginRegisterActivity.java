package com.example.smartworkout;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.auth.UserProfileChangeRequest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginRegisterActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);
        mAuth = FirebaseAuth.getInstance();
        Button btn = findViewById(R.id.login_btn);
        EditText email = findViewById(R.id.email_editText);
        EditText pass = findViewById(R.id.password_editText);
        EditText nameEditText = findViewById(R.id.name_editText);
//        String regex = "^(.+)@(\\\\S+)$";
//        Pattern pattern = Pattern.compile(regex);

        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Do something in response to button click
                String emailText = email.getText().toString();
                if (emailText.equals("")) {
                    email.setError("email can not be empty");
                } else {

                    String passText = pass.getText().toString();


                    if (!validate(emailText)){
                        email.setError(emailText+" is not in a correct format");
                        Toast.makeText(LoginRegisterActivity.this, ""+emailText+"  is not in a correct format",
                                Toast.LENGTH_SHORT).show();
                    } else{
                        if (passText.equals("")) {
                            pass.setError("password can not be empty");
                        } else {
                            mAuth.fetchSignInMethodsForEmail(emailText)
                                    .addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {

                                            boolean isNewUser = task.getResult().getSignInMethods().isEmpty();

                                            if (isNewUser) {

                                                CreateUser(email.getText().toString(), pass.getText().toString(),nameEditText.getText().toString());
                                            } else {
                                                mAuth.signInWithEmailAndPassword(emailText, passText)
                                                        .addOnCompleteListener(LoginRegisterActivity.this, new OnCompleteListener<AuthResult>() {
                                                            @Override
                                                            public void onComplete(@NonNull Task<AuthResult> task) {
                                                                if (task.isSuccessful()) {
                                                                    // Sign in success, update UI with the signed-in user's information
                                                                    Log.d(TAG, "signInWithEmail:success");
                                                                    Toast.makeText(LoginRegisterActivity.this, "Authentication successful.",
                                                                            Toast.LENGTH_SHORT).show();
                                                                    FirebaseUser user = mAuth.getCurrentUser();
                                                                    Intent i= new Intent(LoginRegisterActivity.this, MainActivity.class);
                                                                    startActivity(i);
                                                                    // Start a new activity
                                                                } else {
                                                                    // If sign in fails, display a message to the user.
                                                                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                                                                    Toast.makeText(LoginRegisterActivity.this, "Authentication failed.",
                                                                            Toast.LENGTH_SHORT).show();
                                                                }
                                                            }
                                                        });
                                            }
                                        }
                                    });
                        }
                    }

                }
            }
        });

    }
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }
    private void CreateUser(String email, String pass, String name) {


        mAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                    .setDisplayName(name)
                                    .build();

                            user.updateProfile(profileUpdates)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Log.d(TAG, "User profile updated.");
                                            }
                                        }
                                    });
                            Toast.makeText(LoginRegisterActivity.this, "Registration Successful!!" ,
                                    Toast.LENGTH_SHORT).show();
                            Intent i= new Intent(LoginRegisterActivity.this, MainActivity.class);
                            startActivity(i);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(LoginRegisterActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }
                    }
                });
    }
}