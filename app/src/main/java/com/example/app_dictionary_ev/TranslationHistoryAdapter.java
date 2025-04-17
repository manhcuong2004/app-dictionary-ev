package com.example.app_dictionary_ev;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TranslationHistoryAdapter
        extends RecyclerView.Adapter<TranslationHistoryAdapter.ViewHolder> {

    public interface OnItemClickListener {
        void onExpandClick(int position);
        void onDeleteClick(int position);
    }

    private List<TranslationItem> items;
    private OnItemClickListener listener;

    public TranslationHistoryAdapter(List<TranslationItem> items, OnItemClickListener listener) {
        this.items = items;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_translate, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TranslationItem item = items.get(position);
        holder.textSource.setText(item.getSourceText());
        holder.textTranslation.setText(item.getTranslatedText());

        // Show or hide translation based on expanded state
        holder.textTranslation.setVisibility(item.isExpanded() ? View.VISIBLE : View.GONE);

        holder.itemView.setOnClickListener(v -> {
            // Optional: Insert back to translation fields
        });

        holder.iconPlay.setOnClickListener(v -> listener.onExpandClick(position));
        holder.iconTrash.setOnClickListener(v -> listener.onDeleteClick(position));
    }

    @Override public int getItemCount() { return items.size(); }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textSource, textTranslation;
        ImageView iconPlay, iconTrash;

        ViewHolder(View itemView) {
            super(itemView);
            textSource = itemView.findViewById(R.id.textSource);
            textTranslation = itemView.findViewById(R.id.textTranslation);
            iconPlay = itemView.findViewById(R.id.iconPlay);
            iconTrash = itemView.findViewById(R.id.iconTrash);
        }
    }
}