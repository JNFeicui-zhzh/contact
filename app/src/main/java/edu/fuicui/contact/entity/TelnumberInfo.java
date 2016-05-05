package edu.fuicui.contact.entity;

/**
 * Created by hp on 2016/5/5.
 * 获取主流电话号码
 */
public class TelnumberInfo {
    //名称
    public String name;
    //电话号码
    public String number;
    //重写构造方法
    public TelnumberInfo(String name, String number) {
        super();
        this.name = name;
        this.number = number;
    }
}

