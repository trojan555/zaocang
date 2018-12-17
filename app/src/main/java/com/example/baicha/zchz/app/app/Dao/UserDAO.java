package com.example.baicha.zchz.app.app.Dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.baicha.zchz.app.app.Modle.Tb_user;


public class UserDAO {
    private ShujukuDAO helper;
    private SQLiteDatabase db;


    public UserDAO(Context context) {
        helper = new ShujukuDAO(context);
        db = helper.getWritableDatabase();
    }

    public void add(Tb_user tb_user) {
        db.execSQL("insert into tb_user (_id,username,password)"
                        + "values (?,?,?)",
                new Object[] {tb_user.get_id(), tb_user.getUsername(), tb_user.getPassword()});
    }

    public void update(Tb_user tb_user) {
        db.execSQL("update tb_user set username = ?,password = ? where _id = ?",
                new Object[] {tb_user.getUsername(), tb_user.getPassword(), tb_user.get_id() });
    }

    public boolean find(String username, String password) {
        Cursor cursor = db.rawQuery(
                "select username,password from tb_user where username = ? and password = ?",
                new String[] {String.valueOf(username), String.valueOf(password)});
        if(cursor.moveToNext())
            return true;
        cursor.close();
        return false;
    }

    public boolean find(String username) {
        Cursor cursor = db.rawQuery(
                "select username from tb_user where username = ?",
                new String[] {String.valueOf(username)});
        if(cursor.moveToNext())
            return true;
        cursor.close();
        return false;
    }
    public boolean find1(String password) {
        Cursor cursor = db.rawQuery(
                "select username from tb_user where password = ?",
                new String[] {String.valueOf(password)});
        if(cursor.moveToNext())
            return true;
        cursor.close();
        return false;
    }

    public void delete(Integer...ids) {
        if(ids.length > 0) {
            StringBuffer sb = new StringBuffer();
            for(int i = 0; i < ids.length; i++) {
                sb.append('?').append(',');
            }
            sb.deleteCharAt(sb.length() - 1);
            db.execSQL("delete from tb_user where _id (" + sb + ")",
                    (Object[]) ids);
        }
    }

    public int getMaxId() {
        db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select max (_id) from tb_user", null);
        while (cursor.moveToLast()) {
            return cursor.getInt(0);
        }
        cursor.close();
        return 0;
    }

}