package com.example.kwadrat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.kwadrat.Adapters.MyOfferAdapter;
import com.example.kwadrat.Models.MyOfferModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyOffersActivity extends AppCompatActivity {

    private String email;
    private String URL_MY_OFFERS = "http://192.168.8.100/kwadrat/myoffers.php";
    private List<MyOfferModel> myOfferList;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_offers);

        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        recyclerView = findViewById(R.id.my_offers);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        myOfferList = new ArrayList<>();

        Intent intent = getIntent();
        email = intent.getStringExtra("email");

        loadOffers(email);

    }

    private void loadOffers(String email) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_MY_OFFERS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);

                            for(int i=0; i<array.length(); i++) {
                                JSONObject myOffer = array.getJSONObject(i);

                                if(myOffer.getString("blocked").equals("0") && myOffer.getString("visibility").equals("1")) {
                                    myOfferList.add(new MyOfferModel(
                                            myOffer.getString("id").trim(),
                                            "Ulica: " + myOffer.getString("street").trim(),
                                            "Miasto: " + myOffer.getString("city_name").trim(),
                                            "Dzielnica: " + myOffer.getString("district_name").trim(),
                                            myOffer.getString("rent").trim() + " zł",
                                            "Powierzchnia mieszkania: " + myOffer.getString("flat_area").trim() + " m²",
                                            "aktywne",
                                            myOffer.getString("path").trim()
                                    ));
                                } else if(myOffer.getString("blocked").equals("0") && myOffer.getString("visibility").equals("0")) {
                                    myOfferList.add(new MyOfferModel(
                                            myOffer.getString("id").trim(),
                                            "Ulica: " + myOffer.getString("street").trim(),
                                            "Miasto: " + myOffer.getString("city_name").trim(),
                                            "Dzielnica: " + myOffer.getString("district_name").trim(),
                                            myOffer.getString("rent").trim() + " zł",
                                            "Powierzchnia mieszkania: " + myOffer.getString("flat_area").trim() + " m²",
                                            "nieaktywne",
                                            myOffer.getString("path").trim()
                                    ));
                                }
                            }
                            MyOfferAdapter adapter = new MyOfferAdapter(MyOffersActivity.this, myOfferList);
                            recyclerView.setAdapter(adapter);
                            progressBar.setVisibility(View.INVISIBLE);
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
