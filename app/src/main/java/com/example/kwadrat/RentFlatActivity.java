package com.example.kwadrat;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.kwadrat.Models.UserModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class RentFlatActivity extends AppCompatActivity {

    private Spinner spinnerUser;
    private ArrayList<UserModel> userModelArrayList;
    private ArrayList<String> users = new ArrayList<String>();
    private static String URL_GET_USERS = "http://192.168.8.100/kwadrat/users.php";
    private static String URL_RENT = "http://192.168.8.100/kwadrat/rent.php";
    private String URL_DEACTIVATE_OFFER = "http://192.168.8.100/kwadrat/deactivateoffer.php";
    private EditText since_when, until_when;
    private ImageButton btn_set_since_when, btn_set_until_when;
    private DatePickerDialog.OnDateSetListener mDateSetListener1, mDateSetListener2;
    private Button btn_rent;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent_flat);

        spinnerUser = findViewById(R.id.user);

        since_when = findViewById(R.id.since_when);
        until_when = findViewById(R.id.until_when);

        btn_set_since_when = findViewById(R.id.btn_set_since_when);
        btn_set_until_when = findViewById(R.id.btn_set_until_when);

        btn_rent = findViewById(R.id.btn_rent);

        Intent intent = getIntent();

        String flat_id = intent.getStringExtra("flat_id");
        email = intent.getStringExtra("email");

        getUsers(flat_id);

        btn_set_since_when.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        RentFlatActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener1,
                        year, month, day);
                dialog.setTitle("Wybierz datę:");
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener1 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;

                if(month < 10 && day < 10) {
                    String get_available_date = year + "-0" + month + "-0" + day;
                    since_when.setText(get_available_date);
                } else if (month < 10 && day > 10) {
                    String get_available_date = year + "-0" + month + "-" + day;
                    since_when.setText(get_available_date);
                } else if (month > 10 && day < 10) {
                    String get_available_date = year + "-" + month + "-0" + day;
                    since_when.setText(get_available_date);
                } else {
                    String get_available_date = year + "-" + month + "-" + day;
                    since_when.setText(get_available_date);
                }
            }
        };

        btn_set_until_when.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        RentFlatActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener2,
                        year, month, day);
                dialog.setTitle("Wybierz datę:");
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener2 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;

                if(month < 10 && day < 10) {
                    String get_available_date = year + "-0" + month + "-0" + day;
                    until_when.setText(get_available_date);
                } else if (month < 10 && day > 10) {
                    String get_available_date = year + "-0" + month + "-" + day;
                    until_when.setText(get_available_date);
                } else if (month > 10 && day < 10) {
                    String get_available_date = year + "-" + month + "-0" + day;
                    until_when.setText(get_available_date);
                } else {
                    String get_available_date = year + "-" + month + "-" + day;
                    until_when.setText(get_available_date);
                }
            }
        };

        btn_rent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Rent(spinnerUser.getSelectedItem().toString(), flat_id, since_when.getText().toString(), until_when.getText().toString());
                ChangeVisibility(flat_id);

            }
        });
    }

    private void getUsers(String flat_id) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_GET_USERS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            userModelArrayList = new ArrayList<>();
                            JSONArray array = new JSONArray(response);

                            for(int i=0; i<array.length(); i++) {
                                UserModel userModel = new UserModel();
                                JSONObject object = array.getJSONObject(i);

                                userModel.setLogin(object.getString("login"));

                                userModelArrayList.add(userModel);
                            }

                            for(int i=0; i<userModelArrayList.size(); i++) {
                                users.add(userModelArrayList.get(i).getLogin().toString());
                            }

                            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(RentFlatActivity.this, R.layout.one_row, users);
                            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                            spinnerUser.setAdapter(spinnerArrayAdapter);

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
                params.put("flat_id", flat_id);
                return params;
            }
        };

        Volley.newRequestQueue(this).add(stringRequest);
    }

    private void Rent(String user_login, String flat_id, String since_when, String until_when){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_RENT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")) {
                                Toast.makeText(RentFlatActivity.this, "Wynajęto pomyślnie!", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(RentFlatActivity.this, "Błąd wynajmowania! " + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RentFlatActivity.this, "Błąd wynajmowania! " + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })

        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError{
                Map<String, String> params = new HashMap<>();
                params.put("user_login", user_login);
                params.put("flat_id", flat_id);
                params.put("since_when", since_when);
                params.put("until_when", until_when);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void ChangeVisibility(String id){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DEACTIVATE_OFFER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")) {
                                Toast.makeText(RentFlatActivity.this, "Wynajęto pomyślnie!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(RentFlatActivity.this, MyOffersActivity.class);

                                intent.putExtra("email", email);

                                startActivity(intent);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(RentFlatActivity.this, "Błąd aktualizacji! " + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RentFlatActivity.this, "Błąd aktualizacji! " + error.toString(), Toast.LENGTH_SHORT).show();
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
}
