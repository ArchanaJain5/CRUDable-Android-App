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

public class DeleteEducational extends AppCompatActivity {
Button b1;
String id;
RequestQueue queue;
int no;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_educational);
        b1=(Button)findViewById(R.id.button25);
        queue= Volley.newRequestQueue(this);
        Intent i=getIntent();
        id=i.getStringExtra("id");
       // Toast.makeText(DeleteEducational.this,id,Toast.LENGTH_LONG).show();
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                deletedetails();
            }
        });
    }




    private void deletedetails() {


        JsonObjectRequest request=new JsonObjectRequest(Request.Method.DELETE,Constants.baseurl+"/user/educationdetail/"+id
                ,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            String msg=response.getString("status_message");
                            Toast.makeText(DeleteEducational.this,msg,Toast.LENGTH_LONG).show();



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

