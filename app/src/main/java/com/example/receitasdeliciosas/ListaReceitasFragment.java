package com.example.receitasdeliciosas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class ListaReceitasFragment extends Fragment {

    private String[] nomesReceitas;
    private String[] descricoesReceitas;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista_receitas, container, false);

        Spinner spinnerFiltro = view.findViewById(R.id.spinnerFiltro);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewReceitas);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        nomesReceitas = getResources().getStringArray(R.array.nomes_receitas);
        descricoesReceitas = getResources().getStringArray(R.array.descricoes_receitas);

        // Adapter original com todas as receitas
        ReceitasAdapter adapterCompleto = new ReceitasAdapter(getContext());
        recyclerView.setAdapter(adapterCompleto);

        // Preenche o Spinner com as categorias
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(
                requireContext(),
                R.array.categorias_receitas,
                android.R.layout.simple_spinner_item
        );
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFiltro.setAdapter(spinnerAdapter);

        // Filtro por categoria
        spinnerFiltro.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view1, int position, long id) {
                ArrayList<String> nomesFiltrados = new ArrayList<>();
                ArrayList<String> descricoesFiltradas = new ArrayList<>();

                for (int i = 0; i < nomesReceitas.length; i++) {
                    switch (position) {
                        case 0: // Sobremesas
                            if (i >= 5 && i <= 9) {
                                nomesFiltrados.add(nomesReceitas[i]);
                                descricoesFiltradas.add(descricoesReceitas[i]);
                            }
                            break;
                        case 1: // Pratos Principais
                            if (i >= 0 && i <= 2) {
                                nomesFiltrados.add(nomesReceitas[i]);
                                descricoesFiltradas.add(descricoesReceitas[i]);
                            }
                            break;
                        case 2: // Aperitivos
                            if (i == 11) {
                                nomesFiltrados.add(nomesReceitas[i]);
                                descricoesFiltradas.add(descricoesReceitas[i]);
                            }
                            break;
                        case 3: // Bebidas
                            if (i == 4 || i == 10) {
                                nomesFiltrados.add(nomesReceitas[i]);
                                descricoesFiltradas.add(descricoesReceitas[i]);
                            }
                            break;
                    }
                }

                // Se nenhuma receita filtrada, exibe todas
                if (nomesFiltrados.isEmpty()) {
                    recyclerView.setAdapter(new ReceitasAdapter(getContext()));
                } else {
                    recyclerView.setAdapter(new ReceitasAdapterFiltrada(
                            getContext(),
                            nomesFiltrados.toArray(new String[0]),
                            descricoesFiltradas.toArray(new String[0])
                    ));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        return view;
    }
}
