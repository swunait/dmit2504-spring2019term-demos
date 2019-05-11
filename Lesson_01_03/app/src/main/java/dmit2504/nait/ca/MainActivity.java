package dmit2504.nait.ca;

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

    public void sendData(View view) {
        // Find the EditText field in the layout
        EditText dataText = findViewById(R.id.text_view_data);
        String data = dataText.getText().toString();
//        Toast.makeText(this, "You entered: " + data, Toast.LENGTH_LONG).show();

        Intent receiveActivityIntent = new Intent(this, ReceiveActivity.class);
        receiveActivityIntent.putExtra("PREFIX","You typed: ");
        receiveActivityIntent.putExtra("DATA",data);
        startActivity(receiveActivityIntent);
    }
}
