package com.example.hooch.medicsource;

import android.content.Context;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.login.LoginManager;
import com.mindorks.placeholderview.annotations.Click;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;

/**
 * Created by hooch on 21/3/2018.
 */

@Layout(R.layout.drawer_item)
public class DrawerMenuItem {

    public static final int DRAWER_MENU_ITEM_PROFILE = 3;
    public static final int DRAWER_MENU_ITEM_DASHBOARD = 2;
    public static final int DRAWER_MENU_ITEM_HOME = 1;
    public static final int DRAWER_MENU_ITEM_LOGOUT = 4;

    private int mMenuPosition;
    private Context mContext;
    private DrawerCallBack mCallBack;

    @View(R.id.itemNameTxt)
    private TextView itemNameTxt;

    @View(R.id.itemIcon)
    private ImageView itemIcon;

    public DrawerMenuItem(Context context, int menuPosition) {
        mContext = context;
        mMenuPosition = menuPosition;
    }

    @Resolve
    private void onResolved() {
        switch (mMenuPosition){
            case DRAWER_MENU_ITEM_HOME:
                itemNameTxt.setText("Home");
                itemIcon.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_home_black_24dp));
                break;
            case DRAWER_MENU_ITEM_DASHBOARD:
                itemNameTxt.setText("DashBoard");
                itemIcon.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_home_black_24dp));
                break;
            case DRAWER_MENU_ITEM_PROFILE:
                itemNameTxt.setText("Profile");
                itemIcon.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_home_black_24dp));
                break;
            case DRAWER_MENU_ITEM_LOGOUT:
                itemNameTxt.setText("Log Out");
                itemIcon.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_home_black_24dp));
                break;

        }
    }

    @Click(R.id.mainView)
    private void onMenuItemClick(){
        switch (mMenuPosition){
            case DRAWER_MENU_ITEM_PROFILE:
                Toast.makeText(mContext, "Profile", Toast.LENGTH_SHORT).show();
                if(mCallBack != null)mCallBack.onProfileMenuSelected();
                break;
            case DRAWER_MENU_ITEM_HOME:
                Toast.makeText(mContext, "Home", Toast.LENGTH_SHORT).show();
                if(mCallBack != null)mCallBack.onHometMenuSelected();
                break;
            case DRAWER_MENU_ITEM_DASHBOARD:
                Toast.makeText(mContext, "Dashboard", Toast.LENGTH_SHORT).show();
                if(mCallBack != null)mCallBack.onDashboardMenuSelected();
                break;

            case DRAWER_MENU_ITEM_LOGOUT:

                if(mCallBack != null)mCallBack.onLogoutMenuSelected();
                break;
        }
    }

    public void setDrawerCallBack(DrawerCallBack callBack) {
        mCallBack = callBack;
    }

    public interface DrawerCallBack{
        void onProfileMenuSelected();
        void onHometMenuSelected();
        void onDashboardMenuSelected();
        void onLogoutMenuSelected();
    }
}