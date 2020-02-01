package com.example.kwadrat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.kwadrat.Adapters.EquipmentAdapter;
import com.example.kwadrat.Adapters.PhotoAdapter;
import com.example.kwadrat.Models.EquipmentModel;
import com.example.kwadrat.Models.PhotoModel;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FavouriteOfferDetailsActivity extends AppCompatActivity {

    private TextView city_name,
            street,
            district,
            number_of_tenants,
            flat_area,
            floor,
            number_of_floors,
            ground_floor_flats,
            number_of_rooms,
            type_of_building,
            heating,
            parking_place,
            number_of_parking_place,
            rubbish,
            flat_direction,
            brightness_of_flat,
            environment_description,
            number_of_lifts,
            garage,
            balcony,
            animals,
            family_with_children,
            smoking_person,
            sublet_permission,
            inconveniences,
            available_from,
            description,
            rent,
            media_fees,
            additional_fees,
            deposit,
            login;
    private RecyclerView flat_equipment, kitchen_equipment, bathroom_equipment, media, photos;
    private ImageView imageView1;
    private List<EquipmentModel> flatEquipmentList;
    private List<EquipmentModel> kitchenEquipmentList;
    private List<EquipmentModel> bathroomEquipmentList;
    private List<EquipmentModel> mediaList;
    private List<PhotoModel> photoList;
    private String URL_GET_FLAT_EQUIPMENT = "http://192.168.8.100/kwadrat/getflatequipment.php";
    private String URL_GET_KITCHEN_EQUIPMENT = "http://192.168.8.100/kwadrat/getkitchenequipment.php";
    private String URL_GET_BATHROOM_EQUIPMENT = "http://192.168.8.100/kwadrat/getbathroomequipment.php";
    private String URL_GET_MEDIA = "http://192.168.8.100/kwadrat/getmedia.php";
    private String URL_GET_PHOTOS = "http://192.168.8.100/kwadrat/getphotos.php";
    private String URL_GET_DATA = "http://192.168.8.100/kwadrat/getdata.php";

    private String URL_DELETE_FROM_FAVOURITES = "http://192.168.8.100/kwadrat/deletefromfavourites.php";

    private Button btn_delete_from_favourites, btn_send_message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_offer_details);

        city_name = findViewById(R.id.city_name);
        street = findViewById(R.id.street);
        district = findViewById(R.id.district);
        number_of_tenants = findViewById(R.id.number_of_tenants);
        flat_area = findViewById(R.id.flat_area);
        floor = findViewById(R.id.floor);
        number_of_floors = findViewById(R.id.number_of_floors);
        ground_floor_flats = findViewById(R.id.ground_floor_flats);
        number_of_rooms = findViewById(R.id.number_of_rooms);
        type_of_building = findViewById(R.id.type_of_building);
        heating = findViewById(R.id.heating);
        parking_place = findViewById(R.id.parking_place);
        number_of_parking_place = findViewById(R.id.number_of_parking_place);
        rubbish = findViewById(R.id.rubbish);
        flat_direction = findViewById(R.id.flat_direction);
        brightness_of_flat = findViewById(R.id.brightness_of_flat);
        environment_description = findViewById(R.id.environment_description);
        number_of_lifts = findViewById(R.id.number_of_lifts);
        garage = findViewById(R.id.garage);
        balcony = findViewById(R.id.balcony);
        animals = findViewById(R.id.animals);
        family_with_children = findViewById(R.id.family_with_children);
        smoking_person = findViewById(R.id.smoking_person);
        sublet_permission = findViewById(R.id.sublet_permission);
        inconveniences = findViewById(R.id.inconveniences);
        available_from = findViewById(R.id.available_from);
        description = findViewById(R.id.description);
        rent = findViewById(R.id.rent);
        media_fees = findViewById(R.id.media_fees);
        additional_fees = findViewById(R.id.additional_fees);
        deposit = findViewById(R.id.deposit);
        login = findViewById(R.id.login);

        btn_delete_from_favourites = findViewById(R.id.btn_delete_from_favourites);
        btn_send_message = findViewById(R.id.btn_send_message);

        flat_equipment = findViewById(R.id.flat_equipment);
        kitchen_equipment = findViewById(R.id.kitchen_equipment);
        bathroom_equipment = findViewById(R.id.bathroom_equipment);
        media = findViewById(R.id.media);
        photos = findViewById(R.id.photos);

        flat_equipment.setHasFixedSize(true);
        flat_equipment.setLayoutManager(new LinearLayoutManager(this));

        kitchen_equipment.setHasFixedSize(true);
        kitchen_equipment.setLayoutManager(new LinearLayoutManager(this));

        bathroom_equipment.setHasFixedSize(true);
        bathroom_equipment.setLayoutManager(new LinearLayoutManager(this));

        media.setHasFixedSize(true);
        media.setLayoutManager(new LinearLayoutManager(this));

        photos.setHasFixedSize(true);
        photos.setLayoutManager(new LinearLayoutManager(this));

        imageView1 = findViewById(R.id.imageView1);

        flatEquipmentList = new ArrayList<>();
        kitchenEquipmentList = new ArrayList<>();
        bathroomEquipmentList = new ArrayList<>();
        mediaList = new ArrayList<>();
        photoList = new ArrayList<>();

        Intent intent = getIntent();
        String mId = intent.getStringExtra("id");
        String mEmail = intent.getStringExtra("user_email");

        getData(mId);
        getFlatEquipment(mId);
        getKitchenEquipment(mId);
        getBathroomEquipment(mId);
        getMedia(mId);
        getPhotos(mId);

        btn_delete_from_favourites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteFromFavourites(mId, mEmail);

                //startActivity(new Intent());
            }
        });

        btn_send_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FavouriteOfferDetailsActivity.this, SendMessageActivity.class);
                intent.putExtra("flat_id", mId);
                intent.putExtra("email", mEmail);
                startActivity(intent);
            }
        });
    }

    private void getData(String id) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_GET_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("offerdetails");

                            if (success.equals("1")) {

                                for (int i = 0; i < jsonArray.length(); i++) {

                                    JSONObject object = jsonArray.getJSONObject(i);

                                    city_name.setText(object.getString("city_name"));
                                    street.setText(object.getString("street"));
                                    district.setText(object.getString("district_name"));
                                    number_of_tenants.setText(object.getString("number_of_tenants"));
                                    flat_area.setText(object.getString("flat_area"));
                                    floor.setText(object.getString("floor_number"));
                                    number_of_floors.setText(object.getString("number_of_floors"));
                                    ground_floor_flats.setText(object.getString("ground_floor_flats"));
                                    number_of_rooms.setText(object.getString("number_of_rooms"));
                                    type_of_building.setText(object.getString("type_of_building"));
                                    heating.setText(object.getString("type_of_heating"));
                                    parking_place.setText(object.getString("type_of_parking_place"));
                                    number_of_parking_place.setText(object.getString("number_of_parking_place"));
                                    rubbish.setText(object.getString("rubbish"));
                                    flat_direction.setText(object.getString("flat_direction"));
                                    brightness_of_flat.setText(object.getString("brightness_of_flat"));
                                    environment_description.setText(object.getString("environment_description"));
                                    number_of_lifts.setText(object.getString("number_of_lifts"));

                                    if(object.getString("garage").equals("1")) garage.setTextColor(Color.rgb(0, 150, 0));
                                    else garage.setTextColor(Color.rgb(150, 0, 0));

                                    if(object.getString("balcony").equals("1")) balcony.setTextColor(Color.rgb(0, 150, 0));
                                    else balcony.setTextColor(Color.rgb(150, 0, 0));

                                    if(object.getString("family_with_children").equals("1")) family_with_children.setTextColor(Color.rgb(0, 150, 0));
                                    else family_with_children.setTextColor(Color.rgb(150, 0, 0));

                                    if(object.getString("smoking_person").equals("1")) smoking_person.setTextColor(Color.rgb(0, 150, 0));
                                    else smoking_person.setTextColor(Color.rgb(150, 0, 0));

                                    if(object.getString("animals").equals("1")) animals.setTextColor(Color.rgb(0, 150, 0));
                                    else animals.setTextColor(Color.rgb(150, 0, 0));

                                    if(object.getString("sublet_permission").equals("1")) sublet_permission.setTextColor(Color.rgb(0, 150, 0));
                                    else sublet_permission.setTextColor(Color.rgb(150, 0, 0));

                                    available_from.setText(object.getString("available_from"));
                                    description.setText(object.getString("description"));
                                    rent.setText(object.getString("rent") + " zł");
                                    media_fees.setText(object.getString("media_fees"));
                                    additional_fees.setText(object.getString("additional_fees"));
                                    deposit.setText(object.getString("deposit"));
                                    login.setText(object.getString("login"));
                                    inconveniences.setText(object.getString("inconvenience_description"));

                                    Picasso.get().load(object.getString("path")).into(imageView1);
                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(FavouriteOfferDetailsActivity.this, "Error " +e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(FavouriteOfferDetailsActivity.this, "Error " +error.toString(), Toast.LENGTH_SHORT).show();
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

    private void getFlatEquipment(String id) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_GET_FLAT_EQUIPMENT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);

                            for(int i=0; i<array.length(); i++) {
                                JSONObject oneEquipment = array.getJSONObject(i);

                                flatEquipmentList.add(new EquipmentModel(
                                        oneEquipment.getString("equipment_name").trim()
                                ));
                            }
                            EquipmentAdapter adapter = new EquipmentAdapter(FavouriteOfferDetailsActivity.this, flatEquipmentList);
                            flat_equipment.setAdapter(adapter);
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
                params.put("id", id);
                return params;
            }
        };

        Volley.newRequestQueue(this).add(stringRequest);
    }

    private void getKitchenEquipment(String id) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_GET_KITCHEN_EQUIPMENT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);

                            for(int i=0; i<array.length(); i++) {
                                JSONObject oneEquipment = array.getJSONObject(i);

                                kitchenEquipmentList.add(new EquipmentModel(
                                        oneEquipment.getString("equipment_name").trim()
                                ));
                            }
                            EquipmentAdapter adapter = new EquipmentAdapter(FavouriteOfferDetailsActivity.this, kitchenEquipmentList);
                            kitchen_equipment.setAdapter(adapter);
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
                params.put("id", id);
                return params;
            }
        };

        Volley.newRequestQueue(this).add(stringRequest);
    }

    private void getBathroomEquipment(String id) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_GET_BATHROOM_EQUIPMENT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);

                            for(int i=0; i<array.length(); i++) {
                                JSONObject oneEquipment = array.getJSONObject(i);

                                bathroomEquipmentList.add(new EquipmentModel(
                                        oneEquipment.getString("equipment_name").trim()
                                ));
                            }
                            EquipmentAdapter adapter = new EquipmentAdapter(FavouriteOfferDetailsActivity.this, bathroomEquipmentList);
                            bathroom_equipment.setAdapter(adapter);
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
                params.put("id", id);
                return params;
            }
        };

        Volley.newRequestQueue(this).add(stringRequest);
    }

    private void getMedia(String id) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_GET_MEDIA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);

                            for(int i=0; i<array.length(); i++) {
                                JSONObject oneEquipment = array.getJSONObject(i);

                                mediaList.add(new EquipmentModel(
                                        oneEquipment.getString("media_name").trim()
                                ));
                            }
                            EquipmentAdapter adapter = new EquipmentAdapter(FavouriteOfferDetailsActivity.this, mediaList);
                            media.setAdapter(adapter);
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
                params.put("id", id);
                return params;
            }
        };

        Volley.newRequestQueue(this).add(stringRequest);
    }

    private void getPhotos(String id) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_GET_PHOTOS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);

                            for(int i=0; i<array.length(); i++) {
                                JSONObject onePhoto = array.getJSONObject(i);

                                photoList.add(new PhotoModel(
                                        onePhoto.getString("path").trim()
                                ));
                            }
                            PhotoAdapter adapter = new PhotoAdapter(FavouriteOfferDetailsActivity.this, photoList);
                            photos.setAdapter(adapter);
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
                params.put("id", id);
                return params;
            }
        };

        Volley.newRequestQueue(this).add(stringRequest);
    }

    private void deleteFromFavourites(String id, String email){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DELETE_FROM_FAVOURITES,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")) {
                                Toast.makeText(FavouriteOfferDetailsActivity.this, "Odobserwowano pomyślnie!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(FavouriteOfferDetailsActivity.this, FavouritesActivity.class);
                                intent.putExtra("email", email);
                                startActivity(intent);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(FavouriteOfferDetailsActivity.this, "Błąd odobserwowania! " + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(FavouriteOfferDetailsActivity.this, "Błąd odobserwowania! " + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })

        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError{
                Map<String, String> params = new HashMap<>();
                params.put("id", id);
                params.put("email", email);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
