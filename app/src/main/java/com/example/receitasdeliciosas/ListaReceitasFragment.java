package com.example.receitasdeliciosas;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;

public class ListaReceitasFragment extends Fragment {

    private MediaPlayer mediaPlayer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista_receitas, container, false);

        // Configura a ListView
        ListView listaReceitas = view.findViewById(R.id.listaReceitas);
        String[] nomesReceitas = getResources().getStringArray(R.array.nomes_receitas);
        String[] descricoesReceitas = getResources().getStringArray(R.array.descricoes_receitas);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_list_item_1,
                nomesReceitas
        );

        listaReceitas.setAdapter(adapter);

        // Ação ao clicar em um item da lista
        listaReceitas.setOnItemClickListener((parent, view1, position, id) -> {
            String mensagem = "Você selecionou: " + nomesReceitas[position] + "\n" + descricoesReceitas[position];
            Toast.makeText(getContext(), mensagem, Toast.LENGTH_LONG).show();
        });

        // Configura o botão de som
        Button btnTocarSom = view.findViewById(R.id.btnTocarSom);
        btnTocarSom.setOnClickListener(v -> {
            if (mediaPlayer == null) {
                mediaPlayer = MediaPlayer.create(getContext(), R.raw.som_receita);
                mediaPlayer.setOnCompletionListener(mp -> {
                    mediaPlayer.release();
                    mediaPlayer = null;
                });
            }
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