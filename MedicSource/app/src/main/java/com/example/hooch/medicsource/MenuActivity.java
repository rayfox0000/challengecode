package com.example.hooch.medicsource;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mindorks.placeholderview.PlaceHolderView;

public class MenuActivity extends AppCompatActivity implements DrawerMenuItem.DrawerCallBack{

       private PlaceHolderView mDrawerView;
       private DrawerLayout mDrawer;
       private DrawerMenuItem m1, m2, m3, m4;
       private Toolbar mToolbar;

       //private ImageView mNewsIV;
       //private PlaceHolderView mGalleryView;

       @Override
       protected void onCreate(Bundle savedInstanceState) {
           super.onCreate(savedInstanceState);
           setContentView(R.layout.activity_menu);

           mDrawer = (DrawerLayout)findViewById(R.id.drawerLayout);
           mDrawerView = (PlaceHolderView)findViewById(R.id.drawerView);
           mToolbar = (Toolbar)findViewById(R.id.toolbar);

           //mNewsIV = (ImageView) findViewById(R.id.newsIV);
           MenuHomeFragment menuHomeFragment = new MenuHomeFragment();
           FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
           transaction.replace(R.id.content_main, menuHomeFragment);
           transaction.commit();


           setupDrawer();
       }

       private void setupDrawer(){
           m1 = new DrawerMenuItem(this.getApplicationContext(), DrawerMenuItem.DRAWER_MENU_ITEM_PROFILE);
           m2 = new DrawerMenuItem(this.getApplicationContext(), DrawerMenuItem.DRAWER_MENU_ITEM_HOME);
           m3 = new DrawerMenuItem(this.getApplicationContext(), DrawerMenuItem.DRAWER_MENU_ITEM_DASHBOARD);
           m4 = new DrawerMenuItem(this.getApplicationContext(), DrawerMenuItem.DRAWER_MENU_ITEM_LOGOUT);
           mDrawerView
                   .addView(new DrawerHeader())
                   .addView(m1)
                   .addView(m2)
                   .addView(m3)
                   .addView(m4);


           ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, mDrawer, mToolbar, R.string.open_drawer, R.string.close_drawer){
               @Override
               public void onDrawerOpened(View drawerView) {
                   super.onDrawerOpened(drawerView);
               }
               @Override
               public void onDrawerClosed(View drawerView) {
                   super.onDrawerClosed(drawerView);
               }
           };

           mDrawer.addDrawerListener(drawerToggle);
           drawerToggle.syncState();
           m1.setDrawerCallBack(this);
           m2.setDrawerCallBack(this);
           m3.setDrawerCallBack(this);
           m4.setDrawerCallBack(this);
//           DrawerMenuItem drawerMenuItem = new DrawerMenuItem()
           /*DrawerMenuItem.DrawerCallBack callBack = new DrawerMenuItem.DrawerCallBack() {
               @Override
               public void onProfileMenuSelected() {
                   ProfileFragment profileFragment = new ProfileFragment();
                   FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                   transaction.replace(R.id.content_main, profileFragment);
                   transaction.commit();
               }

               @Override
               public void onHometMenuSelected() {
                   Intent intent = new Intent(getBaseContext(), MenuActivity.class);
                   getBaseContext().startActivity(intent);
               }

               @Override
               public void onDashboardMenuSelected() {
                   DashboardFragment dashboardFragment = new DashboardFragment();
                   FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                   transaction.replace(R.id.content_main, dashboardFragment);
                   transaction.commit();
               }

               @Override
               public void onLogoutMenuSelected() {
                   Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                   getBaseContext().startActivity(intent);
               }
           };*/

       }

    @Override
    public void onProfileMenuSelected() {
        ProfileFragment profileFragment = new ProfileFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_main, profileFragment);
        transaction.commit();
        mDrawer.closeDrawer(mDrawerView);
    }

    @Override
    public void onHometMenuSelected() {
        MenuHomeFragment menuHomeFragment = new MenuHomeFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_main, menuHomeFragment);
        transaction.commit();
        mDrawer.closeDrawer(mDrawerView);
    }

    @Override
    public void onDashboardMenuSelected() {

        Intent intent = new Intent(this, DashboardFragment.class);
        this.startActivity(intent);
        mDrawer.closeDrawer(mDrawerView);
    }

    @Override
    public void onLogoutMenuSelected() {
        Intent intent = new Intent(this, LoginActivity.class);
        this.startActivity(intent);
        mDrawer.closeDrawer(mDrawerView);
    }
}

