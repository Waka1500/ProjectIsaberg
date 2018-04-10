package se.ju.waka1500student.projectisaberg;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.custom.SimpleCustomValidation;


/**
 * Created by Tjalle on 2018-04-04.
 */

public class RegisterActivity extends AppCompatActivity {

    private EditText editTextEmail, editTextFirstname, editTextLastname, editTextPhone,
        editTextPassword, editTextConfirmPassword;

    private Button nextButton;

    private RadioButton creditcard, invoice, paypal;

    private RadioGroup radioGroup;

    //defining AwesomeValidation object

    private AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editTextEmail = (EditText) findViewById(R.id.register_email_input);
        editTextFirstname = (EditText) findViewById(R.id.register_firstname_input);
        editTextPhone = (EditText) findViewById(R.id.register_phone_input);
        editTextLastname = (EditText) findViewById(R.id.register_lastname_input);
        editTextPassword = (EditText) findViewById(R.id.register_password_input);
        editTextConfirmPassword = (EditText) findViewById(R.id.register_confirm_input);

        nextButton = (Button) findViewById(R.id.next_button);

        creditcard = (RadioButton) findViewById(R.id.radio_card);
        invoice = (RadioButton) findViewById(R.id.radio_invoice);
        paypal = (RadioButton) findViewById(R.id.radio_paypal);
        radioGroup = (RadioGroup) findViewById(R.id.Payment_choice);

        //initializing awesomeValidation
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        //adding validation
        awesomeValidation.addValidation(this, R.id.register_email_input, Patterns.EMAIL_ADDRESS, R.string.emailerror);
        awesomeValidation.addValidation(this, R.id.register_firstname_input, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.nameerror);
        awesomeValidation.addValidation(this, R.id.register_lastname_input, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.nameerror);
        awesomeValidation.addValidation(this, R.id.register_phone_input, "^[+]?[0-9]{10,13}$", R.string.phoneerror);
        awesomeValidation.addValidation(this, R.id.register_password_input, new SimpleCustomValidation() {
            @Override
            public boolean compare(String s) {
                if(editTextPassword.getText().toString().isEmpty())
                {
                    return false;
                }
                else {
                    return true;
                }
            }
        }, R.string.passworderror);
        awesomeValidation.addValidation(this, R.id.register_confirm_input, new SimpleCustomValidation() {
            @Override
            public boolean compare(String s) {
                if(editTextConfirmPassword.getText().toString().isEmpty())
                {
                    return false;
                }
                else {
                    return true;
                }
            }
        }, R.string.passworderror);
    }

    public void nextButtonClicked(View view){
        System.out.println("Next Button was clicked.");
        //Intent intent = new Intent(this, PaypalActivity.class);
        //startActivity(intent);
        submitForm();
    }

    private void submitForm(){
        if (awesomeValidation.validate()) {
            String password = editTextPassword.getText().toString().trim();
            String confirmPassword = editTextConfirmPassword.getText().toString().trim();
            if (password.equals(confirmPassword))
            {
                if (radioGroup.getCheckedRadioButtonId() == -1)
                {
                    Toast.makeText(this, "Please choose a payment option", Toast.LENGTH_LONG).show();
                }
                else
                {
                    if(creditcard.isChecked())
                    {
                        Toast.makeText(this, "Validation Successfull", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(this, CreditcardActivity.class);
                        startActivity(intent);
                    }
                    if(paypal.isChecked())
                    {
                        Toast.makeText(this, "Validation Successfull", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(this, PaypalActivity.class);
                        startActivity(intent);
                    }
                    if(invoice.isChecked())
                    {
                        Toast.makeText(this, "Validation Successfull", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(this, InvoiceActivity.class);
                        startActivity(intent);
                    }
                }
            }
            else {
                Toast.makeText(this, "Passwords not matching", Toast.LENGTH_SHORT).show();
            }
        }
    }
}