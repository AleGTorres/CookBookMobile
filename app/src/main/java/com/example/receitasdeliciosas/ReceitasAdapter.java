package com.example.receitasdeliciosas;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ReceitasAdapter extends RecyclerView.Adapter<ReceitasAdapter.ReceitaViewHolder> {

    private final String[] nomesReceitas;
    private final String[] descricoesReceitas;
    private final Context context;

    public ReceitasAdapter(String[] nomesReceitas, String[] descricoesReceitas, Context context) {
        this.nomesReceitas = nomesReceitas;
        this.descricoesReceitas = descricoesReceitas;
        this.context = context;
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
                } else {
                    // Log para depuração
                    System.out.println("Erro: MediaPlayer não foi criado.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
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
