package com.nathanhaze.snapcalculator.ui.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.nathanhaze.snapcalculator.R;

import java.util.List;

public class TerminalAdapter extends RecyclerView.Adapter<TerminalAdapter.ViewHolder> {

    private List<String> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    public TerminalAdapter(Context context, List<String> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.view_adapter_terminal, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String newInput = mData.get(position);
        holder.myTextView.setText(newInput);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myTextView;

        ViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.tv_adapter_terminal);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

//    String getItem(int id) {
//        return mData.get(id);
//    }

    public void setData(List<String> list) {
        mData = list;
    }

    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }


    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
