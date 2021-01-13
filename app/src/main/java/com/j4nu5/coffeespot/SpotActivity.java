package com.j4nu5.coffeespot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import com.j4nu5.coffeespot.model.Spot;

public class SpotActivity extends AppCompatActivity {

    Button btnSimpan;
    TextInputLayout
            tilNama,
            tilAlamat,
            tilSpecialty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_spot);
        inisialisasiView();
    }

    private void inisialisasiView() {
        btnSimpan = findViewById(R.id.btnSimpan);
        btnSimpan.setOnClickListener(view -> simpan());
        tilNama = findViewById(R.id.tilNama);
        tilAlamat = findViewById(R.id.tilAlamat);
        tilSpecialty = findViewById(R.id.tilSpecialty);
    }

    private void simpan() {
        if (isDataValid()) {
            Spot nt = new Spot();
            nt.setNama(tilNama.getEditText().getText().toString());
            nt.setAlamat(tilAlamat.getEditText().getText().toString());
            nt.setSpecialty(tilSpecialty.getEditText().getText().toString());
            SPUtils.addSpot(this,nt);
            Toast.makeText(this,"Data berhasil disimpan", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    finish();
                }
            }, 500);
        }
    }

    private boolean isDataValid() {
        if (tilNama.getEditText().getText().toString().isEmpty()
                || tilAlamat.getEditText().getText().toString().isEmpty()
                || tilSpecialty.getEditText().getText().toString().isEmpty()
        ) {
            Toast.makeText(this,"Lengkapi semua isian!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}