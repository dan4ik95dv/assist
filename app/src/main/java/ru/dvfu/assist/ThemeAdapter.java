package ru.dvfu.assist;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by user on 09.01.2017.
 */

public class ThemeAdapter extends RecyclerView.Adapter {


    ArrayList<String> datasetList = new ArrayList<>();

    public ThemeAdapter() {
        this.datasetList = datasetList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder;
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View view = layoutInflater.inflate(R.layout.item, parent, false);
        holder = new ThemeViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        String value = datasetList.get(position);
        ThemeViewHolder themeViewHolder = (ThemeViewHolder) holder;
        themeViewHolder.bind(value);
    }


    @Override
    public int getItemCount() {
        return datasetList.size();
    }

    @Nullable
    public void setDatasetList(ArrayList<String> datasetList) {
        this.datasetList = datasetList;
        notifyDataSetChanged();
    }



    static class ThemeViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public void bind(String someString) {
            textView.setText(someString);
        }

        public ThemeViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.textView);
        }
    }

}
