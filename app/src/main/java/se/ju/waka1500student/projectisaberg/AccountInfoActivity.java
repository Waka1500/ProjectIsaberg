package se.ju.waka1500student.projectisaberg;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

/**
 * Created by carol on 22/04/2018.
 */

public class AccountInfoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accountinfo);
    }
    public void editButtonClicked(View view){

        Toast.makeText(this, "Click", Toast.LENGTH_LONG).show();
    }

}
