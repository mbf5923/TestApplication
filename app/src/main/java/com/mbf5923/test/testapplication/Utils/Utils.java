package com.mbf5923.test.testapplication.Utils;

import android.content.Context;
import android.net.ConnectivityManager;

import com.mbf5923.test.testapplication.DI.Quantifier.ApplicationContext;

public class Utils {
    public static boolean isNetworkConnected(Context ctx) {

        ConnectivityManager cm = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }
}
