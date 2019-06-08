package com.example.sparkfoundationapis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class GetEducational extends AppCompatActivity {
    RequestQueue queue;
    TextView t1;
    String getdegree,getendyear,getlocation,getorganization;
    String getstartyear;
    Button view;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_educational);
        queue= Volley.newRequestQueue(this);
        t1=(TextView)findViewById(R.id.textView6);
        Intent i=getIntent();
        id=i.getStringExtra("id");
        view=(Button)findViewById(R.id.button26);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getdetails();
            }
        });


    }
    private void getdetails() {
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET,Constants.baseurl+"/user/educationdetail/"+id
                ,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject data = response.getJSONObject("data");

                            int id = data.getInt("id");
                           getstartyear=data.getString("start_year");
                            getdegree=data.getString("degree");
                            getorganization=data.getString("organisation");
                            getlocation=data.getString("location");
                            getendyear=data.getString("end_year");
                            //Object getstartyr=data.get("start_year");

                            //Toast.makeText(GetEducational.this,""+getstartyear+"\n "+getdegree+getlocation+getorganization+getendyear,Toast.LENGTH_LONG).show();
                            String text="Degree: "+getdegree+"\n StartYear: "+getstartyear+"\n EndYear: "+getendyear+"\n Organization: "+getorganization+"\n Location: "+getlocation;
                            //t1.setText(getstartyear+"\n "+getdegree+getlocation+getorganization+getendyear);
                                t1.setText(text);


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
