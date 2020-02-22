package com.dmdddm.ads;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import static com.dmdddm.ads.EncoderByMD5.getMD5SDtring;

public class login extends AppCompatActivity implements View.OnClickListener {
    private EditText userName;
    private EditText userPwd;

    private Button register;
    private Button ForgetPassword;
    private Button Login;
    private TextView error;

    /**全局变量**/
    private String name;
    private URL url;
    private String[] result;
    private Intent iFinish = new Intent();
    private String pwd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_login );

        /**绑定控件 设置点击事件**/
        userName = findViewById(R.id.Account);
        userPwd = findViewById(R.id.Password);

        register = findViewById(R.id.Register);
        register.setOnClickListener(this);

        ForgetPassword = findViewById(R.id.ForgetPassword);
        ForgetPassword.setOnClickListener(this);

        Login = findViewById(R.id.Login);
        Login.setOnClickListener(this);

        error = findViewById(R.id.tips);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ForgetPassword:
                ;
                break;
            case R.id.Register:
                ;
                break;
            case R.id.Login:
                login();
                break;
        }
    }
    /**登录函数**/
    public void login(){
        name = userName.getText().toString();
       pwd = getMD5SDtring(userPwd.getText().toString());

        String[] item={"LoginStatus"};
        //如果账号为空 事件
        if (name.isEmpty()){
            userName.setHint("账户名不能为空!!!");
            userName.setHintTextColor(Color.parseColor("#FF0000"));
        }
        //账号名不为空时
        else {
            try {
                url = new URL("https://www.dmdddm.cn/SWS/LoginController?Mode=login&name="+ URLEncoder.encode(name,"UTF-8")+"&pwd="+pwd);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            MyHTTPConnect myHttpConnect = new MyHTTPConnect();
            result =  myHttpConnect.getJson(url,item,handler);

        }

    }
    /**Handler对象
     * 监听线程
     * 线程完成
     * 执行以下代码
     * **/
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1){
                if (result[0].equals("successful")){
                    /**登录成功**/
                    iFinish.putExtra("UserName",name);
                    setResult(2,iFinish);
                    finish();
                }
                else {
                    /**登录失败**/
                    error.setText("账号或密码有误!!请重新输入");
                    userPwd.setText("");
                }
            }
        }
    };
}
