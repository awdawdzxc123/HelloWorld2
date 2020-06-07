package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.example.helloworld.databinding.ActivityHomeBinding;

import java.util.Objects;

public class HomeActivity extends AppCompatActivity {
    private ActivityHomeBinding mBinding;
    private long exitTime=0;
   public static final String EXIT_HOME ="exit_home";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding=ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        Intent intent=getIntent();//获取传过来的Intent对象
        //接受转过来的Intent对象
        UserInfo u=(UserInfo) intent.getSerializableExtra("userIntfo");
        //取出对应的值
        String  Phone  = Objects.requireNonNull(u).getShone();
        String userName =u.getUserName();
        String userSex =u.getSex();
        String userSms =u.getSms();

        mBinding.userPhone.setText(Phone);
        mBinding.userName.setText(userName);
        mBinding.userSex.setText(userSex);
        String temp =mBinding.userSms.getText().toString()+":"+userSms;
        mBinding.userSms.setText(temp);
        //返回数据
        intent.putExtra(EXIT_HOME,"你已经退出账号");
        setResult(RESULT_OK,intent);
    }

    /**
     * 拦截系统返回键
     */
    @Override
    public void onBackPressed() {
        exit();

    }
    private void exit(){
        long time=2000;
        if (System.currentTimeMillis() - exitTime > time){
            //存储此次点击返回键的时间
            exitTime=System.currentTimeMillis();
            Toast.makeText(getApplicationContext(),"快速点击两次,退出当前账号",Toast.LENGTH_SHORT).show();
        }else{
            //完全退出APP
            finish();
        }
    }
}
