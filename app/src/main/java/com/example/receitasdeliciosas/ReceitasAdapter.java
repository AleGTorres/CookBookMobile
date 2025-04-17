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

public class ReceitasAdapter extends RecyclerView.Adapter<ReceitasAdapter.ReceitaViewHolder> {

    private final String[] nomesReceitas;
    private final String[] descricoesReceitas;
    private final String[] ingredientesReceitas;
    private final String[] modosPreparoReceitas;
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

    public ReceitasAdapter(Context context) {
        this.context = context;
        this.nomesReceitas = context.getResources().getStringArray(R.array.nomes_receitas);
        this.descricoesReceitas = context.getResources().getStringArray(R.array.descricoes_receitas);
        this.ingredientesReceitas = context.getResources().getStringArray(R.array.ingredientes_receitas);
        this.modosPreparoReceitas = context.getResources().getStringArray(R.array.dicas_culinarias);
    }

    @NonNull
    @Override
    public ReceitaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_receita, parent, false);
        return new ReceitaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReceitaViewHolder holder, int position) {
        holder.tvNomeReceita.setText(nomesReceitas[position]);
        holder.tvDescricaoReceita.setText(descricoesReceitas[position]);

        holder.btnTocarSom.setOnClickListener(v -> {
            try {
                MediaPlayer mediaPlayer = MediaPlayer.create(context, R.raw.som_receita);
                if (mediaPlayer != null) {
                    mediaPlayer.setOnCompletionListener(MediaPlayer::release);
                    mediaPlayer.start();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // Clique no botão "Ver" (TextView com id "ver")
        TextView btnVer = holder.itemView.findViewById(R.id.ver);
        btnVer.setOnClickListener(v -> {
            LayoutInflater inflater = LayoutInflater.from(context);
            View dialogView = inflater.inflate(R.layout.dialog_receita, null);

            ImageView img = dialogView.findViewById(R.id.imgReceitaDialog);
            TextView txtIngredientes = dialogView.findViewById(R.id.txtIngredientes);
            TextView txtModoPreparo = dialogView.findViewById(R.id.txtModoPreparo);

            img.setImageResource(imagens[position]);
            txtIngredientes.setText("Ingredientes:\n" + ingredientesReceitas[position]);
            txtModoPreparo.setText("Modo de preparo:\n" + modosPreparoReceitas[position]);

            new AlertDialog.Builder(context)
                    .setTitle(nomesReceitas[position])
                    .setView(dialogView)
                    .setPositiveButton("Fechar", null)
                    .show();
        });
    }

    @Override
    public int getItemCount() {
        return nomesReceitas.length;
    }

    static class ReceitaViewHolder extends RecyclerView.ViewHolder {
        TextView tvNomeReceita, tvDescricaoReceita;
        View btnTocarSom;

        public ReceitaViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNomeReceita = itemView.findViewById(R.id.tvNomeReceita);
            tvDescricaoReceita = itemView.findViewById(R.id.tvDescricaoReceita);
            btnTocarSom = itemView.findViewById(R.id.btnTocarSom);
        }
    }
}
