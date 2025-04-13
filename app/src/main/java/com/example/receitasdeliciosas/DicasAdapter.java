package com.example.receitasdeliciosas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class DicasAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] dicas;
    private final int[] imagens;

    public DicasAdapter(Context context, String[] dicas, int[] imagens) {
        super(context, R.layout.item_dica, dicas);
        this.context = context;
        this.dicas = dicas;
        this.imagens = imagens;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.item_dica, parent, false);
        TextView textView = rowView.findViewById(R.id.textoDica);
        ImageView imageView = rowView.findViewById(R.id.imagemDica);

        textView.setText(dicas[position]);
        imageView.setImageResource(imagens[position]);

        return rowView;
    }
}