package com.geek.myapplication.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.geek.myapplication.OnItemClickListener;
import com.geek.myapplication.databinding.ItemTodoBinding;
import com.geek.myapplication.model.Task;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    private ArrayList<Task> list = new ArrayList<>();
    private  OnItemClickListener onItemClickListener;


    public void addItem(Task task){
        list.add(task);
        notifyItemInserted(list.indexOf(task));
        notifyDataSetChanged();}

    public void updateItem(Task task, int position){
        list.remove(position);
        list.add(position,task);
        notifyItemChanged(position);
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

    public  void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public Task getItem(int position) {
        return list.get(position);
    }

    public void removeItem(int position) {
        list.remove(position);
        notifyItemRemoved(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ItemTodoBinding binding;

        public ViewHolder(ItemTodoBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
            itemView.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(getAdapterPosition());
                }
            });
            itemView.getRoot().setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onItemClickListener.onItemLongClick(getAdapterPosition());
                    return true;
                }
            });
        }

        public void onBind(Task task) {
            binding.text.setText(task.getTitle());
        }
    }
}
