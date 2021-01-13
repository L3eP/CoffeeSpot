package com.j4nu5.coffeespot;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.DialogInterface;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import com.j4nu5.coffeespot.model.Spot;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton btnAddSpot;
    ImageButton btnChangeUname;
    ListView lvSpotList;
    TextView txNoData, txUname;
    SpotAdapter adapter;
    List<Spot> daftarSpot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inisialisasiView();
        loadTempatNgopi();
        setupListView();
    }

    private void inisialisasiView() {
        btnAddSpot = findViewById(R.id.btn_add_spot);
        btnAddSpot.setOnClickListener(view -> bukaSpotAdd());
        btnChangeUname = findViewById(R.id.btn_change_uname);
        btnChangeUname.setOnClickListener(view -> changeUname());
        lvSpotList = findViewById(R.id.lvJumlahSpotNgopi);
        txNoData = findViewById(R.id.txNodata);
        txUname = findViewById(R.id.txUname);
        txUname.setText(SPUtils.getUserName(this));
    }

    private void setupListView() {
        adapter = new SpotAdapter(this, daftarSpot);
        lvSpotList.setAdapter(adapter);
    }


    private void loadTempatNgopi() {
        daftarSpot = SPUtils.getAllSpot(this);
        if (daftarSpot.size()>0) {
            txNoData.setVisibility(View.GONE);
        } else {
            txNoData.setVisibility(View.VISIBLE);
        }
    }

    private void refreshListView() {
        adapter.clear();
        loadTempatNgopi();
        adapter.addAll(daftarSpot);
    }

    private void bukaSpotAdd() {
        Intent intent = new Intent(this, SpotActivity.class);
        startActivity(intent);
    }

    private void changeUname() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Ganti nama");

        final EditText input = new EditText(this);
        builder.setView(input);

        builder.setPositiveButton("Simpan", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SPUtils.saveUserName(getApplicationContext(),input.getText().toString());
                Toast.makeText(getApplicationContext(), "Nama user berhasil disimpan", Toast.LENGTH_SHORT).show();
                txUname.setText(SPUtils.getUserName(getApplicationContext()));
            }
        });
        builder.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshListView();
    }


}