package com.example.kwadrat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.kwadrat.Models.CityModel;
import com.example.kwadrat.Models.DistrictModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.view.View.INVISIBLE;

public class FiltersActivity extends AppCompatActivity {

    private EditText rent_min, rent_max, flat_area_min, flat_area_max;
    private CheckBox city_check, district_check;
    private Spinner orderBySpinner;
    private ArrayAdapter<CharSequence> adapter;
    private Button btn_show;
    private ProgressBar progressBar;
    private Spinner spinnerCity, spinnerDistrict;
    private ArrayList<CityModel> cityModelArrayList;
    private ArrayList<String> cities = new ArrayList<String>();
    private ArrayList<DistrictModel> districtModelArrayList;
    private ArrayList<String> districts = new ArrayList<String>();

    private static String URL_GET_CITIES = "http://192.168.8.100/kwadrat/cities.php";
    private static String URL_GET_DISTRICTS = "http://192.168.8.100/kwadrat/districts.php";

    String city = "Warszawa";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filters);

        orderBySpinner = findViewById(R.id.order_by);

        rent_min = findViewById(R.id.rent_min);
        rent_max = findViewById(R.id.rent_max);

        flat_area_min = findViewById(R.id.flat_area_min);
        flat_area_max = findViewById(R.id.flat_area_max);

        spinnerCity = findViewById(R.id.city);
        spinnerDistrict = findViewById(R.id.district);

        getCities();

        getDistricts(city);

        city_check = findViewById(R.id.city_check);
        district_check = findViewById(R.id.district_check);

        city_check.setChecked(false);

        district_check.setEnabled(false);
        district_check.setChecked(false);

        spinnerDistrict.setEnabled(false);

        btn_show = findViewById(R.id.btn_show);
        progressBar = findViewById(R.id.progressBar);

        progressBar.setVisibility(INVISIBLE);

        adapter = ArrayAdapter.createFromResource(this, R.array.order_by_array, R.layout.one_row);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        orderBySpinner.setAdapter(adapter);

        spinnerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                city = spinnerCity.getSelectedItem().toString();

                districts.clear();

                getDistricts(city);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });

        city_check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(city_check.isChecked()) {
                    district_check.setEnabled(true);
                    spinnerDistrict.setEnabled(true);
                } else {
                    district_check.setEnabled(false);
                    district_check.setChecked(false);
                    spinnerDistrict.setEnabled(false);
                }

            }
        });

        btn_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);

                //filtrowanie po cenie
                String rent_min_string, rent_max_string, rent_final;

                rent_min_string = rent_min.getText().toString();
                rent_max_string = rent_max.getText().toString();

                if(!rent_min_string.isEmpty() && rent_max_string.isEmpty()) rent_final = "AND flats.rent>=" + rent_min_string;
                else if(!rent_min_string.isEmpty() && !rent_max_string.isEmpty()) rent_final = "AND flats.rent>=" + rent_min_string + " AND flats.rent<=" + rent_max_string;
                else if(rent_min_string.isEmpty() && !rent_max_string.isEmpty()) rent_final = "AND flats.rent<=" + rent_max_string;
                else rent_final = "";

                //filtrowanie po metrażu
                String flat_area_min_string, flat_area_max_string, flat_area_final;

                flat_area_min_string = flat_area_min.getText().toString();
                flat_area_max_string = flat_area_max.getText().toString();

                if(!flat_area_min_string.isEmpty() && flat_area_max_string.isEmpty()) flat_area_final = "AND flats.flat_area>=" + flat_area_min_string;
                else if(!flat_area_min_string.isEmpty() && !flat_area_max_string.isEmpty()) flat_area_final = "AND flats.flat_area>=" + flat_area_min_string + " AND flats.flat_area<=" + flat_area_max_string;
                else if(flat_area_min_string.isEmpty() && !flat_area_max_string.isEmpty()) flat_area_final = "AND flats.flat_area<=" + flat_area_max_string;
                else flat_area_final = "";

                //filtrowanie po miejscowości
                String city_name, city_final;

                if(city_check.isChecked()) {
                    city_name = spinnerCity.getSelectedItem().toString();
                    city_final = "AND flats.city_id=(SELECT cities.id FROM cities WHERE cities.city_name='" + city_name + "')";
                } else city_final = "";

                //filtrowanie po dzielnicy
                String  district_name, district_final;

                if(district_check.isChecked()) {
                    city_name = spinnerCity.getSelectedItem().toString();
                    district_name = spinnerDistrict.getSelectedItem().toString();
                    district_final = "AND flats.district_id=(SELECT districts.id FROM districts WHERE districts.district_name='" + district_name + "' AND districts.city_id=(SELECT id FROM cities WHERE cities.city_name='" + city_name +  "'))";
                } else district_final = "";

                //sortowanie
                String order_by_string = orderBySpinner.getSelectedItem().toString().trim();
                String order_by_final;

                if(order_by_string.equals("Data dodania (rosnąco)")) order_by_final = "ORDER BY flats.created_at ASC";
                else if(order_by_string.equals("Data dodania (malejąco)")) order_by_final = "ORDER BY flats.created_at DESC";
                else if(order_by_string.equals("Cena (rosnąco)")) order_by_final = "ORDER BY flats.rent ASC";
                else if(order_by_string.equals("Cena (malejąco)")) order_by_final = "ORDER BY flats.rent DESC";
                else if(order_by_string.equals("Metraż mieszkania (rosnąco)")) order_by_final = "ORDER BY flats.flat_area ASC";
                else order_by_final = "ORDER BY flats.flat_area DESC";

                //wysyłanie filtrów i sortowania
                String final_query = rent_final + " " + flat_area_final + " " + city_final + " " + district_final + " GROUP BY flats.id " + order_by_final;

                Intent intentEmail = getIntent();
                String mEmail = intentEmail.getStringExtra("email");

                Intent intent = new Intent(FiltersActivity.this, AllOffersActivity.class);
                intent.putExtra("final_query", final_query);
                intent.putExtra("email", mEmail);
                startActivity(intent);
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

                            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(FiltersActivity.this, R.layout.one_row, cities);
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

    private void getDistricts(String city_name) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_GET_DISTRICTS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            districtModelArrayList = new ArrayList<>();
                            JSONArray array = new JSONArray(response);

                            for(int i=0; i<array.length(); i++) {
                                DistrictModel nameModel = new DistrictModel();
                                JSONObject object = array.getJSONObject(i);

                                nameModel.setDistrict_name(object.getString("district_name"));

                                districtModelArrayList.add(nameModel);
                            }

                            for(int i=0; i<districtModelArrayList.size(); i++) {
                                districts.add(districtModelArrayList.get(i).getDistrict_name().toString());
                            }

                            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(FiltersActivity.this, R.layout.one_row, districts);
                            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                            spinnerDistrict.setAdapter(spinnerArrayAdapter);

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
                params.put("city_name", city_name);
                return params;
            }
        };

        Volley.newRequestQueue(this).add(stringRequest);
    }
}
