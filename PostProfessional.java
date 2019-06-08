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

public class PostProfessional extends AppCompatActivity {
    RequestQueue queue;
    EditText des,startdate,enddate,organization;
    Button save;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_professional);
        des=(EditText)findViewById(R.id.editText28);
        startdate=(EditText)findViewById(R.id.editText31);
        enddate=(EditText)findViewById(R.id.editText32);
        organization=(EditText)findViewById(R.id.editText27);
        save=(Button)findViewById(R.id.button35);
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

        String desg=des.getText().toString();
        String startyr=startdate.getText().toString();
        String endyr=enddate.getText().toString();
        String org=organization.getText().toString();


        Map<String,String> params=new HashMap<>();

        params.put("end_date",endyr);
        params.put("organisation",org);
        params.put("designation",desg);
        params.put("start_date",startyr);


        JSONObject sendparam=new JSONObject(params);
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.POST,Constants.baseurl+"/user/professionaldetail/"+id
                ,sendparam,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject data = response.getJSONObject("data");
                            int id = data.getInt("id");
                            //  String getname=data.getString("name");
                            Toast.makeText(PostProfessional.this,"Details are successfully saved ",Toast.LENGTH_LONG).show();

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
