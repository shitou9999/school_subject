package com.example.administrator.mytable.School;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.administrator.mytable.R;
import com.example.administrator.mytable.School.Entiy.StuInfos;
import com.example.administrator.mytable.School.MyApplication.MyApplication;

import java.util.HashMap;
import java.util.Map;

/**
 * 已经不用了
 * Created by Administrator on 2015/12/30.
 */
public class School_yt_To_Login_NoNeed extends AppCompatActivity
        implements RequestPackage.callLogin{
    private LinearLayout iBack;
    private EditText edit01;
    private EditText edit02;
    private CheckBox checkbox01;
    private CheckBox checkbox02;
    private Button editLogin;
    private RequestPackage pac;
    private MyApplication app;
    private AlertDialog checkDialog;
    private String name;
    private String pwd;
    private SharedPreferences.Editor edit;
    private SharedPreferences preference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schoole_to_login);
        iBack=(LinearLayout)super.findViewById(R.id.iBack);
        edit01=(EditText)super.findViewById(R.id.edit01);
        edit02=(EditText)super.findViewById(R.id.edit02);
        checkbox01=(CheckBox)super.findViewById(R.id.checkbox01);
        checkbox02=(CheckBox)super.findViewById(R.id.checkbox02);
        editLogin=(Button)super.findViewById(R.id.editLogin);
        initOnClikLisener();
        pac=new RequestPackage((MyApplication)this.getApplication(),School_yt_To_Login_NoNeed.this);///////////////////////////////////
        pac.setcllLogin(this);/////////////////////
        //用上次登录的用户名和密码
        preference = getSharedPreferences("person",Context.MODE_PRIVATE);
        edit01.setText(preference.getString("User", "")); //preference.getString(标示符,默认值<这里为空>）
        edit02.setText(preference.getString("Psw", ""));
        checkbox01.setChecked(preference.getBoolean("status", true));
//        注意：这里的preference要分开定义，不能用同一个preference，不然无法生效。
        editLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
                AlertDialog.Builder builder = new AlertDialog.Builder(School_yt_To_Login_NoNeed.this);
                builder.setTitle("登录");
                builder.setMessage("正在登录.....");
                checkDialog = builder.create();
                checkDialog.show();
            }
        });
    }
    private void login(){
        name=edit01.getText().toString();
        pwd=edit02.getText().toString();
        Map<String, String> params=new HashMap<String, String>();//////
        params.put("userName", name);//和后台的参数一致
        params.put("pwd", pwd);//和后台的参数一致
        pac.doLoginByPost((HashMap<String, String>) params);

        //保存本次输入的数据
        preference = getSharedPreferences("person", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = preference.edit();
        edit.putString("User", name);
        edit.putString("Psw", pwd);
        edit.putBoolean("status", true);
        edit.commit();
    }
    @Override
    public void callLogin(StuInfos jsonData) {////////////////////////
        checkDialog.dismiss();
        if("211463501108".equals(jsonData.getUno())&&"111111".equals(jsonData.getPwd())){
            Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(School_yt_To_Login_NoNeed.this, MainActivity_Net.class);
            Bundle bundle=new Bundle();
            bundle.putSerializable("infos", jsonData);
            intent.putExtras(bundle);
            setResult(0, intent);
            finish();
        }else{
            Toast.makeText(this,"登录失败",Toast.LENGTH_SHORT).show();
        }

    }
    private void initOnClikLisener(){
        iBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        checkbox01.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                     edit01.setText(preference.getString("User", name));
                     edit02.setText(preference.getString("Psw", pwd));
                     checkbox01.setChecked(preference.getBoolean("status",true));//设置状态
                } else {
                    edit01.setText("");
                    edit02.setText("");
                }
            }
        });
        checkbox02.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){

                }else {

                }
            }
        });
    }
}
