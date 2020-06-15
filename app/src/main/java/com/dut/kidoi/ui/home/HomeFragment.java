package com.dut.kidoi.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.dut.kidoi.LoginActivity;
import com.dut.kidoi.R;
import com.dut.kidoi.repositories.FirebaseRepository;
import com.google.firebase.auth.FirebaseAuth;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        final TextView logout = root.findViewById(R.id.btn_logout);
        logout.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            FirebaseRepository.getInstance().reset();
            Intent in = new Intent(getActivity(), LoginActivity.class);
            startActivity(in);
        });

        final TextView sessionName = root.findViewById(R.id.session_name);
        System.out.println(FirebaseRepository.getInstance().getConnectedUser().getLogin());

        /*final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        return root;
    }
}
