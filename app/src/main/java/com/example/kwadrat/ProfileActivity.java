package com.example.kwadrat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class ProfileActivity extends AppCompatActivity {

    private TextView id, name, second_name, last_name, birth_date, place, postcode, login, gender, phone_number, email;
    private String URL_PROFILE = "http://192.168.8.100/kwadrat/profile.php";
    private Button btn_edit;
    private String string_id, string_name, string_second_name, string_last_name, string_birth_date, string_place, string_postcode, string_login, string_gender, string_phone_number, string_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Intent intent = getIntent();
        string_email = intent.getStringExtra("email");

        Test(string_email);

        id = findViewById(R.id.id_view);
        name = findViewById(R.id.name_view);
        second_name = findViewById(R.id.second_name_view);
        last_name = findViewById(R.id.last_name_view);
        birth_date = findViewById(R.id.birth_date_view);
        place = findViewById(R.id.place_view);
        postcode =findViewById(R.id.postcode_view);
        login = findViewById(R.id.login_view);
        gender = findViewById(R.id.gender_view);
        phone_number = findViewById(R.id.phone_number_view);
        email = findViewById(R.id.email_view);

        btn_edit = findViewById(R.id.btn_edit);

        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, UpdateProfileActivity.class);
                intent.putExtra("name", string_name);
                intent.putExtra("last_name", string_last_name);
                intent.putExtra("second_name", string_second_name);
                intent.putExtra("place", string_place);
                intent.putExtra("postcode", string_postcode);
                intent.putExtra("phone_number", string_phone_number);
                intent.putExtra("id", string_id);
                intent.putExtra("email", string_email);
                startActivity(intent);
            }
        });

    }

    private void Test(final String mEmail) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_PROFILE,
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

                                    string_id = object.getString("id").trim();
                                    string_name = object.getString("name").trim();
                                    string_second_name = object.getString("second_name").trim();
                                    string_last_name = object.getString("last_name").trim();
                                    string_birth_date = object.getString("birth_date").trim();
                                    string_place = object.getString("place").trim();
                                    string_postcode = object.getString("postcode").trim();
                                    string_login = object.getString("login").trim();
                                    string_gender = object.getString("gender").trim();
                                    string_phone_number = object.getString("phone_number").trim();

                                    id.setText(string_id);
                                    name.setText(string_name);

                                    if(string_second_name.isEmpty()) second_name.setText("-");
                                    else second_name.setText(string_second_name);


                                    last_name.setText(string_last_name);
                                    birth_date.setText(string_birth_date);
                                    place.setText(string_place);
                                    postcode.setText(string_postcode);
                                    login.setText(string_login);

                                    if(string_gender.equals("1")) gender.setText("Kobieta");
                                    else if(string_gender.equals("2")) gender.setText("Mężczyzna");

                                    phone_number.setText(string_phone_number);
                                    email.setText(mEmail);
                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(ProfileActivity.this, "Error " +e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ProfileActivity.this, "Error " +error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email", mEmail);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
