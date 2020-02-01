package com.example.kwadrat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SendMessageActivity extends AppCompatActivity {

    private EditText message;
    private TextView message_to, about_flat;

    private Button btn_send;

    private String URL_OWNER_LOGIN = "http://192.168.8.100/kwadrat/ownerlogin.php";
    private String URL_SEND_MESSAGE = "http://192.168.8.100/kwadrat/sendmessage.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_message);

        message = findViewById(R.id.message);

        message_to = findViewById(R.id.message_to);
        about_flat = findViewById(R.id.about_flat);

        btn_send = findViewById(R.id.btn_send);

        Intent intent = getIntent();
        about_flat.setText(intent.getStringExtra("flat_id"));

        getUserLogin(about_flat.getText().toString());

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage(about_flat.getText().toString(), intent.getStringExtra("email"), message_to.getText().toString(), message.getText().toString());
            }
        });
    }

    private void getUserLogin(String id) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_OWNER_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("profile");

                            if (success.equals("1")) {

                                for (int i = 0; i < jsonArray.length(); i++) {

                                    JSONObject object = jsonArray.getJSONObject(i);

                                    message_to.setText(object.getString("login").trim());

                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(SendMessageActivity.this, "Error " +e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(SendMessageActivity.this, "Error " +error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id", id);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void sendMessage(String flat_id, String user_from_email, String user_to_login, String message) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_SEND_MESSAGE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")) {

                                Toast.makeText(SendMessageActivity.this, "Wiadomość wysłana pomyślnie!", Toast.LENGTH_SHORT).show();

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(SendMessageActivity.this, "Error " +e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(SendMessageActivity.this, "Error " +error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("flat_id", flat_id);
                params.put("user_from_email", user_from_email);
                params.put("user_to_login", user_to_login);
                params.put("message", message);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}
