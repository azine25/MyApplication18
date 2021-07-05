package com.geek.myapplication.ui.onboard;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.geek.myapplication.R;
import com.geek.myapplication.databinding.PagerBoardBinding;

import org.jetbrains.annotations.NotNull;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.ViewHolder> {

    private NextBtn nextBtn;

    private String [] title = new String[] {"Live","Read","Enjoy"};
    private String [] title2 = new String[] {"Meet your favorite\ncharacters and feel \nlike a part of the story","Order a book in any \nlanguage and we\nwill deliver it to you ","We will recommend \nyou a book based\non your preferences"};
    private Integer [] image = {R.drawable.pinterest,R.drawable.lyssa,R.drawable.twitter};

    public void setNextBtn(NextBtn nextBtn) {
        this.nextBtn = nextBtn;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new ViewHolder(PagerBoardBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return title.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
       private PagerBoardBinding binding;
        public ViewHolder(@NonNull @NotNull PagerBoardBinding itemView) {
            super(itemView.getRoot());
            binding= itemView;
        }

        public void bind(int position) {
            binding.textTitle.setText(title[position]);
            binding.textTitle2.setText(title2[position]);
            binding.imageView.setImageResource(image[position]);
            if (position == getItemCount() - 1) {
                binding.btnNext.setVisibility(View.VISIBLE);
                binding.btnNext.setOnClickListener(v -> nextBtn.fin());
            }else {
                binding.btnNext.setVisibility(View.GONE);
            }
        }
    }
    public interface NextBtn {
        void fin ();
    }
}
