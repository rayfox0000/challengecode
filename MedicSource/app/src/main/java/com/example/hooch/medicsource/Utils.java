package com.example.hooch.medicsource;

import android.app.Activity;
import android.view.View;

/**
 * Created by hooch on 19/3/2018.
 */

public class Utils {
    public static final String LoginFrag = "LoginFragment";
    public static final String SignupFrag = "SignupFragment";
    public static final String ForgotPassFrag = "ForgotPasswordFragment";
    //Server IP please change when it run locally.
    public static final String IP = "http://192.168.0.120:3000";
    public static final String searchMap = "Clinics near me";
    public static User user;

    public static void customToast(Activity activity, View view, String msg) {
        new CustomToast().Show_Toast(activity, view, msg);
    }

}
