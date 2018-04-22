package se.ju.waka1500student.projectisaberg;

import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import static android.app.PendingIntent.getActivity;

/**
 * Created by Tjalle on 2018-04-19.
 */

public class ScanNFCActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfcscan);
        mAdapter = NfcAdapter.getDefaultAdapter(this);

       // ncfNonCompatible();

        if (mAdapter  == null) {
            System.out.println("No NFC on device!");
            ncfNonCompatible();
            finish();
        }else {
            if (mAdapter.isEnabled()) {
                System.out.println("Isenable"); //nfc igång behövs ingen action
            } else {
                System.out.println("notenable");
                AlertDialog.Builder adb = new AlertDialog.Builder(this);
                adb.setTitle("NFC is off, Turn it on?");
                adb.setIcon(android.R.drawable.ic_dialog_alert);
                adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        boolean x = enNfc(mAdapter); //gå till inställningarna
                        if (x) {
                            finish();
                        } else {
                            Toast.makeText(ScanNFCActivity.this,
                                    "Turn on NFC for the functions to work!", Toast.LENGTH_LONG).show();
                        }
                    }
                });
                adb.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                adb.show();
            }

            mPendingIntent = getActivity(this, 0, new Intent(this,
                    getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);

            Tag tag = getIntent().getParcelableExtra(NfcAdapter.EXTRA_TAG);
            GetDataFromTag(tag, getIntent());
            // startActivityForResult(mPendingIntent, STATE_ON);


        }
    }

    public boolean enNfc(NfcAdapter adapt)
    {
        if (android.os.Build.VERSION.SDK_INT >= 16) {
            startActivity(new Intent(android.provider.Settings.ACTION_NFC_SETTINGS));
        } else {
            startActivity(new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS));
        }
        return(adapt.isEnabled());
    }
    public void ncfNonCompatible()
    {
        AlertDialog.Builder a = new AlertDialog.Builder(this);
        a.setTitle("Your device is not compatible with nfc");
        a.setIcon(android.R.drawable.ic_dialog_alert);
        a.setNeutralButton("Order NFC Reader online", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String url = "http://www.sillenz.co"; //TODO ändra url till nfcköp.
                Intent xd = new Intent(Intent.ACTION_VIEW);
                xd.setData(Uri.parse(url));
                startActivity(xd);
            }
        });
        a.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(ScanNFCActivity.this,
                        "NFC not compatible!", Toast.LENGTH_LONG).show();
            } });
        a.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                finish();
            } });
        a.show();
    }

    NfcAdapter mAdapter;
    PendingIntent mPendingIntent;
    @Override
    protected void onResume() {
        super.onResume();
        mAdapter.enableForegroundDispatch(this, mPendingIntent, null, null);

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mAdapter != null) {
            mAdapter.disableForegroundDispatch(this);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        GetDataFromTag(tag, intent);

    }

    private void GetDataFromTag(Tag tag, Intent intent) {
/*
        String action = intent.getAction();
        if (mAdapter.ACTION_NDEF_DISCOVERED.equals(action) || mAdapter.ACTION_TAG_DISCOVERED.equals(action) || mAdapter.ACTION_TECH_DISCOVERED.equals(action)) {
            Parcelable[] raw = intent.getParcelableArrayExtra(mAdapter.EXTRA_NDEF_MESSAGES);
            NdefMessage[] msg = new NdefMessage[raw.length];
            if (raw != null) {
                msg = new NdefMessage[raw.length];
                for (int i = 0; i < raw.length; i++) {
                    msg[i] = (NdefMessage) raw[i];
                }
            } else {
                byte[] empty = new byte[0];
                byte[] id = intent.getByteArrayExtra(mAdapter.EXTRA_ID);
            }
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < msg.length; i++) {
                builder.append(msg[i]);
            }
            Toast.makeText(getApplicationContext(), builder.toString(), Toast.LENGTH_LONG).show();

        }
*/

        try {
            Ndef ndef = Ndef.get(tag);
            ndef.connect();
//            txtType.setText(ndef.getType().toString());
//            txtSize.setText(String.valueOf(ndef.getMaxSize()));
//            txtWrite.setText(ndef.isWritable() ? "True" : "False");

            Parcelable[] messages = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);

            if (messages != null) {
                NdefMessage[] ndefMessages = new NdefMessage[messages.length];
                for (int i = 0; i < messages.length; i++) {
                    ndefMessages[i] = (NdefMessage) messages[i];
                }
                NdefRecord record = ndefMessages[0].getRecords()[0];

                byte[] payload = record.getPayload();
                String text = new String(payload);
                Log.e("tag", "vahid" + text);
                Toast.makeText(getApplicationContext(),text , Toast.LENGTH_LONG).show();

                ndef.close();

            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Cannot Read From Tag.", Toast.LENGTH_LONG).show();
        }
    }
    public void accountInfoButtonClicked(View view) {
        Intent intent = new Intent(this, AccountInfoActivity.class);
        startActivity(intent);
    }
}
