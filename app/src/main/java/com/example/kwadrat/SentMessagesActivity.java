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
import com.example.kwadrat.Adapters.SentMessageAdapter;
import com.example.kwadrat.Models.MessageModel;
import com.example.kwadrat.Models.SentMessageModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SentMessagesActivity extends AppCompatActivity {

    private String URL_SENT_MESSAGES = "http://192.168.8.100/kwadrat/sentmessages.php";
    private List<SentMessageModel> sentMessagesList;
    private RecyclerView recyclerView;
    private Button btn_received;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sent_messages);

        recyclerView = findViewById(R.id.messages);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        btn_received = findViewById(R.id.btn_received);

        sentMessagesList = new ArrayList<>();

        Intent intent = getIntent();
        String email = intent.getStringExtra("email");

        loadMessages(email);

        btn_received.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SentMessagesActivity.this, MessagesActivity.class);

                intent.putExtra("email", email);
                startActivity(intent);
            }
        });

    }

    private void loadMessages(String email) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_SENT_MESSAGES,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);

                            for(int i=0; i<array.length(); i++) {
                                JSONObject myOffer = array.getJSONObject(i);


                                sentMessagesList.add(new SentMessageModel(
                                        myOffer.getString("login"),
                                        myOffer.getString("flat_id"),
                                        myOffer.getString("content_of_message"),
                                        email
                                ));

                            }
                            SentMessageAdapter adapter = new SentMessageAdapter(SentMessagesActivity.this, sentMessagesList);
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
