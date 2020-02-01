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
import com.example.kwadrat.Models.DistrictModel;
import com.example.kwadrat.Models.FloorModel;
import com.example.kwadrat.Models.HeatingModel;
import com.example.kwadrat.Models.NumberOfRoomsModel;
import com.example.kwadrat.Models.ParkingPlaceModel;
import com.example.kwadrat.Models.TypeOfBuildingModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AddFlat2Activity extends AppCompatActivity {

    private EditText number_of_floors,
            ground_floor_flats,
            number_of_parking_place;

    private Button btn_next;

    private Spinner spinnerDistrict,
                    spinnerFloor,
                    spinnerNumberOfRooms,
                    spinnerTypeOfBuilding,
                    spinnerHeating,
                    spinnerParkingPlace;

    private ArrayList<DistrictModel> districtModelArrayList;
    private ArrayList<FloorModel> floorModelArrayList;
    private ArrayList<NumberOfRoomsModel> numberOfRoomsModelArrayList;
    private ArrayList<TypeOfBuildingModel> typeOfBuildingModelArrayList;
    private ArrayList<HeatingModel> heatingModelArrayList;
    private ArrayList<ParkingPlaceModel> parkingPlaceModelArrayList;

    private ArrayList<String> districts = new ArrayList<String>();
    private ArrayList<String> floors = new ArrayList<String>();
    private ArrayList<String> numberOfRooms = new ArrayList<String>();
    private ArrayList<String> typeOfBuildings = new ArrayList<String>();
    private ArrayList<String> heatings = new ArrayList<String>();
    private ArrayList<String> parkingPlaces = new ArrayList<String>();

    private ProgressBar progressBar;

    private static String URL_ADD = "http://192.168.8.100/kwadrat/addflat2.php";
    private static String URL_GET_DISTRICTS = "http://192.168.8.100/kwadrat/districts.php";
    private static String URL_GET_FLOORS = "http://192.168.8.100/kwadrat/floors.php";
    private static String URL_GET_NUMBER_OF_ROOMS = "http://192.168.8.100/kwadrat/numberofrooms.php";
    private static String URL_GET_TYPE_OF_BUILDINGS = "http://192.168.8.100/kwadrat/typeofbuildings.php";
    private static String URL_GET_HEATINGS = "http://192.168.8.100/kwadrat/heating.php";
    private static String URL_GET_PARKING_PLACES = "http://192.168.8.100/kwadrat/parkingplaces.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_flat2);

        spinnerDistrict = findViewById(R.id.district);
        spinnerFloor = findViewById(R.id.floor);
        spinnerNumberOfRooms = findViewById(R.id.number_of_rooms);
        spinnerTypeOfBuilding = findViewById(R.id.type_of_building);
        spinnerHeating = findViewById(R.id.heating);
        spinnerParkingPlace = findViewById(R.id.parking_place);

        number_of_floors = findViewById(R.id.number_of_floors);
        ground_floor_flats = findViewById(R.id.ground_floor_flats);
        number_of_parking_place = findViewById(R.id.number_of_parking_place);

        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);

        btn_next = findViewById(R.id.btn_next);

        Intent intent = getIntent();
        String city_name = intent.getStringExtra("city_name");

        getDistricts(city_name);
        getFloors();
        getNumberOfRooms();
        getTypeOfBuildings();
        getHeatings();
        getParkingPlaces();

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);

                Intent intent = getIntent();
                String mEmail = intent.getStringExtra("email");

                Add(mEmail, city_name);
            }
        });
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

                            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(AddFlat2Activity.this, R.layout.one_row, districts);
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

    private void getFloors() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_GET_FLOORS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            floorModelArrayList = new ArrayList<>();
                            JSONArray array = new JSONArray(response);

                            for(int i=0; i<array.length(); i++) {
                                FloorModel nameModel = new FloorModel();
                                JSONObject object = array.getJSONObject(i);

                                nameModel.setFloor_number(object.getString("floor_number"));

                                floorModelArrayList.add(nameModel);
                            }

                            for(int i=0; i<floorModelArrayList.size(); i++) {
                                floors.add(floorModelArrayList.get(i).getFloor_number().toString());
                            }

                            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(AddFlat2Activity.this, R.layout.one_row, floors);
                            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                            spinnerFloor.setAdapter(spinnerArrayAdapter);

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

    private void getNumberOfRooms() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_GET_NUMBER_OF_ROOMS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            numberOfRoomsModelArrayList = new ArrayList<>();
                            JSONArray array = new JSONArray(response);

                            for(int i=0; i<array.length(); i++) {
                                NumberOfRoomsModel nameModel = new NumberOfRoomsModel();
                                JSONObject object = array.getJSONObject(i);

                                nameModel.setNumber_of_rooms(object.getString("number_of_rooms"));

                                numberOfRoomsModelArrayList.add(nameModel);
                            }

                            for(int i=0; i<numberOfRoomsModelArrayList.size(); i++) {
                                numberOfRooms.add(numberOfRoomsModelArrayList.get(i).getNumber_of_rooms().toString());
                            }

                            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(AddFlat2Activity.this, R.layout.one_row, numberOfRooms);
                            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                            spinnerNumberOfRooms.setAdapter(spinnerArrayAdapter);

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

    private void getTypeOfBuildings() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_GET_TYPE_OF_BUILDINGS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            typeOfBuildingModelArrayList = new ArrayList<>();
                            JSONArray array = new JSONArray(response);

                            for(int i=0; i<array.length(); i++) {
                                TypeOfBuildingModel nameModel = new TypeOfBuildingModel();
                                JSONObject object = array.getJSONObject(i);

                                nameModel.setType_of_building(object.getString("type_of_building"));

                                typeOfBuildingModelArrayList.add(nameModel);
                            }

                            for(int i=0; i<typeOfBuildingModelArrayList.size(); i++) {
                                typeOfBuildings.add(typeOfBuildingModelArrayList.get(i).getType_of_building().toString());
                            }

                            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(AddFlat2Activity.this, R.layout.one_row, typeOfBuildings);
                            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                            spinnerTypeOfBuilding.setAdapter(spinnerArrayAdapter);

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

    private void getHeatings() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_GET_HEATINGS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            heatingModelArrayList = new ArrayList<>();
                            JSONArray array = new JSONArray(response);

                            for(int i=0; i<array.length(); i++) {
                                HeatingModel nameModel = new HeatingModel();
                                JSONObject object = array.getJSONObject(i);

                                nameModel.setType_of_heating(object.getString("type_of_heating"));

                                heatingModelArrayList.add(nameModel);
                            }

                            for(int i=0; i<heatingModelArrayList.size(); i++) {
                                heatings.add(heatingModelArrayList.get(i).getType_of_heating().toString());
                            }

                            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(AddFlat2Activity.this, R.layout.one_row, heatings);
                            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                            spinnerHeating.setAdapter(spinnerArrayAdapter);

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

    private void getParkingPlaces() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_GET_PARKING_PLACES,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            parkingPlaceModelArrayList = new ArrayList<>();
                            JSONArray array = new JSONArray(response);

                            for(int i=0; i<array.length(); i++) {
                                ParkingPlaceModel nameModel = new ParkingPlaceModel();
                                JSONObject object = array.getJSONObject(i);

                                nameModel.setType_of_parking_place(object.getString("type_of_parking_place"));

                                parkingPlaceModelArrayList.add(nameModel);
                            }

                            for(int i=0; i<parkingPlaceModelArrayList.size(); i++) {
                                parkingPlaces.add(parkingPlaceModelArrayList.get(i).getType_of_parking_place().toString());
                            }

                            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(AddFlat2Activity.this, R.layout.one_row, parkingPlaces);
                            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                            spinnerParkingPlace.setAdapter(spinnerArrayAdapter);

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

    private void Add(String email, String city_name) {
        final String district_name = this.spinnerDistrict.getSelectedItem().toString().trim();
        final String floor_number = this.spinnerFloor.getSelectedItem().toString().trim();
        final String number_of_floors = this.number_of_floors.getText().toString().trim();
        final String ground_floor_flats = this.ground_floor_flats.getText().toString().trim();
        final String number_of_rooms = this.spinnerNumberOfRooms.getSelectedItem().toString().trim();
        final String type_of_building = this.spinnerTypeOfBuilding.getSelectedItem().toString().trim();
        final String heating = this.spinnerHeating.getSelectedItem().toString().trim();
        final String parking_place = this.spinnerParkingPlace.getSelectedItem().toString().trim();
        final String number_of_parking_place = this.number_of_parking_place.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_ADD,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")) {
                                progressBar.setVisibility(View.INVISIBLE);
                                Toast.makeText(AddFlat2Activity.this, "Krok drugi wykonany pomyślnie!", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(AddFlat2Activity.this, AddFlat3Activity.class);
                                intent.putExtra("email", email);
                                startActivity(intent);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(AddFlat2Activity.this, "Błąd dodawania! " + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AddFlat2Activity.this, "Błąd! " + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })

        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("city_name", city_name);
                params.put("district_name", district_name);
                params.put("floor_number", floor_number);
                params.put("number_of_floors", number_of_floors);
                params.put("ground_floor_flats", ground_floor_flats);
                params.put("number_of_rooms", number_of_rooms);
                params.put("type_of_building", type_of_building);
                params.put("heating", heating);
                params.put("parking_place", parking_place);
                params.put("number_of_parking_place", number_of_parking_place);
                params.put("email", email);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
