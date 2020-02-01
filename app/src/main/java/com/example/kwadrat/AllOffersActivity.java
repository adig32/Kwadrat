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
import com.example.kwadrat.Adapters.OfferAdapter;
import com.example.kwadrat.Models.OfferModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AllOffersActivity extends AppCompatActivity {

    private String URL_ALL_OFFERS = "http://192.168.8.100/kwadrat/alloffers.php";
    private List<OfferModel> offerList;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private Button btn_filters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_offers);

        btn_filters = findViewById(R.id.btn_filters);

        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        recyclerView = findViewById(R.id.all_offers);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        offerList = new ArrayList<>();

        Intent intent = getIntent();
        String final_query = intent.getStringExtra("final_query");
        String mEmail = intent.getStringExtra("email");

        loadOffers(mEmail, final_query);

        btn_filters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AllOffersActivity.this, FiltersActivity.class);
                intent.putExtra("email", mEmail);
                startActivity(intent);
            }
        });
    }

    private void loadOffers(String email, String query) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_ALL_OFFERS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);

                            for(int i=0; i<array.length(); i++) {
                                JSONObject oneOffer = array.getJSONObject(i);

                                if(oneOffer.getString("blocked").equals("0") && oneOffer.getString("visibility").equals("1")) {
                                    offerList.add(new OfferModel(
                                            oneOffer.getString("id").trim(),
                                            "Ulica: " + oneOffer.getString("street").trim(),
                                            "Miasto: " + oneOffer.getString("city_name").trim(),
                                            "Dzielnica: " + oneOffer.getString("district_name").trim(),
                                            oneOffer.getString("rent").trim() + " zł",
                                            "Powierzchnia mieszkania: " + oneOffer.getString("flat_area").trim() + " m²",
                                            "Dodane przez użytkownika: " + oneOffer.getString("login").trim(),
                                            oneOffer.getString("path").trim(),
                                            oneOffer.getString("user_email").trim()
                                    ));
                                }
                            }
                            OfferAdapter adapter = new OfferAdapter(AllOffersActivity.this, offerList);
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
                params.put("query", query);
                params.put("email", email);
                return params;
            }
        };

        Volley.newRequestQueue(this).add(stringRequest);
    }
}
