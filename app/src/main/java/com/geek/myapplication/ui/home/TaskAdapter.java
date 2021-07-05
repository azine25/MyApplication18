package com.geek.myapplication.ui.home;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.geek.myapplication.databinding.ItemTodoBinding;
import com.geek.myapplication.model.Task;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    private ArrayList<Task> list = new ArrayList<>();


    public void addItem(Task task){
        list.add(0,task);
        notifyItemInserted(list.indexOf(task));
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemTodoBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        holder.onBind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ItemTodoBinding binding;

        public ViewHolder(ItemTodoBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }

        public void onBind(Task task) {
            binding.text.setText(task.getTitle());
        }
    }
}
