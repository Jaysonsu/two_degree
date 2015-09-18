package com.exercise.swd3.two_degree;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.exercise.swd3.two_degree.bean.BaseBean;
import com.exercise.swd3.two_degree.bean.UserBean;
import com.exercise.swd3.two_degree.callback.ResultCallback;
import com.exercise.swd3.two_degree.constants.HttpConstants;
import com.exercise.swd3.two_degree.util.AppUtils;
import com.exercise.swd3.two_degree.util.HttpUtil;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;


public class LoginActivity extends Activity {
    private EditText usernameEdit = null;
    private EditText passwordEdit = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        usernameEdit = (EditText) findViewById(R.id.username);
        passwordEdit = (EditText) findViewById(R.id.password);
        findViewById(R.id.loginBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (usernameEdit.getText() == null || passwordEdit.getText() ==null || "".equals(usernameEdit.getText().toString()) || "".equals(passwordEdit.getText().toString())) {
                    showToast("Please input username or password!");
                } else if(!AppUtils.checkNetwork(LoginActivity.this)) {
                    showToast("Please check network!");
                } else {
                    List<NameValuePair> allP=new ArrayList<NameValuePair>();
                    String username = usernameEdit.getText().toString();
                    String password = passwordEdit.getText().toString();
                    allP.add(new BasicNameValuePair("phone", username));
                    allP.add(new BasicNameValuePair("password", password));
                    Log.d("jayson", "url==" + HttpConstants.HTTP_LOGIN);
                    Log.d("jayson", "access==" + AppUtils.checkNetwork(LoginActivity.this));
                    HttpUtil.doPost(HttpConstants.HTTP_LOGIN, allP, new ResultCallback() {

                        @Override
                        public void getReslt(String result) {
                            // TODO Auto-generated method stub
                            Log.d("jayson","getReslt=="+result);
                            if (!result.isEmpty() && !"1".equals(result)) {
                                BaseBean b = JSON.parseObject(result, BaseBean.class);
                                if ("0".equals(b.getRespcode())) {
                                    UserBean u = JSON.parseObject(b.getData(), UserBean.class);
//                                new SharedPreferenceDb(LoginActivity.this).setUserId(u.getId());
//                                new SharedPreferenceDb(LoginActivity.this).setName(u.getName());
                                    Intent intent = new Intent();
                                    intent.putExtra("user", u);
                                    intent.setClass(LoginActivity.this, MainActivity.class);
                                    startActivity(intent);
//                                if (new SharedPreferenceDb(LoginActivity.this).getAnimation() == true) {
//                                    overridePendingTransition(R.anim.slide_left,
//                                            R.anim.slide_right);
//                                }
//                                close();
                                } else if("0".equals(b.getRespcode())){
                                    //showToast("用户名或密码错误");
                                    showToast(b.getMessage());
//                                close();
                                } else{
                                    //showToast("用户不存在");
                                    showToast(b.getMessage());
                                }
                            } else {
                                Toast.makeText(LoginActivity.this, "服务器响应失败", Toast.LENGTH_LONG).show();
//                            close();
                            }
                        }
                    });
                }
//                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
        Toast.makeText(LoginActivity.this, str, Toast.LENGTH_LONG).show();
    }
}
