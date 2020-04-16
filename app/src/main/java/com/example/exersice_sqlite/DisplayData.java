package com.example.exersice_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DisplayData extends AppCompatActivity {
    int from_Where_I_Am_Coming = 0;
    private DBHelper mydb;
    EditText nama;
    EditText phone;
    EditText alamat;
    EditText email;
    EditText emailv;
    String emailu;
    int id_To_Update = 0;
    String formatEmail = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_data);
        emailv = (EditText) findViewById(R.id.editTextEmail);
        nama = (EditText) findViewById(R.id.editTextName);
        phone = (EditText) findViewById(R.id.editTextTelp);
        alamat = (EditText) findViewById(R.id.editTextAlamat);
        email = (EditText) findViewById(R.id.editTextEmail);
        mydb = new DBHelper(this);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int Value = extras.getInt("id");
            if (Value > 0) {
                Cursor rs = mydb.getData(Value);
                id_To_Update = Value;
                rs.moveToFirst();
                String nam =
                        rs.getString(rs.getColumnIndex(DBHelper.MHS_COLUMN_NAMA));
                String phon =
                        rs.getString(rs.getColumnIndex(DBHelper.MHS_COLUMN_PHONE));
                String alama =
                        rs.getString(rs.getColumnIndex(DBHelper.MHS_COLUMN_ALAMAT));
                String emai =
                        rs.getString(rs.getColumnIndex(DBHelper.MHS_COLUMN_EMAIL));
                if (!rs.isClosed()) {
                    rs.close();
                }
                Button b = (Button) findViewById(R.id.button);
                b.setVisibility(View.INVISIBLE);
                nama.setText((CharSequence) nam);
                nama.setFocusable(false);
                nama.setClickable(false);
                phone.setText((CharSequence) phon);
                phone.setFocusable(false);
                phone.setClickable(false);
                alamat.setText((CharSequence) alama);
                alamat.setFocusable(false);
                alamat.setClickable(false);
                email.setText((CharSequence) emai);
                email.setFocusable(false);
                email.setClickable(false);

            }
        }

    }



    public void run(View view) {
        emailu = email.getText().toString().trim();
        if (nama.getText().toString().equals("") ||
                phone.getText().toString().equals("") ||
                alamat.getText().toString().equals("") ||
                email.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "data harus diisi semua !", Toast.LENGTH_LONG).show();
        } else {
            mydb.insertContact(nama.getText().toString(), phone.getText().toString(), alamat.getText().toString(), email.getText().toString());
            Toast.makeText(getApplicationContext(), "insert data berhasil", Toast.LENGTH_LONG).show();

            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
        } if (emailu.matches(formatEmail)){
            mydb.insertContact(nama.getText().toString(), phone.getText().toString(), email.getText().toString(), alamat.getText().toString());
            Toast.makeText(getApplicationContext(), "Insert Data Berhasil", Toast.LENGTH_LONG).show();

            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
        }
        else{
            Toast.makeText(getApplicationContext(),"Format Email Salah", Toast.LENGTH_LONG).show();
        }

    }

}
