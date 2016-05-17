package com.example.administrator.mytable.School;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.administrator.mytable.School.Entiy.StuInfos;
import com.example.administrator.mytable.School.Entiy.Theme;
import com.example.administrator.mytable.School.HttpURL.HttpURL;
import com.example.administrator.mytable.School.MyApplication.MyApplication;
import com.example.administrator.mytable.School.MyStringRequest.MyStringRequest;
import com.example.administrator.mytable.School.db.CheckHttpUtil;
import com.example.administrator.mytable.School.db.DBManager;
import com.example.administrator.mytable.School.yt_LinkMan.yt_Entity.Class;
import com.example.administrator.mytable.School.yt_LinkMan.yt_Entity.Major;
import com.example.administrator.mytable.School.yt_LinkMan.yt_Entity.Role;
import com.example.administrator.mytable.School.yt_LinkMan.yt_HttpURl.yt_HttpURl;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2016/1/16.
 * 封装的类
 */
public class RequestPackage {
    private MyApplication application;
    private callBack callBack;
    private callLogin callLogin;
    private Context context;
    private CheckHttpUtil checkHttpUtil;

    public RequestPackage(MyApplication application,Context context) {
        this.application = application;
        this.context=context;
    }
    //***********************************************************************************
    public interface callBack{
        void call(List list);
    }
    public void setCallBack(callBack callBack) {
        this.callBack = callBack;
    }

