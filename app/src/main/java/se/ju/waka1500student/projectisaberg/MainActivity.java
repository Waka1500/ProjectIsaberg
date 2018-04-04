package se.ju.waka1500student.projectisaberg;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

/**
 * Created by Tjalle on 2018-04-03.
 */

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView forgotPassword = (TextView)findViewById(R.id.forgot_password_text);


        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    public void loginButtonClicked(View view) {

    }

    public void registerButtonClicked(View view) {
        System.out.println("Register Button was clicked.");
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    public void forgotPasswordClicked(View view) {

    }
}

