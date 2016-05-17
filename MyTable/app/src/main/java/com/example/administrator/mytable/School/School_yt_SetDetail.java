package com.example.administrator.mytable.School;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.example.administrator.mytable.R;
import com.example.administrator.mytable.School.Entiy.StuInfos;
import com.example.administrator.mytable.School.MyApplication.MyApplication;
import com.example.administrator.mytable.School.yt_Apk.VersionManager.VersionManger;

/**
 * Created by Administrator on 2015/12/30.
 */
public class School_yt_SetDetail extends AppCompatActivity {
    private LinearLayout iBack;
    private RelativeLayout toSeeInformation;
    private RelativeLayout toChangePassword;
    private RelativeLayout toPersonalCollection;
    private RelativeLayout toCheckVersion;
    private Button exitLogin;
    private AlertDialog checkDialog;
    private ImageView image01;
    private TextView name;
    private TextView userName;
    private MyApplication app;
    private StuInfos stuInfos;
    private CheckBox autoLogin;
    private SharedPreferences sp;
    private boolean isSelector;
    private Bitmap bitmap;
    private VersionManger versionManger;
    @Override
    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.school_yt_slinding_set_detail);
        iBack=(LinearLayout)super.findViewById(R.id.iBack);
        toSeeInformation = (RelativeLayout) super.findViewById(R.id.toSeeInformation);
        toChangePassword = (RelativeLayout) super.findViewById(R.id.toChangePassword);
        toPersonalCollection = (RelativeLayout) super.findViewById(R.id.toPersonalCollection);
        image01=(ImageView)super.findViewById(R.id.image01);
        name=(TextView)super.findViewById(R.id.name);
        userName=(TextView)super.findViewById(R.id.userName);
        app=(MyApplication)super.getApplication();
        autoLogin= (CheckBox) super.findViewById(R.id.autoLogin);
        exitLogin= (Button) super.findViewById(R.id.exitLogin);
        versionManger=new VersionManger(this);
        sp=getSharedPreferences("person", Context.MODE_PRIVATE);
        isSelector=sp.getBoolean("AUTO_ISCHECK", false);
        initView();
        initsetView();
        initautoLoginLisener();
    }

    @Override
    protected void onStart() {
        super.onStart();
       // image01.setImageBitmap(app.getBitmap());
        loadHead(app.getStu());
    }

    private void loadHead(StuInfos stu){
        String url=stu.getImg();
        MyApplication.getInstance().getImageLoader().get(url, ImageLoader.getImageListener(image01, R.mipmap.logo, R.mipmap.logo));
    }

    private void initautoLoginLisener() {
        autoLogin.setChecked(isSelector);
        autoLogin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                sp.edit().putBoolean("AUTO_ISCHECK", isChecked).commit();
            }
        });
    }

    private void initsetView(){
        Intent intent=super.getIntent();
        Bundle bundle=intent.getExtras();
        stuInfos=(StuInfos)bundle.get("infos");
        app.getImageLoader().get(stuInfos.getImg(),
                app.getImageLoader().getImageListener(image01, R.mipmap.cc_default_head, R.mipmap.cc_default_head));
        name.setText(stuInfos.getUno());
        userName.setText(stuInfos.getStuName());
    }
    private void initView() {
        iBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(School_yt_SetDetail.this,MainActivity_Net.class);
                Bundle bundle=new Bundle();
                bundle.putParcelable("data",bitmap);
                intent.putExtras(bundle);
                setResult(4,intent);
                finish();
            }
        });
        toSeeInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(School_yt_SetDetail.this,School_yt_ToSee_UserDetail.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("infos", stuInfos);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        toChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(School_yt_SetDetail.this, School_yt_ToChangePassword.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("infos", stuInfos);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        toPersonalCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(School_yt_SetDetail.this, School_yt_PersonalCollection.class);
                startActivity(intent);
            }
        });

    }

    public void exitLoginDialog(View view) {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("退出登录");
        builder.setMessage("确定退出当前登录用户？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Bitmap bitmap = BitmapFactory.decodeResource(School_yt_SetDetail.this.getResources(), R.mipmap.logo);
                app.setBitmap(bitmap);
                sp.edit().putBoolean("autoLogin", false).commit();
                dialog.dismiss();
                app.setStu(null);
               // System.exit(0);//////////////
                finish();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog=builder.create();
        dialog.show();
    }
    public void updataDialog(View view) {
          versionManger.getVersionInfo(handler);
    }
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case VersionManger.GET_VERSTON_SUCCESS:
                    if(versionManger.checkUpdate()){
                        //显示更新对话框
                        showUpdateDialog();
                    }else{
                        Toast.makeText(School_yt_SetDetail.this, "已经是最新版本", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    };
    private void showUpdateDialog(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("更新软件").setMessage("发现新版本，是否更新");
        builder.setPositiveButton("更新", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                versionManger.downloadAPK();
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        versionManger.clear();  //释放广播
    }
    //    private Handler handler=new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            switch (msg.what){
//                case 0:
//                    checkDialog.dismiss();//UI操作在主线程里面执行
//                    checkResultDialog();
//                    break;
//            }
//        }
//    };
//    public void checkResultDialog() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("恭喜");
//        builder.setMessage("更新成功");
//        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.dismiss();
//            }
//        });
//        AlertDialog dialog = builder.create();
//        dialog.show();
//    }
//
//    public void updataDialog(View view) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("提示");
//        builder.setMessage("正在检测更新.....");
//        checkDialog = builder.create();
//        checkDialog.show();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                handler.sendEmptyMessage(0);
//            }
//        }).start();
//    }
}
