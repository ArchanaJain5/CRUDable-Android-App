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

public class UpdatePersonalDetails extends AppCompatActivity {
    RequestQueue queue;
    Map<String,String> params=new HashMap<>();
    EditText name,email,mobile,location,links,skills;
    Button update;
    String id;
    String getname,getskills,getno,getlocation,getemail,getlinks;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_personal_details);
        email=(EditText)findViewById(R.id.editText12);
        mobile=(EditText)findViewById(R.id.editText13);
        location=(EditText)findViewById(R.id.editText14);
        links=(EditText)findViewById(R.id.editText15);
        name=(EditText)findViewById(R.id.editText11);
        skills=(EditText)findViewById(R.id.editText16);
        update=(Button)findViewById(R.id.button14);
        queue= Volley.newRequestQueue(this);
        Intent i=getIntent();
        id=i.getStringExtra("id");
        getname=i.getStringExtra("name");
        getlinks=i.getStringExtra("links");
        getlocation=i.getStringExtra("loc");
        getno=i.getStringExtra("no");
        getskills=i.getStringExtra("skills");


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                updatedetails();
            }
        });
    }



    private void updatedetails() {



        String fullname,emailid,loc,link,no,skill;
        if(name.getText().toString().length()!=0) {
             fullname = name.getText().toString();
        }
        else
            fullname=getname;
        if(email.getText().toString().length()!=0)
        emailid=email.getText().toString();
        else
            emailid=getemail;
        if(location.getText().toString().length()!=0)
        loc=location.getText().toString();
        else
            loc=getlocation;
        if(links.getText().toString().length()!=0)
        link=links.getText().toString();
        else
            link=getlinks;
        if(mobile.getText().toString().length()!=0)
        no=mobile.getText().toString();
        else
            no=getno;
        if(skills.getText().toString().length()!=0)
        skill=skills.getText().toString();
        else
            skill=getskills;

        Map<String,String> params1=new HashMap<>();

        params1.put("skills",skill);
       // params.put("image",null);
        params1.put("mobile_no",no);

        params1.put("name",fullname);

        params1.put("links",link);
        params1.put("location",loc);

        JSONObject sendparam=new JSONObject(params1);
        JsonObjectRequest request1=new JsonObjectRequest(Request.Method.PUT,Constants.baseurl+"/user/personaldetail/"+id
                ,sendparam,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject data = response.getJSONObject("data");
                            int id = data.getInt("id");
                            String getname=data.getString("name");
                            Toast.makeText(UpdatePersonalDetails.this,"Details are successfully saved ",Toast.LENGTH_LONG).show();

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
        queue.add(request1);





    }


}

