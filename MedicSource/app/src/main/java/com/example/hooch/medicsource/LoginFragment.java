package com.example.hooch.medicsource;

/**
 * Created by hooch on 19/3/2018.
 */

import android.content.Intent;
import android.content.pm.PackageInstaller;
import android.content.res.ColorStateList;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

//import com.example.hooch.ewalletapp.domain.LoginResponse;
//import com.example.hooch.ewalletapp.request.APIClient;
//import com.google.gson.Gson;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.Map;


public class LoginFragment extends Fragment implements OnClickListener {
    public static CallbackManager callbackManager;
    private static View view;
    private static EditText email, password;
    private static Button loginButton;
    private static TextView forgotPassword, signUp;
    private static CheckBox show_hide_password;
    private static LinearLayout loginLayout;
    private static Animation shakeAnimation;
    private static FragmentManager fragmentManager;

    public LoginFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_login, container, false);
        FacebookSdk.sdkInitialize(getContext());
        initViews();
        setListeners();
        callbackManager = CallbackManager.Factory.create();
        LoginButton loginButton = (LoginButton) view.findViewById(R.id.login_button);
        loginButton.setFragment(this);
        loginButton.registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        Toast.makeText(getActivity(), "Success Login", Toast.LENGTH_SHORT).show();

                        enterMain();
                        Log.d("TEST", ".asdfghj()");

                    }

                    @Override
                    public void onCancel() {
                        Toast.makeText(getActivity(), "Verify your Account", Toast.LENGTH_SHORT).show();

                        Log.d("TEST", ".getFirstName()");

                    }

                    @Override
                    public void onError(FacebookException error) {
                        Toast.makeText(getActivity(), "Invalid Credential", Toast.LENGTH_SHORT).show();

                    }
                });

        LoginManager.getInstance().logOut();
        return view;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode,resultCode,data);


    }

    private void initViews() {
        fragmentManager = getActivity().getSupportFragmentManager();

        email = (EditText) view.findViewById(R.id.login_email);
        password = (EditText) view.findViewById(R.id.login_password);
        loginButton = (Button) view.findViewById(R.id.loginBtn);
        forgotPassword = (TextView) view.findViewById(R.id.forgot_password);
        signUp = (TextView) view.findViewById(R.id.createAccount);
        show_hide_password = (CheckBox) view
                .findViewById(R.id.show_hide_password);
        loginLayout = (LinearLayout) view.findViewById(R.id.login_layout);

        shakeAnimation = AnimationUtils.loadAnimation(getActivity(),
                R.anim.shake);

        XmlResourceParser xrp = getResources().getXml(R.drawable.text_selector);
        try {
            ColorStateList csl = ColorStateList.createFromXml(getResources(),
                    xrp);

            forgotPassword.setTextColor(csl);
            show_hide_password.setTextColor(csl);
            signUp.setTextColor(csl);
        } catch (Exception e) {
        }
    }

    private void setListeners() {
        loginButton.setOnClickListener(this);
        forgotPassword.setOnClickListener(this);
        signUp.setOnClickListener(this);

        show_hide_password
                .setOnCheckedChangeListener(new OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(CompoundButton button,
                                                 boolean isChecked) {


                        if (isChecked) {

                            show_hide_password.setText(R.string.hide_pwd);


                            password.setInputType(InputType.TYPE_CLASS_TEXT);
                            password.setTransformationMethod(HideReturnsTransformationMethod
                                    .getInstance());
                        } else {
                            show_hide_password.setText(R.string.show_pwd);// change


                            password.setInputType(InputType.TYPE_CLASS_TEXT
                                    | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                            password.setTransformationMethod(PasswordTransformationMethod
                                    .getInstance());// hide password

                        }

                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loginBtn:
                checkValidation();
                break;

            case R.id.forgot_password:

                fragmentManager
                        .beginTransaction()
                        .setCustomAnimations(R.anim.right_enter, R.anim.left_out)
                        .replace(R.id.frameContainer,
                                new ForgotPassFragment(),
                                Utils.ForgotPassFrag).commit();
                break;
            case R.id.createAccount:

                fragmentManager
                        .beginTransaction()
                        .setCustomAnimations(R.anim.right_enter, R.anim.left_out)
                        .replace(R.id.frameContainer, new SignupFragment(),
                                Utils.SignupFrag).commit();
                break;
        }

    }

    private void checkValidation() {
        String getEmail = email.getText().toString();
        String getPassword = password.getText().toString();

        if (getEmail.trim().length() == 0 || getPassword.trim().length() == 0) {
            loginLayout.startAnimation(shakeAnimation);
            Utils.customToast(getActivity(), view, "Enter both credentials.");
        } else if (getEmail.equals(""))
            Utils.customToast(getActivity(), view, "Your Email Id is Invalid.");
        else
            login(getEmail, getPassword);
    }

    private void login(String email, String password) {

        RequestQueue requestQueue = Volley.newRequestQueue(this.getContext());

        final String url = Utils.IP+"/login?email=" + email + "&password=" + password;

// prepare the Request


        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response) {
                        // display response
                        try {
                            JSONObject object = response;
                            Utils.user = new User(object.getString("email"), object.getString("name"));

                        } catch (JSONException e) {
                            Toast.makeText(getContext(), "Invalid credentials", Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }

                        Log.d("Response", response.toString());
                        if(!response.toString().isEmpty()){
                            enterMain();
                        }

                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), "Check internet connection/Verify Account/ Check Credential", Toast.LENGTH_SHORT).show();
                    }
                }
        );

// add it to the RequestQueue
        requestQueue.add(getRequest);
//        Call<LoginResponse> tokenDetails = APIClient.userRequests.login(email, password);
//        tokenDetails.enqueue(new Callback<LoginResponse>() {
//            @Override
//            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
//                if (response.isSuccessful()) {
//                    SharedPrefManager.save(SharedPrefManager.CURRENT_USER, new Gson().toJson(response.body().getUser()));
//                    CurrentUser.setCurrentUser(response.body().getUser());
//                    SharedPrefManager.save(SharedPrefManager.JWT, "BEARER " + response.body().getToken());
//                    CurrentUser.setToken("BEARER " + response.body().getToken());
//                    enterMain();
//                } else {
//                    try {
//                        Utils.customToast(getActivity(), view, response.errorBody().string());
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//            @Override
//            public void onFailure(Call<LoginResponse> call, Throwable t) {
//                System.out.println(t);
//            }
//        });
    }

    private void enterMain() {
        Intent mainIntent = new Intent(getActivity(), MenuActivity.class);
        mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(mainIntent);
        getActivity().finish();
    }
}
