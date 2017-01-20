package ru.dvfu.assist;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import ru.dvfu.assist.model.Theme;

/**
 * Created by user on 09.01.2017.
 */

public class ThemeAdapter extends RecyclerView.Adapter {

    Callback callback;
    ArrayList<Theme> datasetList = new ArrayList<>();

    public ThemeAdapter(Callback callback) {
        this.callback = callback;
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
        Theme value = datasetList.get(position);
        ThemeViewHolder themeViewHolder = (ThemeViewHolder) holder;
        themeViewHolder.bind(value, callback);
    }


    @Override
    public int getItemCount() {
        return datasetList.size();
    }

    @Nullable
    public void setDatasetList(ArrayList<Theme> datasetList) {
        this.datasetList = datasetList;
        notifyDataSetChanged();
    }


    static class ThemeViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public void bind(final Theme theme, final Callback callback) {
            textView.setText(theme.getName());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callback.clickElement(theme.getId());
                }
            });
        }

        public ThemeViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.textView);

        }
    }

    public interface Callback {
        void clickElement(int id);
    }
}
