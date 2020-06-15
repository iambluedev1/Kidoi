package com.dut.kidoi.ui.envoyer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.dut.kidoi.R;

public class EnvoyerFragment extends Fragment {

    private EnvoyerViewModel envoyerViewModel;

    public static EnvoyerFragment newInstance() {
        EnvoyerFragment fragment = new EnvoyerFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        envoyerViewModel =
                ViewModelProviders.of(this).get(EnvoyerViewModel.class);
        View root = inflater.inflate(R.layout.fragment_envoyer, container, false);

       /* final TextView textView = root.findViewById(R.id.text_notifications);
        envoyerViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        return root;
    }
}
