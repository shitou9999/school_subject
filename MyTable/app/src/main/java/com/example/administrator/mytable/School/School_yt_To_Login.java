package com.example.administrator.mytable.School;

import android.app.AlertDialog;
import android.content.Context;
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
import com.example.administrator.mytable.School.db.CheckHttpUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2015/12/30.
 */
public class School_yt_To_Login extends AppCompatActivity implements RequestPackage.callLogin{
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
    private SharedPreferences sp;
    private boolean checkbox1;
    private boolean checkbox2;
    private boolean autoLogin=false;
    private CheckHttpUtil checkHttpUtil;
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
        app= (MyApplication)super.getApplication();
        pac=new RequestPackage((MyApplication)this.getApplication(),School_yt_To_Login.this);
        pac.setcllLogin(this);
        //用上次登录的用户名和密码
        sp = getSharedPreferences("person",Context.MODE_PRIVATE);
        checkbox1=sp.getBoolean("ISCHECK", false);
        checkbox2=sp.getBoolean("AUTO_ISCHECK",false);
        if(checkbox1){
            checkbox01.setChecked(checkbox1);
            edit01.setText(sp.getString("User",""));
            edit02.setText(sp.getString("Psw", ""));
        }else{
            checkbox01.setChecked(checkbox1);
        }
        if(checkbox2){
            checkbox02.setChecked(checkbox2);
        }
        editLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkHttpUtil.checkNetWorkConnectionState(School_yt_To_Login.this)){
                    login();
                    AlertDialog.Builder builder = new AlertDialog.Builder(School_yt_To_Login.this);
                    builder.setTitle("登录");
                    builder.setMessage("正在登录.....");
                    checkDialog = builder.create();
                    checkDialog.show();
                }else{
                    Toast.makeText(School_yt_To_Login.this, "请检查网络", Toast.LENGTH_SHORT).show();
                }
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
        //登录成功和记住密码框为选中状态才保存用户信息
        if(checkbox01.isChecked()) {   //保存本次输入的数据
            sp = getSharedPreferences("person", Context.MODE_PRIVATE);
            //记住用户名、密码、
            SharedPreferences.Editor edit = sp.edit();
            edit.putString("User", name);
            edit.putString("Psw", pwd);
            edit.putBoolean("status", true);
            edit.commit();
        }
    }
    @Override
    public void callLogin(StuInfos jsonData) {
        checkDialog.dismiss();
        if("bad users".equals(jsonData.getInfo())){
            Toast.makeText(this,"登录失败,请检查用户名和密码",Toast.LENGTH_SHORT).show();
        }else{
//            if("211463501108".equals(jsonData.getUno())&&"111111".equals(jsonData.getPwd())){
                Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
                //保存登录状态、、、、、
//                sp.edit().putBoolean("isLogin",true).commit();
//                String url=jsonData.getImg();
//                ImageRequest request=new ImageRequest(url, new Response.Listener<Bitmap>() {
//                    @Override
//                    public void onResponse(Bitmap response) {
//                        app.setBitmap(response);
//                    }
//                },0,0, Bitmap.Config.RGB_565,new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//
//                    }
//                });
             //   app.getRequestQueue().add(request);
                sp.edit().putBoolean("autoLogin",true).commit();
//                Intent intent = new Intent(School_yt_To_Login.this, MainActivity_Net.class);
//                Bundle bundle=new Bundle();
//                bundle.putSerializable("infos", jsonData);
//                intent.putExtras(bundle);
//                setResult(0, intent);
                app.setStu(jsonData);
                finish();
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
                sp.edit().putBoolean("ISCHECK", isChecked).commit();
            }
        });

        checkbox02.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                sp.edit().putBoolean("AUTO_ISCHECK",isChecked).commit();
            }
        });
    }
}

