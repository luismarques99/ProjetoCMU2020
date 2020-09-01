package com.click2eat.app.ui.wishlist;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.click2eat.app.R;

public class WishlistFragment extends Fragment {

    private WishlistViewModel wishlistViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        wishlistViewModel =
                ViewModelProviders.of(this).get(WishlistViewModel.class);
        View root = inflater.inflate(R.layout.fragment_wishlist, container, false);
        final TextView textView = root.findViewById(R.id.text_wishlist);
        wishlistViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}