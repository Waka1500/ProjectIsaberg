package se.ju.waka1500student.projectisaberg;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Tjalle on 2018-04-10.
 */

public class ForgotPasswordActivity extends AppCompatActivity {


    //public Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);

        //button = (Button) findViewById(R.id.forgotPW);
    }

    public void sendResetRequest(View view){
        System.out.println("Reset button was clicked.");
        Toast.makeText(this, "Sending request...", Toast.LENGTH_LONG).show();
    }
}
