package com.example.administrator.mytable.School.yt_LinkMan.yt_HttpURl;

/**
 * Created by Administrator on 2016/1/20.
 */
public class yt_HttpURl {
//    public static  final String HOST="http://211.86.106.63/";
    public static  final String HOST="http://211.86.106.63/";
    public static  final String WEBSITE="SchoolLife";
    public static  final String URL=HOST+WEBSITE+"/";
    public static final String MAJOR=
            URL+"NewsPushServlet";
    //数学与应用数学   信息与计算科学  统计学
    public static final String MAJORALL=   //返回一个集合，包含实体Major
            URL+"NewsPushServlet?dataType=major";
    public static final String MATH=   //数学与应用数学  返回集合包含实体Class
            URL+"NewsPushServlet?majorId=1&dataType=majortoclass";
    public static final String getClassURL(int majorId){
        switch (majorId){
            case 1:
                return MATH;
            case 2:
                return INFOR;
            case 3:
                return STATISTICS;
            default:
                return null;
        }
    }

    public static String getAll(String name){
        switch (name){
            case "专业":
                return MAJORALL;
            case "职务":
                return POST;
            case "%E5%AD%A6%E7%94%9F":
                return STU;
            case "%E7%8F%AD%E9%95%BF":
                return BAN;
            case "%E6%95%99%E5%B8%88":
                return TEACHER;
            default:
                return null;
        }
    }
    //学生 班长  教师
    private static final String POST=
            URL+"NewsPushServlet?dataType=role";
    private static final String STU=  //学生  返回集合 实体学生StuInfos
            URL+"NewsPushServlet?role=%E5%AD%A6%E7%94%9F&dataType=roletostudent";
    private static final String BAN=  //班长   返回集合 实体学生StuInfos
            URL+"NewsPushServlet?role=%E7%8F%AD%E9%95%BF&dataType=roletostudent";
    public static final String TEACHER= //教师  返回集合 实体StuInfos
            URL+"NewsPushServlet?role=%E6%95%99%E5%B8%88&dataType=roletostudent";

    public static String getLinkManURL(int classNo){
        switch (classNo){
            case 1:
                return CLASS131;
            case 2:
                return CLASS132;
            case 15:
                return CLASS141;
            case 18:
                return CLASS142;
            case 19:
                return CLASS151;
            case 20:
                return CLASS152;
            case 3:
                return CLASS1301;
            case 13:
                return CLASS1302;
            case 23:
                return CLASS1500;
            case 14:
                return CLASS13T;
            case 21:
                return CLASS151T;
            case 22:
                return CLASS152T;
            default:
                return null;
        }
    }

    private static final String CLASS131=   //返回集合  实体学生StuInfos
            URL+"NewsPushServlet?classNo=1&dataType=classtostudent";
    private static final String CLASS132=
            URL+"NewsPushServlet?classNo=2&dataType=classtostudent";
    private static final String CLASS141=
            URL+"NewsPushServlet?classNo=15&dataType=classtostudent";
    private static final String CLASS142=
            URL+"NewsPushServlet?classNo=18&dataType=classtostudent";
    private static final String CLASS151=
            URL+"NewsPushServlet?classNo=19&dataType=classtostudent";
    private static final String CLASS152=
            URL+"NewsPushServlet?classNo=20&dataType=classtostudent";
    private static final String INFOR=  //信息与计算科学
            URL+"NewsPushServlet?majorId=2&dataType=majortoclass";
    private static final String CLASS1301=
            URL+"NewsPushServlet?classNo=3&dataType=classtostudent";
    private static final String CLASS1302=
            URL+"NewsPushServlet?classNo=13&dataType=classtostudent";
    private static final String CLASS1500=
            URL+"NewsPushServlet?classNo=23&dataType=classtostudent";
    private static final String STATISTICS=  //统计学
            URL+"NewsPushServlet?majorId=3&dataType=majortoclass";
    private static final String CLASS13T=
            URL+"NewsPushServlet?classNo=14&dataType=classtostudent";
    private static final String CLASS151T=
            URL+"NewsPushServlet?classNo=21&dataType=classtostudent";
    private static final String CLASS152T=
            URL+"NewsPushServlet?classNo=22&dataType=classtostudent";


}
