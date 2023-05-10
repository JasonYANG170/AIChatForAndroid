package com.YANG.Chat;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.R;
import org.json.JSONException;
import org.json.JSONObject;

public class SecondActivity extends AppCompatActivity {
    String know="";
    public static boolean isNumeric(String str){
        for(int i=str.length();--i>=0;){
            int chr=str.charAt(i);
            if(chr<48 || chr>57)
                return true;
        }
        return false;
    }
    public static boolean isNumeri2(String str){
       if(str.equals("")){
                return true;
        }
        return false;
    }
     //   private long mPressedTime = 0; // 用于记录返回键按下时间

        @Override
        public void onBackPressed() {
         //   long mNowTime = System.currentTimeMillis(); // 获取当前时间
         //   if ((mNowTime - mPressedTime) > 2000) {
                // 和前一次按返回键时间差大于2000ms，给出提示并记录这次按键时间
           //     Toast.makeText(this, "再按一次返回键退出应用程序", Toast.LENGTH_SHORT).show();
           //     mPressedTime = mNowTime;
          //  } else {
                // 和前一次按返回键时间差小于等于2000ms，退出应用程序
                moveTaskToBack(true);
         //   }
        }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second2);
        RequestQueue queue2 = Volley.newRequestQueue(this);
        JsonObjectRequest request2 = new JsonObjectRequest(Request.Method.GET, "https://api.vvhan.com/api/qqsc?key=d99dcb4f1464d219df975eb56d91733c", null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String asd = response.toString();
                     //   Toast.makeText(SecondActivity.this, response.toString(), Toast.LENGTH_LONG).show();
                        try {
                            JSONObject jsonObject = new JSONObject(asd);
                            String ves = jsonObject.getString("text");
                            if(ves.equals("V3.0")) {
                                bt = findViewById(R.id.button);
                                bt.setTextSize(15);
                                bt.setText("暂无更新");
                            }else{
                               // tv1 = findViewById(R.id.textView3);
                               // tv1.setTextSize(20);
                                bt = findViewById(R.id.button);
                                bt.setTextSize(15);
                                bt.setText("发现新版本");
                                know="发现新版本\""+ves+"\"请更新。\n\n";
                              //  Toast.makeText(SecondActivity.this, know, Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
// 处理错误
                     Toast.makeText(SecondActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        queue2.add(request2);
      //  RequestQueue queue2 = Volley.newRequestQueue(this);
        JsonObjectRequest request3 = new JsonObjectRequest(Request.Method.GET, "https://api.vvhan.com/api/qqsc?key=d0bad3425fe73b651253c3dea47c759d", null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String asd = response.toString();
                      //  Toast.makeText(SecondActivity.this, response.toString(), Toast.LENGTH_LONG).show();
                        try {
                            JSONObject jsonObject = new JSONObject(asd);
                            String ves1 = "公告：\n"+jsonObject.getString("text");
                           know=know+ves1;

                                tv1 = findViewById(R.id.textView3);
                                tv1.setTextSize(20);
                                tv1.setText(know);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
// 处理错误
                       Toast.makeText(SecondActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        queue2.add(request3);
    }
    private TextView tv1;
    int passcard=0;
    public void myOnclick(View view) {
        // EditText tv1=(EditText) findViewById(R.id.editTextTextPersonName);
//获取文本
        EditText tv2 = (EditText) findViewById(R.id.editTextTextPersonName);
        if (passcard == 0) {
            tv1 = findViewById(R.id.textView3);
            tv1.setTextSize(20);
            tv1.setText("正在等待服务器验证您的密钥......");
            RequestQueue queue2 = Volley.newRequestQueue(this);
            String pass2 = tv2.getText().toString();
            if(isNumeric(pass2)||isNumeri2(pass2)){
                tv1 = findViewById(R.id.textView3);
                tv1.setTextSize(20);
                tv1.setText("请先输入密钥完成身份验证");
               et = findViewById(R.id.editTextTextPersonName);
                et.setText("");
            }else {


                int pass3 = Integer.valueOf(pass2);
                // RequestQueue queue = Volley.newRequestQueue(this);
                JsonObjectRequest request2 = new JsonObjectRequest(Request.Method.GET, "https://api.vvhan.com/api/qqsc?key=492fa5701060de4b2f30e08cbbe2defe", null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                String asd = response.toString();
                             //   Toast.makeText(SecondActivity.this, response.toString(), Toast.LENGTH_LONG).show();
                                try {
                                    JSONObject jsonObject = new JSONObject(asd);
                                    int name = jsonObject.getInt("text");
                                    if (name-2004 == pass3) {
                                        passcard = 1;
                                    //    Toast.makeText(SecondActivity.this, "pass", Toast.LENGTH_LONG).show();
                                        tv1 = findViewById(R.id.textView3);
                                        tv1.setTextSize(20);
                                        tv1.setText("验证成功，您已成功登录。");
                                        et = findViewById(R.id.editTextTextPersonName);
                                        et.setText("");
                                    } else {
                                        tv1 = findViewById(R.id.textView3);
                                        tv1.setTextSize(20);
                                        tv1.setText("验证失败，请检查您的密钥或与开发者联系。");
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }


                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
// 处理错误
                                Toast.makeText(SecondActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                queue2.add(request2);
            }
        } else {

            // EditText tv2=(EditText) findViewById(R.id.editTextTextPersonName);
            String name = tv2.getText().toString();
            TextView mTextView = null;
            setContentView(R.layout.activity_second2);

            RequestQueue queue = Volley.newRequestQueue(this);
            tv1 = findViewById(R.id.textView3);
            tv1.setTextSize(20);
            tv1.setText("正在等待ChatGPT回复，请稍后......");
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, "https://hub.onmicrosoft.cn/chat?q=" + name, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            String asd = response.toString();
                   //         Toast.makeText(SecondActivity.this, response.toString(), Toast.LENGTH_LONG).show();
                            try {
                                JSONObject jsonObject = new JSONObject(asd);
                                String name = jsonObject.getString("answer");
                                tv1 = findViewById(R.id.textView3);
                                tv1.setTextSize(20);
                                tv1.setText(name);
                                et = findViewById(R.id.editTextTextPersonName);
                                et.setText("");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
// 处理错误
                            Toast.makeText(SecondActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

// 将请求添加到请求队列中request2

            queue.add(request);
        }
    }
    private EditText et;
    private Button bt;
    public void myOnclick2(View view) {

        Uri uri = Uri.parse("https://www.baidu.com");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);

    }


}