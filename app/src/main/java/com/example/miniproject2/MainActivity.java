package com.example.miniproject2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int blueTeamScore = 0;
    private int redTeamScore = 0;
    private int pointDifferenceBlue = 0;
    private int pointDifferenceRed = 0;
    private TextView teamRedScore = null;
    private TextView teamBlueScore = null;
    private Object ExtraData = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        teamBlueScore = findViewById(R.id.teamBlueScore);
        teamRedScore = findViewById(R.id.teamRedScore);

    }

    public void scorePointBlue(View view) {
        blueTeamScore++;
        teamBlueScore.setText(Integer.toString(blueTeamScore));
        if (blueTeamScore == 5) {
            // call the winner activity with blue team as the winner and the point difference
            pointDifferenceBlue = blueTeamScore - redTeamScore;
            Bundle pointDifferenceDataBlue = new Bundle();
            pointDifferenceDataBlue.putInt((String) ExtraData, pointDifferenceBlue);

            Intent winnerActivity = new Intent(this, WinnerActivity.class);

            winnerActivity.putExtra((String) ExtraData, pointDifferenceDataBlue);
            startActivity(winnerActivity);
        }
    }


    public void scorePointRed(View view) {
        redTeamScore++;
        teamRedScore.setText(Integer.toString(redTeamScore));
        if (redTeamScore == 5) {
            // call the winner activity with blue team as the winner and the point difference
            pointDifferenceRed = redTeamScore - blueTeamScore;
            Bundle pointDifferenceDataRed = new Bundle();
            pointDifferenceDataRed.putInt((String) ExtraData, pointDifferenceRed);

            Intent winnerActivity = new Intent(this, WinnerActivity.class);

            winnerActivity.putExtra((String) ExtraData, pointDifferenceDataRed);
            startActivity(winnerActivity);
        }
    }


}
