package com.example.adoptme;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {
    TextView txtToSignIn;
    Button button2;
    private TextInputLayout username2,phone, password2;
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String name = "nameKey";
    public static final String pass = "passwordKey";
    SharedPreferences sharedpreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getSupportActionBar().hide();

        txtToSignIn = findViewById(R.id.txtToSignIn);

        button2 = findViewById(R.id.buttonRegister);
        txtToSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });
        username2 = (TextInputLayout)findViewById(R.id.EdRegisterUsernamelay);
        phone = (TextInputLayout)findViewById(R.id.EdRegisterPhoneLay);
        password2 = (TextInputLayout)findViewById(R.id.EdRegisterPasswordLay);
        sharedpreferences = getSharedPreferences("UserInfo",
                Context.MODE_PRIVATE);


        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getSupportActionBar().hide();


        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tex_username2 = Objects.requireNonNull(username2.getEditText().getText()).toString();
                String tex_phone = Objects.requireNonNull(phone.getEditText().getText()).toString();
                String tex_password2 = Objects.requireNonNull(password2.getEditText().getText()).toString();
                if (TextUtils.isEmpty(tex_password2) || TextUtils.isEmpty(tex_phone)|| TextUtils.isEmpty(tex_username2)){
                    Toast.makeText(RegisterActivity.this,
                            "All Fields Required", Toast.LENGTH_SHORT).show();
                }
                else{
                    register(tex_username2,tex_phone,tex_password2);
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
    private void register(String username, String phone, String password) {

        final ProgressDialog progressDialog = new ProgressDialog(
                RegisterActivity.this);
        progressDialog.setTitle("Buat Akun....");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setIndeterminate(false);
        progressDialog.show();
//        String uRl = "https://sphere-apps.herokuapp.com/api/auth/login";
        String uRl = "https://adoptme-api.000webhostapp.com/register?username="+username2+"&email="+phone+"&password="+password2;
        //String uRl = "https://adoptme-api.000webhostapp.com/login";
        StringRequest request = new StringRequest(Request.Method.POST,
                uRl,
                (String response) -> {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONObject dataObj = jsonObject.getJSONObject("data");
                        JSONObject dataObj2 = dataObj.getJSONObject("data");
                        Toast.makeText(RegisterActivity.this,
                                "Berhasil", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    progressDialog.dismiss();
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // As of f605da3 the following should work
                        NetworkResponse response = error.networkResponse;
                        if (error instanceof ServerError && response != null) {
                            try {
                                String res = new String(response.data,
                                        HttpHeaderParser.parseCharset(response.headers, "utf-8"));
                                // Now you can use any deserializer to make sense of data
                                JSONObject obj = new JSONObject(res);
                                Toast.makeText(RegisterActivity.this, obj.getString("message"), Toast.LENGTH_SHORT).show();
                            } catch (UnsupportedEncodingException e1) {
                                // Couldn't properly decode data to string
                                e1.printStackTrace();
                            } catch (JSONException e2) {
                                // returned data is not JSONObject?
                                e2.printStackTrace();
                            }

                            progressDialog.dismiss();
                        }
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
                param.put("username", username);
                param.put("email", phone);
                param.put("password", password);
                return param;
            }
        };
        request.setRetryPolicy(
                new DefaultRetryPolicy(30000,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        MySingleton.getmInstance(RegisterActivity.this).
                addToRequestQueue(request);
    }
}