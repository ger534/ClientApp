package com.example.user.clientapp;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by User on 26/10/2016.
 */

public class VolleyS {
    private static VolleyS mVolleyS = null;
    //Este objeto es la cola que usará la aplicación
    private RequestQueue mRequestQueue;

    private VolleyS(Context context) {
        mRequestQueue = Volley.newRequestQueue(context);
    }

    public static VolleyS getInstance(Context context) {
        if (mVolleyS == null) {
            mVolleyS = new VolleyS(context);
        }
        return mVolleyS;
    }

    public RequestQueue getRequestQueue() {
        return mRequestQueue;
    }

}