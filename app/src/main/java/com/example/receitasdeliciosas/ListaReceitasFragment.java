package com.example.receitasdeliciosas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ListaReceitasFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista_receitas, container, false);

        // Configura o RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewReceitas);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        String[] nomesReceitas = getResources().getStringArray(R.array.nomes_receitas);
        String[] descricoesReceitas = getResources().getStringArray(R.array.descricoes_receitas);

        ReceitasAdapter adapter = new ReceitasAdapter(nomesReceitas, descricoesReceitas, getContext());
        recyclerView.setAdapter(adapter);

        return view;
    }
}