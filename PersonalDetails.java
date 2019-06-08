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

public class PersonalDetails extends AppCompatActivity {
    Button b1,b2,b3;
    String id,text;
    RequestQueue queue;
    String getname,getskills,getno,getlocation,getemail,getlinks;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_details);
        b1=(Button)findViewById(R.id.button9);
        b2=(Button)findViewById(R.id.button10);
        b3=(Button)findViewById(R.id.button11);
        queue= Volley.newRequestQueue(this);

        Intent i=getIntent();
        id=i.getStringExtra("id");
        //Toast.makeText(PersonalDetails.this,"id "+id,Toast.LENGTH_LONG).show();
        getdetails();
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String send=getdetails();
                Intent i1=new Intent(PersonalDetails.this,GetPersonal.class);
                i1.putExtra("id",id);
                //i1.putExtra("text",send);
                startActivity(i1);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2=new Intent(PersonalDetails.this,PostPersonalDetails.class);
                i2.putExtra("id",id);
                startActivity(i2);
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i3=new Intent(PersonalDetails.this,UpdatePersonalDetails.class);
                i3.putExtra("id",id);
                i3.putExtra("name",getname);
                i3.putExtra("no",getno);
                i3.putExtra("skills",getskills);
                i3.putExtra("loc",getlocation);
                i3.putExtra("links",getlinks);

                startActivity(i3);

            }
        });

    }

    private void getdetails() {
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET,Constants.baseurl+"/user/personaldetail/"+id
                ,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject data = response.getJSONObject("data");

                            int id = data.getInt("id");
                            getskills=data.getString("skills");
                            getno=data.getString("mobile_no");
                            getname=data.getString("name");
                            getlinks=data.getString("links");
                            getlocation=data.getString("location");

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
