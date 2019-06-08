package com.example.sparkfoundationapis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class GetProfessional extends AppCompatActivity {
    RequestQueue queue;
    TextView t1;
    String getdesgn,getendyear,getorganization;
    String getstartyear;
    Button view;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_professional);
        queue= Volley.newRequestQueue(this);
        t1=(TextView)findViewById(R.id.textView8);
        Intent i=getIntent();
        id=i.getStringExtra("id");
        //Toast.makeText(GetProfessional.this,id,Toast.LENGTH_LONG).show();
        view=(Button)findViewById(R.id.button34);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getdetails();
            }
        });

    }
    private void getdetails() {
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET,Constants.baseurl+"/user/professionaldetail/"+id
                ,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject data = response.getJSONObject("data");
                            getendyear=data.getString("end_date");
                            getorganization=data.getString("organisation");
                            getdesgn=data.getString("designation");
                            getstartyear=data.getString("start_date");



                            //Object getstartyr=data.get("start_year");

                           // Toast.makeText(GetProfessional.this,""+getstartyear+"\n "+getdesgn+getorganization+getendyear,Toast.LENGTH_LONG).show();
                            String text="Organization: "+getorganization+"\n"+"Designation: "+getdesgn+"\n Start Date: "+getstartyear+"\n End Date: "+getendyear;
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
