package com.example.baicha.zchz.app.app.Modle;

public class Tb_user {
    private int _id;
    private String username;
    private String password;
    private String p_name;

    public Tb_user() {
        super();
    }

    public Tb_user(int id, String username, String password) {
        super();
        this._id = id;
        this.username = username;
        this.password = password;
        this.p_name = p_name;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }
}
