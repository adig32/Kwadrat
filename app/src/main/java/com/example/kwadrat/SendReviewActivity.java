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

public class SendReviewActivity extends AppCompatActivity {

    private EditText review_content, note;
    private TextView review_to, about_flat;

    private Button btn_send;

    private String URL_SEND_REVIEW = "http://192.168.8.100/kwadrat/sendreview.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_review);

        review_content = findViewById(R.id.review_content);
        note = findViewById(R.id.note);

        review_to = findViewById(R.id.review_to);
        about_flat = findViewById(R.id.about_flat);

        btn_send = findViewById(R.id.btn_send);

        Intent intent = getIntent();
        about_flat.setText(intent.getStringExtra("id"));
        review_to.setText(intent.getStringExtra("owner_id"));

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(note.getText().toString().equals("1") || note.getText().toString().equals("2") || note.getText().toString().equals("3") || note.getText().toString().equals("4") || note.getText().toString().equals("5"))
                    sendMessage(about_flat.getText().toString(), intent.getStringExtra("user_id"), review_to.getText().toString(), review_content.getText().toString(), note.getText().toString().trim());
                else note.setError("Ocena musi być z przedziału 1-5!");
            }
        });
    }

    private void sendMessage(String flat_id, String user_id, String owner_id, String review_content, String note) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_SEND_REVIEW,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")) {

                                Toast.makeText(SendReviewActivity.this, "Opinia dodana pomyślnie!", Toast.LENGTH_SHORT).show();

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(SendReviewActivity.this, "Error " +e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(SendReviewActivity.this, "Error " +error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("flat_id", flat_id);
                params.put("user_id", user_id);
                params.put("owner_id", owner_id);
                params.put("review_content", review_content);
                params.put("note", note);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}
