package ch.zivfed.bmicalc;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {
    LocalService bmiService;
    boolean mBound = false;

    TextView bmiResult;
    TextView classificationResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        bmiResult = (TextView) findViewById(R.id.bmiResult);
        classificationResult = (TextView) findViewById(R.id.classificationResult);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, LocalService.class);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unbindService(connection);
        mBound = false;
    }

    private ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {
            LocalService.LocalBinder binder = (LocalService.LocalBinder) service;
            bmiService = binder.getService();
            mBound = true;

            Intent mainIntent = getIntent();
            float weight = mainIntent.getFloatExtra(MainActivity.WEIGHT_INPUT, 0);
            float height = mainIntent.getFloatExtra(MainActivity.HEIGHT_INPUT, 0);

            float calculatedBmi = bmiService.getBmi(weight, height);
            BmiClassification classification = bmiService.getBmiClassification(calculatedBmi);
            String classificationText = "";
            switch (classification) {
                case UNDERWEIGHT:
                    classificationText = "Untergewicht";
                    break;
                case NORMALWEIGHT:
                    classificationText = "Normalgewicht";
                    break;
                case PREOBESITY:
                    classificationText = "Präadipositas";
                    break;
                case OBESITY1:
                    classificationText = "Adipositas Grad I";
                    break;
                case OBESITY2:
                    classificationText = "Adipositas Grad II";
                    break;
                case OBESITY3:
                    classificationText = "Adipositas Grad III";
                    break;
            }

            bmiResult.setText(calculatedBmi + "");
            classificationResult.setText(classificationText);
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
        }
    };
}