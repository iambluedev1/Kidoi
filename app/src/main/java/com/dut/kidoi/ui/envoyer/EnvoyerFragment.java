package com.dut.kidoi.ui.envoyer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.dut.kidoi.R;
import com.dut.kidoi.ui.demander.DemanderFragment;

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
