package com.exercise.swd3.two_degree;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TwoDegree extends ActionBarActivity {
    private Button meBtn = null;
    private Button twoDegreeBtn = null;
    private Button dynamicBtn = null;
    private Button informationBtn = null;
    private TextView textView = null;
    private String[] names = new String[]
            { "leo", "jayson", "surge", "dy"};
    private String[] descs = new String[]
            { "有4个好友认识他", "有3个好友认识他", "有2个好友认识他", "有1个好友认识他"};
    private int[] imageIds = new int[]
            { R.drawable.leo , R.drawable.jayson, R.drawable.surge , R.drawable.dy};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_degree);

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
        List<Map<String, Object>> listItems =
                new ArrayList<Map<String, Object>>();
        for (int i = 0; i < names.length; i++)
        {
            Map<String, Object> listItem = new HashMap<String, Object>();
            listItem.put("header", imageIds[i]);
            listItem.put("personName", names[i]);
            listItem.put("desc", descs[i]);
            listItems.add(listItem);
        }

        SimpleAdapter simpleAdapter = new SimpleAdapter(this, listItems,
                R.layout.simple_item,
                new String[] { "personName", "header" , "desc"},
                new int[] { R.id.name, R.id.header , R.id.desc });
        ListView list = (ListView) findViewById(R.id.mylist);

        list.setAdapter(simpleAdapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                System.out.println(names[position]
                        + "请点击我");
                startActivity(new Intent(TwoDegree.this, FriendActivity.class));
                    }
        });
        list.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                System.out.println(names[position]
                        + "请选择我");

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_two_degree, menu);
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
