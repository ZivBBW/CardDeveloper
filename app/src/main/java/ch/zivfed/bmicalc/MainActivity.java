package ch.zivfed.bmicalc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static final String WEIGHT_INPUT = "weightInput";
    public static final String HEIGHT_INPUT = "heightInput";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button calc = findViewById(R.id.calc);
        EditText weightEdit = findViewById(R.id.weightEdit);
        EditText heightEdit = findViewById(R.id.heightEdit);
        calc.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ResultActivity.class);
            float weight = Float.valueOf(String.valueOf(weightEdit.getText()));
            float height = Float.valueOf(String.valueOf(heightEdit.getText()));
            intent.putExtra(WEIGHT_INPUT, weight);
            intent.putExtra(HEIGHT_INPUT, height);
            startActivity(intent);
        });
    }
}