package com.exercise.swd3.two_degree.twoDegree;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.exercise.swd3.two_degree.R;

/**
 * Created by swd3 on 8/31/15.
 */
public class SendImActivity extends Activity {
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sendim);
        findViewById(R.id.callback).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SendImActivity.this, FriendDetailActivity.class));
            }
        });
     }

}