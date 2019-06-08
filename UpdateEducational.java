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

import static java.lang.Integer.parseInt;

public class UpdateEducational extends AppCompatActivity {
    RequestQueue queue;
    EditText degree,startyear,endyear,location,organization;
    String getdegree,getendyear,getlocation,getorganization;
    String getstartyear;
    Button update;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_educational);
        degree=(EditText)findViewById(R.id.editText22);
        startyear=(EditText)findViewById(R.id.editText23);
        location=(EditText)findViewById(R.id.editText26);
        endyear=(EditText)findViewById(R.id.editText24);
        organization=(EditText)findViewById(R.id.editText25);
        update=(Button)findViewById(R.id.button24);
        queue= Volley.newRequestQueue(this);
        Intent i=getIntent();
        id=i.getStringExtra("id");
        getdegree=i.getStringExtra("degree");
        getendyear=i.getStringExtra("end");
        getlocation=i.getStringExtra("loc");
        getorganization=i.getStringExtra("org");
        getstartyear=i.getStringExtra("start");
     // Toast.makeText(UpdateEducational.this,""+getstartyear+"\n "+getdegree+getlocation+getorganization+getendyear,Toast.LENGTH_LONG).show();
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                updatedetails();

            }
        });


    }
   /* private void getdetails() {
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

                            //Toast.makeText(UpdateEducational.this,""+getstartyear+"\n "+getdegree+getlocation+getorganization+getendyear,Toast.LENGTH_LONG).show();
                            //String text="Degree: "+getdegree+"\n StartYear: "+getstartyear+"\n EndYear: "+getendyear+"\n Organization: "+getorganization+"\n Location: "+getlocation;
                            //t1.setText(getstartyear+"\n "+getdegree+getlocation+getorganization+getendyear);
                            //t1.setText(text);


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
*/



    private void updatedetails() {

        String deg,org,loc,endyr;
        String startyr;

        if(degree.getText().toString().length()!=0) {
            deg = degree.getText().toString();
        }
        else
            deg=getdegree;
        if(startyear.getText().toString().length()!=0)
            startyr=startyear.getText().toString();
        else
            startyr=getstartyear;
        if(location.getText().toString().length()!=0)
            loc=location.getText().toString();
        else
            loc=getlocation;
        if(endyear.getText().toString().length()!=0)
            endyr=endyear.getText().toString();
        else
            endyr=getendyear;
        if(organization.getText().toString().length()!=0)
            org=organization.getText().toString();
        else
            org=getorganization;


        Map<String,String> params=new HashMap<>();

        params.put("uid",id);
        params.put("image_path","");
        params.put("start_year",startyr);
        params.put("degree",deg);
        params.put("organisation",org);
        params.put("location",loc);
        params.put("end_year",endyr);

        JSONObject sendparam=new JSONObject(params);
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.PUT,Constants.baseurl+"/user/educationdetail/"+id
                ,sendparam,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject data = response.getJSONObject("data");
                            int id = data.getInt("id");

                            Toast.makeText(UpdateEducational.this,"Details are successfully updated ",Toast.LENGTH_LONG).show();

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
