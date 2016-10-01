package in.ac.iiitd.mt14033assignement1;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class HintActivity extends AppCompatActivity {

    final String TAG = "HINT";
    private Boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hint);

        Log.d(TAG, "In Hint onCreate");
        flag = true;
    }

    @Override
    public void onBackPressed() {
        Log.d(TAG, "in onBackPressed");
        Intent resultIntent = new Intent();
        resultIntent.putExtra("hint_flag", flag);
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }
}
