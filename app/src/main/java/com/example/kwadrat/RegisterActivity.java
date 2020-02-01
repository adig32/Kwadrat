package com.example.kwadrat;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
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

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    private EditText name, second_name, last_name, birth_date, place, postcode, login, phone_number, email, password, c_password;
    private RadioButton gender_female, gender_male;
    private Integer gender;
    private Button btn_regist;
    private ImageButton btn_set_date;
    private TextView tv_returnToLogin;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private ProgressBar progressBar;
    private static String URL_REGIST = "http://192.168.8.100/kwadrat/register.php";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = findViewById(R.id.name);
        second_name = findViewById(R.id.second_name);
        last_name = findViewById(R.id.last_name);
        birth_date = findViewById(R.id.birth_date);
        place = findViewById(R.id.place);
        postcode = findViewById(R.id.postcode);
        login = findViewById(R.id.login);
        gender_female = findViewById(R.id.gender_female);
        gender_male = findViewById(R.id.gender_male);
        gender = checkGender();
        phone_number = findViewById(R.id.phone_number);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        c_password = findViewById(R.id.c_password);

        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);

        btn_set_date = findViewById(R.id.btn_set_date);
        btn_regist = findViewById(R.id.btn_regist);
        tv_returnToLogin = findViewById(R.id.tv_returnToLogin);


        btn_regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                Regist();
            }
        });

        tv_returnToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });

        btn_set_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        RegisterActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year, month, day);
                dialog.setTitle("Wybierz datę urodzenia:");
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;

                if(month < 10 && day < 10) {
                    String get_birth_date = year + "-0" + month + "-0" + day;
                    birth_date.setText(get_birth_date);
                } else if (month < 10 && day > 10) {
                    String get_birth_date = year + "-0" + month + "-" + day;
                    birth_date.setText(get_birth_date);
                } else if (month > 10 && day < 10) {
                    String get_birth_date = year + "-" + month + "-0" + day;
                    birth_date.setText(get_birth_date);
                } else {
                    String get_birth_date = year + "-" + month + "-" + day;
                    birth_date.setText(get_birth_date);
                }
            }
        };

    }
    private Integer checkGender() {
        if(gender_male.isChecked()) {
            return 2;
        } else if(gender_female.isChecked()) return 1;
        else return 0;
    }

    private void Regist(){
        btn_regist.setVisibility(View.VISIBLE);

        final String name = this.name.getText().toString().trim();
        final String second_name = this.second_name.getText().toString().trim();
        final String last_name = this.last_name.getText().toString().trim();
        final String birth_date = this.birth_date.getText().toString().trim();
        final String place = this.place.getText().toString().trim();
        String string_postcode = this.postcode.getText().toString().trim();
        final Integer postcode;

        if(Integer.getInteger(string_postcode) != null) {
            postcode = Integer.getInteger(string_postcode);
        } else postcode = null;

        final String login = this.login.getText().toString().trim();
        Integer int_gender = checkGender();
        String string_gender = String.valueOf(int_gender);
        String string_phone_number = this.phone_number.getText().toString().trim();
        final Integer phone_number;

        if(Integer.getInteger(string_phone_number) != null) {
            phone_number = Integer.getInteger(string_phone_number);
        } else phone_number = null;

        final String email = this.email.getText().toString().trim();
        final String password = this.password.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGIST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")) {
                                progressBar.setVisibility(View.INVISIBLE);
                                Toast.makeText(RegisterActivity.this, "Zarejestrowano pomyślnie!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(RegisterActivity.this, "Błąd rejestracji! " + e.toString(), Toast.LENGTH_SHORT).show();
                            btn_regist.setVisibility(View.VISIBLE);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RegisterActivity.this, "Błąd rejestracji! " + error.toString(), Toast.LENGTH_SHORT).show();
                        btn_regist.setVisibility(View.VISIBLE);
                    }
                })

        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError{
                Map<String, String> params = new HashMap<>();
                params.put("name", name);
                params.put("second_name", second_name);
                params.put("last_name", last_name);
                params.put("birth_date", birth_date);
                params.put("place", place);
                params.put("postcode", string_postcode);
                params.put("login", login);
                params.put("gender", string_gender);
                params.put("phone_number", string_phone_number);
                params.put("email", email);
                params.put("password", password);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
