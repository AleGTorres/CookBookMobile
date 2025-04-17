package com.example.receitasdeliciosas;

import android.app.AlertDialog;
import android.content.Context;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ReceitasAdapterFiltrada extends RecyclerView.Adapter<ReceitasAdapterFiltrada.ViewHolder> {

    private final String[] nomes;
    private final String[] descricoes;
    private final String[] ingredientes;
    private final String[] modosPreparo;
    private final int[] imagens = {
            R.drawable.lasanha_beringela,
            R.drawable.bolo_chocolate,
            R.drawable.pizza,
            R.drawable.salada_caesar,
            R.drawable.smoothie,
            R.drawable.torta_limao,
            R.drawable.brigadeiro,
            R.drawable.churros,
            R.drawable.pudim,
            R.drawable.pave,
            R.drawable.suco_detox,
            R.drawable.bolinho_bacalhau
    };
    private final Context context;

    public ReceitasAdapterFiltrada(Context context, String[] nomes, String[] descricoes) {
        this.context = context;
        this.nomes = nomes;
        this.descricoes = descricoes;
        this.ingredientes = context.getResources().getStringArray(R.array.ingredientes_receitas);
        this.modosPreparo = context.getResources().getStringArray(R.array.dicas_culinarias);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_receita, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String nome = nomes[position];
        String descricao = descricoes[position];

        holder.nome.setText(nome);
        holder.descricao.setText(descricao);

        holder.botaoSom.setOnClickListener(v -> {
            MediaPlayer mediaPlayer = MediaPlayer.create(context, R.raw.som_receita);
            if (mediaPlayer != null) {
                mediaPlayer.setOnCompletionListener(MediaPlayer::release);
                mediaPlayer.start();
            }
        });

        holder.ver.setOnClickListener(v -> {
            // Encontrar o índice original da receita no array completo
            int indexOriginal = encontrarIndiceOriginal(nome);

            LayoutInflater inflater = LayoutInflater.from(context);
            View dialogView = inflater.inflate(R.layout.dialog_receita, null);

            ImageView img = dialogView.findViewById(R.id.imgReceitaDialog);
            TextView txtIngredientes = dialogView.findViewById(R.id.txtIngredientes);
            TextView txtModoPreparo = dialogView.findViewById(R.id.txtModoPreparo);

            img.setImageResource(imagens[indexOriginal]);
            txtIngredientes.setText("Ingredientes:\n" + ingredientes[indexOriginal]);
            txtModoPreparo.setText("Modo de preparo:\n" + modosPreparo[indexOriginal]);

            new AlertDialog.Builder(context)
                    .setTitle(nome)
                    .setView(dialogView)
                    .setPositiveButton("Fechar", null)
                    .show();
        });
    }

    @Override
    public int getItemCount() {
        return nomes.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nome, descricao, ver;
        View botaoSom;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nome = itemView.findViewById(R.id.tvNomeReceita);
            descricao = itemView.findViewById(R.id.tvDescricaoReceita);
            ver = itemView.findViewById(R.id.ver);
            botaoSom = itemView.findViewById(R.id.btnTocarSom);
        }
    }

    private int encontrarIndiceOriginal(String nome) {
        String[] todosNomes = context.getResources().getStringArray(R.array.nomes_receitas);
        for (int i = 0; i < todosNomes.length; i++) {
            if (todosNomes[i].equals(nome)) {
                return i;
            }
        }
        return 0; // fallback
    }
}
