package in.ac.iiitd.mt14033assignement1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {

    private int prime = -1;
    private Boolean flag = false;
    final String TAG = "CHEAT";

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
        setContentView(R.layout.activity_cheat);
        Log.d(TAG, "in Cheat onCreate");

        final TextView numberPrime = (TextView) findViewById(R.id.textCheatNumber);
        final TextView numberPrimeResult = (TextView) findViewById(R.id.textCheatResultText);

        // get random number initially
        prime = getIntent().getExtras().getInt("prime_number");

        // set view
        numberPrime.setText(Integer.toString(prime));
        if (isPrime(prime)) {
            numberPrimeResult.setText("is PRIME :/");
        } else {
            numberPrimeResult.setText("is NOT A PRIME :/");
        }
        flag = true;
    }

    @Override
    public void onBackPressed() {
        Log.d(TAG, "in onBackPressed");
        Intent resultIntent = new Intent();
        resultIntent.putExtra("cheat_flag", flag);
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }
}
