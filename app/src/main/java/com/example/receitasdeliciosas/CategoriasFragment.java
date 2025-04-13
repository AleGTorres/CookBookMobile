package com.example.receitasdeliciosas;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.fragment.app.Fragment;

public class CategoriasFragment extends Fragment {

    private MediaPlayer mediaPlayer;
    private int[] imagensReceitas = {
            R.drawable.bolo_chocolate,
            R.drawable.pizza,
            R.drawable.salada,
            R.drawable.smoothie
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_categorias, container, false);

        // Configura o Spinner
        Spinner spinner = view.findViewById(R.id.spinnerCategorias);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                requireContext(),
                R.array.categorias_receitas,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        // Ação ao selecionar uma categoria no Spinner
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String categoria = parent.getItemAtPosition(position).toString();
                Toast.makeText(getContext(), "Categoria selecionada: " + categoria, Toast.LENGTH_SHORT).show();

                // Tocar som ao selecionar uma categoria
                if (mediaPlayer == null) {
                    mediaPlayer = MediaPlayer.create(getContext(), R.raw.som_categoria);
                    mediaPlayer.setOnCompletionListener(mp -> {
                        mediaPlayer.release();
                        mediaPlayer = null;
                    });
                }
                mediaPlayer.start();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        // Configura o GridView
        GridView gridView = view.findViewById(R.id.gridReceitas);
        gridView.setAdapter(new ImageAdapter(requireContext(), imagensReceitas));

        // Ação ao clicar em um item do Grid
        gridView.setOnItemClickListener((parent, view1, position, id) -> {
            Toast.makeText(getContext(), "Receita " + (position + 1) + " selecionada", Toast.LENGTH_SHORT).show();
        });

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}