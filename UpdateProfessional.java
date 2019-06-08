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

public class UpdateProfessional extends AppCompatActivity {
    RequestQueue queue;
    EditText desgn,startyear,endyear,organization;
    String getdesgn,getendyear,getorganization;
    String getstartyear;
    Button update;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_professional);
        desgn=(EditText)findViewById(R.id.editText30);
        startyear=(EditText)findViewById(R.id.editText33);
        endyear=(EditText)findViewById(R.id.editText34);
        organization=(EditText)findViewById(R.id.editText29);
        update=(Button)findViewById(R.id.button36);
        queue= Volley.newRequestQueue(this);
        Intent i=getIntent();
        id=i.getStringExtra("id");
        getdesgn=i.getStringExtra("designation");
        getendyear=i.getStringExtra("end");
        getorganization=i.getStringExtra("org");
        getstartyear=i.getStringExtra("start");
        //  Toast.makeText(UpdateEducational.this,""+getstartyear+"\n "+getdegree+getlocation+getorganization+getendyear,Toast.LENGTH_LONG).show();
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                updatedetails();

            }
        });


    }

    private void updatedetails() {

        String deg,org,endyr;
        String startyr;

        if(desgn.getText().toString().length()!=0) {
            deg = desgn.getText().toString();
        }
        else
            deg=getdesgn;
        if(startyear.getText().toString().length()!=0)
            startyr=startyear.getText().toString();
        else
            startyr=getstartyear;

        if(endyear.getText().toString().length()!=0)
            endyr=endyear.getText().toString();
        else
            endyr=getendyear;
        if(organization.getText().toString().length()!=0)
            org=organization.getText().toString();
        else
            org=getorganization;


        Map<String,String> params=new HashMap<>();

        params.put("end_date",endyr);
        params.put("organisation",org);
        params.put("designation",deg);
        params.put("start_date",startyr);





        JSONObject sendparam=new JSONObject(params);
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.PUT,Constants.baseurl+"/user/professionaldetail/"+id
                ,sendparam,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject data = response.getJSONObject("data");
                            int id = data.getInt("id");

                            Toast.makeText(UpdateProfessional.this,"Details are successfully updated ",Toast.LENGTH_LONG).show();

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
