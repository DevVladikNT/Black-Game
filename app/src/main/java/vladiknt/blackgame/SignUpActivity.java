package vladiknt.blackgame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mAuth = FirebaseAuth.getInstance();
    }

    public void registerUser(View view) {
        EditText et = findViewById(R.id.signUpLogin);
        String email = et.getText().toString();
        et = findViewById(R.id.signUpPassword);
        String password = et.getText().toString();
        et = findViewById(R.id.repeatPassword);
        String password2 = et.getText().toString();

        if (password.equals(password2)) {
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign Up success, update UI with the signed-in user's information
                                Toast.makeText(SignUpActivity.this, "Register success.",
                                        Toast.LENGTH_SHORT).show();
                                mAuth.getCurrentUser().sendEmailVerification();
                                finish();
                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(SignUpActivity.this, "Register failed.",
                                        Toast.LENGTH_SHORT).show();
                            }

                            // ...
                        }
                    });
        } else
            Toast.makeText(SignUpActivity.this, "Enter passwords again.",
                    Toast.LENGTH_SHORT).show();
    }

}