    private String url="";
    public void getRequest(String channel){
        url= HttpURL.getHttpURLDetil(channel);/////////
        if(checkHttpUtil.checkNetWorkConnectionState(context)){
            MyStringRequest request=new MyStringRequest(url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            //读取缓存数据
//                            String data = dbManager.getData(url);//根据url获取缓存数据
                            DBManager dbManager = new DBManager(context);
                            dbManager.insertData(url, response);
                            Gson gson=new Gson();
                            List<Theme> subjectHeadNetList=gson.fromJson(response,new TypeToken<List<Theme>>(){}.getType());
                            callBack.call(subjectHeadNetList);
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });
            application.getRequestQueue().add(request);
        }else{
            DBManager dbManager = new DBManager(context);
            String data=dbManager.getData(url);
            if(data!=null&&!data.equals("")) {
                Gson gson = new Gson();
                List<Theme> subjectHeadNetList = gson.fromJson(data, new TypeToken<List<Theme>>() {
                }.getType());
                callBack.call(subjectHeadNetList);
            }
        }

    }
    //***********************************************************************************
    public interface callLogin{
        void callLogin(StuInfos jsonData);
    }
    public void setcllLogin(callLogin callLogin){
        this.callLogin= callLogin;
    }
    public void doLoginByPost(HashMap<String,String> params){  //封装起来用
        String url=HttpURL.Login;
            final MyStringRequest request=new MyStringRequest(url, params,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Gson gson=new Gson();
                            StuInfos jsonData=gson.fromJson(response, StuInfos.class); //直接返回一个类
//                          application.setStuInfos(jsonData);//保存在app里面
                            callLogin.callLogin(jsonData);
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                        }
                    });
            application.getRequestQueue().add(request);

    }
    //****************************************************************************************
    private callChangePwd callChangePwd;
    public interface callChangePwd{
        void callChangePwd(String flag);
    }
    public void setCallChangePwd(RequestPackage.callChangePwd callChangePwd) {
        this.callChangePwd = callChangePwd;
    }
    public void dochangePwdByPost(HashMap<String,String> params){  //封装起来用
        String url=HttpURL.CHANGEPWD;
        final MyStringRequest request=new MyStringRequest(url, params,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        callChangePwd.callChangePwd(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                });
        application.getRequestQueue().add(request);
    }
    //****************************************************************************************
    private callBackMajorAll callBackMajorAll;
    public interface callBackMajorAll{
        void callMajorAll(List majorList);
    }
    public void setCallBackMajorAll(callBackMajorAll callBackMajorAll) {
        this.callBackMajorAll = callBackMajorAll;
    }
    public void getMajor(){
        String url=HttpURL.HOST+"SchoolLife/NewsPushServlet?dataType=major";
        MyStringRequest request=new MyStringRequest(url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson=new Gson();
                        List<Major> majorList=gson.fromJson(response,new TypeToken<List<Major>>(){}.getType());
                        callBackMajorAll.callMajorAll(majorList);//传一个List
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        application.getRequestQueue().add(request);
    }
    public void getMajorAllByGet(String name){      //数学与应用数学   信息与计算科学  统计学
        String url=yt_HttpURl.getAll(name);
        MyStringRequest request=new MyStringRequest(url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson=new Gson();
                        List<Major> majorList=gson.fromJson(response,new TypeToken<List<Major>>(){}.getType());
                        callBackMajorAll.callMajorAll(majorList);//传一个List
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        application.getRequestQueue().add(request);
    }
    //****************************************************************************************
    private callStuInfos callStuInfos;
    public interface callStuInfos{
        void callStuInfos(List stuInfosList);
    }
    public void setcallStuInfos(callStuInfos callStuInfos){
        this.callStuInfos= callStuInfos;
    }
    public void getCallStuInfosByGet(int classNo){
        String url=yt_HttpURl.getLinkManURL(classNo);
        final MyStringRequest request=new MyStringRequest(url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson=new Gson();
                        List<StuInfos> stuInfosList=gson.fromJson(response,new TypeToken<List<StuInfos>>(){}.getType());
                        callStuInfos.callStuInfos(stuInfosList);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                });
        application.getRequestQueue().add(request);
    }
    //****************************************************************************************
    private callMath callMath;
    public interface callMath{
        void callMath(List classList);
    }
    public void setcallMath(callMath callMath){
        this.callMath= callMath;
    }
    public void getCallMathByGet(int classId){
        String url=yt_HttpURl.getClassURL(classId);
        final MyStringRequest request=new MyStringRequest(url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson=new Gson();
                        List<Class> classList=gson.fromJson(response,new TypeToken<List<Class>>(){}.getType());
                        callMath.callMath(classList);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                });
        application.getRequestQueue().add(request);
    }
    //****************************************************************************************
    private callBackRoleAll callBackRoleAll;
    public interface callBackRoleAll{
        void callBackRoleAll(List roleList);
    }
    public void setcallBackRoleAll(callBackRoleAll callBackRoleAll) {
        this.callBackRoleAll = callBackRoleAll;
    }
    public void getRoleAllByGet(String name){                 //学生，教师，班长
        String url=yt_HttpURl.getAll(name);
        MyStringRequest request=new MyStringRequest(url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson=new Gson();
                        List<Role> roleList=gson.fromJson(response,new TypeToken<List<Role>>(){}.getType());
                        callBackRoleAll.callBackRoleAll(roleList);//传一个List
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        application.getRequestQueue().add(request);
    }
    //****************************************************************************************
    private callBackRoleDetil callBackRoleDetil;
    public interface callBackRoleDetil{
        void callBackRoleDetil(List roleList);
    }
    public void setCallBackRoleDetil(callBackRoleDetil callBackRoleDetil) {
        this.callBackRoleDetil = callBackRoleDetil;
    }
    public void getCallRoleStuInfosByGet(){
        String url=HttpURL.HOST+"SchoolLife/NewsPushServlet?role=%E6%95%99%E5%B8%88&dataType=roletostudent";
//        try {
//            url = yt_HttpURl.getAll(URLEncoder.encode(role, "utf-8"));
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
        final MyStringRequest request=new MyStringRequest(url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson=new Gson();
                        List<StuInfos> stuInfosList=gson.fromJson(response,new TypeToken<List<StuInfos>>(){}.getType());
                        callBackRoleDetil.callBackRoleDetil(stuInfosList);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                });
        application.getRequestQueue().add(request);
    }
//    public void getCallRoleStuInfosByGet(String role){
//        String url= null;
//        try {
//            url = yt_HttpURl.getAll(URLEncoder.encode(role, "utf-8"));
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        final MyStringRequest request=new MyStringRequest(url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        Gson gson=new Gson();
//                        List<StuInfos> stuInfosList=gson.fromJson(response,new TypeToken<List<StuInfos>>(){}.getType());
//                        callBackRoleDetil.callBackRoleDetil(stuInfosList);
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                    }
//                });
//        application.getRequestQueue().add(request);
//    }
}
