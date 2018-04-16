package sg.edu.rp.c346.p01_dailygoals;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {
int rab1;
int rab2;
int rab3;
String summary1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnDone = (Button) findViewById(R.id.button);
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View arg0){
                    RadioGroup radio1 = (RadioGroup) findViewById(R.id.grp1);
                    int selectedButtonId = radio1.getCheckedRadioButtonId();
                    RadioButton rb1 = (RadioButton) findViewById(selectedButtonId);
                    rab1 = selectedButtonId;

                    RadioGroup radio2 = (RadioGroup) findViewById(R.id.grp2);
                int selectedButtonId2 = radio2.getCheckedRadioButtonId();
                RadioButton rb2 = (RadioButton) findViewById(selectedButtonId2);
                    rab2 = selectedButtonId2;

                RadioGroup radio3 = (RadioGroup) findViewById(R.id.grp3);
                int selectedButtonId3 = radio3.getCheckedRadioButtonId();
                RadioButton rb3 = (RadioButton) findViewById(selectedButtonId3);
                rab3 = selectedButtonId3;

                EditText RJ = (EditText) findViewById(R.id.editText);
                summary1 = RJ.getText().toString();

                String[] info = {rb1.getText().toString(), rb2.getText().toString(), rb3.getText().toString(), RJ.getText().toString() };
                Intent i = new Intent(MainActivity.this, Summary.class);
                i.putExtra("info", info);
                startActivity(i);
            }
        });
    }
    @Override
    protected void onPause(){
        super.onPause();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor prefEdit = prefs.edit();
        prefEdit.putInt("rb1",rab1);
        prefEdit.putInt("rb2",rab2);
        prefEdit.putInt("rb3",rab3);
        prefEdit.putString("summary",summary1);
        prefEdit.commit();
    }

    @Override
    protected void onResume(){
        super.onResume();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        Integer rb1 = prefs.getInt("rb1", 0);
        Integer rb2 = prefs.getInt("rb2", 0);
        Integer rb3 = prefs.getInt("rb3", 0);
        String strInfo = prefs.getString("summary", "e");
        EditText RJ = (EditText) findViewById(R.id.editText) ;
        RadioGroup radio1 = (RadioGroup) findViewById(R.id.grp1);
        RadioGroup radio2 = (RadioGroup) findViewById(R.id.grp2);
        RadioGroup radio3 = (RadioGroup) findViewById(R.id.grp3);

        RJ.setText(strInfo);
        radio1.check(rb1);
        radio2.check(rb2);
        radio3.check(rb3);

    }
}
