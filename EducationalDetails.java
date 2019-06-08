package com.example.sparkfoundationapis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class EducationalDetails extends AppCompatActivity {
String id,no;
RequestQueue queue;
    Button b1,b2,b3,b4;
    String getdegree,getendyear,getlocation,getorganization;
    String getstartyear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_educational_details);
        Intent i=getIntent();
        id=i.getStringExtra("id");
       // Toast.makeText(EducationalDetails.this,id,Toast.LENGTH_LONG).show();

        queue= Volley.newRequestQueue(this);
getid();
        b1=(Button)findViewById(R.id.button16);
        b2=(Button)findViewById(R.id.button17);
        b3=(Button)findViewById(R.id.button18);
        b4=(Button)findViewById(R.id.button22);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(EducationalDetails.this,GetEducational.class);
                i1.putExtra("id",id);
                startActivity(i1);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent i1=new Intent(EducationalDetails.this,PostEducational.class);
                i1.putExtra("id",id);
               startActivity(i1);
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i1=new Intent(EducationalDetails.this,UpdateEducational.class);
                i1.putExtra("id",id);
                i1.putExtra("degree",getdegree);
                i1.putExtra("start",getstartyear);
                i1.putExtra("end",getendyear);
                i1.putExtra("loc",getlocation);
                i1.putExtra("org",getorganization);
                startActivity(i1);
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i1=new Intent(EducationalDetails.this,DeleteEducational.class);
                i1.putExtra("id",no);

                startActivity(i1);
            }
        });
    }
    public void getid(){
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET,Constants.baseurl+"/user/educationdetail/"+id
                ,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject data = response.getJSONObject("data");

                            no = data.getString("id");
                            getstartyear=data.getString("start_year");
                            getdegree=data.getString("degree");
                            getorganization=data.getString("organisation");
                            getlocation=data.getString("location");
                            getendyear=data.getString("end_year");


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
