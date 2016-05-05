package edu.fuicui.contact.db;

import android.content.Context;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by hp on 2016/5/4.
 * 用来操作本地 Assets 文件夹内 db 数据的管理类
 */
public class AssetsDBManager {
    /**
     * 复制本地 Assets 中的 db 文件到指定目录中
     *
     * @param context
     * @param path
     * Assets 内 db 文件路径
     * @param toFile
     * 目标位置
     * @throws IOException
     */
    public static void copyAssetsFileToFile(Context context,String path,File toFile) throws IOException {
        InputStream inputStream=null;

        // 输入流(用来读取当前项目的 Assets 内的 db 文本)
        BufferedInputStream bufferedInputStream=null;
        //输出流(用来将读取到的 db 信息写到指定目录文件 toFile 中去)
        BufferedOutputStream bufferedOutputStream=null;

        try {
            inputStream=context.getAssets().open(path);
            bufferedInputStream=new BufferedInputStream(inputStream);
            bufferedOutputStream=new BufferedOutputStream(new FileOutputStream(toFile,false));
            // IO 操作
            int len=0;
            byte [] buff=new byte[2*1024];
            while ((len=bufferedInputStream.read(buff))!=-1){
                bufferedOutputStream.write(buff,0,len);
            }
            bufferedOutputStream.flush();
        } catch (IOException e) {
            // TODO: handle exception
            throw e;
        }finally {
            //IO关闭
            inputStream.close();
            bufferedInputStream.close();
            bufferedOutputStream.close();
        }
    }

}
