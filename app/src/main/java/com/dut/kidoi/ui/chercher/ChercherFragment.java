package com.dut.kidoi.ui.chercher;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.dut.kidoi.R;

public class ChercherFragment extends Fragment {

    private ChercherViewModel chercherViewModel;

    public static ChercherFragment newInstance() {
        ChercherFragment fragment = new ChercherFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        chercherViewModel =
                ViewModelProviders.of(this).get(ChercherViewModel.class);
        View root = inflater.inflate(R.layout.fragment_chercher, container, false);

        /*final TextView textView = root.findViewById(R.id.text_dashboard);
        chercherViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        return root;
    }
}
