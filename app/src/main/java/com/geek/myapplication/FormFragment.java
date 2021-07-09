package com.geek.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.geek.myapplication.databinding.FragmentFormBinding;
import com.geek.myapplication.model.Task;

public class FormFragment extends Fragment {
    private FragmentFormBinding binding;
    private Task task;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (getArguments() != null) {
            task = (Task) getArguments().getSerializable("update");
        }
        binding = FragmentFormBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (requireArguments().getSerializable("task") != null) {
            task = (Task) requireArguments().getSerializable("task");
            binding.editText.setText(task.getTitle());
        }
        binding.btnSave.setOnClickListener(v -> save());
    }


    private void save() {
        Task text = new Task(binding.editText.getText().toString());
        Bundle bundle = new Bundle();
        /*if (task != null) {
            binding.editText.setText(text.getTitle());
            bundle.putSerializable("update", text);
        } */
        task = new Task(text.getTitle());
        bundle.putSerializable("text", text.getTitle());

        getParentFragmentManager().setFragmentResult("rk_form", bundle);
        close();

    }

    private void close() {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
        navController.navigateUp();
    }
}