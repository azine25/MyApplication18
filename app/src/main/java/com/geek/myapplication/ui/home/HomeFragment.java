package com.geek.myapplication.ui.home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.geek.myapplication.FormFragment;
import com.geek.myapplication.OnItemClickListener;
import com.geek.myapplication.R;
import com.geek.myapplication.databinding.FragmentHomeBinding;
import com.geek.myapplication.model.Task;
import com.geek.myapplication.ui.App;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.jetbrains.annotations.NotNull;

public class HomeFragment extends Fragment {

    private TaskAdapter adapter;
    private FragmentHomeBinding binding;
    private static int pos;
    private boolean isChanged = false;

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new TaskAdapter();

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FloatingActionButton fab = view.findViewById(R.id.fab);

        fab.setOnClickListener(v -> {
            isChanged = false;
            openFrag(null);
        });

        setFragmentListener();
        initList();
    }

    private void initList() {
        binding.recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                pos = position;
                isChanged = true;
                Task task = adapter.getItem(position);
                Bundle bundle = new Bundle();
                bundle.putSerializable("update", task);
                FormFragment fragment = new FormFragment();
                fragment.setArguments(bundle);
                openFrag(task);
            }

            @Override
            public void onItemLongClick(int position) {
                Task task = adapter.getItem(position);
                new AlertDialog.Builder(requireContext()).setMessage("Удалить запись" + task.getTitle() + "?")
                        .setNegativeButton("Нет", null)
                        .setPositiveButton("да", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                adapter.removeItem(position);
                            }
                        }).show();
            }
        });
    }

    private void setFragmentListener() {
        getParentFragmentManager().setFragmentResultListener("rk_form", getViewLifecycleOwner(), new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull @NotNull String requestKey, @NonNull @NotNull Bundle result) {
                Task task = new Task((String) result.getSerializable("text"));
                if (isChanged) {
                    adapter.updateItem(task, pos);
                } else {
                    adapter.addItem(task);
                }
            }
        });
    }


    private void openFrag(Task task) {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
        Bundle bundle = new Bundle();
        bundle.putSerializable("task", task);
        navController.navigate(R.id.formFragment, bundle);
    }
}
