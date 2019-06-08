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

public class GetPersonal extends AppCompatActivity {
    RequestQueue queue;
    TextView t1;
    String getname,getskills,getno,getlocation,getemail,getlinks,getimage;
    Button view;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_personal);
        queue= Volley.newRequestQueue(this);
        t1=(TextView)findViewById(R.id.textView5);
        Intent i=getIntent();
        id=i.getStringExtra("id");
        view=(Button)findViewById(R.id.View);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getdetails();
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
                            getimage=data.getString("image");
                           // getno=data.getString("mobile_no");
                            getno=data.getString("mobile_no");
                            getname=data.getString("name");
                            getlinks=data.getString("links");
                            getlocation=data.getString("location");

                            //Object getstartyr=data.get("start_year");

                            //Toast.makeText(GetPersonal.this,""+getno,Toast.LENGTH_LONG).show();
                            String text="Name: "+getname+"\n Mobile No: "+getno+"\n Links: "+getlinks+"\n Skills: "+getskills+"\n Location: "+getlocation;
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
