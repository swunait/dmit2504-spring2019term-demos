package dmit2504.nait.radiobuttondemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String selectedCategory = "Cat 1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void categoryChanged(View view) {
        switch (view.getId()) {
            case R.id.activity_main_category1_radiobutton:
                selectedCategory = "Cat 1";
                break;
            case R.id.activity_main_category2_radiobutton:
                selectedCategory = "Cat 2";
                break;
            case R.id.activity_main_category3_radiobutton:
                selectedCategory = "Cat 3";
                break;
            case R.id.activity_main_category4_radiobutton:
                selectedCategory = "Cat 4";
                break;
        }
    }

    public void onSubmit(View view) {
        RadioGroup categoryRadioGroup = findViewById(R.id.activity_main_category_radiogroup);
        switch (categoryRadioGroup.getCheckedRadioButtonId()) {
            case R.id.activity_main_category1_radiobutton:
                selectedCategory = "Cat 11";
                break;
            case R.id.activity_main_category2_radiobutton:
                selectedCategory = "Cat 22";
                break;
            case R.id.activity_main_category3_radiobutton:
                selectedCategory = "Cat 33";
                break;
            case R.id.activity_main_category4_radiobutton:
                selectedCategory = "Cat 44";
                break;
        }
        Toast.makeText(this, selectedCategory, Toast.LENGTH_SHORT).show();

    }
}
