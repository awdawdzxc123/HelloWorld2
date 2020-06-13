package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.helloworld.databinding.ActivityDemoBinding;

public class DemoActivity extends AppCompatActivity  {

    private ActivityDemoBinding mBinding;
    String name,sex="",pwd,pwdok,phone;
    boolean sms,protocol;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding=ActivityDemoBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        mBinding.buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name=mBinding.editName.getText().toString().trim();
                pwd=mBinding.editPwd.getText().toString().trim();
                pwdok=mBinding.editPwdOk.getText().toString().trim();
                phone=mBinding.editPhone.getText().toString().trim();
                sms=mBinding.switchSms.isChecked();
                protocol =mBinding.checkProtocol.isChecked();
                String toast;
                if(name.equals("")){
                    toast ="请填写昵称";
                }else if (phone.equals("")){
                    toast="请填写手机号码";
                }else if (sex.equals("")) {
                    toast = "请选择性别";
                }else if (pwd.length()!=6){
                toast="密码长度必须为6位";
                }else if (!pwd.equals(pwdok)){
                    toast="请确保两次输入密码一致";
                }else if (phone.length()!=11){
                    toast="请使用正确中国大陆手机号码";
                }else if (!protocol) {
                    toast = "请同意本软件的相关协议和政略,否则无法注册";
                }else{
                    //存储该用户的注册数据
                    //Sharedpreferences存储技术
                    SharedPreferences sp = getSharedPreferences("user_info", MODE_PRIVATE);
                    String temp = sp.getString("phone_" + phone, "0");//如果是0，说明没有找到这个手机
                    if (!temp.equals("0")) {//如果不是0，则表示该手机号码是否已被注册
                        toast = "该数据号码也被注册";
                    } else {
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString("phone_" + phone, phone);
                        editor.putString("name_" + phone, name);
                        editor.putString("sex_" + phone, sex);
                        editor.putString("pwd" + phone, pwd);
                        temp =(sms?"1":"0");
                        editor.putString("sms_" + phone, temp);
                        editor.apply();
                        toast = "注册成功";
                        finish();
                        //跳转至登录界面
                    }
                }
                Toast.makeText(DemoActivity.this,toast,Toast.LENGTH_LONG).show();
            }
        });
        mBinding.radioGroupSex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId==R.id.radio_man){
                    sex = mBinding.radioMan.getText().toString();
            }else {
                    sex = mBinding.radioWomen.getText().toString();
                }

            }

        });
    }
}
