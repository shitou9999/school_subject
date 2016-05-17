package com.example.administrator.mytable.School.HttpURL;

/**
 * Created by Administrator on 2016/1/16.
 */
public class HttpURL {
    public static  final String HOST="http://211.86.106.63/";
    public static  final String WEBSITE="SchoolLife";
    public static  final String URL=HOST+WEBSITE+"/";
    public static final String Login=
            URL+"LoginServlet";
    public static String getHttpURLDetil(String channel){
        switch (channel){
            case "通知公告":
                return GAME01;
            case "校园新闻":
                return GAME02;
            case "素质拓展":
                return GAME03;
            case "团学动态":
                return THINK01;
            case "好书推荐":
                return THINK02;
            case "师生风采":
                return THINK03;
            case "心灵分享":
                return THINK04;
            case "招聘信息":
                return WORK01;
            case "通知文件":
                return WORK02;
            case "政策分享":
                return WORK03;
            case "就职在线":
                return WORK04;
            case "考研分析":
                return ABORAD01;
            case "考研动态":
                return ABORAD02;
            case "留学动态":
                return ABORAD03;
            default:
                return null;
        }
    }

    public static final String CHANGEPWD=
            URL+"LoginServlet?update=1";
    private static final String GAME01=
            URL+"NewsRequestServlet?pageTagFlag=0&pageTag=5&dataType=news&pageNum=0";
    private static final String GAME02=
            URL+"NewsRequestServlet?pageTagFlag=0&pageTag=6&dataType=news&pageNum=0";
    private static final String GAME03=
            URL+"NewsRequestServlet?pageTagFlag=0&pageTag=7&dataType=news&pageNum=0";
    private static final String THINK01=
            URL+"NewsRequestServlet?pageTagFlag=0&pageTag=9&dataType=news&pageNum=0";
    private static final String THINK02=
            URL+"NewsRequestServlet?pageTagFlag=0&pageTag=10&dataType=news&pageNum=0";
    private static final String THINK03=
            URL+"NewsRequestServlet?pageTagFlag=0&pageTag=11&dataType=news&pageNum=0";
    private static final String THINK04=
            URL+"NewsRequestServlet?pageTagFlag=0&pageTag=12&dataType=news&pageNum=0";
    private static final String WORK01=
            URL+"NewsRequestServlet?pageTagFlag=0&pageTag=13&dataType=news&pageNum=0";
    private static final String WORK02=
            URL+"NewsRequestServlet?pageTagFlag=0&pageTag=14&dataType=news&pageNum=0";
    private static final String WORK03=
            URL+"NewsRequestServlet?pageTagFlag=0&pageTag=15&dataType=news&pageNum=0";
    private static final String WORK04=
            URL+"NewsRequestServlet?pageTagFlag=0&pageTag=17&dataType=news&pageNum=0";
    private static final String  ABORAD01=
            URL+"NewsRequestServlet?pageTagFlag=0&pageTag=18&dataType=news&pageNum=0";
    private static final String  ABORAD02=
            URL+"NewsRequestServlet?pageTagFlag=0&pageTag=19&dataType=news&pageNum=0";
    private static final String  ABORAD03=
            URL+"NewsRequestServlet?pageTagFlag=0&pageTag=20&dataType=news&pageNum=0";

}
