package com.nathanhaze.snapcalculator.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nathanhaze.snapcalculator.R;

import java.util.List;

public class TerminalAdapter extends RecyclerView.Adapter<TerminalAdapter.ViewHolder> {
    private List<String> mData;
    private final LayoutInflater mInflater;

    public TerminalAdapter(Context context, List<String> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.view_adapter_terminal, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String newInput = mData.get(position);
        holder.tvUserOut.setText(newInput);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvUserOut;

        ViewHolder(View itemView) {
            super(itemView);
            tvUserOut = itemView.findViewById(R.id.tv_adapter_terminal);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
        }
    }

    public void setData(List<String> list) {
        mData = list;
    }
}
