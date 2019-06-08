package com.example.sparkfoundationapis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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

public class PostPersonalDetails extends AppCompatActivity {
    RequestQueue queue;
    EditText name,email,mobile,location,links,skills;
    Button save;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_personal_details);
        email=(EditText)findViewById(R.id.editText6);
        mobile=(EditText)findViewById(R.id.editText7);
        location=(EditText)findViewById(R.id.editText8);
        links=(EditText)findViewById(R.id.editText9);
        name=(EditText)findViewById(R.id.editText5);
        skills=(EditText)findViewById(R.id.editText10);
        save=(Button)findViewById(R.id.button12);
        queue= Volley.newRequestQueue(this);
        Intent i=getIntent();
        id=i.getStringExtra("id");

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               postdetails();
            }
        });
    }

    private void postdetails() {

        String fullname=name.getText().toString();
        String emailid=email.getText().toString();
        String loc=location.getText().toString();
        String link=links.getText().toString();
        String no=mobile.getText().toString();
        String skill=skills.getText().toString();
        Map<String,String> params=new HashMap<>();
        params.put("skills",skill);
        params.put("image","");
        params.put("mobile_no",no);
        params.put("name",fullname);
        params.put("links",link);
        params.put("location",loc);

        JSONObject sendparam=new JSONObject(params);
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.POST,Constants.baseurl+"/user/personaldetail/"+id
                ,sendparam,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject data = response.getJSONObject("data");
                            int id = data.getInt("id");
                            String getname=data.getString("name");
                            Toast.makeText(PostPersonalDetails.this,"Details are successfully saved ",Toast.LENGTH_LONG).show();

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

