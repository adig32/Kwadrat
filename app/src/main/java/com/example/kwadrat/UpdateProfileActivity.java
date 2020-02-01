package com.example.kwadrat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UpdateProfileActivity extends AppCompatActivity {

    private EditText email, name, second_name, last_name, phone_number, postcode, place;
    private Button btn_update;
    private ProgressBar progressBar;
    private String URL_UPDATE = "http://192.168.8.100/kwadrat/updateprofile.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        name = findViewById(R.id.name);
        second_name = findViewById(R.id.second_name);
        last_name = findViewById(R.id.last_name);
        place = findViewById(R.id.place);
        postcode = findViewById(R.id.postcode);
        phone_number = findViewById(R.id.phone_number);
        email = findViewById(R.id.email);

        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);

        btn_update = findViewById(R.id.btn_update);

        Intent intent = getIntent();
        String extraId = intent.getStringExtra("id");
        String extraName = intent.getStringExtra("name");
        String extraSecondName = intent.getStringExtra("second_name");
        String extraLastName = intent.getStringExtra("last_name");
        String extraPlace = intent.getStringExtra("place");
        String extraPostcode = intent.getStringExtra("postcode");
        String extraPhoneNumber = intent.getStringExtra("phone_number");
        String extraEmail = intent.getStringExtra("email");

        name.setText(extraName);
        second_name.setText(extraSecondName);
        last_name.setText(extraLastName);
        place.setText(extraPlace);
        postcode.setText(extraPostcode);
        phone_number.setText(extraPhoneNumber);
        email.setText(extraEmail);

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                Update(extraId);
            }
        });
    }

    private void Update(String id){
        final String name = this.name.getText().toString().trim();
        final String second_name = this.second_name.getText().toString().trim();
        final String last_name = this.last_name.getText().toString().trim();
        final String place = this.place.getText().toString().trim();
        final String postcode = this.postcode.getText().toString().trim();
        final String phone_number = this.phone_number.getText().toString().trim();
        final String email = this.email.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_UPDATE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")) {
                                Toast.makeText(UpdateProfileActivity.this, "Dane zaktualizowane pomyślnie!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(UpdateProfileActivity.this, HomeActivity.class);

                                intent.putExtra("name", name);
                                intent.putExtra("email", email);

                                startActivity(intent);

                                progressBar.setVisibility(View.INVISIBLE);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(UpdateProfileActivity.this, "Błąd aktualizacji! " + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(UpdateProfileActivity.this, "Błąd aktualizacji! " + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })

        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id", id);
                params.put("name", name);
                params.put("second_name", second_name);
                params.put("last_name", last_name);
                params.put("place", place);
                params.put("postcode", postcode);
                params.put("phone_number", phone_number);
                params.put("email", email);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
