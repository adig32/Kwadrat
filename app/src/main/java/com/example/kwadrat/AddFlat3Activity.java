package com.example.kwadrat;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
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

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class AddFlat3Activity extends AppCompatActivity {

    private EditText rubbish, flat_direction, brightness_of_flat, environment_description, number_of_lifts, available_from, description;
    private ImageButton btn_set_date;
    private Button btn_next;
    private CheckBox garage, balcony, animals, family_with_children, smoking_person, sublet_permission;
    private ProgressBar progressBar;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private static String URL_ADD = "http://192.168.8.100/kwadrat/addflat3.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_flat3);

        rubbish = findViewById(R.id.rubbish);
        flat_direction = findViewById(R.id.flat_direction);
        brightness_of_flat = findViewById(R.id.brightness_of_flat);
        environment_description = findViewById(R.id.environment_description);
        number_of_lifts = findViewById(R.id.number_of_lifts);
        available_from = findViewById(R.id.available_from);
        description = findViewById(R.id.description);

        btn_set_date = findViewById(R.id.btn_set_date);

        btn_next = findViewById(R.id.btn_next);

        garage = findViewById(R.id.garage);
        balcony = findViewById(R.id.balcony);
        animals = findViewById(R.id.animals);
        family_with_children = findViewById(R.id.family_with_children);
        smoking_person = findViewById(R.id.smoking_person);
        sublet_permission = findViewById(R.id.sublet_permission);

        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);

        btn_set_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        AddFlat3Activity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year, month, day);
                dialog.setTitle("Wybierz datę:");
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;

                if(month < 10 && day < 10) {
                    String get_available_date = year + "-0" + month + "-0" + day;
                    available_from.setText(get_available_date);
                } else if (month < 10 && day > 10) {
                    String get_available_date = year + "-0" + month + "-" + day;
                    available_from.setText(get_available_date);
                } else if (month > 10 && day < 10) {
                    String get_available_date = year + "-" + month + "-0" + day;
                    available_from.setText(get_available_date);
                } else {
                    String get_available_date = year + "-" + month + "-" + day;
                    available_from.setText(get_available_date);
                }
            }
        };

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

    private void Add(String email) {
        final String rubbish = this.rubbish.getText().toString().trim();
        final String flat_direction = this.flat_direction.getText().toString().trim();
        final String brightness_of_flat = this.brightness_of_flat.getText().toString().trim();
        final String environment_description = this.environment_description.getText().toString().trim();
        final String number_of_lifts = this.number_of_lifts.getText().toString().trim();
        final String available_from = this.available_from.getText().toString().trim();
        final String description = this.description.getText().toString().trim();

        String garage, balcony, animals, family_with_children, smoking_person, sublet_permission, elevator;

        if(this.garage.isChecked()) garage= "1";
        else garage = "0";

        if(this.balcony.isChecked()) balcony = "1";
        else balcony = "0";

        if(this.animals.isChecked()) animals = "1";
        else animals = "0";

        if(this.family_with_children.isChecked()) family_with_children = "1";
        else family_with_children = "0";

        if(this.smoking_person.isChecked()) smoking_person = "1";
        else smoking_person = "0";

        if(this.sublet_permission.isChecked()) sublet_permission = "1";
        else sublet_permission = "0";

        if(number_of_lifts.equals("0")) elevator = "0";
        else elevator = "1";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_ADD,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")) {
                                progressBar.setVisibility(View.INVISIBLE);
                                Toast.makeText(AddFlat3Activity.this, "Krok trzeci wykonany pomyślnie!", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(AddFlat3Activity.this, AddFlat4Activity.class);
                                intent.putExtra("email", email);
                                startActivity(intent);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(AddFlat3Activity.this, "Błąd dodawania! " + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AddFlat3Activity.this, "Błąd! " + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })

        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("rubbish", rubbish);
                params.put("flat_direction", flat_direction);
                params.put("brightness_of_flat", brightness_of_flat);
                params.put("environment_description", environment_description);
                params.put("number_of_lifts", number_of_lifts);
                params.put("available_from", available_from);
                params.put("description", description);
                params.put("garage", garage);
                params.put("balcony", balcony);
                params.put("animals", animals);
                params.put("family_with_children", family_with_children);
                params.put("smoking_person", smoking_person);
                params.put("elevator", elevator);
                params.put("sublet_permission", sublet_permission);
                params.put("email", email);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
