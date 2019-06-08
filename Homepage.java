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

public class Homepage extends AppCompatActivity {
Button b1,b2,b3,b4,b5;
String id,no;
RequestQueue queue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        queue= Volley.newRequestQueue(this);
        b1=(Button)findViewById(R.id.button4);
        b2=(Button)findViewById(R.id.button6);
        b3=(Button)findViewById(R.id.button7);
        b4=(Button)findViewById(R.id.button8);
        b5=(Button)findViewById(R.id.button30);

        Intent i=getIntent();
        id=i.getStringExtra("id");
        //Toast.makeText(Homepage.this,"id "+id,Toast.LENGTH_LONG).show();

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i1=new Intent(Homepage.this,PersonalDetails.class);
                i1.putExtra("id",id);
                startActivity(i1);

            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(Homepage.this,Profile.class);
                i1.putExtra("id",id);
                startActivity(i1);
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(Homepage.this,EducationalDetails.class);
                i1.putExtra("id",id);
                startActivity(i1);
            }
        });


        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(Homepage.this,Professional.class);
                i1.putExtra("id",id);
                startActivity(i1);

            }
        });





        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i4= new Intent(Homepage.this,MainActivity.class);

                startActivity(i4);
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
