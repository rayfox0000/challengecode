package com.example.hooch.medicsource;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

/**
 * Created by hooch on 21/3/2018.
 */

public class EditProfileActivity extends Activity{
    private EditText name;
    private EditText password;
    private EditText newPassword;
    private Button btnEdit;
    private Button btnPassword;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        name = (EditText) findViewById(R.id.name);
        password = (EditText) findViewById(R.id.password);
        newPassword = (EditText)findViewById(R.id.newPassword);
        btnEdit = (Button) findViewById(R.id.btnEdit);
        btnPassword = (Button) findViewById(R.id.btnPassword);
        name.setText(Utils.user.name);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editProfile(Utils.user.email,name.getText().toString());
            }
        });

        btnPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editPassword(Utils.user.email,password.getText().toString(),newPassword.getText().toString());
            }
        });


    }









    private void editProfile(String email, String name){
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        final String url = Utils.IP + "/edit?email=" + email + "&name=" + name;

// prepare the Request
        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // display response
                        Log.d("Response", response.toString());
                        if (!response.toString().isEmpty()) {

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error.Response", error.getMessage());
                    }
                }
        );
    }

    private void editPassword(String email, String password, String newPassword) {

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        final String url = Utils.IP + "/changePassword?email=" + email + "&password=" + password + "&newPassword=" + newPassword;
// prepare the Request
        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // display response
                        Log.d("Response", response.toString());
                        if (!response.toString().isEmpty()) {

                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error.Response", error.getMessage());
                    }
                }
        );
    }




}
