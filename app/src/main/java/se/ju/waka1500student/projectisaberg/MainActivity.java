package se.ju.waka1500student.projectisaberg;

import android.content.Intent;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.view.MenuItem;

/**
 * Created by Tjalle on 2018-04-03.
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        TextView forgotPassword = (TextView)findViewById(R.id.forgot_password_text);
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("ForgotPassword Button was clicked.");
                forgotPasswordClicked(view);
            }
        });
        /*DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_view);
        final ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, "Open", "Close");
        drawer.setDrawerListener(toggle);
        toggle.syncState();*/
    }

    public void loginButtonClicked(View view) {
        Intent intent = new Intent(this, ScanNFCActivity.class);
        startActivity(intent);
    }

    public void registerButtonClicked(View view) {
        System.out.println("Register Button was clicked.");

       //  Intent intent = new Intent(this, NfcTask.class); //TODO TEST! , uncomment to test!. implement gui button to start nfc
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    public void forgotPasswordClicked(View view) {
        Intent intent = new Intent(this, ForgotPasswordActivity.class);
        startActivity(intent);
    }


 /*   @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);

        }
        else if (id == R.id.nav_manage) {
            Intent intent = new Intent(this, ToolsActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_sign_out) {
           // mAuth.signOut();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }*/
}

