package com.exercise.swd3.two_degree.util;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.exercise.swd3.two_degree.callback.ResultCallback;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.util.List;

@TargetApi(Build.VERSION_CODES.GINGERBREAD)
public class HttpUtil {

	
	public static void doPost(final String reqUrl,final List<NameValuePair> params,ResultCallback callback){

		resultCallback=callback;
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Message message=new Message();
				
				HttpPost httpPost=new HttpPost(reqUrl);
				try{
					httpPost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
					HttpResponse httpResponse;
					try{
						Log.d("jayson", "start get response");
						httpResponse=new DefaultHttpClient().execute(httpPost);
						if(httpResponse.getStatusLine().getStatusCode()==200){//response success
							Log.d("jayson","response successs");
							String result = EntityUtils.toString(httpResponse.getEntity());
							if(!result.isEmpty()){
								message.what=0;
								message.obj=result;
								Log.d("jayson", "get result successs");
								myHandler.sendMessage(message);
							}else{
								message.what=1;
								myHandler.sendMessage(message);
							}
						}else{
							Log.d("jayson","response faild");
							message.what=1;
							myHandler.sendMessage(message);
						}
					}catch(Exception e){
						Log.d("jayson","exception1:"+e.getMessage());
						message.what=1;
						myHandler.sendMessage(message);
						e.printStackTrace();
					}
				}catch(Exception e){
					Log.d("jayson","exception2:"+e.getMessage());
					message.what=1;
					myHandler.sendMessage(message);
					e.printStackTrace();
				}
			}
		}).start();
		
	}
	
	static Handler myHandler=new Handler(){
		@Override
		public void handleMessage(android.os.Message msg) {
			
			if(msg.what==1){
				Log.d("jayson","callback faild");
				resultCallback.getReslt("1");
			}else if(msg.what==0){
				Log.d("jayson","callback success");
				String result=(String) msg.obj;
				resultCallback.getReslt(result);
			}
		};
	};
	
	static ResultCallback resultCallback=new ResultCallback() {
		
		@Override
		public void getReslt(String result) {
			// TODO Auto-generated method stub
			Log.d("jayson","getReslt init");
		}
	};
}
