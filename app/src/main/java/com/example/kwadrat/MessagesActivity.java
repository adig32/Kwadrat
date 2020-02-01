package com.example.kwadrat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.kwadrat.Adapters.MessageAdapter;
import com.example.kwadrat.Models.MessageModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessagesActivity extends AppCompatActivity {

    private String URL_RECEIVED_MESSAGES = "http://192.168.8.100/kwadrat/receivedmessages.php";
    private List<MessageModel> messagesList;
    private RecyclerView recyclerView;
    private Button btn_sent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);

        recyclerView = findViewById(R.id.messages);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        btn_sent = findViewById(R.id.btn_sent);

        messagesList = new ArrayList<>();

        Intent intent = getIntent();
        String email = intent.getStringExtra("email");

        loadMessages(email);

        btn_sent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MessagesActivity.this, SentMessagesActivity.class);

                intent.putExtra("email", email);
                startActivity(intent);
            }
        });

    }

    private void loadMessages(String email) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_RECEIVED_MESSAGES,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);

                            for(int i=0; i<array.length(); i++) {
                                JSONObject myOffer = array.getJSONObject(i);


                                messagesList.add(new MessageModel(
                                        myOffer.getString("login"),
                                        myOffer.getString("flat_id"),
                                        myOffer.getString("content_of_message"),
                                        email
                                ));

                            }
                            MessageAdapter adapter = new MessageAdapter(MessagesActivity.this, messagesList);
                            recyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {}
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email", email);
                return params;
            }
        };

        Volley.newRequestQueue(this).add(stringRequest);
    }
}
