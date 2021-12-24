package com.example.miniproject2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import static com.example.miniproject2.WinnerActivity.REQUEST_CALL_PHONE;

public class PhoneActivity extends AppCompatActivity {

    private static String logs;
    private static final String TAG = logs;
    private View phoneActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);
        phoneActivity = findViewById(R.id.editTextPhone);
    }
    public void phoneAFriend(View view) {
            // Find the editText_main view.
            EditText editText = findViewById(R.id.editText_main);
            String phoneNum = null;
            // If the editText field is not null,
            // concatenate "tel: " with the phone number string.
            if (editText != null) phoneNum = "tel:" +
                    editText.getText().toString();
            // Optional: Log the concatenated phone number for dialing.
            Log.d(TAG, "dialNumber: " + phoneNum);
            // Specify the intent.
            Intent intent = new Intent(Intent.ACTION_DIAL);
            // Set the data for the intent as the phone number.
            intent.setData(Uri.parse(phoneNum));
            // If the intent resolves to a package (app),
            // start the activity with the intent.
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            } else {
                Log.d("ImplicitIntents", "Can't handle this!");
            }
    }
}

