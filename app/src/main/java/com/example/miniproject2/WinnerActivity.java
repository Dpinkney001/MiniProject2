package com.example.miniproject2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ShareCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.BreakIterator;


public class WinnerActivity extends AppCompatActivity {
    public static final int REQUEST_CALL_PHONE = 2;

    private Intent redTeamWin;
    private Intent blueTeamWin;
    private String messageBlue;
    private String messageRed;
    private TextView winningTextView;
    private ImageView winningPicture;
    private TextView editTextLoc;
    private TextView editTextPlainMsg;
    private Object PhoneActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner);
        winningTextView = findViewById(R.id.winningTextView);
        winningPicture = findViewById(R.id.winningPicture);


    }

    public void resultMain(View view) {
        // analyize the intput data from the other class
        // if data is point difference is blue then winner is blue
        // if data point difference is red then winner is red
        Intent intent = new Intent();
        Intent intent1 = new Intent();

        TextView teamRedScore = null;
        assert false;
        messageRed = teamRedScore.getText().toString();
        TextView teamBlueScore = null;
        assert teamBlueScore != null;
        messageBlue = teamBlueScore.getText().toString();
        redTeamWin = intent.putExtra(String.valueOf(RESULT_OK), messageRed);
        blueTeamWin = intent1.putExtra(String.valueOf(RESULT_OK), messageBlue);

        if (redTeamWin != null) {
            winningTextView.setText("TEAM RED WINS");
            startActivity(redTeamWin);
        } else if (blueTeamWin != null) {
            winningTextView.setText("TEAM Blue WINS");
            startActivity(blueTeamWin);
        }
    }

    public void pointDifference(View view) {
        int blueTeamScore = 0;
        int redTeamScore = 0;
        if (blueTeamScore == 5) {
            // call the winner activity with blue team as the winner and the point difference
            int pointDifferenceBlue = blueTeamScore - redTeamScore;
            winningTextView.setText("");
        } else if (redTeamScore == 5) {
            // call the winner activity with blue team as the winner and the point difference
            int pointDifferenceRed = redTeamScore - blueTeamScore;
        }

    }
        public void sendAMessage (View view){
            String textToSend = editTextPlainMsg.getText().toString();
            String mimeType = "text/plain";
            ShareCompat.IntentBuilder
                    .from(this)
                    .setType(mimeType)
                    .setChooserTitle("Pick an app")
                    .setText(textToSend)
                    .startChooser();

        }

        public void openMaps (View view){
            String loc = editTextLoc.getText().toString();
            Uri geoLoc = Uri.parse("geo:0,0?q=" + loc);
            Intent intent3 = new Intent(Intent.ACTION_VIEW, geoLoc);
            if (intent3.resolveActivity(getPackageManager()) != null) {
                startActivity(intent3);
            } else {

                Toast.makeText(this, "Cannot find location " + loc, Toast.LENGTH_LONG).show();
            }
        }

    public void phoneAFriend(View view) {
        //Intent intent2 = new Intent(Intent.ACTION_CALL);//needs AndroidManifest permissions
        //intent2.setData(Uri.parse("tel:2128675309"));
        Intent phoneActivity = new Intent(this, PhoneActivity.class);
        if (phoneActivity.resolveActivity(getPackageManager()) != null) {
            //dialer app is available for usage
            //need to check permissions
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL_PHONE);
                //need to override onRequestPermissionsResult method for when the REQUEST_CALL_PHONE triggers the callback method
            } else {//have permission to CALL_PHONE so we can start the activity by passing in the intent

                phoneActivity.setData(Uri.parse("tel:2128675309"));
                startActivity(phoneActivity);
            }
        }
    }// end phoneAfriend method
}
