package dmit2504.nait.sharedpreferencedemo;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText mUsernameEditText;
    private EditText mPasswordEditText;
    private Switch mRememberMeSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUsernameEditText = findViewById(R.id.activity_main_username);
        mPasswordEditText = findViewById(R.id.activity_main_password);
        mRememberMeSwitch = findViewById(R.id.activity_main_remember_me);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String username = prefs.getString("username","");
        String password = prefs.getString("password","");
        mUsernameEditText.setText(username);
        mPasswordEditText.setText(password);
    }

    public void login(View view) {
        // If the remember me switch is on save the username and password values as a shared preference
        if (mRememberMeSwitch.isChecked()) {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("username",mUsernameEditText.getText().toString());
            editor.putString("password",mPasswordEditText.getText().toString());
            editor.commit();
            Toast.makeText(this,"Saved preferences",Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this,"Not saving preferences", Toast.LENGTH_SHORT).show();
        }
    }
}
