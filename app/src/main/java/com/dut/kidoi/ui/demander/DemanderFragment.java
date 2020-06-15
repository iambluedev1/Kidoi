package com.dut.kidoi.ui.demander;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.dut.kidoi.R;

public class DemanderFragment extends Fragment {

    private DemanderViewModel demanderViewModel;

    public static DemanderFragment newInstance() {
        DemanderFragment fragment = new DemanderFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        demanderViewModel =
                ViewModelProviders.of(this).get(DemanderViewModel.class);
        View root = inflater.inflate(R.layout.fragment_demander, container, false);

        /*final TextView textView = root.findViewById(R.id.text_dashboard);
        demanderViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        return root;
    }
}
