package com.j4nu5.coffeespot.model;

import android.widget.TableRow;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.UUID;

public class Spot {
    private String nama;
    private String alamat;
    private String specialty;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public static Spot fromJSONObject(JSONObject obj) {
        Spot nt = new Spot();
        try {
            nt.setNama(obj.getString("nama"));
            nt.setAlamat(obj.getString("alamat"));
            nt.setSpecialty(obj.getString("specialty"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return nt;
    }

    public JSONObject toJSONObject() {
        JSONObject jsonObj = new JSONObject();
        try {
            jsonObj.put("nama",this.nama);
            jsonObj.put("alamat",this.alamat);
            jsonObj.put("specialty",this.specialty);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObj;
    }
}
