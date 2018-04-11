package se.ju.waka1500student.projectisaberg;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.custom.SimpleCustomValidation;

/**
 * Created by Tjalle on 2018-04-10.
 */

public class InvoiceActivity extends AppCompatActivity {

    private AwesomeValidation awesomeValidation;

    private EditText invoiceName, invoiceAddress, invoiceZip, invoiceCity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice);

        invoiceName = (EditText) findViewById(R.id.invoice_name_input);
        invoiceAddress = (EditText) findViewById(R.id.invoice_address_input);
        invoiceZip = (EditText) findViewById(R.id.invoice_zip_input);
        invoiceCity = (EditText) findViewById(R.id.invoice_city_input);

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        awesomeValidation.addValidation(this, R.id.invoice_name_input, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.nameerror);
        awesomeValidation.addValidation(this, R.id.invoice_city_input, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.cityerror);
        awesomeValidation.addValidation(this, R.id.invoice_zip_input, "^[+]?[0-9]{5}$", R.string.ziperror);
        awesomeValidation.addValidation(this, R.id.invoice_address_input, new SimpleCustomValidation() {
            @Override
            public boolean compare(String s) {
                if(invoiceAddress.getText().toString().isEmpty())
                {
                    return false;
                }
                else {
                    return true;
                }
            }
        }, R.string.addresserror);
    }

    public void confirmButtonClicked(View view){
        submitForm();
    }

    public void submitForm(){
        if(awesomeValidation.validate()){
            Toast.makeText(this, "Registration successful!", Toast.LENGTH_LONG).show();
            //Intent intent = new Intent(this, InvoiceActivity.class);
            //startActivity(intent);
        }
    }
}
