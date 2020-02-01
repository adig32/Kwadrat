package com.example.kwadrat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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

public class AddFlat4Activity extends AppCompatActivity {

    private CheckBox f1, f2, f3, f4, f5, f6, f7, f8, f9;
    private CheckBox b1, b2, b3, b4, b5, b6, b7, b8, b9;
    private CheckBox k1, k2, k3, k4, k5, k6, k7, k8, k9, k10, k11;
    private CheckBox m1, m2, m3, m4;
    private EditText inconveniences;
    private ProgressBar progressBar;
    private Button btn_next;
    private static String URL_ADD = "http://192.168.8.100/kwadrat/addflat4.php";
    private static String URL_FLAT = "http://192.168.8.100/kwadrat/addequipmentflat.php";
    private static String URL_BATHROOM = "http://192.168.8.100/kwadrat/addequipmentbathroom.php";
    private static String URL_KITCHEN = "http://192.168.8.100/kwadrat/addequipmentkitchen.php";
    private static String URL_MEDIA = "http://192.168.8.100/kwadrat/addmedia.php";
    private static String URL_UPDATE = "http://192.168.8.100/kwadrat/updateflat.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_flat4);

        f1 = findViewById(R.id.hoover);
        f2 = findViewById(R.id.tv);
        f3 = findViewById(R.id.bed);
        f4 = findViewById(R.id.bedclothes);
        f5 = findViewById(R.id.blinds);
        f6 = findViewById(R.id.ironing_board);
        f7 = findViewById(R.id.wardrobe);
        f8 = findViewById(R.id.desk);
        f9 = findViewById(R.id.iron);

        b1 = findViewById(R.id.washer);
        b2 = findViewById(R.id.shower);
        b3 = findViewById(R.id.bath);
        b4 = findViewById(R.id.towels);
        b5 = findViewById(R.id.clothes_dryer);
        b6 = findViewById(R.id.hair_dryer);
        b7 = findViewById(R.id.mirror);
        b8 = findViewById(R.id.mop_bucket);
        b9 = findViewById(R.id.detergents);

        k1 = findViewById(R.id.fridge);
        k2 = findViewById(R.id.electric_kettle);
        k3 = findViewById(R.id.heating_plate);
        k4 = findViewById(R.id.kitchenware);
        k5 = findViewById(R.id.openers);
        k6 = findViewById(R.id.pots);
        k7 = findViewById(R.id.washing_machine);
        k8 = findViewById(R.id.coffe_machine);
        k9 = findViewById(R.id.microwave);
        k10 = findViewById(R.id.oven);
        k11 = findViewById(R.id.toaster);

        m1 = findViewById(R.id.internet);
        m2 = findViewById(R.id.cable_television);
        m3 = findViewById(R.id.digital_television);
        m4 = findViewById(R.id.telephone);

