package com.example.administrator.mytable.School;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.example.administrator.mytable.R;
import com.example.administrator.mytable.School.Entiy.StuInfos;
import com.example.administrator.mytable.School.MyApplication.MyApplication;
import com.example.administrator.mytable.School.MyStringRequest.MyStringRequest;
import com.example.administrator.mytable.School.util.BitmapUtil;
import com.example.administrator.mytable.School.util.FileUitlity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class School_yt_ToSee_UserDetail extends AppCompatActivity implements View.OnClickListener{
    private LinearLayout iBack;
    private StuInfos stuInfos;
    private ImageView image;
    private TextView name;
    private TextView num;
    private TextView classs;
    private TextView math;
    private MyApplication app;
    private RelativeLayout setPicture;
//    private PopupWindow mPopupWindow;
    private PopupWindow window;
    private Bitmap roundHead;
    private SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_yt__to_see__user_detail);
        image=(ImageView)super.findViewById(R.id.image);
        name=(TextView)super.findViewById(R.id.name);
        num=(TextView)super.findViewById(R.id.num);
        classs=(TextView)super.findViewById(R.id.classs);
        math=(TextView)super.findViewById(R.id.math);
        app=(MyApplication)super.getApplication();//////////////////////////////
        iBack=(LinearLayout)super.findViewById(R.id.iBack);
        setPicture= (RelativeLayout) super.findViewById(R.id.setPicture);
        setPicture.setOnClickListener(this);
        initSetView();
        setViewLisener();
    }
    @Override
     protected void onStart() {
        super.onStart();
        //image.setImageBitmap(app.getBitmap());
        loadHead(app.getStu());
    }
    private void initSetView(){
        Intent intent=super.getIntent();
        Bundle bundle=intent.getExtras();
        stuInfos=(StuInfos)bundle.get("infos");
        name.setText(stuInfos.getStuName());
        num.setText(stuInfos.getUno());
        classs.setText(stuInfos.getClassName());
        math.setText(stuInfos.getMajorName());
        app.getImageLoader().get(stuInfos.getImg(),
                app.getImageLoader().getImageListener(image, R.mipmap.cc_default_head, R.mipmap.cc_default_head));
    }

    private void setViewLisener(){
        iBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    @Override
    public void onClick(View v) {
        View view= LayoutInflater.from(this).inflate(R.layout.yt_set_activity_camera_menu, null);
        window=new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        window.setAnimationStyle(R.style.popWindow_Share_Show);
        window.setFocusable(true);
        window.setOutsideTouchable(true);
        backgroundAlpha(0.3f);
        window.setBackgroundDrawable(new ColorDrawable(0));
        window.showAtLocation(v, Gravity.BOTTOM, 0, 0);
        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1f);
            }
        });
    }
    /**
     * 设置添加屏幕的背景透明度
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha){
        WindowManager.LayoutParams lp=getWindow().getAttributes();
        lp.alpha=bgAlpha;
        getWindow().setAttributes(lp);
    }
    public void canel(View view){
        window.dismiss();
    }
    private String headFile;
    private final int CAMERA_PHOTO=1;//照的
    private final int STORE_PHOTO=2;//本地图库
    private String headName;
    public void camera(View view){
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            //调用相机
            Intent camera=new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            File parent= FileUitlity.getInstance(this).makeDir("head_img");
            headName=System.currentTimeMillis()+".jpg";
            headFile=parent.getPath()+File.separator+headName;
            camera.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(headFile)));
            camera.putExtra(MediaStore.EXTRA_VIDEO_QUALITY,1);
            startActivityForResult(camera, CAMERA_PHOTO);

        }
    }
    public void photo(View view){
        Intent photo=new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(photo, STORE_PHOTO);
    }

    private Uri uri;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode!= Activity.RESULT_OK){
            return;
        }
        switch(requestCode){
            case CAMERA_PHOTO:
                uri=Uri.fromFile(new File(headFile));
                startPhotoZoom(uri);
                break;
            case STORE_PHOTO:
                if(data!=null){
                    uri=data.getData();
                    startPhotoZoom(uri);
                }
                break;
            case RESULT_PHOTO:
                Bundle extras = data.getExtras();
                if (extras != null) {
                    Bitmap b = extras.getParcelable("data");
                    roundHead= BitmapUtil.toRoundBitmap(b);//裁剪图片
                    alertUpdateDialog(roundHead);
                }
                break;
        }

    }


    public void alertUpdateDialog(final Bitmap b){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        View alertView = LayoutInflater.from(this).inflate(R.layout.diy_dialog, null);
        builder.setView(alertView);
        final AlertDialog alertAialog = builder.create();
        alertView.findViewById(R.id.PositiveButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertAialog.dismiss();
                updatePhoto(convertBitmap(b));
            }
        });
        alertView.findViewById(R.id.NegativeButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertAialog.dismiss();
            }
        });
        TextView title = (TextView)alertView.findViewById(R.id.Title);
        title.setText("提示");
        TextView subTitle= (TextView)alertView.findViewById(R.id.SubTitle);
        subTitle.setText("确定上传此头像吗？");
        alertAialog.show();
    }

    private final int RESULT_PHOTO=3;
    /**
     * camera系统裁剪照片
     * @param uri
     */
    public void startPhotoZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 设置裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, RESULT_PHOTO);
    }

    /**
     * 将Bitmap转换为Base64编码的字符串
     * @param b
     * @return
     */
    public String convertBitmap(Bitmap b){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        b.compress(Bitmap.CompressFormat.PNG, 100, baos);
        try {
            baos.flush();
            baos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String  headPicString = new String(Base64.encode(baos.toByteArray(), 0));
        try {
           if(baos!=null) baos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return headPicString;
    }

    /**
     * 上传至服务器数据库
     * @param photoStr
     */
    public void updatePhoto(String photoStr){
        // final MyApplication user = MyApplication.getInstance().getCurUser();
        final ProgressDialog pd = new ProgressDialog(this);
        pd.setMessage("正在上传，请稍后……");
        if (pd != null)
            pd.show();
        // 加载网络数据
        String url="http://211.86.106.63/SchoolLife/LoginServlet";
        Map<String,String> params=new HashMap<String,String>();
        params.put("userName", "211463501108");
        params.put("pwd", "1111111");
        params.put("headImage","1");
        params.put("uhead", photoStr);
        MyStringRequest request=new MyStringRequest(url, params, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(pd!=null && pd.isShowing())
                    pd.dismiss();
                    Message message=new Message();
                    message.obj=response;
                    message.what=1;
                    handler.sendMessage(message);
                Toast.makeText(getApplicationContext(), "头像上传成功！", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(pd!=null && pd.isShowing())
                    pd.dismiss();
                Toast.makeText(getApplicationContext(), "头像上传失败！", Toast.LENGTH_SHORT).show();
            }
        });
        request.setRetryPolicy(new DefaultRetryPolicy(10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MyApplication.getInstance().getRequestQueue().add(request);

    }
   private  Handler handler=new Handler(){

        @Override
        public void handleMessage(Message msg) {
           switch (msg.what){
               case 1:
                   //MyApplication.getInstance().clearBitmapCache(MyApplication.getInstance().getStu().getImg());
                   String imgUrl=(String)msg.obj;
                   MyApplication.getInstance().getStu().setImg(imgUrl);
                   image.setImageBitmap(roundHead);
                   break;
           }
        }
    };

    private void loadHead(StuInfos stu){
        String url=stu.getImg();
        MyApplication.getInstance().getImageLoader().
                get(url, ImageLoader.getImageListener(image, R.mipmap.logo, R.mipmap.logo));
    }


    /*
    private void initConmmentPopWindow(){
        View view=LayoutInflater.from(this).inflate(R.layout.yt_set_activity_camera_menu,null);
        mPopupWindow=new PopupWindow(view);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setBackgroundDrawable(new ColorDrawable(0));
        //1.弹出popupwindow，背景变暗
        WindowManager.LayoutParams lp=getWindow().getAttributes();
        lp.alpha=0.4f;//0.0-1.0
        getWindow().setAttributes(lp);
        mPopupWindow.setAnimationStyle(R.style.popWindowShareShow);
    }
    public void showCommentPopWindow(View view){
        initConmmentPopWindow();
        int windth=DisplayUtil.getScreenWidth(this);
        int heigth=DisplayUtil.getScreenHeight(this)/3;
//        int heigth=view.getHeight();
        mPopupWindow.setWidth(windth);
        mPopupWindow.setHeight(heigth);
        mPopupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
        //2.popupwindow消失之后，背景恢复
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1;
                getWindow().setAttributes(lp);
            }
        });
    }
    */
}
