package com.example.hooch.medicsource;

import android.content.res.ColorStateList;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by hooch on 19/3/2018.
 */

public class ForgotPassFragment extends Fragment implements
        View.OnClickListener {
    private static View view;

    private static EditText email;
    private static TextView submit, back;

    public ForgotPassFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_forgotpass, container,
                false);
        initViews();
        setListeners();
        return view;
    }

    private void initViews() {
        email = (EditText) view.findViewById(R.id.registered_email);
        submit = (TextView) view.findViewById(R.id.forgot_button);
        back = (TextView) view.findViewById(R.id.backToLoginBtn);

        // Setting text selector over textviews
        XmlResourceParser xrp = getResources().getXml(R.drawable.text_selector);
        try {
            ColorStateList csl = ColorStateList.createFromXml(getResources(),
                    xrp);

            back.setTextColor(csl);
            submit.setTextColor(csl);

        } catch (Exception e) {
        }

    }

    private void setListeners() {
        back.setOnClickListener(this);
        submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.backToLoginBtn:

                new LoginActivity().replaceLoginFragment();
                break;

            case R.id.forgot_button:

                submitButtonTask(email.getText().toString());
                break;

        }

    }

    public void submitButtonTask(String email) {


        if (email.equals("") || email.length() == 0)

            new CustomToast().Show_Toast(getActivity(), view,
                    "Please enter your Email Id.");

        else
            requestPassword(email);

    }

    public void requestPassword(String email){
        RequestQueue requestQueue = Volley.newRequestQueue(this.getContext());

        final String url = Utils.IP+"/forgotPass?email="+email;

// prepare the Request
        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response) {
                        // display response
                        Toast.makeText(getActivity(), "Get Forgot Password.",
                                Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), "Invalid Email",
                                Toast.LENGTH_SHORT).show();
                    }
                }
        );

// add it to the RequestQueue
       requestQueue.add(getRequest);

    }
}
