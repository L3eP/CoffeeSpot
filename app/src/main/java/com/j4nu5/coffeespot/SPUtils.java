package com.j4nu5.coffeespot;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.j4nu5.coffeespot.model.Spot;

public class SPUtils {

    private static final String PREF_FILE ="com.j4nu5.coffeespot.DATA";
    private static final String TRANS_KEY = "TRANS"; // KEY untuk daftar note
    private static final String USER_NAME_KEY = "USERNAME"; // KEY untuk nama user

    private static SharedPreferences getSharedPref(Context ctx) {
        SharedPreferences sharedPref = ctx.getSharedPreferences(
                PREF_FILE, Context.MODE_PRIVATE
        );
        return sharedPref;
    }
    /*
        Ambil data username dari Shared Preference
     */
    public static String getUserName(Context ctx) {
        return getSharedPref(ctx).getString(USER_NAME_KEY, "NO NAME");
    }
    /*
        Simpan data ke Shared Preference
     */
    public static void saveUserName(Context ctx, String userName) {
        Log.d("SH PREF", "Change user name to"+userName);
        getSharedPref(ctx).edit().putString(USER_NAME_KEY,userName).apply();
    }
    /*
         Ambil data transaksi dari Shared Preference
         Perhatikan bahwa data disimpan dalam format JSON String
     */
    public static List<Spot> getAllSpot(Context ctx) {
        String jsonString = getSharedPref(ctx).getString(TRANS_KEY,null);
        List<Spot> nt = new ArrayList<Spot>();
        if (jsonString != null) {
            Log.d("SH PREF", "json string is:"+jsonString);
            try {
                JSONArray jsonArray = new JSONArray(jsonString);
                for (int i=0; i < jsonArray.length(); i++) {
                    JSONObject obj = jsonArray.getJSONObject(i);
                    nt.add(Spot.fromJSONObject(obj));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        Collections.sort(nt, (spot, n1) -> {
            return spot.getNama().compareTo(n1.getNama());
        }); // Urutkan data note berdasarkan nama
        return nt;
    }
    /*
        Simpan data note ke Shared Preference
        Perhatikan bahwa data disimpan dalam format JSON String
     */

    private static void saveAllSpot(Context ctx, List<Spot> spts) {
        List<JSONObject> jsonNt = new ArrayList<JSONObject>();
        JSONArray jsonArray = new JSONArray();
        for (Spot nt : spts) {
            jsonArray.put(nt.toJSONObject());
        }
        getSharedPref(ctx).edit().putString(TRANS_KEY, jsonArray.toString()).apply();
    }
    /*
        Tambah data note baru ke Shared Preference
     */
    public static void addSpot(Context ctx, Spot nt) {
        List<Spot> spts = getAllSpot(ctx);
        spts.add(nt);
        saveAllSpot(ctx, spts);
    }

    public static void editSpot(Context ctx, Spot nt) {
        List<Spot> spts = getAllSpot(ctx);
        for (Spot spot:spts) {
            if (nt.getNama().equals(spot.getNama())) {
                spts.remove(spot);
                spts.add(nt);
            }
        }
        saveAllSpot(ctx,spts);
    }

    public static void deleteSpot(Context ctx, String nama) {
        List<Spot> spots = getAllSpot(ctx);
        for(Spot nt : spots) {
            if(nt.getNama().equals(nama)) {
                spots.remove(nt);
            }
        }
        saveAllSpot(ctx, spots);
    }
}
