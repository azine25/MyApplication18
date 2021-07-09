package com.geek.myapplication.ui.ProfileFragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.geek.myapplication.Prefs;
import com.geek.myapplication.R;
import com.geek.myapplication.databinding.FragmentProfileBinding;

import org.jetbrains.annotations.NotNull;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    private Uri select;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnSelect.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);
        });

        binding.btnDelete.setOnClickListener(v -> {
            binding.imgView.setImageBitmap(null);
            Prefs.getInstance().delete();
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            select = data.getData();
            if (select != null) {
                Prefs.getInstance().saveImage(String.valueOf(select));
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (Prefs.getInstance().getImageUri() != null){
            select=Uri.parse(Prefs.getInstance().getImageUri());
        }
        Glide.with(requireContext()).load(select).circleCrop().into(binding.imgView);
    }
}