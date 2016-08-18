package in.ac.iiitd.mt14033.assignement1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button btnYes;
    private Button btnNo;
    private Button btnNext;
    private TextView numberPrime;
    private Random rand = new Random();
    int randomNum;
    private boolean prime;
    final private String TAG = "mt14033.Assignment1";

    public int getRandom() {
        return rand.nextInt(200);
    }
    public boolean isPrime(int n) {
        int i;
        for (i = 2; i < n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button btnYes = (Button) findViewById(R.id.buttonYES);
        final Button btnNo = (Button) findViewById(R.id.buttonNO);
        final Button btnNext = (Button) findViewById(R.id.buttonNEXT);
        final TextView numberPrime = (TextView) findViewById(R.id.questionStr2);

        // get random number initially
        numberPrime.setText(Integer.toString(getRandom()));

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG,"in btnNext");

                btnYes.setEnabled(false);
                btnNo.setEnabled(false);
                btnNext.setEnabled(false);

                int num = Integer.parseInt(numberPrime.getText().toString());
                prime = isPrime(num);
                if (prime) {
                    Toast toast = Toast.makeText(getApplicationContext(), "YaY! :D         (CORRECT)", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "buhuhuhu! :.../ (WRONG)", Toast.LENGTH_SHORT);
                    toast.show();
                }
                btnNext.performClick();
            }
        });
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d(TAG,"in btnNext");
                btnYes.setEnabled(false);
                btnNo.setEnabled(false);
                btnNext.setEnabled(false);

                int num = Integer.parseInt(numberPrime.getText().toString());
                prime = isPrime(num);
                if (prime) {
                    Toast toast = Toast.makeText(getApplicationContext(), "buhuhuhu! :.../ (WRONG)", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "YaY! :D         (CORRECT)", Toast.LENGTH_SHORT);
                    toast.show();
                }
                btnNext.performClick();
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG,"in btnNext");

                randomNum = getRandom();
                Log.d(TAG,"nextRandom: "+Integer.toString(randomNum));
                numberPrime.setText(Integer.toString(randomNum));
                btnYes.setEnabled(true);
                btnNo.setEnabled(true);
                btnNext.setEnabled(true);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "Inside OnStart");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "Inside OnPause");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "Inside OnResume");

    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "Inside OnStop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "Inside OnDestroy");
    }
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.sym_def_app_icon)
                .setTitle("Why :.....(")
                .setMessage("Are you sure you want to leave?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }
}
