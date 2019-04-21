package com.example.rxjavaviewmodel.features.home;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rxjavaviewmodel.R;
import com.example.rxjavaviewmodel.entities.Category;

import java.util.ArrayList;
import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    private List<Category> categories;

    public HomeAdapter() {
        categories = new ArrayList<>();
    }

    public void updateList(List<Category> newList) {
        categories.clear();
        categories.addAll(newList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_category, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Category category = categories.get(i);
        TextView textView = viewHolder.itemView.findViewById(R.id.tv_category_name);
        textView.setText(category.getTitle());
    }

    @Override
    public int getItemCount() {
        return categories == null ? 0 : categories.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
