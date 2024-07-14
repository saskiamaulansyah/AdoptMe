package com.example.adoptme;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.example.adoptme.adoptme.AdoptmeActivity;
import com.example.adoptme.adoptme.ApprovalProcessActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    TextView txtToSignUp;
    Button button;
    private EditText email,password;
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String name = "nameKey";
    public static final String pass = "passwordKey";
    SharedPreferences sharedpreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = (EditText)findViewById(R.id.editText1);
        password = (EditText)findViewById(R.id.editText2);
        sharedpreferences = getSharedPreferences("UserInfo",
                Context.MODE_PRIVATE);
        String loginStatus = sharedpreferences
                .getString(getResources().getString(R.string.prefStatus),"");
        if (loginStatus.equals("loggedin")){
            startActivity(new Intent(LoginActivity.
                    this,MainActivity.class));
            finish();
        }

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getSupportActionBar().hide();

        txtToSignUp = findViewById(R.id.txtToSignUp);
        button = findViewById(R.id.buttonLogin);
        txtToSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tex_email = Objects.requireNonNull(email.getText()).toString();
                String tex_password = Objects.requireNonNull(password.getText()).toString();
                if (TextUtils.isEmpty(tex_email) || TextUtils.isEmpty(tex_password)){
                    Toast.makeText(LoginActivity.this,
                            "All Fields Required", Toast.LENGTH_SHORT).show();
                }
                else{
                    login(tex_email,tex_password);
                }
            }
        });
    }
    @Override
    protected void onResume() {
        sharedpreferences=getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        if (sharedpreferences.contains(name))
        {
            if(sharedpreferences.contains(pass)){
                Intent i = new Intent(this, MainActivity.class);
                startActivity(i);
            }
        }
        super.onResume();
    }
    private void login(String email, String password) {
        System.out.println("email : " +email);
        System.out.println("password :" +password);

        final Dialog progressDialog = new Dialog(
                LoginActivity.this);
        progressDialog.setContentView(R.layout.activity_loading);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
//        progressDialog.setIndeterminate(false);
        progressDialog.show();

//        String uRl = "https://sphere-apps.herokuapp.com/api/auth/login";
        String uRl = "http://103.150.92.83/login?email="+email+"&password="+password;
        //String uRl = "https://adoptme-api.000webhostapp.com/login";
        StringRequest request = new StringRequest(Request.Method.POST,
                uRl,
                (String response) -> {
//                    Toast.makeText(LoginActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
//                    try {
//                        JSONObject jsonObject = new JSONObject(response);
//                        JSONObject dataObj = jsonObject.getJSONObject("data");
//                        JSONObject dataObj2 = dataObj.getJSONObject("data");
                        Toast.makeText(LoginActivity.this,
                                "Login Success", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
                    progressDialog.dismiss();
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // As of f605da3 the following should work
                        NetworkResponse response = error.networkResponse;
                        Toast.makeText(LoginActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                        if (error instanceof ServerError && response != null) {
                            try {
                                String res = new String(response.data,
                                        HttpHeaderParser.parseCharset(response.headers, "utf-8"));
                                // Now you can use any deserializer to make sense of data
                                JSONObject obj = new JSONObject(res);
                                Toast.makeText(LoginActivity.this, obj.getString("message"), Toast.LENGTH_SHORT).show();
                            } catch (UnsupportedEncodingException e1) {
                                // Couldn't properly decode data to string
                                e1.printStackTrace();
                            } catch (JSONException e2) {
                                // returned data is not JSONObject?
                                e2.printStackTrace();
                            }
                        }
                        progressDialog.dismiss();
                    }
                })  {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Accept", "application/json");
                return params;
            }

            @Override
            protected Map<String, String> getParams() {
                HashMap<String, String> param = new HashMap<>();
                param.put("email", email);
                param.put("password", password);
                return param;
            }
        };
        request.setRetryPolicy(
                new DefaultRetryPolicy(30000,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        MySingleton.getmInstance(LoginActivity.this).
                addToRequestQueue(request);
    }
}
