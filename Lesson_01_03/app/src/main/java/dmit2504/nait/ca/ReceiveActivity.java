package dmit2504.nait.ca;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ReceiveActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive);

        Intent mainActivityIntent = getIntent();
        String prefix = mainActivityIntent.getStringExtra("PREFIX");
        String data = mainActivityIntent.getStringExtra("DATA");

        TextView textView = findViewById(R.id.text_view_receive);
        textView.setText(prefix + data);
    }
}
