package com.example.administrator.mytable.School;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.administrator.mytable.R;
import com.example.administrator.mytable.School.Entiy.StuInfos;
import com.example.administrator.mytable.School.MyApplication.MyApplication;

import java.util.HashMap;
import java.util.Map;

public class School_yt_ToChangePassword extends AppCompatActivity
        implements RequestPackage.callChangePwd{
    private LinearLayout iBack;
    private EditText edit01;
    private EditText edit02;
    private EditText edit03;
    private Button editLogin;
    private MyApplication app;
    private RequestPackage pac;
    private StuInfos stuInfos;
    private ProgressDialog m_pDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.school_yt__to_change_password_detail);
        edit01= (EditText) super.findViewById(R.id.edit01);
        edit02= (EditText) super.findViewById(R.id.edit02);
        edit03= (EditText) super.findViewById(R.id.edit03);
        editLogin= (Button) super.findViewById(R.id.editLogin);
        iBack=(LinearLayout)super.findViewById(R.id.iBack);
        iBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        pac= new RequestPackage((MyApplication) this.getApplication(),School_yt_ToChangePassword.this);///////////////////////
        pac.setCallChangePwd(this);////////////////
        Intent intent=super.getIntent();
        Bundle bundle=intent.getExtras();
        stuInfos=(StuInfos)bundle.get("infos");
        editLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initChangePwd();////////////////////
                m_pDialog=new ProgressDialog(School_yt_ToChangePassword.this);
                m_pDialog.setTitle("提示");
                m_pDialog.setMessage("正在修改数据，请稍后！");
                m_pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);// 设置进度条风格，风格为圆形，旋转的
                m_pDialog.setIndeterminate(false);// 设置ProgressDialog 的进度条是否不明确
                m_pDialog.setCancelable(true);// 设置ProgressDialog 是否可以按退回按键取消
                m_pDialog.show();
            }
        });
    }
    private void initChangePwd(){
        String e1=edit01.getText().toString();//用户输入老密码
        String e2=edit02.getText().toString();//用户输入
        String e3=edit03.getText().toString();//用户输入新密码
        Map<String, String> params=new HashMap<String, String>();
        if(e1.equals(stuInfos.getPwd())&&e2.equals(e3)){
            params.put("userName",stuInfos.getUno());//用户名
            params.put("pwd",e3);//修改的密码
        }
        pac.dochangePwdByPost((HashMap<String, String>) params);
    }
    @Override
    public void callChangePwd(String flag){
        try {
            Thread.sleep(2000);
            m_pDialog.dismiss();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if("1".equals(flag)){
            Toast.makeText(School_yt_ToChangePassword.this, "密码修改成功", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(School_yt_ToChangePassword.this, "密码不一致，请重新修改", Toast.LENGTH_SHORT).show();
        }
    }
}
