package edu.fuicui.contact.entity;

/**
 * Created by hp on 2016/5/4.
 * 获取数据库 classlist 里的数据
 */
public class TelclassInfo {
    //电话名称
    public String name;
    //唯一ID
    public int idx;

    //重写构造方法
    public TelclassInfo(String name, int idx) {
        super();
        this.name = name;
        this.idx = idx;
    }
    //系统默认构造方法
    public TelclassInfo(){
        super();
    }
}
