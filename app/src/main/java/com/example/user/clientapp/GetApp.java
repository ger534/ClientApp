package com.example.user.clientapp;

/**
 * Created by User on 11/10/2016.
 */

import android.app.Application;

/**
 * Created by User on 28/9/2016.
 */

public class GetApp extends Application {
    private static GetApp singleton;
    private Connection conn;
    String pNickname;
    String pIP;
    int pPort;

    public GetApp getInstance() {
        return singleton;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        conn = null;
        singleton = this;

    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(String pNickname, String pIP, int pPort){
        this.pNickname = pNickname;
        this.pPort = pPort;
        this.pIP = pIP;
        this.conn = new Connection(pNickname, pIP, pPort, null);
    }

}