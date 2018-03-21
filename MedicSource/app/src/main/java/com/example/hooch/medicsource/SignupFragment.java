package com.example.hooch.medicsource;

import android.content.res.ColorStateList;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by hooch on 19/3/2018.
 */

public class SignupFragment extends Fragment implements View.OnClickListener {
    private static View view;
    private static EditText name, email, password, confirmPassword;
    private static TextView login;
    private static Button signUpButton;
    private static CheckBox terms_conditions;

    public SignupFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_signup, container, false);
        initViews();
        setListeners();
        return view;
    }

    private void initViews() {
        name = (EditText) view.findViewById(R.id.name);
        email = (EditText) view.findViewById(R.id.email);
//        mobileNumber = (EditText) view.findViewById(R.id.mobileNumber);
        password = (EditText) view.findViewById(R.id.password);
        confirmPassword = (EditText) view.findViewById(R.id.confirmPassword);
        signUpButton = (Button) view.findViewById(R.id.signUpBtn);
        login = (TextView) view.findViewById(R.id.already_user);
        terms_conditions = (CheckBox) view.findViewById(R.id.terms_conditions);

        XmlResourceParser xrp = getResources().getXml(R.drawable.text_selector);
        try {
            ColorStateList csl = ColorStateList.createFromXml(getResources(),
                    xrp);

            login.setTextColor(csl);
            terms_conditions.setTextColor(csl);
        } catch (Exception e) {
        }
    }

    private void setListeners() {
        signUpButton.setOnClickListener(this);
        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signUpBtn:

                checkValidation();
                break;

            case R.id.already_user:

                new LoginActivity().replaceLoginFragment();
                break;
        }

    }

    private void checkValidation() {

        String getName = name.getText().toString();
        String getEmail = email.getText().toString();
//        String getMobileNumber = mobileNumber.getText().toString();
        String getPassword = password.getText().toString();
        String getConfirmPassword = confirmPassword.getText().toString();

//        Matcher m = p.matcher(getEmail);

        if (getName.equals("") || getName.length() == 0
                || getEmail.equals("") || getEmail.length() == 0
//                || getMobileNumber.equals("") || getMobileNumber.length() == 0
                || getPassword.equals("") || getPassword.length() == 0
                || getConfirmPassword.equals("")
                || getConfirmPassword.length() == 0)

            new CustomToast().Show_Toast(getActivity(), view,
                    "All fields are required.");
        else {
            signUp(getName,getEmail,getPassword); //getMobileNumber
        }
    }
    public void signUp(final String getName, final String getEmail, final String getPassword){
        RequestQueue queue = Volley.newRequestQueue(this.getContext());  // this = context
        String url = Utils.IP+"/register";
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Toast.makeText(getActivity(), "Success Created", Toast.LENGTH_SHORT).show();

                        Log.d("Response", response);
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Toast.makeText(getActivity(), "Invalid Information", Toast.LENGTH_SHORT).show();

                        Log.d("Error.Response", error.getMessage().toString());
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("email", getEmail);
                params.put("password", getPassword);
                params.put("name", getName);

                return params;
            }
        };
        queue.add(postRequest);
    }
}

//        else if (!m.find())
//            new CustomToast().Show_Toast(getActivity(), view,
//                    "Your Email Id is Invalid.");
//
//        else if (!getConfirmPassword.equals(getPassword))
//            new CustomToast().Show_Toast(getActivity(), view,
//                    "Both password doesn't match.");
//
//        else if (!terms_conditions.isChecked())
//            new CustomToast().Show_Toast(getActivity(), view,
//                    "Please select Terms and Conditions.");
//
//        else
//            Toast.makeText(getActivity(), "Do SignUp.", Toast.LENGTH_SHORT)
//                    .show();

//    }

    //getMobileNumber
//    private void signUp(String getFirstName, String getLastName, String getEmail, String getPassword,String getUserId){
//        User user = new User(getUserId,getPassword,getEmail,getFirstName,getLastName);
////        Call<String> signUp = APIClient.userRequests.signUp(new User(getUserId,getPassword,getEmail,getFirstName,getLastName));
////        Log.d("TST",getUserId + getPassword + getEmail + getFirstName + getLastName);
//        Call<Void> signUp = APIClient.userRequests.signUp(user);
////        Log.d("TEST","test");
//        signUp.enqueue(new Callback<Void>() {
//            @Override
//            public void onResponse(Call<Void> call, Response<Void> response) {
//                if(response.isSuccessful() && response.code() == 200){
//                    Utils.customToast(getActivity(), view, "Success");
//                }else{
//                    try {
//                        Utils.customToast(getActivity(), view, response.errorBody().string());
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }

//            @Override
//            public void onFailure(Call<Void> call, Throwable t) {
//
//            }
//        });
//    }
//}

