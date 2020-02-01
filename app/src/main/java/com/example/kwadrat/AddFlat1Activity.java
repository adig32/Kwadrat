package com.example.kwadrat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.kwadrat.Models.CityModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AddFlat1Activity extends AppCompatActivity {

    private EditText flat_area,
            number_of_tenants,
            rent,
            additional_fees,
            deposit,
            media_fees,
            street;
    private Button btn_next;
    private Spinner spinnerCity;
    private ArrayList<CityModel> cityModelArrayList;
    private ArrayList<String> cities = new ArrayList<String>();
    private ProgressBar progressBar;
    private static String URL_ADD = "http://192.168.8.100/kwadrat/addflat1.php";
    private static String URL_GET_CITIES = "http://192.168.8.100/kwadrat/cities.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_flat1);

        spinnerCity = findViewById(R.id.city);

        flat_area = findViewById(R.id.flat_area);
        street = findViewById(R.id.street);
        number_of_tenants = findViewById(R.id.number_of_tenants);
        rent = findViewById(R.id.rent);
        additional_fees = findViewById(R.id.additional_fees);
        deposit = findViewById(R.id.deposit);
        media_fees = findViewById(R.id.media_fees);

        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);

        btn_next = findViewById(R.id.btn_next);

        getCities();

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);

                Intent intent = getIntent();
                String mEmail = intent.getStringExtra("email");

                Add(mEmail);
            }
        });
    }

    private void getCities() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_GET_CITIES,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            cityModelArrayList = new ArrayList<>();
                            JSONArray array = new JSONArray(response);

                            for(int i=0; i<array.length(); i++) {
                                CityModel nameModel = new CityModel();
                                JSONObject object = array.getJSONObject(i);

                                nameModel.setCity_name(object.getString("city_name"));

                                cityModelArrayList.add(nameModel);
                            }

                            for(int i=0; i<cityModelArrayList.size(); i++) {
                                cities.add(cityModelArrayList.get(i).getCity_name().toString());
                            }

                            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(AddFlat1Activity.this, R.layout.one_row, cities);
                            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                            spinnerCity.setAdapter(spinnerArrayAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {}
                });

        Volley.newRequestQueue(this).add(stringRequest);
    }

    private void Add(String email) {
        final String flat_area = this.flat_area.getText().toString().trim();
        final String street =  this.street.getText().toString().trim();
        final String number_of_tenants = this.number_of_tenants.getText().toString().trim();
        final String rent = this.rent.getText().toString().trim();
        final String additional_fees = this.additional_fees.getText().toString().trim();
        final String deposit = this.deposit.getText().toString().trim();
        final String media_fees = this.media_fees.getText().toString().trim();
        final String city_name = this.spinnerCity.getSelectedItem().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_ADD,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")) {
                                progressBar.setVisibility(View.INVISIBLE);
                                Toast.makeText(AddFlat1Activity.this, "Krok pierwszy wykonany pomyślnie!", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(AddFlat1Activity.this, AddFlat2Activity.class);
                                intent.putExtra("email", email);
                                intent.putExtra("city_name", city_name);
                                startActivity(intent);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(AddFlat1Activity.this, "Błąd dodawania! " + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AddFlat1Activity.this, "Błąd! " + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })

        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("city_name", city_name);
                params.put("street", street);
                params.put("flat_area", flat_area);
                params.put("number_of_tenants", number_of_tenants);
                params.put("rent", rent);
                params.put("additional_fees", additional_fees);
                params.put("deposit", deposit);
                params.put("media_fees", media_fees);
                params.put("email", email);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
