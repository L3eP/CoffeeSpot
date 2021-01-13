package com.j4nu5.coffeespot;

import android.content.Context;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import com.j4nu5.coffeespot.model.Spot;

public class SpotAdapter extends ArrayAdapter<Spot> {
    Context context;

    public SpotAdapter(@NonNull Context context, @NonNull List<Spot> objects) {
        super(context, R.layout.row_note, objects);
        this.context = context;
    }

    class ViewHolder {
        TextView txNama;
        TextView txAlamat;
        TextView txSpecialty;
    }

    @NonNull
    @Nullable
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Spot nt = getItem(position);
        ViewHolder viewHolder;
        if(convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.row_note, parent,false);
            viewHolder = new ViewHolder();
            viewHolder.txNama = convertView.findViewById(R.id.rowTx_Nama);
            viewHolder.txAlamat = convertView.findViewById(R.id.rowTx_Alamat);
            viewHolder.txSpecialty = convertView.findViewById(R.id.rowTx_Specialty);
            convertView.setTag(viewHolder);
        } else  {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.txNama.setText(nt.getNama());
        viewHolder.txAlamat.setText(nt.getAlamat());
        viewHolder.txSpecialty.setText(nt.getSpecialty());
        return convertView;
    }
}
