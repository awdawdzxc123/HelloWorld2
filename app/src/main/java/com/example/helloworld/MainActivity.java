package com.example.helloworld;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.helloworld.databinding.ActivityMainBinding;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding mBinding;
    public static  final int RESULT_CODE =100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        mBinding.buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //intent:意图分为是显示和隐式
                //new Intent的参数上下文,目标activity的类
                String phone=mBinding.editPhone.getText().toString();
                String pwd=mBinding.editPwd.getText().toString();
                SharedPreferences sp=getSharedPreferences("user_info",MODE_PRIVATE);
                String temp_phone =sp.getString("phone_"+phone,"error");
                String temp_pwd =sp.getString("pwd_"+phone,"error");
                if(phone.equals(temp_phone)&&pwd.equals(temp_pwd)){

                    String userName = sp.getString("name_" + phone, "0");
                    String userSex = sp.getString("sex_" + phone, "0");
                    String userSms = "1".equals(sp.getString("sms_" + phone, "0")) ? "接受":"不接受";

                    //自定义的activity的跳转
                    HomeActivity.actionStart(MainActivity.this,userName,pwd,userSex,phone,userSms,RESULT_CODE);
                }else{
                    Toast.makeText(MainActivity.this,"手机号或密码错误",Toast.LENGTH_LONG).show();
                }

            }
        });
        mBinding.buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,DemoActivity.class);
                startActivity(intent);

            }
        });
        mBinding.buttonForget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,DemoActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==RESULT_CODE){
            if (resultCode==RESULT_OK){
                String s= Objects.requireNonNull(data).getStringExtra(HomeActivity.EXIT_HOME);
                Toast.makeText(MainActivity.this,s,Toast.LENGTH_LONG).show();
            }
        }
    }
}
