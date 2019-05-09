package dmit2504.nait.ca;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i(TAG, "onCreate() method called");
        Button button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"Button 1 clicked", Toast.LENGTH_SHORT).show();
            }
        });

        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener((View v) ->
                Toast.makeText(this, "Button 2 clicked",Toast.LENGTH_SHORT).show());

        Button button3 = findViewById(R.id.button3);
        button3.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG,"onStart() method called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume() method called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG,"onPause() method called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG,"onStop() method called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"onDestroy() method called");
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button3 )
            Toast.makeText(this,"Button 3 clicked", Toast.LENGTH_SHORT).show();
        else if (v.getId() == R.id.button4 )
            Toast.makeText(this,"Button 4 clicked", Toast.LENGTH_SHORT).show();
    }
}
