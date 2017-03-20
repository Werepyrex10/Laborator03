package ro.pub.cs.systems.eim.lab03.phonedialer;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.SyncStateContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.pm.ActivityInfoCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PhoneDialerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_dialer);
    }


    public void numberOnClick(View view) {
        Button b = (Button) view;
        String s = b.getText().toString();

        EditText text = (EditText) findViewById(R.id.textCall);

        text.append(s);
    }


    public void backspaceOnClick(View view) {
        EditText text = (EditText) findViewById(R.id.textCall);
        String s = text.getText().toString();
        if (text.length() != 0)
            text.setText(s.substring(0, s.length() - 1));
    }

    public void callOnClick(View view) {
        EditText phoneNumberEditText = (EditText) findViewById(R.id.textCall);

        if (ContextCompat.checkSelfPermission(PhoneDialerActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    PhoneDialerActivity.this,
                    new String[]{Manifest.permission.CALL_PHONE},
                    1
            );
        } else {
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:" + phoneNumberEditText.getText().toString()));
            startActivity(intent);
        }
    }

    public void endOnClick(View view) {
        finish();
    }

    public void contactsOnClick(View view) {
        EditText number = (EditText) findViewById(R.id.textCall);
        Intent intent = new Intent("ro.pub.cs.systems.eim.lab04.contactsmanager.intent.action.ContactsManagerActivity");
        intent.putExtra("ro.pub.cs.systems.eim.lab04.contactsmanager.PHONE_NUMBER_KEY", number.getText().toString());
        startActivityForResult(intent, 1);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK)
                Toast.makeText(this, "SUCCES", Toast.LENGTH_SHORT).show();
            else if(resultCode == Activity.RESULT_CANCELED)
                Toast.makeText(this, "CANCELED", Toast.LENGTH_SHORT).show();
        }
    }
}
