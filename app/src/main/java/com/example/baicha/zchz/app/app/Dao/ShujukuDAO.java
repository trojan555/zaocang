package com.example.baicha.zchz.app.app.Dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ShujukuDAO extends SQLiteOpenHelper {
    private static final int VERSION = 1;// 定义数据库版本号
    private static final String DBNAME = "account.db";// 定义数据库名

    public ShujukuDAO(Context context){// 定义构造函数

        super(context, DBNAME, null, VERSION);// 重写基类的构造函数
    }

    @Override
    public void onCreate(SQLiteDatabase db){// 创建数据库


       /* db.execSQL("create table tb_bf (_id integer primary key,  price decimal,   name varchar(10),"
                + "info varchar(100), bs1 varchar(10), total integer,  rq integer)"); // 创建菜单信息表

        db.execSQL("create table tb_bs (_id integer primary key, name varchar(10),phone varchar(10),"
                + "address varchar(100),boss varchar(10),rd varchar(200),canteen varchar(10))");   // 创建档口信息表

        db.execSQL("create table tb_order (_id integer primary key, status varchar(20),orderTime varchar(10),"
                + "expectedTime varchar(10), address varchar(100),price decimal,discountI varchar(200),"
                + "usernumber integer, username varchar(10), bfname varchar(10),bfnumber integer,pp varchar(200))");   // 创建订单信息表
         */
        db.execSQL("create table tb_user (_id integer primary key,username varchar(20),password varchar(20),p_name varchar(20))");// 创建用户表表

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){// 覆写基类的onUpgrade方法，以便数据库版本更新
    }
}