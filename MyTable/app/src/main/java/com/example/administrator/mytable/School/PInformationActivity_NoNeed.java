package com.example.administrator.mytable.School;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
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
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.administrator.mytable.R;
import com.example.administrator.mytable.School.MyApplication.MyApplication;
import com.example.administrator.mytable.School.MyStringRequest.MyStringRequest;
import com.example.administrator.mytable.School.util.BitmapUtil;
import com.example.administrator.mytable.School.util.FileUitlity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 杨帆头像上传测试用
 */
public class PInformationActivity_NoNeed extends AppCompatActivity implements View.OnClickListener{
    private TextView tvBack;
    private Bundle bundle;
    private MyApplication app;
    private CircleImageView image;
    private PopupWindow window;
    private Bitmap roundHead;
    private String headFile;
    private final int CAMERA_PHOTO=1;
    private final int STORE_PHOTO=2;
    private final int RESULT_PHOTO=3;
    private Uri uri;
    private RelativeLayout setPicture;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_yt__to_see__user_detail_test);
        setPicture= (RelativeLayout) super.findViewById(R.id.setPicture);
        image=(CircleImageView)super.findViewById(R.id.image);
        app=(MyApplication)super.getApplication();
        setPicture.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        View popwinView= LayoutInflater.from(this).inflate(R.layout.yt_set_activity_camera_menu,null);
        int popWidth=v.getWidth();
        //int popWidth= DisplayUtil.getScreenWidth(this)/4;
        int popHeight=this.getResources().getDisplayMetrics().heightPixels/3;
        window=new PopupWindow(popwinView, popWidth,popHeight);
        window.setFocusable(true);
        window.setBackgroundDrawable(new ColorDrawable(0));
//        int[] location=new int[2];
//        v.getLocationOnScreen(location);
        window.setAnimationStyle(R.style.popWindow_Share_Show);
        WindowManager.LayoutParams params=PInformationActivity_NoNeed.this.getWindow().getAttributes();
        params.alpha=0.7f;

        PInformationActivity_NoNeed.this.getWindow().setAttributes(params);
        window.showAtLocation(v, Gravity.BOTTOM, 0, 0);
        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams params2 = PInformationActivity_NoNeed.this.getWindow().getAttributes();
                params2.alpha = 1f;
                PInformationActivity_NoNeed.this.getWindow().setAttributes(params2);
            }
        });

    }


    public void canel(View view){
        window.dismiss();
    }
    public void camera(View view){
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            //调用相机
            Intent camera=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            File parent= FileUitlity.getInstance(this).makeDir("head_img");
            headFile=parent.getPath()+File.separator+System.currentTimeMillis()+".jpg";
            camera.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(headFile)));
            camera.putExtra(MediaStore.EXTRA_VIDEO_QUALITY,1);
            startActivityForResult(camera, CAMERA_PHOTO);

        }
    }
    public void photo(View view){
        Intent photo=new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(photo, STORE_PHOTO);
    }
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
        b.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        try {
            baos.flush();
            baos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String  headPicString = new String(Base64.encode(
                baos.toByteArray(), 0));
        return headPicString;




    }
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
        MyApplication.getInstance().getRequestQueue().add(request);
    }
    private Handler handler=new Handler(){

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    //MyApplication.getInstance().clearBitmapCache(MyApplication.getInstance().getStu().getImg());
//                   String imgUrl=(String)msg.obj;
//                   MyApplication.getInstance().getStu().setImg(msg.obj.toString());
                    image.setImageBitmap(roundHead);
                    break;
            }
        }
    };
}
