package com.click2eat.app.ui.live;

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

import com.click2eat.app.R;

public class LiveFragment extends Fragment {

    private LiveViewModel liveViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        liveViewModel = ViewModelProviders.of(this).get(LiveViewModel.class);
        View root = inflater.inflate(R.layout.fragment_live, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        liveViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}