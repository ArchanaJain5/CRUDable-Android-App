package com.example.sparkfoundationapis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Professional extends AppCompatActivity {
    Button b1,b2,b3,b4;
    String organization,designation,enddate,startdate,id,no;
    RequestQueue queue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professional);
        Intent i=getIntent();
        id=i.getStringExtra("id");
        // Toast.makeText(EducationalDetails.this,id,Toast.LENGTH_LONG).show();

        queue= Volley.newRequestQueue(this);
        getid();
        b1=(Button)findViewById(R.id.button27);
        b2=(Button)findViewById(R.id.button31);
        b3=(Button)findViewById(R.id.button32);
        b4=(Button)findViewById(R.id.button33);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(Professional.this,GetProfessional.class);
                i1.putExtra("id",id);
                startActivity(i1);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(Professional.this,PostProfessional.class);
                i1.putExtra("id",id);
                startActivity(i1);
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i1=new Intent(Professional.this,UpdateProfessional.class);
                i1.putExtra("id",id);
                i1.putExtra("designation",designation);
                i1.putExtra("start",startdate);
                i1.putExtra("end",enddate);

                i1.putExtra("org",organization);
                startActivity(i1);
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getid();

                Intent i1=new Intent(Professional.this,DeleteProfessional.class);
                i1.putExtra("id",no);

                startActivity(i1);
            }
        });
    }
    public void getid(){
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET,Constants.baseurl+"/user/professionaldetail/"+id
                ,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject data = response.getJSONObject("data");

                            no = data.getString("id");
                            startdate=data.getString("start_date");
                            designation=data.getString("designation");
                            organization=data.getString("organisation");
                           enddate=data.getString("end_date");


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
