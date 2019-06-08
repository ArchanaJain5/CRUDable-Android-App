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

public class PostEducational extends AppCompatActivity {
    RequestQueue queue;
    EditText degree,startyear,endyear,location,organization;
    Button save;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_educational);
        degree=(EditText)findViewById(R.id.editText17);
        startyear=(EditText)findViewById(R.id.editText18);
        location=(EditText)findViewById(R.id.editText21);
        endyear=(EditText)findViewById(R.id.editText19);
        organization=(EditText)findViewById(R.id.editText20);
        save=(Button)findViewById(R.id.button23);
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

        String deg=degree.getText().toString();
        String startyr=startyear.getText().toString();
        String endyr=endyear.getText().toString();
        String org=organization.getText().toString();
        String loc=location.getText().toString();

        Map<String,String> params=new HashMap<>();
        params.put("uid",id);
        params.put("image",null);
        params.put("start_year",startyr);
        params.put("degree",deg);
        params.put("organisation",org);
        params.put("location",loc);
        params.put("id",id);
        params.put("end_year",endyr);

        JSONObject sendparam=new JSONObject(params);
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.POST,Constants.baseurl+"/user/educationdetail/"+id
                ,sendparam,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject data = response.getJSONObject("data");
                            int id = data.getInt("id");
                          //  String getname=data.getString("name");
                            Toast.makeText(PostEducational.this,"Details are successfully saved ",Toast.LENGTH_LONG).show();

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
