package com.example.sparkfoundationapis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    EditText email,password;
    Button b1;
    RequestQueue queue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email=findViewById(R.id.editText3);
        password=findViewById(R.id.editText4);
        b1=findViewById(R.id.button5);
        queue= Volley.newRequestQueue(this);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginuser();
            }
        });
    }

    private void loginuser() {
        String emailid=email.getText().toString();
        String pass=password.getText().toString();
        Map<String,String> params=new HashMap<>();
        params.put("email",emailid);
        params.put("password",pass);
        JSONObject sendparam=new JSONObject(params);
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.POST,Constants.baseurl+"/user/login",sendparam,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject data = response.getJSONObject("data");
                            int id = data.getInt("id");
                            String uid=""+id;
                            Toast.makeText(LoginActivity.this,"successfully logged in with id "+id,Toast.LENGTH_LONG).show();
                            Intent i=new Intent(LoginActivity.this,Homepage.class);
                            i.putExtra("id",uid);
                            startActivity(i);
                        } catch (JSONException e) {
                            e.printStackTrace();

                        }
                    }
                }
                ,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error
                    }
                });
        queue.add(request);








    }
}
