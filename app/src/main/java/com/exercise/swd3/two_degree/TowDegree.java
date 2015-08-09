package com.exercise.swd3.two_degree;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class TowDegree extends ActionBarActivity {
    private Button meBtn = null;
    private Button twoDegreeBtn = null;
    private Button dynamicBtn = null;
    private Button informationBtn = null;
    private TextView textView = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tow_degree);

        meBtn = (Button)findViewById(R.id.meBtn);
        twoDegreeBtn = (Button)findViewById(R.id.two_degreeBtn);
        dynamicBtn = (Button)findViewById(R.id.dynamicBtn);
        informationBtn = (Button)findViewById(R.id.informationBtn);
        textView = (TextView)findViewById(R.id.testView);
        meBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(R.string.me_text);
                meBtn.setTextColor(getResources().getColor(R.color.blue));
                twoDegreeBtn.setTextColor(getResources().getColor(R.color.black));
                dynamicBtn.setTextColor(getResources().getColor(R.color.black));
                informationBtn.setTextColor(getResources().getColor(R.color.black));
            }
        });
        twoDegreeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(R.string.two_degree_text);
                meBtn.setTextColor(getResources().getColor(R.color.black));
                twoDegreeBtn.setTextColor(getResources().getColor(R.color.blue));
                dynamicBtn.setTextColor(getResources().getColor(R.color.black));
                informationBtn.setTextColor(getResources().getColor(R.color.black));
            }
        });
        dynamicBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(R.string.dynamic_text);
                dynamicBtn.setTextColor(getResources().getColor(R.color.blue));
                meBtn.setTextColor(getResources().getColor(R.color.black));
                twoDegreeBtn.setTextColor(getResources().getColor(R.color.black));
                informationBtn.setTextColor(getResources().getColor(R.color.black));
            }
        });
        informationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(R.string.information_text);
                meBtn.setTextColor(getResources().getColor(R.color.black));
                twoDegreeBtn.setTextColor(getResources().getColor(R.color.black));
                dynamicBtn.setTextColor(getResources().getColor(R.color.black));
                informationBtn.setTextColor(getResources().getColor(R.color.blue));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tow_degree, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
