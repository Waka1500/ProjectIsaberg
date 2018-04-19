package se.ju.waka1500student.projectisaberg;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;

import java.util.Calendar;

/**
 * Created by Tjalle on 2018-04-10.
 */

public class CreditcardActivity extends AppCompatActivity {

    private Button confirmButton;
    private AwesomeValidation awesomeValidation;
    private EditText cardHolder, CVC, cardNumber;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creditcard);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        cardHolder = (EditText) findViewById(R.id.card_name_input);
        cardNumber = (EditText) findViewById(R.id.card_number_input);
        CVC = (EditText) findViewById(R.id.card_cvc_input);

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        awesomeValidation.addValidation(this, R.id.card_name_input, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.nameerror);
        awesomeValidation.addValidation(this, R.id.card_number_input, "^[+]?[0-9]{16}$", R.string.creditcarderror);
        awesomeValidation.addValidation(this, R.id.card_cvc_input, "^[+]?[0-9]{3}$", R.string.cvcerror);




        confirmButton = (Button) findViewById(R.id.card_button_confirm);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitForm();
            }
        });
    }

    public void submitForm(){
        if(awesomeValidation.validate()){
            returnToMain();
        }
    }

    private void returnToMain(){
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        Toast.makeText(this, "Registration Successful!", Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }
}
