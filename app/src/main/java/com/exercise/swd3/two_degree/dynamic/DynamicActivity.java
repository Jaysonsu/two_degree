package com.exercise.swd3.two_degree.dynamic;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.exercise.swd3.two_degree.R;
import com.exercise.swd3.two_degree.twoDegree.FriendDetailActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by swd3 on 8/27/15.
 */
public class DynamicActivity extends Activity {
    private int[] Images = new int[]
            { R.drawable.a , R.drawable.a ,R.drawable.a ,R.drawable.a};
    private String[] names = new String[]
            { "leo", "jayson", "surge", "dy"};
    private String[] substance = new String[]
            { "听到一句很装逼的话：你，就是你的命运。", "人生总有好运气，飘扬过海来看你。", "我倒要看看，任何事我拼尽全力去做会怎样。","总有人要成功，为什么不可以是我。我就是要做最出色最好的那一个，得到大家的认同。"};
    private String[] times = new String[]
            {"3分钟前" , "7分钟前" , "12分钟前" ,"15分钟前" };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.dynamic);
        List<Map<String, Object>> listItems =
                new ArrayList<Map<String, Object>>();
        for (int i = 0; i < names.length; i++)
        {
            Map<String, Object> listItem = new HashMap<String, Object>();
            listItem.put("personName", names[i]);
            listItem.put("subs", substance[i]);
            listItem.put("time", times[i]);
            listItem.put("images",Images[i]);
            listItems.add(listItem);
        }

        SimpleAdapter simpleAdapter = new SimpleAdapter(this, listItems,
                R.layout.dynamic_listview,
                new String[] { "personName", "subs" , "time" , "images"},
                new int[] { R.id.name , R.id.subs , R.id.time, R.id.images});
        ListView list = (ListView) findViewById(R.id.dynamiclist);

        list.setAdapter(simpleAdapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                System.out.println(names[position]
                        + "请点击我");
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
}
