package edu.fuicui.contact.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.File;
import java.util.ArrayList;

import edu.fuicui.contact.entity.TelclassInfo;
import edu.fuicui.contact.entity.TelnumberInfo;

/**
 * Created by hp on 2016/5/4.
 * 用来做数据库文件的读取操作
 */
public class DBReader {
    /**
     * 通讯大全 File
     */
    public static File telFile;

    static {
        //默认位置
        String dbFileDir = "data/data/edu.feicui.contacts/";
        // 存储卡
        // String sdcardState = Environment.getExternalStorageState();
        // if (sdcardState.equals(Environment.MEDIA_MOUNTED)) {
        // dbFileDir = Environment.getExternalStorageDirectory() +
        // "/azyphone/cache";
        // }
        //

        File fileDir=new File(dbFileDir);//创建文件并指定路径
        fileDir.mkdirs();// 文件目录的创建
        telFile =new File(dbFileDir,"commonnum.db");//传入路径和名称

    }
    /** 检测是否存在通讯大全 DB 文件 */
    public static boolean isExistsTeldbFile(){
        // 没有通讯大全 File
        File toFile=DBReader.telFile;//获得路径
        if(!toFile.exists()||toFile.length()<=0){
            return false;
        }
        return true;
    }
    /** 读取 telFile 这个数据库文件中的 classlist 这个表内的数据
     * @throws Exception */
    public static ArrayList<TelclassInfo> readTeldbClasslist(){
        ArrayList<TelclassInfo> telclassInfos=new ArrayList<>();
        // 打开 DB 文件
        SQLiteDatabase db=null;
        // 执行查询的 SQL 语句 select * from classlist
        Cursor cursor=null;

        try {
            db=SQLiteDatabase.openOrCreateDatabase(telFile,null);
            cursor=db.rawQuery("select * from classlist",null);
            if (cursor.moveToFirst()){
                do {
                    String name =cursor.getString(cursor.getColumnIndex("name"));
                    //idx 为 classlist 表中电话的 ID，根据 idx 值进行指定页面的跳转
                    int idx=cursor.getInt((cursor.getColumnIndex("idx")));
                    TelclassInfo telclassInfo =new TelclassInfo(name,idx);

                   telclassInfos.add(telclassInfo);
                }while (cursor.moveToNext());
            }
        } catch (Exception e) {
            // TODO: handle exception
          throw e;
        }finally {
            try {
                cursor.close();
                db.close();
            } catch (Exception e2) {
                // TODO: handle exception
                throw e2;
            }
            return telclassInfos;
        }

    }
    /** 读取 telFile 这个数据库文件中的 tableN 这个表内的数据(商家名称和电话号码)
     * @throws Exception */
    public static ArrayList<TelnumberInfo> readTeldbTable(int idx){
        ArrayList<TelnumberInfo> telnumberInfos=new ArrayList<TelnumberInfo>();
        //idx 为 classlist 表中电话的 ID，根据 idx 值进行指定页面的跳转
        String sql="select * from table"+idx;
        SQLiteDatabase db=null;
        Cursor cursor=null;

        try {
            // 打开 DB 文件
            db=SQLiteDatabase.openOrCreateDatabase(telFile,null);
            // 执行查询的 SQL 语句 select * from table +idx
            cursor=db.rawQuery(sql,null);
            if (cursor.moveToFirst()){
                do {
                    String name = cursor
                            .getString(cursor
                                    .getColumnIndex("name"));
                    String number = cursor
                            .getString(cursor
                                    .getColumnIndex("number"));
                    TelnumberInfo numberInfo = new TelnumberInfo(
                            name, number);
                    telnumberInfos.add(numberInfo);
                } while (cursor.moveToNext());

                }

        } catch (Exception e) {
            // TODO: handle exception
            throw e;
        }finally {
            try {
                cursor.close();
                db.close();
            }catch (Exception e2){
                // TODO: handle exception
                throw e2;
            }
        }return telnumberInfos;
    }



}
