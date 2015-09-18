package com.exercise.swd3.two_degree.twoDegree;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.exercise.swd3.two_degree.LoginActivity;
import com.exercise.swd3.two_degree.MainActivity;
import com.exercise.swd3.two_degree.R;
import com.exercise.swd3.two_degree.bean.BaseBean;
import com.exercise.swd3.two_degree.bean.UserBean;
import com.exercise.swd3.two_degree.callback.ResultCallback;
import com.exercise.swd3.two_degree.constants.HttpConstants;
import com.exercise.swd3.two_degree.twoDegree.bean.TwoDegreeInfo;
import com.exercise.swd3.two_degree.util.AppUtils;
import com.exercise.swd3.two_degree.util.HttpUtil;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TwoDegree extends Activity {

    private String[] names = new String[]
            { "leo", "jayson", "surge", "dy"};
    private String[] descs = new String[]
            { "有4个好友认识他", "有3个好友认识他", "有2个好友认识他", "有1个好友认识他"};
    private int[] imageIds = new int[]
            { R.drawable.leo , R.drawable.jayson, R.drawable.surge , R.drawable.dy};
    private String[] dist = new String[]
            {"0.25km","0.34km","0.44km","0.58km"};
    private int[] images = new int[]
            { R.drawable.d , R.drawable.d ,R.drawable.d ,R.drawable.d };
    public List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.two_degree);
        getTwoDegreeList();
        /**
        List<Map<String, Object>> listItems =
                new ArrayList<Map<String, Object>>();
        for (int i = 0; i < names.length; i++)
        {
            Map<String, Object> listItem = new HashMap<String, Object>();
            listItem.put("header", imageIds[i]);
            listItem.put("personName", names[i]);
            listItem.put("desc", descs[i]);
            listItem.put("dist", dist[i]);
            listItem.put("images",images[i]);
            listItems.add(listItem);
        }
        */
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, items,
                R.layout.simple_item,
                new String[] { "personName", "header" , "desc" , "dist" , "images"},
                new int[] { R.id.name, R.id.header , R.id.desc , R.id.dist , R.id.images});
        ListView list = (ListView) findViewById(R.id.mylist);

        list.setAdapter(simpleAdapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                System.out.println(names[position]
                        + "请点击我");
                startActivity(new Intent(TwoDegree.this, FriendDetailActivity.class));
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
    public void getTwoDegreeList(){
        List<NameValuePair> allP=new ArrayList<NameValuePair>();
        String phones = "18050236415,18050236417,18850584090";
        allP.add(new BasicNameValuePair("phones", phones));
        HttpUtil.doPost(HttpConstants.HTTP_GET_TWO_DEGREE_LIST, allP, new ResultCallback() {

            @Override
            public void getReslt(String result) {
                // TODO Auto-generated method stub
                Log.d("jayson", "getReslt==" + result);
                if (!result.isEmpty() && !"1".equals(result)) {
                    BaseBean b = JSON.parseObject(result, BaseBean.class);
                    if ("0".equals(b.getRespcode())) {
                        List<TwoDegreeInfo> list = JSON.parseArray(b.getData(), TwoDegreeInfo.class);
                        if (list != null && list.size() > 0) {
                            for (int i = 0; i < list.size(); i++) {
                                Map<String, Object> listItem = new HashMap<String, Object>();
                                listItem.put("header", R.drawable.leo);
                                listItem.put("personName", list.get(i).getName());
                                listItem.put("desc", "有" + list.get(i).getCount() + "个好友认识他");
                                listItem.put("dist", "0.25km");
                                listItem.put("images", R.drawable.d);
                                items.add(listItem);
                            }
                        } else {
                            showToast("get list error!");
                        }
                    } else if ("0".equals(b.getRespcode())) {
                        showToast(b.getMessage());
                    } else {
                        showToast(b.getMessage());
                    }
                } else {
                    Toast.makeText(TwoDegree.this, "服务器响应失败", Toast.LENGTH_LONG).show();
//                            close();
                }
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
    public void showToast(String str){
        Toast.makeText(TwoDegree.this, str, Toast.LENGTH_LONG).show();
    }
}
