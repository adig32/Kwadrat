package com.example.kwadrat;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class FirstPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firstpage);

        Button btn_login_start = findViewById(R.id.btn_login_start);
        Button btn_register_start = findViewById(R.id.btn_register_start);

        btn_login_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FirstPageActivity.this,
                        LoginActivity.class);
                startActivity(i);
            }
        });

        btn_register_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FirstPageActivity.this,
                        RegisterActivity.class);
                startActivity(i);
            }
        });

    }
    @Override
    public void onBackPressed() {
        showDialog(2);
    }

    private void showToast(String message) {
        Toast.makeText(getApplicationContext(),
                message,
                Toast.LENGTH_LONG).show();
    }

    private Dialog createAlertDialogWithButtons() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle("Wyjście");
        dialogBuilder.setMessage("Chcesz wyjść z aplikacji?");
        dialogBuilder.setCancelable(false);
        dialogBuilder.setPositiveButton("Tak", new Dialog.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int whichButton) {
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                startActivity(new Intent(FirstPageActivity.this, FirstPageActivity.class));
                System.exit(1);
            }
        });
        dialogBuilder.setNegativeButton("Nie", new Dialog.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int whichButton) {

            }
        });
        return dialogBuilder.create();
    }

    protected Dialog onCreateDialog(int id) {
        return createAlertDialogWithButtons();
    }

}
