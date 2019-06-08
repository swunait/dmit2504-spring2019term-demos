package dmit2504.nait.simplebroadcastsender;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void broadcastMessage(View view) {
        EditText text = findViewById(R.id.activity_main_edittext);
        String message = text.getText().toString();
        text.setText("");

        Intent intent = new Intent();
        intent.setAction("ca.nait.dmit2504.SIMPLE_NOTIFICATION");
        intent.putExtra("simpleBroadcastData",message);
        sendBroadcast(intent);

        Toast.makeText(this,"Message Sent", Toast.LENGTH_SHORT).show();
    }
}
