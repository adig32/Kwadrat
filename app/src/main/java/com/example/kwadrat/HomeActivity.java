package com.example.kwadrat;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
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

public class HomeActivity extends AppCompatActivity {

    static final private int ALERT_DIALOG_BUTTONS = 2;

    private TextView name, email;
    private Button btn_logout, btn_profile, btn_offers, btn_favourites, btn_messages, btn_review;
    private ImageButton btn_search, btn_add;
    private String URL_GET_NAME = "http://192.168.8.100/kwadrat/profile.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        btn_profile = findViewById(R.id.btn_profile);
        btn_logout = findViewById(R.id.btn_logout);
        btn_offers = findViewById(R.id.btn_offers);
        btn_add = findViewById(R.id.btn_add);
        btn_search = findViewById(R.id.btn_search);
        btn_favourites = findViewById(R.id.btn_fav);
        btn_messages = findViewById(R.id.btn_messages);
        btn_review = findViewById(R.id.btn_review);
        initButtonsClick();

        Intent intent = getIntent();
        String extraEmail = intent.getStringExtra("email");

        email.setText(extraEmail);

        String string_email = email.getText().toString().trim();

        getName(string_email);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, AddFlat1Activity.class);

                intent.putExtra("email", string_email);
                startActivity(intent);
            }
        });

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, AllOffersActivity.class);

                intent.putExtra("final_query", "GROUP BY flats.id ORDER BY flats.created_at DESC");
                intent.putExtra("email", extraEmail);
                startActivity(intent);
            }
        });

        btn_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ProfileActivity.class);
                intent.putExtra("email", string_email);
                startActivity(intent);
            }
        });

        btn_offers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, MyOffersActivity.class);
                intent.putExtra("email", string_email);
                startActivity(intent);
            }
        });

        btn_favourites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, FavouritesActivity.class);

                intent.putExtra("email", string_email);
                startActivity(intent);
            }
        });

        btn_messages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, MessagesActivity.class);

                intent.putExtra("email", string_email);
                startActivity(intent);
            }
        });

        btn_review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ReviewsActivity.class);
                intent.putExtra("email", string_email);
                startActivity(intent);
            }
        });
    }

    private void initButtonsClick() {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(ALERT_DIALOG_BUTTONS);
            }
        };
        btn_logout.setOnClickListener(listener);
    }

    protected Dialog onCreateDialog(int id) {
        return createAlertDialogWithButtons();
    }

    private Dialog createAlertDialogWithButtons() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle("Wylogowanie");
        dialogBuilder.setMessage("Chcesz się wylogować?");
        dialogBuilder.setCancelable(false);
        dialogBuilder.setPositiveButton("Tak", new Dialog.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int whichButton) {
                showToast("Wylogowano!");
                finish();
                startActivity(new Intent(HomeActivity.this, FirstPageActivity.class));
            }
        });
        dialogBuilder.setNegativeButton("Nie", new Dialog.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int whichButton) {

            }
        });
        return dialogBuilder.create();
    }

    private void showToast(String message) {
        Toast.makeText(getApplicationContext(),
                message,
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBackPressed() {
        showDialog(ALERT_DIALOG_BUTTONS);
    }

    private void getName(String email) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_GET_NAME,
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

                                    name.setText(object.getString("name"));
                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(HomeActivity.this, "Błąd dodawania! " + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(HomeActivity.this, "Błąd! " + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }) {
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
}
