package com.example.receitasdeliciosas;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;

public class DicasFragment extends Fragment {

    private MediaPlayer mediaPlayer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dicas, container, false);

        // Configura a ListView personalizada
        ListView listView = view.findViewById(R.id.listaDicas);
        String[] dicas = getResources().getStringArray(R.array.dicas_culinarias);
        int[] imagensDicas = {
                R.drawable.dica1,
                R.drawable.dica2,
                R.drawable.dica3,
                R.drawable.dica4
        };

        DicasAdapter adapter = new DicasAdapter(requireContext(), dicas, imagensDicas);
        listView.setAdapter(adapter);

        // Ação ao clicar em um item da lista
        listView.setOnItemClickListener((parent, view1, position, id) -> {
            Toast.makeText(getContext(), dicas[position], Toast.LENGTH_LONG).show();

            // Tocar som diferente para cada dica
            if (mediaPlayer != null) {
                mediaPlayer.release();
            }

            int[] sonsDicas = {
                    R.raw.som_dica1,
                    R.raw.som_dica2,
                    R.raw.som_dica3,
                    R.raw.som_dica4
            };

            mediaPlayer = MediaPlayer.create(getContext(), sonsDicas[position]);
            mediaPlayer.setOnCompletionListener(mp -> {
                mediaPlayer.release();
                mediaPlayer = null;
            });
            mediaPlayer.start();
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