        inconveniences = findViewById(R.id.inconveniences);

        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);

        btn_next = findViewById(R.id.btn_next);

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);

                Intent intent = getIntent();
                String mEmail = intent.getStringExtra("email");

                //dodawanie wyposażenia mieszkania
                if(f1.isChecked()) AddFlatEquipment(f1.getText().toString(), mEmail);
                if(f2.isChecked()) AddFlatEquipment(f2.getText().toString(), mEmail);
                if(f3.isChecked()) AddFlatEquipment(f3.getText().toString(), mEmail);
                if(f4.isChecked()) AddFlatEquipment(f4.getText().toString(), mEmail);
                if(f5.isChecked()) AddFlatEquipment(f5.getText().toString(), mEmail);
                if(f6.isChecked()) AddFlatEquipment(f6.getText().toString(), mEmail);
                if(f7.isChecked()) AddFlatEquipment(f7.getText().toString(), mEmail);
                if(f8.isChecked()) AddFlatEquipment(f8.getText().toString(), mEmail);
                if(f9.isChecked()) AddFlatEquipment(f9.getText().toString(), mEmail);

                //dodawanie wyposażenia łazienki
                if(b1.isChecked()) AddBathroomEquipment(b1.getText().toString(), mEmail);
                if(b2.isChecked()) AddBathroomEquipment(b2.getText().toString(), mEmail);
                if(b3.isChecked()) AddBathroomEquipment(b3.getText().toString(), mEmail);
                if(b4.isChecked()) AddBathroomEquipment(b4.getText().toString(), mEmail);
                if(b5.isChecked()) AddBathroomEquipment(b5.getText().toString(), mEmail);
                if(b6.isChecked()) AddBathroomEquipment(b6.getText().toString(), mEmail);
                if(b7.isChecked()) AddBathroomEquipment(b7.getText().toString(), mEmail);
                if(b8.isChecked()) AddBathroomEquipment(b8.getText().toString(), mEmail);
                if(b9.isChecked()) AddBathroomEquipment(b9.getText().toString(), mEmail);

                //dodawanie wyposażenia kuchni
                if(k1.isChecked()) AddKitchenEquipment(k1.getText().toString(), mEmail);
                if(k2.isChecked()) AddKitchenEquipment(k2.getText().toString(), mEmail);
                if(k3.isChecked()) AddKitchenEquipment(k3.getText().toString(), mEmail);
                if(k4.isChecked()) AddKitchenEquipment(k4.getText().toString(), mEmail);
                if(k5.isChecked()) AddKitchenEquipment(k5.getText().toString(), mEmail);
                if(k6.isChecked()) AddKitchenEquipment(k6.getText().toString(), mEmail);
                if(k7.isChecked()) AddKitchenEquipment(k7.getText().toString(), mEmail);
                if(k8.isChecked()) AddKitchenEquipment(k8.getText().toString(), mEmail);
                if(k9.isChecked()) AddKitchenEquipment(k9.getText().toString(), mEmail);
                if(k10.isChecked()) AddKitchenEquipment(k10.getText().toString(), mEmail);
                if(k11.isChecked()) AddKitchenEquipment(k11.getText().toString(), mEmail);

                //dodawanie mediów
                if(m1.isChecked()) AddMedia(m1.getText().toString(), mEmail);
                if(m2.isChecked()) AddMedia(m2.getText().toString(), mEmail);
                if(m3.isChecked()) AddMedia(m3.getText().toString(), mEmail);
                if(m4.isChecked()) AddMedia(m4.getText().toString(), mEmail);

                //dodawanie niedogodności
                if(!inconveniences.toString().isEmpty()) AddInconveniences(inconveniences.getText().toString(), mEmail);

                //aktualizowanie czasu
                UpdateFlatUpdated(mEmail);
            }
        });
    }

    private void AddFlatEquipment(String flat_equipment, String email) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_FLAT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");


                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(AddFlat4Activity.this, "Błąd dodawania! " + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AddFlat4Activity.this, "Błąd! " + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })

        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("flat_equipment", flat_equipment);
                params.put("email", email);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void AddBathroomEquipment(String bathroom_equipment, String email) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_BATHROOM,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");


                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(AddFlat4Activity.this, "Błąd dodawania! " + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AddFlat4Activity.this, "Błąd! " + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })

        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("bathroom_equipment", bathroom_equipment);
                params.put("email", email);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void AddKitchenEquipment(String kitchen_equipment, String email) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_KITCHEN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");


                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(AddFlat4Activity.this, "Błąd dodawania! " + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AddFlat4Activity.this, "Błąd! " + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })

        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("kitchen_equipment", kitchen_equipment);
                params.put("email", email);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void AddInconveniences(String inconveniences, String email) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_ADD,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");


                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(AddFlat4Activity.this, "Błąd dodawania! " + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AddFlat4Activity.this, "Błąd! " + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })

        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("inconveniences", inconveniences);
                params.put("email", email);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void UpdateFlatUpdated(String email) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_UPDATE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")) {
                                progressBar.setVisibility(View.INVISIBLE);
                                Toast.makeText(AddFlat4Activity.this, "Krok czwarty wykonany pomyślnie!", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(AddFlat4Activity.this, AddFlat5Activity.class);
                                intent.putExtra("email", email);
                                startActivity(intent);
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(AddFlat4Activity.this, "Błąd dodawania! " + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AddFlat4Activity.this, "Błąd! " + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })

        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email", email);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void AddMedia(String media, String email) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_MEDIA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");


                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(AddFlat4Activity.this, "Błąd dodawania! " + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AddFlat4Activity.this, "Błąd! " + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })

        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("media", media);
                params.put("email", email);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
