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
import com.example.kwadrat.Adapters.OfferToReviewAdapter;
import com.example.kwadrat.Models.MyOfferModel;
import com.example.kwadrat.Models.OfferToReviewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReviewsActivity extends AppCompatActivity {

    private String email;
    private String URL_OFFERS_TO_REVIEW = "http://192.168.8.100/kwadrat/offerstoreview.php";
    private List<OfferToReviewModel> offerToReviewList;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviews);

        recyclerView = findViewById(R.id.offers_to_review);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        offerToReviewList = new ArrayList<>();

        Intent intent = getIntent();
        email = intent.getStringExtra("email");

        loadOffers(email);

    }

    private void loadOffers(String email) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_OFFERS_TO_REVIEW,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);

                            for(int i=0; i<array.length(); i++) {
                                JSONObject offerToReview = array.getJSONObject(i);

                                    offerToReviewList.add(new OfferToReviewModel(
                                            offerToReview.getString("flat_id"),
                                            offerToReview.getString("city_name"),
                                            offerToReview.getString("street"),
                                            offerToReview.getString("district_name"),
                                            offerToReview.getString("user_id"),
                                            offerToReview.getString("owner_id")
                                    ));
                            }
                            OfferToReviewAdapter adapter = new OfferToReviewAdapter(ReviewsActivity.this, offerToReviewList);
                            recyclerView.setAdapter(adapter);
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
